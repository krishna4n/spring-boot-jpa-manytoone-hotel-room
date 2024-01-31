package com.example.nxtstayz.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.nxtstayz.model.Hotel;
import com.example.nxtstayz.model.Room;

public interface RoomRepository {
    ArrayList<Room> getAllRooms();

    Room addRoom(Room room);

    Room getRoomById(int roomId);

    Room updateRoom(Room room, int roomId);

    void deleteRoom(int roomId);

    Hotel getRoomHotel(int roomId);
}