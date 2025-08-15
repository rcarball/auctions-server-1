/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
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
			// Create some users
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
            Article iphone = new Article(0, "Apple iPhone 14 Pro", 999.99f, auctionEndDate, electronics, batman);
            Article ps5 = new Article(1, "Sony PlayStation 5", 499.99f, auctionEndDate, electronics, spiderman);
            Article macbook = new Article(2, "MacBook Air M2", 1199.99f, auctionEndDate, electronics, wonderWoman);
            Article samsung = new Article(3, "Samsung Galaxy S21", 799.99f, auctionEndDate, electronics, captainMarvel);
            // Articles of Sporting Goods category
            Article tennisRacket = new Article(4, "Wilson Tennis Racket", 119.99f, auctionEndDate, sports, batman);
            Article soccerBall = new Article(5, "Adidas Soccer Ball", 29.99f, auctionEndDate, sports, blackWidow);
            Article fitbit = new Article(6, "Fitbit Charge 5 Fitness Tracker", 149.99f, auctionEndDate, sports, captainMarvel);
            Article peloton = new Article(7, "Peloton Exercise Bike", 1899.99f, auctionEndDate, sports, wonderWoman);
            // Articles of Motors category
            Article tesla = new Article(8, "Tesla Model 3", 42999.99f, auctionEndDate, motors, batman);
            Article civic = new Article(9, "Honda Civic 2021", 21999.99f, auctionEndDate, motors, superman);
            Article f150 = new Article(10, "Ford F-150 Pickup Truck", 33999.99f, auctionEndDate, motors, spiderman);
            Article corvette = new Article(11, "Chevrolet Corvette Stingray", 59999.99f, auctionEndDate, motors, captainMarvel);

            auctionsService.addArticle(iphone);
            auctionsService.addArticle(ps5);
            auctionsService.addArticle(macbook);
            auctionsService.addArticle(samsung);
            auctionsService.addArticle(tennisRacket);
            auctionsService.addArticle(soccerBall);
            auctionsService.addArticle(fitbit);
            auctionsService.addArticle(peloton);
            auctionsService.addArticle(tesla);
            auctionsService.addArticle(civic);
            auctionsService.addArticle(f150);
            auctionsService.addArticle(corvette);
            logger.info("Articles saved!");						
		};
	}
}