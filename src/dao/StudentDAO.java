package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Student;

public class StudentDAO {

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students " +
                     "(student_id, student_name, department, year, phone, gender) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
            ps.setString(2, student.getStudentName());
            ps.setString(3, student.getDepartment());
            ps.setInt(4, student.getYear());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getGender());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Student student = new Student();

                student.setStudentId(
                        rs.getInt("student_id"));

                student.setStudentName(
                        rs.getString("student_name"));

                student.setDepartment(
                        rs.getString("department"));

                student.setYear(
                        rs.getInt("year"));

                student.setPhone(
                        rs.getString("phone"));

                student.setGender(
                        rs.getString("gender"));

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET " +
                     "student_name=?, department=?, " +
                     "year=?, phone=?, gender=? " +
                     "WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getDepartment());
            ps.setInt(3, student.getYear());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getGender());
            ps.setInt(6, student.getStudentId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int studentId) {

        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}