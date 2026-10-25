/**
 * Initially generated with ChatGPT 4o and adapted using GitHub Copilot.
 * Reviewed and updated with assistance from Claude Opus 4.8 (July 2026)
 * and ChatGPT-6.1 Sol (October 2026).
 */
package es.deusto.sd.auctions;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.auctions.entity.Article;
import es.deusto.sd.auctions.entity.Category;
import es.deusto.sd.auctions.entity.User;
import es.deusto.sd.auctions.service.AuctionsService;
import es.deusto.sd.auctions.service.AuthService;

@Configuration
public class DataInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
    @Bean
    CommandLineRunner initData(AuctionsService auctionsService, AuthService authService) {
		return args -> {			
			// Create some users.
			User batman = new User("BruceWayne", "batman@dc.com", "Batm@n123!");
			User spiderman = new User("PeterParker", "spiderman@marvel.com", "Sp!derM4n2023");
			User superman = new User("ClarkKent", "superman@dc.com", "Sup3rm@n456!");
			User wonderWoman = new User("DianaPrince", "wonderwoman@dc.com", "Wond3rW0m@n!789");
			User captainMarvel = new User("CarolDanvers", "captainmarvel@marvel.com", "C@ptMarv3l#987");
			User blackWidow = new User("NatashaRomanoff", "blackwidow@marvel.com", "Bl@ckWid0w2023");

			authService.addUser(batman);
			authService.addUser(spiderman);
			authService.addUser(superman);
			authService.addUser(wonderWoman);
			authService.addUser(captainMarvel);
			authService.addUser(blackWidow);			
			
			logger.info("Users saved!");
			
			// Create some categories
			Category electronics = new Category("Electronics");
			Category sports = new Category("Sporting Goods");
			Category motors = new Category("Motors");
			
			auctionsService.addCategory(electronics);
			auctionsService.addCategory(sports);
			auctionsService.addCategory(motors);
			logger.info("Categories saved!");


			// Initialize auctions end date
			Calendar calendar = Calendar.getInstance();
			
			// Set calendar to December 31, current year
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 31);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			
			Date auctionEndDate = calendar.getTime();
			
			// Articles of Electronics category
            // Articles of Electronics category
            Article iphone = new Article(0, "Apple iPhone 16 Pro", 1099.99, auctionEndDate, electronics, batman);
            Article ps5 = new Article(1, "Sony PlayStation 5 Pro", 699.99, auctionEndDate, electronics, spiderman);
            Article macbook = new Article(2, "MacBook Air M3", 1099.99, auctionEndDate, electronics, wonderWoman);
            Article samsung = new Article(3, "Samsung Galaxy S24 Ultra", 1299.99, auctionEndDate, electronics, captainMarvel);

            // Articles of Sporting Goods category
            Article tennisRacket = new Article(4, "Wilson Blade 98 V9 Tennis Racket", 249.99, auctionEndDate, sports, batman);
            Article soccerBall = new Article(5, "Adidas Fussballliebe Pro Ball", 149.99, auctionEndDate, sports, blackWidow);
            Article fitbit = new Article(6, "Fitbit Charge 6 Fitness Tracker", 159.99, auctionEndDate, sports, captainMarvel);
            Article peloton = new Article(7, "Peloton Bike+", 2495.00, auctionEndDate, sports, wonderWoman);

            // Articles of Motors category
            Article c3 = new Article(8, "Citroën C3 1.2 PureTech Feel (2020)", 10890.00, auctionEndDate, motors, batman);
            Article mgZs = new Article(9, "MG ZS 1.5 VTI-tech Comfort (2022)", 13990.00, auctionEndDate, motors, superman);
            Article fiat500 = new Article(10, "Fiat 500 1.0 Hybrid Cult ECO (2021)", 9950.00, auctionEndDate, motors, spiderman);
            Article tucson = new Article(11, "Hyundai Tucson 1.6 TGDI Maxx (2021)", 20900.00, auctionEndDate, motors, captainMarvel);

            auctionsService.addArticle(iphone);
            auctionsService.addArticle(ps5);
            auctionsService.addArticle(macbook);
            auctionsService.addArticle(samsung);
            auctionsService.addArticle(tennisRacket);
            auctionsService.addArticle(soccerBall);
            auctionsService.addArticle(fitbit);
            auctionsService.addArticle(peloton);
            auctionsService.addArticle(c3);
            auctionsService.addArticle(mgZs);
            auctionsService.addArticle(fiat500);
            auctionsService.addArticle(tucson);
            
            logger.info("Articles saved!");						
		};
	}
}