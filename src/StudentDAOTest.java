import java.util.List;

import dao.StudentDAO;
import model.Student;

public class StudentDAOTest {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        // =========================
        // INSERT
        // =========================

        Student student = new Student(
                105,
                "Priya",
                "ECE",
                1,
                "9876543213",
                "WOMEN"
        );

        boolean inserted = dao.addStudent(student);

        if (inserted) {
            System.out.println("Student inserted successfully!");
        } else {
            System.out.println("Student insertion failed!");
        }


        // =========================
        // READ
        // =========================

        System.out.println("\nStudent Records:");

        List<Student> students = dao.getAllStudents();

        for (Student s : students) {

            System.out.println(
                    s.getStudentId() + " | " +
                    s.getStudentName() + " | " +
                    s.getDepartment() + " | " +
                    s.getYear() + " | " +
                    s.getPhone() + " | " +
                    s.getGender()
            );
        }


        // =========================
        // UPDATE
        // =========================

        student.setStudentName("Priya Updated");

        boolean updated = dao.updateStudent(student);

        if (updated) {
            System.out.println("\nStudent updated successfully!");
        } else {
            System.out.println("\nStudent update failed!");
        }


        // =========================
        // DELETE
        // =========================

        boolean deleted = dao.deleteStudent(105);

        if (deleted) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student deletion failed!");
        }
    }
}