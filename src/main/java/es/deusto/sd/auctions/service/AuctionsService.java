/**
 * This code was originally generated with ChatGPT 4o and adapted using GitHub
 * Copilot. It was reviewed, corrected and updated in July 2026 with the
 * assistance of Claude Opus 4.8 (Anthropic).
 */
package es.deusto.sd.auctions.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import es.deusto.sd.auctions.entity.Article;
import es.deusto.sd.auctions.entity.Bid;
import es.deusto.sd.auctions.entity.Category;
import es.deusto.sd.auctions.entity.User;

@Service
public class AuctionsService {

	// Simulating category and article repositories.
	// ConcurrentHashMap is used because the repositories are shared across the
	// threads that Spring uses to serve concurrent HTTP requests.
	private final Map<String, Category> categoryRepository = new ConcurrentHashMap<>();
	private final Map<Long, Article> articleRepository = new ConcurrentHashMap<>();

	// Get all categories
	public List<Category> getCategories() {
		return categoryRepository.values().stream().toList();
	}

	// Get articles of a specific category
	public List<Article> getArticlesByCategoryName(String categoryName) {
		Category category = categoryRepository.get(categoryName);

		if (category == null) {
			throw new RuntimeException("Category not found");
		}

		return category.getArticles();
	}
	
	// Get article by id
	public Article getArticleById(long articleId) {
		return articleRepository.get(articleId);
	}

	// Make a bid on an article
	public void makeBid(User user, long articleId, double amount) {
		// Retrieve the article by ID
		Article article = articleRepository.get(articleId);

		if (article == null) {
			throw new RuntimeException("Article not found");
		}

		// A bid can only be placed while the auction is still open. Once the end date
		// has passed, the article is no longer up for auction (its winner is fixed).
		if (article.getAuctionEnd() != null && System.currentTimeMillis() > article.getAuctionEnd().getTime()) {
			throw new RuntimeException("Auction has ended");
		}

		// The "check current price" and "register the bid" steps must be atomic:
		// otherwise two concurrent bids could both pass the check and be accepted,
		// leaving the auction in an inconsistent state (lost update). We synchronize
		// on the article so that only one bid per article is processed at a time.
		synchronized (article) {
			if (amount <= article.getCurrentPrice()) {
				throw new RuntimeException("Bid amount must be greater than the current price");
			}

			// Create a new bid and associate it with both the article and the bidder,
			// keeping both sides of the domain associations consistent.
			Bid bid = new Bid(System.currentTimeMillis(), amount, article, user);
			article.getBids().add(bid);

			synchronized (user) {
				user.getBids().add(bid);
			}
		}
	}
	
	// Method to add a new category
	public void addCategory(Category category) {
		if (category != null) {
			categoryRepository.putIfAbsent(category.getName(), category);
		}
	}
	
	// Method to add a new article
	public void addArticle(Article article) {
		if (article != null) {
			articleRepository.put(article.getId(), article);
		}
	}
}