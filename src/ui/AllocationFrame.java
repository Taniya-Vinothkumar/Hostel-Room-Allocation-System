
package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.AllocationDAO;
import db.DBConnection;
import model.Allocation;

public class AllocationFrame extends JFrame {

    private JComboBox<String> cmbStudent;
    private JComboBox<String> cmbHostel;
    private JComboBox<String> cmbRoom;

    private JLabel lblGender;
    private JLabel lblHostelType;

    private JTable table;
    private DefaultTableModel model;

    private AllocationDAO dao;

    private List<Integer> studentIds;
    private List<Integer> hostelIds;
    private List<Integer> roomIds;

    // Store the hostel listener so we can temporarily remove it
    private ActionListener hostelListener;

    public AllocationFrame() {

        dao = new AllocationDAO();

        studentIds = new ArrayList<>();
        hostelIds = new ArrayList<>();
        roomIds = new ArrayList<>();

        setTitle("Hostel Room Allocation System - Allocation");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // Title
        JLabel title = new JLabel(
                "HOSTEL ROOM ALLOCATION SYSTEM",
                JLabel.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // Form
        JPanel formPanel = new JPanel(
                new GridLayout(6, 2, 10, 10)
        );

        JLabel lblStudent =
                new JLabel("Student");

        JLabel lblGenderText =
                new JLabel("Gender");

        JLabel lblHostelTypeText =
                new JLabel("Hostel Type");

        JLabel lblHostel =
                new JLabel("Hostel");

        JLabel lblRoom =
                new JLabel("Available Room");

        JLabel lblDate =
                new JLabel("Allocation Date");

        cmbStudent = new JComboBox<>();

        lblGender = new JLabel("-");

        lblHostelType = new JLabel("-");

        cmbHostel = new JComboBox<>();

        cmbRoom = new JComboBox<>();

        JLabel dateValue = new JLabel(
                LocalDate.now().toString()
        );

        formPanel.add(lblStudent);
        formPanel.add(cmbStudent);

        formPanel.add(lblGenderText);
        formPanel.add(lblGender);

        formPanel.add(lblHostelTypeText);
        formPanel.add(lblHostelType);

        formPanel.add(lblHostel);
        formPanel.add(cmbHostel);

        formPanel.add(lblRoom);
        formPanel.add(cmbRoom);

        formPanel.add(lblDate);
        formPanel.add(dateValue);

        JPanel centerPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // Buttons
        JButton btnAllocate =
                new JButton("ALLOCATE");

        JButton btnDelete =
                new JButton("DELETE");

        JButton btnClear =
                new JButton("CLEAR");

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.add(btnAllocate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table
        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[] {
                        "Allocation ID",
                        "Student ID",
                        "Room ID",
                        "Allocation Date"
                }
        );

        table = new JTable(model);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // -------------------------------------------------
        // STUDENT LISTENER
        // -------------------------------------------------

        cmbStudent.addActionListener(e -> {

            updateStudentDetails();

        });

        // -------------------------------------------------
        // HOSTEL LISTENER
        // -------------------------------------------------

        hostelListener = e -> {

            int index =
                    cmbHostel.getSelectedIndex();

            // Only load rooms when a valid hostel ID exists
            if (index >= 0 &&
                    index < hostelIds.size()) {

                loadRooms();
            }
        };

        cmbHostel.addActionListener(
                hostelListener
        );

        // -------------------------------------------------
        // BUTTON LISTENERS
        // -------------------------------------------------

        btnAllocate.addActionListener(e -> {
            allocateRoom();
        });

        btnDelete.addActionListener(e -> {
            deleteAllocation();
        });

        btnClear.addActionListener(e -> {
            clearFields();
        });

        // -------------------------------------------------
        // INITIAL DATA
        // -------------------------------------------------

        loadStudents();

        loadAllocations();

        if (cmbStudent.getItemCount() > 0) {

            cmbStudent.setSelectedIndex(0);

            updateStudentDetails();
        }
    }

    // =====================================================
    // LOAD STUDENTS
    // =====================================================

    private void loadStudents() {

        cmbStudent.removeAllItems();

        studentIds.clear();

        String sql =
                "SELECT student_id, student_name, gender " +
                "FROM students " +
                "ORDER BY student_id";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                int id =
                        rs.getInt("student_id");

                String name =
                        rs.getString("student_name");

                cmbStudent.addItem(
                        id + " - " + name
                );

                studentIds.add(id);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading students."
            );
        }
    }

    // =====================================================
    // UPDATE STUDENT DETAILS
    // =====================================================

    private void updateStudentDetails() {

        int index =
                cmbStudent.getSelectedIndex();

        if (index < 0 ||
                index >= studentIds.size()) {

            return;
        }

        int studentId =
                studentIds.get(index);

        String sql =
                "SELECT gender " +
                "FROM students " +
                "WHERE student_id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, studentId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                String gender =
                        rs.getString("gender");

                lblGender.setText(gender);

                lblHostelType.setText(gender);

                loadHostels(gender);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading student details."
            );
        }
    }

    // =====================================================
    // LOAD MATCHING HOSTELS
    // =====================================================

    private void loadHostels(String hostelType) {

        // IMPORTANT:
        // Remove hostel listener before changing combo box
        cmbHostel.removeActionListener(
                hostelListener
        );

        cmbHostel.removeAllItems();

        hostelIds.clear();

        cmbRoom.removeAllItems();

        roomIds.clear();

        String sql =
                "SELECT hostel_id, hostel_name, block " +
                "FROM hostels " +
                "WHERE hostel_type=? " +
                "ORDER BY hostel_id";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    hostelType
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                int id =
                        rs.getInt("hostel_id");

                String name =
                        rs.getString("hostel_name");

                String block =
                        rs.getString("block");

                // First store ID
                hostelIds.add(id);

                // Then add display text
                cmbHostel.addItem(
                        name +
                        " - Block " +
                        block
                );
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading hostels."
            );
        }

        // Add listener back
        cmbHostel.addActionListener(
                hostelListener
        );

        // Now everything is loaded.
        // Select first hostel manually.
        if (hostelIds.size() > 0) {

            cmbHostel.setSelectedIndex(0);

            loadRooms();

        } else {

            cmbRoom.removeAllItems();

            roomIds.clear();
        }
    }

    // =====================================================
    // LOAD AVAILABLE ROOMS
    // =====================================================

    private void loadRooms() {

        cmbRoom.removeAllItems();

        roomIds.clear();

        int hostelIndex =
                cmbHostel.getSelectedIndex();

        // No hostel selected
        if (hostelIndex < 0) {
            return;
        }

        // Very important safety check
        if (hostelIndex >= hostelIds.size()) {
            return;
        }

        int hostelId =
                hostelIds.get(hostelIndex);

        String sql =
                "SELECT room_id, room_number, room_type, " +
                "capacity, occupied, floor " +
                "FROM rooms " +
                "WHERE hostel_id=? " +
                "AND occupied < capacity " +
                "ORDER BY floor, room_number";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    hostelId
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                int id =
                        rs.getInt("room_id");

                String roomNumber =
                        rs.getString("room_number");

                String roomType =
                        rs.getString("room_type");

                int capacity =
                        rs.getInt("capacity");

                int occupied =
                        rs.getInt("occupied");

                int floor =
                        rs.getInt("floor");

                cmbRoom.addItem(
                        roomNumber +
                        " - Floor " +
                        floor +
                        " - " +
                        roomType +
                        " (" +
                        occupied +
                        "/" +
                        capacity +
                        ")"
                );

                roomIds.add(id);
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error loading rooms."
            );
        }
    }

    // =====================================================
    // ALLOCATE ROOM
    // =====================================================

    private void allocateRoom() {

        int studentIndex =
                cmbStudent.getSelectedIndex();

        int roomIndex =
                cmbRoom.getSelectedIndex();

        if (studentIndex < 0 ||
                studentIndex >= studentIds.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a student."
            );

            return;
        }

        if (roomIndex < 0 ||
                roomIndex >= roomIds.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No available room selected."
            );

            return;
        }

        int studentId =
                studentIds.get(studentIndex);

        int roomId =
                roomIds.get(roomIndex);

        Date date =
                Date.valueOf(
                        LocalDate.now()
                );

        Allocation allocation =
                new Allocation(
                        0,
                        studentId,
                        roomId,
                        date
                );

        boolean success =
                dao.addAllocation(
                        allocation
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room allocated successfully!"
            );

            loadAllocations();

            updateStudentDetails();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Room allocation failed."
            );
        }
    }

    // =====================================================
    // LOAD ALLOCATIONS
    // =====================================================

    private void loadAllocations() {

        model.setRowCount(0);

        List<Allocation> allocations =
                dao.getAllAllocations();

        for (Allocation a : allocations) {

            model.addRow(
                    new Object[] {
                            a.getAllocationId(),
                            a.getStudentId(),
                            a.getRoomId(),
                            a.getAllocationDate()
                    }
            );
        }
    }

    // =====================================================
    // DELETE ALLOCATION
    // =====================================================

    private void deleteAllocation() {

        int row =
                table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an allocation."
            );

            return;
        }

        int allocationId =
                Integer.parseInt(
                        model.getValueAt(
                                row,
                                0
                        ).toString()
                );

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete this allocation?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            boolean success =
                    dao.deleteAllocation(
                            allocationId
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Allocation deleted successfully!"
                );

                loadAllocations();

                updateStudentDetails();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Allocation deletion failed."
                );
            }
        }
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        if (cmbStudent.getItemCount() > 0) {

            cmbStudent.setSelectedIndex(0);

            updateStudentDetails();
        }
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        AllocationFrame frame =
                new AllocationFrame();

        frame.setVisible(true);
    }
}
