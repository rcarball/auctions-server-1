package es.deusto.sd.auctions.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

	public enum ExchangeRate {
		EUR(1f),
		USD(1.104f),
		GBP(0.840f);
			
		private float rate;

		ExchangeRate(float rate) {
			this.rate = rate;
		}
	}
	
    public Optional<Float> getExchangeRate(String currency) {
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