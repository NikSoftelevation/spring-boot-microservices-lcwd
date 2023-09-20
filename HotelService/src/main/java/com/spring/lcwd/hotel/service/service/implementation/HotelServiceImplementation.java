package com.spring.lcwd.hotel.service.service.implementation;

import com.spring.lcwd.hotel.service.dto.HotelDto;
import com.spring.lcwd.hotel.service.entity.Hotel;
import com.spring.lcwd.hotel.service.exception.HotelException;
import com.spring.lcwd.hotel.service.repository.HotelRepository;
import com.spring.lcwd.hotel.service.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImplementation implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public HotelDto createHotel(Hotel hotel) {

        String randomId = UUID.randomUUID().toString();

        hotel.setId(randomId);

        Hotel createdHotel = hotelRepository.save(hotel);

        return modelMapper.map(createdHotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotel(Hotel hotel, String id) {

        Hotel hotelByHotelId = hotelRepository.findById(id).orElseThrow(() -> new HotelException("No hotel found with id " + id));

        hotelByHotelId.setName(hotel.getName());
        hotelByHotelId.setAbout(hotel.getAbout());
        hotelByHotelId.setLocation(hotel.getLocation());

        Hotel updatedUser = hotelRepository.save(hotelByHotelId);

        return modelMapper.map(updatedUser, HotelDto.class);
    }

    @Override
    public HotelDto getHotelByHotelId(String id) {

        Hotel hotelByHotelId = hotelRepository.findById(id).orElseThrow(() -> new HotelException("No hotel found with id " + id));

        return modelMapper.map(hotelByHotelId, HotelDto.class);
    }

    @Override
    public List<HotelDto> getAllHotels() {

        List<Hotel> getAllHotels = hotelRepository.findAll();
        return getAllHotels.stream().map(hotel -> modelMapper.map(hotel, HotelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteHotelByHotelId(String id) {

        hotelRepository.deleteById(id);
    }
}