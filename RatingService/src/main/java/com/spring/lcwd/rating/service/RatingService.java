package com.spring.lcwd.rating.service;

import com.spring.lcwd.rating.entity.Rating;
import com.spring.lcwd.rating.dto.RatingDto;

import java.util.List;

public interface RatingService {
    public List<RatingDto> getAllRatings();

    public RatingDto createRating(Rating rating);

    public RatingDto getRatingByRatingId(String ratingId);

    public List<RatingDto> getAllRatingsByHotelId(String id);

    public List<RatingDto> getAllRatingsByUserId(String userId);

    public RatingDto updateRating(Rating rating, String ratingId);
}