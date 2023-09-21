package com.spring.lcwd.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.lcwd.user.service.entity.Rating;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
	@PostMapping("/api/ratings/create/rating")
	public Rating createRating(Rating rating);
}