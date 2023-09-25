package com.spring.lcwd.user.service.service.implementation;

import com.spring.lcwd.user.service.dto.UserDto;
import com.spring.lcwd.user.service.entity.Hotel;
import com.spring.lcwd.user.service.entity.Rating;
import com.spring.lcwd.user.service.entity.User;
import com.spring.lcwd.user.service.exception.UserException;
import com.spring.lcwd.user.service.external.service.HotelService;
import com.spring.lcwd.user.service.repository.UserRepository;
import com.spring.lcwd.user.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;

	@Override
	public UserDto createUser(User user) {

		String randomUserId = UUID.randomUUID().toString();

		user.setUserId(randomUserId);

		User createdUser = userRepository.save(user);
		return modelMapper.map(createdUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(User user, String userId) {

		User userByUserId = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User not found with userId " + userId));

		userByUserId.setUserId(user.getUserId());
		userByUserId.setName(user.getName());
		userByUserId.setAbout(user.getAbout());
		userByUserId.setEmail(user.getEmail());

		User updatedUser = userRepository.save(userByUserId);

		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public User getUserByUserId(String userId) {

		User userByUserId = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User not found with userId " + userId));

		// fetch rating of the above user from RATING-SERVICE

		Rating[] ratingsOfUser = restTemplate.getForObject(
				"http://RATING-SERVICE/api/ratings/get/all/ratings/by/userId/" + userByUserId.getUserId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {

			// call API to HOTEL-SERVICE to get hotel

//			ResponseEntity<Hotel> forEntity = restTemplate
//					.getForEntity("http://HOTEL-SERVICE/api/hotels/get/" + rating.getHotelId(), Hotel.class);

			Hotel hotel = hotelService.getHotel(rating.getHotelId());

//			logger.info(" Response Status Code : {} ", forEntity.getStatusCode());

			// Set Hotel To Rating
			rating.setHotel(hotel);

			// Return Rating
			return rating;
		}).collect(Collectors.toList());

		userByUserId.setRatings(ratingList);

		return userByUserId;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<User> getAllUsers = userRepository.findAll();

		return getAllUsers.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteUserByUserId(String userId) {
		userRepository.deleteById(userId);
	}
}