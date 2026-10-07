/**
 * Entirely generated with ChatGPT-6.1 in September and October 2026.
 * Reviewed and updated with assistance from ChatGPT-6.1 Sol (October 2026).
 */
package es.deusto.sd.auctions.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.deusto.sd.auctions.entity.User;

class AuthServiceTest {

    private static final String PASSWORD = "secret";

    private AuthService service;
    private User user;

    @BeforeEach
    void setUp() {
        service = new AuthService();
        user = new User("bidder", "bidder@example.com", DigestUtils.sha1Hex(PASSWORD));
        service.addUser(user);
    }

    @Test
    void logsInWithTheClientHashAndInvalidatesTheTokenOnLogout() {
        String token = service.login(user.getEmail(), DigestUtils.sha1Hex(PASSWORD)).orElseThrow();

        assertSame(user, service.getUserByToken(token));
        assertTrue(service.logout(token).orElseThrow());
        assertNull(service.getUserByToken(token));
    }

    @Test
    void rejectsUnknownUsersAndIncorrectHashes() {
        assertTrue(service.login(user.getEmail(), "incorrect-hash").isEmpty());
        assertTrue(service.login("unknown@example.com", DigestUtils.sha1Hex(PASSWORD)).isEmpty());
        assertFalse(service.logout("unknown-token").isPresent());
    }

    @Test
    void rejectsMissingEmailOrPassword() {
        assertTrue(service.login(null, "password-hash").isEmpty());
        assertTrue(service.login(user.getEmail(), null).isEmpty());
    }
}
