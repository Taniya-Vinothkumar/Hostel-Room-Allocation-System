package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Allocation;

public class AllocationDAO {

    // INSERT ALLOCATION
    public boolean addAllocation(Allocation allocation) {

        String checkRoom =
                "SELECT capacity, occupied " +
                "FROM rooms WHERE room_id=?";

        String insert =
                "INSERT INTO allocations " +
                "(student_id, room_id, allocation_date) " +
                "VALUES (?, ?, ?)";

        String updateRoom =
                "UPDATE rooms SET occupied = occupied + 1 " +
                "WHERE room_id=?";

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            // Check room availability
            PreparedStatement check =
                    con.prepareStatement(checkRoom);

            check.setInt(1, allocation.getRoomId());

            ResultSet rs = check.executeQuery();

            if (!rs.next()) {

                System.out.println("Room not found!");
                return false;
            }

            int capacity =
                    rs.getInt("capacity");

            int occupied =
                    rs.getInt("occupied");

            if (occupied >= capacity) {

                System.out.println("Room is full!");
                return false;
            }

            // Insert allocation
            PreparedStatement ps =
                    con.prepareStatement(insert);

            ps.setInt(
                    1,
                    allocation.getStudentId()
            );

            ps.setInt(
                    2,
                    allocation.getRoomId()
            );

            ps.setDate(
                    3,
                    allocation.getAllocationDate()
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                // Increase occupied count
                PreparedStatement update =
                        con.prepareStatement(updateRoom);

                update.setInt(
                        1,
                        allocation.getRoomId()
                );

                update.executeUpdate();

                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // READ ALL ALLOCATIONS
    public List<Allocation> getAllAllocations() {

        List<Allocation> allocations =
                new ArrayList<>();

        String sql =
                "SELECT * FROM allocations";

        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                Allocation allocation =
                        new Allocation();

                allocation.setAllocationId(
                        rs.getInt(
                                "allocation_id"
                        )
                );

                allocation.setStudentId(
                        rs.getInt(
                                "student_id"
                        )
                );

                allocation.setRoomId(
                        rs.getInt(
                                "room_id"
                        )
                );

                allocation.setAllocationDate(
                        rs.getDate(
                                "allocation_date"
                        )
                );

                allocations.add(
                        allocation
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return allocations;
    }

    // UPDATE ALLOCATION
    public boolean updateAllocation(
            Allocation allocation) {

        String sql =
                "UPDATE allocations SET " +
                "student_id=?, room_id=?, " +
                "allocation_date=? " +
                "WHERE allocation_id=?";

        try (Connection con =
                     DBConnection.getConnection();

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    allocation.getStudentId()
            );

            ps.setInt(
                    2,
                    allocation.getRoomId()
            );

            ps.setDate(
                    3,
                    allocation.getAllocationDate()
            );

            ps.setInt(
                    4,
                    allocation.getAllocationId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // DELETE ALLOCATION
    public boolean deleteAllocation(
            int allocationId) {

        String findRoom =
                "SELECT room_id " +
                "FROM allocations " +
                "WHERE allocation_id=?";

        String delete =
                "DELETE FROM allocations " +
                "WHERE allocation_id=?";

        String updateRoom =
                "UPDATE rooms SET occupied = occupied - 1 " +
                "WHERE room_id=? AND occupied > 0";

        Connection con = null;

        try {

            con = DBConnection.getConnection();

            // Find room
            PreparedStatement find =
                    con.prepareStatement(findRoom);

            find.setInt(
                    1,
                    allocationId
            );

            ResultSet rs =
                    find.executeQuery();

            if (!rs.next()) {
                return false;
            }

            int roomId =
                    rs.getInt("room_id");

            // Delete allocation
            PreparedStatement ps =
                    con.prepareStatement(delete);

            ps.setInt(
                    1,
                    allocationId
            );

            int result =
                    ps.executeUpdate();

            if (result > 0) {

                // Decrease occupied count
                PreparedStatement update =
                        con.prepareStatement(updateRoom);

                update.setInt(
                        1,
                        roomId
                );

                update.executeUpdate();

                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}