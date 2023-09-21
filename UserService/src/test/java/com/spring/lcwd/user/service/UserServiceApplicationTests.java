package com.spring.lcwd.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.lcwd.user.service.entity.Rating;
import com.spring.lcwd.user.service.external.service.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {

		Rating rating = new Rating("10", "t", 10, "544");

		Rating savedRating = ratingService.createRating(rating);
	}
}