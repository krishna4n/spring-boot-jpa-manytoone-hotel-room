package com.example.nxtstayz.service;

import java.util.ArrayList;
import java.util.List;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.HotelRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HotelJpaService implements HotelRepository {

    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Override
    public ArrayList<Hotel> getAllHotels() {
        try {
            List<Hotel> hotels = hotelJpaRepository.findAll();
            return (ArrayList<Hotel>) hotels;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        try {
            return hotelJpaRepository.save(hotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel getHotelById(int hotelId) {
        try {
            return hotelJpaRepository.findById(hotelId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Hotel updateHotel(Hotel hotel, int hotelId) {
        try {
            Hotel existingHotel = hotelJpaRepository.findById(hotelId).get();
            if (hotel.getHotelName() != null) {
                existingHotel.setHotelName(hotel.getHotelName());
            }
            if (hotel.getLocation() != null) {
                existingHotel.setLocation(hotel.getLocation());
            }
            if (hotel.getRating() != 0) {
                existingHotel.setRating(hotel.getRating());
            }
            return hotelJpaRepository.save(existingHotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteHotel(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            List<Room> rooms = roomJpaRepository.findByHotel(hotel);
            for (Room room : rooms) {
                room.setHotel(null);
            }
            roomJpaRepository.saveAll(rooms);
            hotelJpaRepository.deleteById(hotelId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        throw new ResponseStatusException(HttpStatus.NO_CONTENT);

    }

    @Override
    public List<Room> getHotelRooms(int hotelId) {
        try {
            Hotel hotel = hotelJpaRepository.findById(hotelId).get();
            return roomJpaRepository.findByHotel(hotel);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}