package com.spring.lcwd.user.service.resource;

import com.spring.lcwd.user.service.dto.UserDto;
import com.spring.lcwd.user.service.entity.User;
import com.spring.lcwd.user.service.response.ApiResponse;
import com.spring.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/create/new/user")
	public ResponseEntity<UserDto> createUser(@RequestBody User user) {

		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUserByUserId(@RequestBody User user, @PathVariable("userId") String userId) {

		return new ResponseEntity<>(userService.updateUser(user, userId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<UserDto> getUserByUserId(@PathVariable("userId") String userId) {

		return new ResponseEntity<>(userService.getUserByUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUserByUserId(@PathVariable("userId") String userId) {

		userService.deleteUserByUserId(userId);

		return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.GONE);
	}
}