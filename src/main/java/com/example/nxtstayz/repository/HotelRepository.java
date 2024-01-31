package com.example.nxtstayz.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;

public interface HotelRepository {
    ArrayList<Hotel> getAllHotels();

    Hotel addHotel(Hotel hotel);

    Hotel getHotelById(int hotelId);

    Hotel updateHotel(Hotel hotel, int hotelId);

    void deleteHotel(int hotelId);

    List<Room> getHotelRooms(int hotelId);
}