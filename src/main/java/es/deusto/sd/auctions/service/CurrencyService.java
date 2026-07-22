package es.deusto.sd.auctions.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

	public enum ExchangeRate {
		EUR(1.0),
		USD(1.104),
		GBP(0.840);

		private double rate;

		ExchangeRate(double rate) {
			this.rate = rate;
		}
	}

    public Optional<Double> getExchangeRate(String currency) {
    	// ExchangeRate.valueOf throws IllegalArgumentException if the currency is not
    	// a valid enum constant (and NullPointerException if it is null). We catch both
    	// here so that an unsupported currency results in an empty Optional, which the
    	// controllers translate into a 400 Bad Request.
    	try {
    		return Optional.of(ExchangeRate.valueOf(currency).rate);
    	} catch (IllegalArgumentException | NullPointerException ex) {
    		return Optional.empty();
    	}
    }
}