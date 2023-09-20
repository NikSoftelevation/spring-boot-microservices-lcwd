package com.spring.lcwd.hotel.service.service;

import com.spring.lcwd.hotel.service.dto.HotelDto;
import com.spring.lcwd.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {
    public HotelDto createHotel(Hotel hotel);

    public HotelDto updateHotel(Hotel hotel, String id);

    public HotelDto getHotelByHotelId(String id);

    public List<HotelDto> getAllHotels();

    public void deleteHotelByHotelId(String id);
}