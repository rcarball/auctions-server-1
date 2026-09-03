package es.deusto.sd.auctions.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CurrencyServiceTest {

    private final CurrencyService service = new CurrencyService();

    @Test
    void returnsConfiguredRatesForSupportedCurrencies() {
        assertEquals(1.0, service.getExchangeRate("EUR").orElseThrow());
        assertEquals(1.104, service.getExchangeRate("USD").orElseThrow());
        assertEquals(0.840, service.getExchangeRate("GBP").orElseThrow());
    }

    @Test
    void rejectsUnsupportedAndNullCurrencies() {
        assertTrue(service.getExchangeRate("JPY").isEmpty());
        assertTrue(service.getExchangeRate(null).isEmpty());
    }
}
