package com.example.nxtstayz.service;

import java.util.ArrayList;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;
import com.example.nxtstayz.repository.HotelJpaRepository;
import com.example.nxtstayz.repository.RoomJpaRepository;
import com.example.nxtstayz.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class RoomJpaService implements RoomRepository {

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private HotelJpaRepository hotelJpaRepository;

    @Override
    public ArrayList<Room> getAllRooms() {
        try {
            List<Room> rooms = roomJpaRepository.findAll();
            return (ArrayList<Room>) rooms;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room addRoom(Room room) {
        try {
            Hotel hotel = room.getHotel();
            int hotelId = hotel.getHotelId();
            Hotel obtainedHotel = hotelJpaRepository.findById(hotelId).get();
            room.setHotel(obtainedHotel);
            return roomJpaRepository.save(room);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room getRoomById(int roomId) {
        try {
            return roomJpaRepository.findById(roomId).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Room updateRoom(Room room, int roomId) {
        try {

            Room existingRoom = roomJpaRepository.findById(roomId).get();
            if (room.getRoomNumber() != null) {
                existingRoom.setRoomNumber(room.getRoomNumber());
            }
            if (room.getRoomType() != null) {
                existingRoom.setRoomType(room.getRoomType());
            }
            if (room.getPrice() != 0.0) {
                existingRoom.setPrice(room.getPrice());
            }
            if (room.getHotel() != null) {
                Hotel hotel = room.getHotel();
                int hotelId = hotel.getHotelId();
                Hotel newHotel = hotelJpaRepository.findById(hotelId).get();
                existingRoom.setHotel(newHotel);

            }

            return roomJpaRepository.save(existingRoom);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRoom(int roomId) {
        try {
            roomJpaRepository.deleteById(roomId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }

    @Override
    public Hotel getRoomHotel(int roomId) {
        try {
            Room room = roomJpaRepository.findById(roomId).get();
            Hotel hotel = room.getHotel();
            return hotel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}