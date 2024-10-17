/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.auctions.entity;

import java.util.Objects;

public class Bid {
	private long date;
	private float amount;
	private Article article;
	private User user;

	// Constructor without parameters	
	public Bid() { }
	
	// Constructor with parameters
	public Bid(long date, float amount, Article article, User user) {
		this.date = date;
		this.amount = amount;
		this.article = article;
		this.user = user;
	}
	
	// Getters and setters
	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// hashCode and equals	
	@Override
	public int hashCode() {
		return Objects.hash(article, date, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		return Objects.equals(article, other.article) && 
			   date == other.date && 
			   Objects.equals(user, other.user);
	}
}