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
    	Optional<Float> exchangeRate = ExchangeRate.valueOf(currency) != null ? Optional.of(ExchangeRate.valueOf(currency).rate) : Optional.empty();

    	try {
			if (exchangeRate.isPresent()) {
				return exchangeRate;
			} else {
				return Optional.of(ExchangeRate.valueOf(currency).rate);
			}
    	} catch(Exception ex) {
    		return Optional.empty();
    	}
    }
}