package com.spring.lcwd.hotel.service.resource;

import com.spring.lcwd.hotel.service.dto.HotelDto;
import com.spring.lcwd.hotel.service.entity.Hotel;
import com.spring.lcwd.hotel.service.response.ApiResponse;
import com.spring.lcwd.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/create/new/hotel")
    public ResponseEntity<HotelDto> createHotel(@RequestBody Hotel hotel) {

        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDto> updateUser(@RequestBody Hotel hotel, @PathVariable("id") String id) {

        return new ResponseEntity<>(hotelService.updateHotel(hotel, id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<HotelDto> getHotelByHotelId(@PathVariable("id") String id) {

        return new ResponseEntity<>(hotelService.getHotelByHotelId(id), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<HotelDto>> getAllHotels() {

        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteHotelById(@PathVariable("id") String id) {

        hotelService.deleteHotelByHotelId(id);

        return new ResponseEntity<>(new ApiResponse("Hotel deleted successfully", true), HttpStatus.GONE);
    }
}