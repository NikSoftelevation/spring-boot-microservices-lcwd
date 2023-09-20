package com.spring.lcwd.rating.service.implementation;

import com.spring.lcwd.rating.entity.Rating;
import com.spring.lcwd.rating.dto.RatingDto;
import com.spring.lcwd.rating.exception.RatingException;
import com.spring.lcwd.rating.repository.RatingRepository;
import com.spring.lcwd.rating.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<RatingDto> getAllRatings() {

        List<Rating> getAllRatings = ratingRepository.findAll();

        return getAllRatings.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public RatingDto createRating(Rating rating) {

        String randomId = UUID.randomUUID().toString();

        rating.setRatingId(randomId);

        Rating createdRating = ratingRepository.save(rating);
        return modelMapper.map(createdRating, RatingDto.class);
    }

    @Override
    public RatingDto getRatingByRatingId(String ratingId) {

        Rating getRatingByRatingId = ratingRepository.findById(ratingId).orElseThrow(() -> new RatingException("No rating with ratingId " + ratingId + "found !"));
        return modelMapper.map(getRatingByRatingId, RatingDto.class);
    }

    @Override
    public List<RatingDto> getAllRatingsByHotelId(String id) {

        List<Rating> ratingsByHotelId = ratingRepository.findByHotelId(id);

        return ratingsByHotelId.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getAllRatingsByUserId(String userId) {

        List<Rating> ratingsByUserId = ratingRepository.findByUserId(userId);

        return ratingsByUserId.stream().map(rating -> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
    }

    @Override
    public RatingDto updateRating(Rating rating, String ratingId) {

        Rating oldRating = ratingRepository.findById(ratingId).orElseThrow(() -> new RatingException("No rating with ratingId " + ratingId + "found !"));


        oldRating.setRating(rating.getRating());
        oldRating.setFeedback(rating.getFeedback());
        oldRating.setHotelId(rating.getHotelId());
        oldRating.setUserId(rating.getUserId());

        Rating updatedRating = ratingRepository.save(oldRating);

        return modelMapper.map(updatedRating, RatingDto.class);
    }
}