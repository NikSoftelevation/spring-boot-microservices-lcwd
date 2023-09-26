package com.spring.lcwd.user.service.resource;

import com.spring.lcwd.user.service.dto.UserDto;
import com.spring.lcwd.user.service.entity.User;
import com.spring.lcwd.user.service.response.ApiResponse;
import com.spring.lcwd.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody User user) {

		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UserDto> updateUserByUserId(@RequestBody User user, @PathVariable("userId") String userId) {

		return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.ACCEPTED);
	}

	int retryCount = 1;

	@GetMapping("/{userId}")
//@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId) {

		logger.info("Get Single User Handler : UserController");

		logger.info("Retry Count : {} ", retryCount);

		retryCount++;

		return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
	}

	// creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception) {

		logger.info("Fallback is executed because service is down", exception.getMessage());

		User user = new User("1234567", "Dummy", "dummy@gmail.com",
				"This user is created because some service is down");

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserByUserId(@PathVariable("userId") String userId) {

		userService.deleteUserByUserId(userId);

		return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.GONE);
	}
}