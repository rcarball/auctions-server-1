/**
 * Entirely generated with ChatGPT-6.1 in September and October 2026.
 * Reviewed and updated with assistance from ChatGPT-6.1 Sol (October 2026).
 */
package es.deusto.sd.auctions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import tools.jackson.databind.json.JsonMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuctionsApplicationTest {

    @Value("${local.server.port}")
    private int port;

    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    void servesOpenApiAndSwaggerUi() throws Exception {
        HttpResponse<String> api = get("/v3/api-docs");
        assertEquals(200, api.statusCode());
        assertTrue(api.body().contains("/auth/login"));
        assertTrue(api.body().contains("/auctions/articles/{articleId}/bid"));
        HttpResponse<String> ui = get("/swagger-ui/index.html");
        assertEquals(200, ui.statusCode());
        assertTrue(ui.body().contains("swagger-ui"));
    }

    @Test
    void completesTheClassroomLoginBidAndLogoutWorkflow() throws Exception {
        assertEquals(200, get("/auctions/categories").statusCode());
        assertEquals(200, get("/auctions/categories/Sporting%20Goods/articles?currency=EUR").statusCode());
        assertEquals(200, get("/auctions/articles/1/details?currency=EUR").statusCode());
        String credentials = "{\"email\":\"batman@dc.com\",\"password\":\""
                + DigestUtils.sha1Hex("Batm@n123!") + "\"}";
        HttpResponse<String> login = post("/auth/login", "application/json", credentials);
        assertEquals(200, login.statusCode());
        String token = login.body();
        assertEquals(204, post("/auctions/articles/1/bid?amount=600&currency=EUR", "text/plain", token).statusCode());
        var details = JsonMapper.builder().build().readTree(get("/auctions/articles/1/details?currency=EUR").body());
        assertEquals(600.0, details.get("currentPrice").asDouble());
        assertEquals(1, details.get("bids").asInt());
        assertEquals(409, post("/auctions/articles/1/bid?amount=600&currency=EUR", "text/plain", token).statusCode());
        assertEquals(204, post("/auth/logout", "text/plain", token).statusCode());
        assertEquals(401, post("/auctions/articles/1/bid?amount=700&currency=EUR", "text/plain", token).statusCode());
    }

    @Test
    void rejectsIncompleteCredentialsWithoutAnInternalServerError() throws Exception {
        assertEquals(401, post("/auth/login", "application/json", "{}").statusCode());
        assertEquals(401, post("/auth/login", "application/json", "{\"email\":\"batman@dc.com\"}").statusCode());
    }

    @Test
    void validatesCurrencyArticleIdsAndBidAmounts() throws Exception {
        assertEquals(400, get("/auctions/articles/1/details?currency=INVALID").statusCode());
        assertEquals(404, get("/auctions/articles/999/details?currency=EUR").statusCode());
        String credentials = "{\"email\":\"spiderman@marvel.com\",\"password\":\""
                + DigestUtils.sha1Hex("Sp!derM4n2023") + "\"}";
        HttpResponse<String> login = post("/auth/login", "application/json", credentials);
        assertEquals(200, login.statusCode());
        String token = login.body();
        try {
            assertEquals(400, post("/auctions/articles/2/bid?amount=NaN&currency=EUR", "text/plain", token).statusCode());
            assertEquals(400, post("/auctions/articles/2/bid?amount=0&currency=EUR", "text/plain", token).statusCode());
            assertEquals(404, post("/auctions/articles/999/bid?amount=2000&currency=EUR", "text/plain", token).statusCode());
        } finally {
            assertEquals(204, post("/auth/logout", "text/plain", token).statusCode());
        }
    }

    private HttpResponse<String> get(String path) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + path)).GET().build(),
                HttpResponse.BodyHandlers.ofString());
    }

    private HttpResponse<String> post(String path, String contentType, String body) throws Exception {
        return client.send(HttpRequest.newBuilder(URI.create("http://localhost:" + port + path))
                .header("Content-Type", contentType).POST(HttpRequest.BodyPublishers.ofString(body)).build(),
                HttpResponse.BodyHandlers.ofString());
    }
}
