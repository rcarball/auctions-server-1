/**
 * This code was originally generated with ChatGPT 4o and adapted using GitHub
 * Copilot. It was reviewed, corrected and updated in July 2026 with the
 * assistance of Claude Opus 4.8 (Anthropic).
 */
package es.deusto.sd.auctions.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.codec.digest.DigestUtils;

public class User {

	private String nickname;
	private String password;
	private String email;
	private List<Bid> bids = new ArrayList<>();
	private List<Article> articles = new ArrayList<>();
	
	// Constructor without parameters
	public User() { }
	
	// Constructor with parameters
	public User(String nickname, String email, String password) {
		this.nickname = nickname;
		this.email = email;
		// Passwords are never stored in plain text: they are hashed with SHA-1
		// before being kept in memory (see the "Login" scenario in the case study).
		this.password = DigestUtils.sha1Hex(password);
	}

	// Check if a password is correct by comparing the SHA-1 hash of the provided
	// password against the stored hash (the plain-text password is never kept).
	public boolean checkPassword(String password) {
        return this.password.equals(DigestUtils.sha1Hex(password));
	}

	//  Getters and setters
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	// Note: there is no getPassword() on purpose. As an additional security measure
	// the stored password (a SHA-1 hash) is never exposed outside the class.

	public void setPassword(String password) {
		this.password = DigestUtils.sha1Hex(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(email, nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && 
			   Objects.equals(nickname, other.nickname);
	}
}