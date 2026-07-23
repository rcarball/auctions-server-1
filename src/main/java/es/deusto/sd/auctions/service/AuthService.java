/**
 * This code was originally generated with ChatGPT 4o and adapted using GitHub
 * Copilot. It was reviewed, corrected and updated in July 2026 with the
 * assistance of Claude Opus 4.8 (Anthropic).
 */
package es.deusto.sd.auctions.service;

import org.springframework.stereotype.Service;

import es.deusto.sd.auctions.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {

    // Simulating a user repository (thread-safe: shared across request threads)
    private final Map<String, User> userRepository = new ConcurrentHashMap<>();

    // Storage to keep the session of the users that are logged in
    private final Map<String, User> tokenStore = new ConcurrentHashMap<>();

    // Login method that checks if the user exists in the database and validates the password
    public Optional<String> login(String email, String password) {
        User user = userRepository.get(email);
        
        if (user != null && user.checkPassword(password)) {
            String token = generateToken();  // Generate a random token for the session
            tokenStore.put(token, user);     // Store the token and associate it with the user

            return Optional.of(token);
        } else {
        	return Optional.empty();
        }
    }
    
    // Logout method to remove the token from the session store
    public Optional<Boolean> logout(String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);

            return Optional.of(true);
        } else {
            return Optional.empty();
        }
    }
    
    // Method to add a new user to the repository
    public void addUser(User user) {
    	if (user != null) {
    		userRepository.putIfAbsent(user.getEmail(), user);
    	}
    }
    
    // Method to get the user based on the token
    public User getUserByToken(String token) {
        return tokenStore.get(token); 
    }
    
    // Method to get the user based on the email
    public User getUserByEmail(String email) {
		return userRepository.get(email);
	}

    // Generates a unique, non-guessable session token.
    // A timestamp-based token is NOT unique (two logins in the same millisecond would
    // collide, overwriting each other's session) and is predictable. UUID.randomUUID()
    // provides uniqueness and unpredictability.
    private static String generateToken() {
        return UUID.randomUUID().toString();
    }
}