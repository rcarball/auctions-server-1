/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.auctions.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Article {
	private long id;
	private String title;
	private float initialPrice;
	private Date auctionEnd;
	private Category category;
	private User owner;
	private List<Bid> bids = new ArrayList<>();

	// Constructor without parameters
	public Article() {
	}

	// Constructor with parameters
	public Article(long id, String title, float initialPrice, 
			       Date auctionEnd, Category category, User owner) {
		this.id = id;
		this.title = title;
		this.initialPrice = initialPrice;
		this.auctionEnd = auctionEnd;
		this.category = category;
		this.category.getArticles().add(this);
		this.owner = owner;
	}

	// Get the current price of the article
	public float getCurrentPrice() {
		if (bids.isEmpty()) {
			return initialPrice;
		}

		return bids.get(bids.size() - 1).getAmount();
	}

	// Get the current winner of the auction
	public User getWinner() {
		if (bids.isEmpty()) {
			return null;
		}

		return bids.get(bids.size() - 1).getUser();
	}

	// Getters and setters
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(float initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return id == other.id;
	}
}