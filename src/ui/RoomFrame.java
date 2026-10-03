package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.RoomDAO;
import model.Room;

public class RoomFrame extends JFrame {

    private JTextField txtRoomId;
    private JTextField txtRoomNumber;
    private JTextField txtCapacity;
    private JTextField txtOccupied;

    private JComboBox<String> cmbHostel;
    private JComboBox<String> cmbFloor;
    private JComboBox<String> cmbRoomType;

    private JTable table;
    private DefaultTableModel model;

    private RoomDAO dao;

    // Hostel IDs corresponding to the hostel dropdown
    private int[] hostelIds = {1, 2, 3, 4};

    public RoomFrame() {

        dao = new RoomDAO();

        setTitle("Hostel Room Allocation System - Room Management");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // Header
        JLabel title = new JLabel(
                "HOSTEL ROOM ALLOCATION SYSTEM",
                JLabel.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        title.setForeground(
                new Color(30, 60, 100)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        // Form
        JPanel formPanel = new JPanel(
                new GridLayout(4, 4, 10, 10)
        );

        JLabel lblRoomId =
                new JLabel("Room ID");

        JLabel lblHostel =
                new JLabel("Hostel");

        JLabel lblFloor =
                new JLabel("Floor");

        JLabel lblRoomNumber =
                new JLabel("Room Number");

        JLabel lblRoomType =
                new JLabel("Room Type");

        JLabel lblCapacity =
                new JLabel("Capacity");

        JLabel lblOccupied =
                new JLabel("Occupied");

        txtRoomId =
                new JTextField();

        txtRoomNumber =
                new JTextField();

        txtCapacity =
                new JTextField();

        txtOccupied =
                new JTextField("0");

        cmbHostel =
                new JComboBox<>(
                        new String[] {
                                "Men Hostel A",
                                "Men Hostel B",
                                "Women Hostel A",
                                "Women Hostel B"
                        }
                );

        cmbFloor =
                new JComboBox<>(
                        new String[] {
                                "1",
                                "2",
                                "3",
                                "4"
                        }
                );

        cmbRoomType =
                new JComboBox<>(
                        new String[] {
                                "Single",
                                "Double",
                                "Triple"
                        }
                );

        formPanel.add(lblRoomId);
        formPanel.add(txtRoomId);

        formPanel.add(lblHostel);
        formPanel.add(cmbHostel);

        formPanel.add(lblFloor);
        formPanel.add(cmbFloor);

        formPanel.add(lblRoomNumber);
        formPanel.add(txtRoomNumber);

        formPanel.add(lblRoomType);
        formPanel.add(cmbRoomType);

        formPanel.add(lblCapacity);
        formPanel.add(txtCapacity);

        formPanel.add(lblOccupied);
        formPanel.add(txtOccupied);

        // Empty spaces
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        // Buttons
        JButton btnAdd =
                new JButton("ADD");

        JButton btnUpdate =
                new JButton("UPDATE");

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

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table
        model =
                new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[] {
                        "Room ID",
                        "Hostel ID",
                        "Floor",
                        "Room Number",
                        "Room Type",
                        "Capacity",
                        "Occupied"
                }
        );

        table =
                new JTable(model);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        // Center
        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

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

        // Load rooms
        loadRooms();

        // ADD
        btnAdd.addActionListener(e -> {

            try {

                int roomId =
                        Integer.parseInt(
                                txtRoomId.getText()
                        );

                int hostelId =
                        hostelIds[
                                cmbHostel.getSelectedIndex()
                        ];

                int floor =
                        Integer.parseInt(
                                cmbFloor.getSelectedItem()
                                        .toString()
                        );

                String roomNumber =
                        txtRoomNumber.getText();

                String roomType =
                        cmbRoomType.getSelectedItem()
                                .toString();

                int capacity =
                        Integer.parseInt(
                                txtCapacity.getText()
                        );

                int occupied =
                        Integer.parseInt(
                                txtOccupied.getText()
                        );

                Room room =
                        new Room(
                                roomId,
                                hostelId,
                                floor,
                                roomNumber,
                                roomType,
                                capacity,
                                occupied
                        );

                boolean success =
                        dao.addRoom(room);

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room added successfully!"
                    );

                    loadRooms();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to add room."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Room ID, Capacity and Occupied must be numbers."
                );
            }
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            try {

                int roomId =
                        Integer.parseInt(
                                txtRoomId.getText()
                        );

                int hostelId =
                        hostelIds[
                                cmbHostel.getSelectedIndex()
                        ];

                int floor =
                        Integer.parseInt(
                                cmbFloor.getSelectedItem()
                                        .toString()
                        );

                String roomNumber =
                        txtRoomNumber.getText();

                String roomType =
                        cmbRoomType.getSelectedItem()
                                .toString();

                int capacity =
                        Integer.parseInt(
                                txtCapacity.getText()
                        );

                int occupied =
                        Integer.parseInt(
                                txtOccupied.getText()
                        );

                Room room =
                        new Room(
                                roomId,
                                hostelId,
                                floor,
                                roomNumber,
                                roomType,
                                capacity,
                                occupied
                        );

                boolean success =
                        dao.updateRoom(room);

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room updated successfully!"
                    );

                    loadRooms();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Room not found."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid numbers."
                );
            }
        });

        // DELETE
        btnDelete.addActionListener(e -> {

            try {

                int roomId =
                        Integer.parseInt(
                                txtRoomId.getText()
                        );

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this room?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (choice ==
                        JOptionPane.YES_OPTION) {

                    boolean success =
                            dao.deleteRoom(roomId);

                    if (success) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Room deleted successfully!"
                        );

                        loadRooms();
                        clearFields();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Room not found."
                        );
                    }
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid Room ID."
                );
            }
        });

        // CLEAR
        btnClear.addActionListener(
                e -> clearFields()
        );

        // Table selection
        table.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()
                            && table.getSelectedRow() != -1) {

                        int row =
                                table.getSelectedRow();

                        txtRoomId.setText(
                                model.getValueAt(
                                        row, 0
                                ).toString()
                        );

                        int hostelId =
                                Integer.parseInt(
                                        model.getValueAt(
                                                row, 1
                                        ).toString()
                                );

                        cmbHostel.setSelectedIndex(
                                hostelId - 1
                        );

                        cmbFloor.setSelectedItem(
                                model.getValueAt(
                                        row, 2
                                ).toString()
                        );

                        txtRoomNumber.setText(
                                model.getValueAt(
                                        row, 3
                                ).toString()
                        );

                        cmbRoomType.setSelectedItem(
                                model.getValueAt(
                                        row, 4
                                ).toString()
                        );

                        txtCapacity.setText(
                                model.getValueAt(
                                        row, 5
                                ).toString()
                        );

                        txtOccupied.setText(
                                model.getValueAt(
                                        row, 6
                                ).toString()
                        );
                    }
                });
    }

    // Load rooms
    private void loadRooms() {

        model.setRowCount(0);

        List<Room> rooms =
                dao.getAllRooms();

        for (Room r : rooms) {

            model.addRow(
                    new Object[] {
                            r.getRoomId(),
                            r.getHostelId(),
                            r.getFloor(),
                            r.getRoomNumber(),
                            r.getRoomType(),
                            r.getCapacity(),
                            r.getOccupied()
                    }
            );
        }
    }

    // Clear fields
    private void clearFields() {

        txtRoomId.setText("");
        txtRoomNumber.setText("");
        txtCapacity.setText("");
        txtOccupied.setText("0");

        cmbHostel.setSelectedIndex(0);
        cmbFloor.setSelectedIndex(0);
        cmbRoomType.setSelectedIndex(0);

        table.clearSelection();
    }

    // Main
    public static void main(String[] args) {

        RoomFrame frame =
                new RoomFrame();

        frame.setVisible(true);
    }
}