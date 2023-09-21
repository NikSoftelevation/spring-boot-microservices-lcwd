package com.spring.lcwd.user.service.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.lcwd.user.service.entity.Hotel;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/api/hotels/get/{hotelId}")
	public Hotel getHotel(@PathVariable("hotelId") String hotelId);
}