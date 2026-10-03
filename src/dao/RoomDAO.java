package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Room;

public class RoomDAO {

    // INSERT
    public boolean addRoom(Room room) {

        String sql = "INSERT INTO rooms " +
                     "(room_id, hostel_id, floor, room_number, " +
                     "room_type, capacity, occupied) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, room.getRoomId());
            ps.setInt(2, room.getHostelId());
            ps.setInt(3, room.getFloor());
            ps.setString(4, room.getRoomNumber());
            ps.setString(5, room.getRoomType());
            ps.setInt(6, room.getCapacity());
            ps.setInt(7, room.getOccupied());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM rooms";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room();

                room.setRoomId(
                        rs.getInt("room_id"));

                room.setHostelId(
                        rs.getInt("hostel_id"));

                room.setFloor(
                        rs.getInt("floor"));

                room.setRoomNumber(
                        rs.getString("room_number"));

                room.setRoomType(
                        rs.getString("room_type"));

                room.setCapacity(
                        rs.getInt("capacity"));

                room.setOccupied(
                        rs.getInt("occupied"));

                rooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // UPDATE
    public boolean updateRoom(Room room) {

        String sql = "UPDATE rooms SET " +
                     "hostel_id=?, floor=?, room_number=?, " +
                     "room_type=?, capacity=?, occupied=? " +
                     "WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, room.getHostelId());
            ps.setInt(2, room.getFloor());
            ps.setString(3, room.getRoomNumber());
            ps.setString(4, room.getRoomType());
            ps.setInt(5, room.getCapacity());
            ps.setInt(6, room.getOccupied());
            ps.setInt(7, room.getRoomId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteRoom(int roomId) {

        String sql = "DELETE FROM rooms WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}