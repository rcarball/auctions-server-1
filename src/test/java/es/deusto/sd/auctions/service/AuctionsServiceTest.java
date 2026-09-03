package es.deusto.sd.auctions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.deusto.sd.auctions.entity.Article;
import es.deusto.sd.auctions.entity.Category;
import es.deusto.sd.auctions.entity.User;

class AuctionsServiceTest {

    private AuctionsService service;
    private User bidder;
    private Article article;

    @BeforeEach
    void setUp() {
        service = new AuctionsService();
        Category category = new Category("Electronics");
        User owner = new User("owner", "owner@example.com", "owner-password-hash");
        bidder = new User("bidder", "bidder@example.com", "bidder-password-hash");
        article = new Article(1L, "Laptop", 100.0,
                new Date(System.currentTimeMillis() + 60_000), category, owner);

        service.addCategory(category);
        service.addArticle(article);
    }

    @Test
    void returnsCategoriesAndArticlesForAnExistingCategory() {
        assertEquals(1, service.getCategories().size());
        assertEquals(article, service.getArticlesByCategoryName("Electronics").getFirst());
    }

    @Test
    void registersAnIncreasingBidAndUpdatesTheWinner() {
        service.makeBid(bidder, article.getId(), 125.0);

        assertEquals(125.0, article.getCurrentPrice());
        assertSame(bidder, article.getWinner());
        assertEquals(1, article.getBids().size());
        assertEquals(1, bidder.getBids().size());
    }

    @Test
    void rejectsBidsThatDoNotImproveTheCurrentPrice() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> service.makeBid(bidder, article.getId(), 100.0));

        assertEquals("Bid amount must be greater than the current price", exception.getMessage());
        assertEquals(100.0, article.getCurrentPrice());
    }

    @Test
    void rejectsBidsForAnExpiredAuction() {
        article.setAuctionEnd(new Date(System.currentTimeMillis() - 1));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> service.makeBid(bidder, article.getId(), 125.0));

        assertEquals("Auction has ended", exception.getMessage());
    }

    @Test
    void rejectsNaNInfiniteAndNonPositiveBidAmounts() {
        assertInvalidAmount(Double.NaN);
        assertInvalidAmount(Double.POSITIVE_INFINITY);
        assertInvalidAmount(0.0);
        assertInvalidAmount(-1.0);
    }

    @Test
    void rejectsAnUnknownArticleOrCategory() {
        RuntimeException unknownArticle = assertThrows(RuntimeException.class,
                () -> service.makeBid(bidder, 999L, 125.0));
        RuntimeException unknownCategory = assertThrows(RuntimeException.class,
                () -> service.getArticlesByCategoryName("Unknown"));

        assertEquals("Article not found", unknownArticle.getMessage());
        assertEquals("Category not found", unknownCategory.getMessage());
    }

    private void assertInvalidAmount(double amount) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.makeBid(bidder, article.getId(), amount));
        assertEquals("Bid amount must be a finite positive number", exception.getMessage());
    }
}
