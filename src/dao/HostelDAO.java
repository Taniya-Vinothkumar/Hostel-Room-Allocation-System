package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Hostel;

public class HostelDAO {

    // INSERT
    public boolean addHostel(Hostel hostel) {

        String sql = "INSERT INTO hostels " +
                     "(hostel_id, hostel_name, hostel_type, block) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hostel.getHostelId());
            ps.setString(2, hostel.getHostelName());
            ps.setString(3, hostel.getHostelType());
            ps.setString(4, hostel.getBlock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Hostel> getAllHostels() {

        List<Hostel> hostels = new ArrayList<>();

        String sql = "SELECT * FROM hostels";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Hostel hostel = new Hostel();

                hostel.setHostelId(
                        rs.getInt("hostel_id"));

                hostel.setHostelName(
                        rs.getString("hostel_name"));

                hostel.setHostelType(
                        rs.getString("hostel_type"));

                hostel.setBlock(
                        rs.getString("block"));

                hostels.add(hostel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hostels;
    }

    // UPDATE
    public boolean updateHostel(Hostel hostel) {

        String sql = "UPDATE hostels SET " +
                     "hostel_name=?, hostel_type=?, block=? " +
                     "WHERE hostel_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hostel.getHostelName());
            ps.setString(2, hostel.getHostelType());
            ps.setString(3, hostel.getBlock());
            ps.setInt(4, hostel.getHostelId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteHostel(int hostelId) {

        String sql = "DELETE FROM hostels WHERE hostel_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hostelId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}