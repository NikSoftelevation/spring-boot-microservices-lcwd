package com.spring.lcwd.rating.resource;

import com.spring.lcwd.rating.entity.Rating;
import com.spring.lcwd.rating.dto.RatingDto;
import com.spring.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@CrossOrigin("*")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<RatingDto> createNewRating(@RequestBody Rating rating) {

		return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
	}

	@PutMapping("/update/{ratingId}")
	public ResponseEntity<RatingDto> updateRating(@RequestBody Rating rating,
			@PathVariable("ratingId") String ratingId) {

		return new ResponseEntity<>(ratingService.updateRating(rating, ratingId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{ratingId}")
	public ResponseEntity<RatingDto> getRatingByRatingId(@PathVariable("ratingId") String ratingId) {

		return new ResponseEntity<>(ratingService.getRatingByRatingId(ratingId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<RatingDto>> getAllRatings() {

		return new ResponseEntity<>(ratingService.getAllRatings(), HttpStatus.OK);
	}

	@GetMapping("/get/ratings/by/userId/{userId}")
	public ResponseEntity<List<RatingDto>> getAllRatingsByUserId(@PathVariable("userId") String userId) {

		return new ResponseEntity<>(ratingService.getAllRatingsByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/get/ratings/by/hotelId/{id}")
	public ResponseEntity<List<RatingDto>> getAllRatingsByHotelId(@PathVariable("id") String id) {

		return new ResponseEntity<>(ratingService.getAllRatingsByHotelId(id), HttpStatus.OK);
	}
}