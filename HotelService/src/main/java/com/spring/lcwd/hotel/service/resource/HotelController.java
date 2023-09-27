package com.spring.lcwd.hotel.service.resource;

import com.spring.lcwd.hotel.service.dto.HotelDto;
import com.spring.lcwd.hotel.service.entity.Hotel;
import com.spring.lcwd.hotel.service.response.ApiResponse;
import com.spring.lcwd.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin("*")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping
	public ResponseEntity<HotelDto> createHotel(@RequestBody Hotel hotel) {

		return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<HotelDto> updateUser(@RequestBody Hotel hotel, @PathVariable("id") String id) {

		return new ResponseEntity<>(hotelService.updateHotel(hotel, id), HttpStatus.ACCEPTED);
	}

	@PreAuthorize("hasAuthority('SCOPE_INTERNAl')")
	@GetMapping("/get/{id}")
	public ResponseEntity<HotelDto> getHotelByHotelId(@PathVariable("id") String id) {

		return new ResponseEntity<>(hotelService.getHotelByHotelId(id), HttpStatus.OK);
	}

	@PreAuthorize("hasAuthority('SCOPE_INTERNAL') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<HotelDto>> getAllHotels() {

		return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteHotelById(@PathVariable("id") String id) {

		hotelService.deleteHotelByHotelId(id);

		return new ResponseEntity<>(new ApiResponse("Hotel deleted successfully", true), HttpStatus.GONE);
	}
}
