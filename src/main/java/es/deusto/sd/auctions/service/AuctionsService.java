/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.auctions.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import es.deusto.sd.auctions.entity.Article;
import es.deusto.sd.auctions.entity.Bid;
import es.deusto.sd.auctions.entity.Category;
import es.deusto.sd.auctions.entity.User;

@Service
public class AuctionsService {

	// Simulating category and article repositories
	private static Map<String, Category> categoryRepository = new HashMap<>();
	private static Map<Long, Article> articleRepository = new HashMap<>();

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
	public void makeBid(User user, long articleId, float amount) {
		// Retrieve the article by ID
		Article article = articleRepository.get(articleId);

		if (article == null) {
			throw new RuntimeException("Article not found");
		}

		if (amount <= article.getCurrentPrice()) {
			throw new RuntimeException("Bid amount must be greater than the current price");
		}
		
		// Create a new bid and associate it with the article
		Bid bid = new Bid(System.currentTimeMillis(), amount, article, user);
		article.getBids().add(bid);
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