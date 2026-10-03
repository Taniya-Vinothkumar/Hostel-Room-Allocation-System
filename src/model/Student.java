package model;

public class Student {

    private int studentId;
    private String studentName;
    private String department;
    private int year;
    private String phone;
    private String gender;

    public Student() {
    }

    public Student(int studentId, String studentName,
                   String department, int year,
                   String phone, String gender) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.year = year;
        this.phone = phone;
        this.gender = gender;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}