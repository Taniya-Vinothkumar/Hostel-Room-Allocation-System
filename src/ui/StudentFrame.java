package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import dao.StudentDAO;
import model.Student;

public class StudentFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtPhone;

    private JComboBox<String> cmbDepartment;
    private JComboBox<String> cmbYear;
    private JComboBox<String> cmbGender;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private StudentDAO studentDAO;

    public StudentFrame() {

        studentDAO = new StudentDAO();

        setTitle("Hostel Room Allocation System - Student Management");

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();

        loadStudents();
    }

    private void createUI() {

        setLayout(new BorderLayout(15, 15));

        // ================= HEADER =================

        JPanel headerPanel = new JPanel();

        headerPanel.setBackground(new Color(35, 47, 62));

        JLabel titleLabel =
                new JLabel("HOSTEL ROOM ALLOCATION SYSTEM");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);


        // ================= FORM =================

        JPanel formPanel = new JPanel(
                new GridBagLayout()
        );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Details"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;


        // ================= STUDENT ID =================

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel("Student ID:"),
                gbc
        );

        txtId = new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                txtId,
                gbc
        );


        // ================= STUDENT NAME =================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Student Name:"),
                gbc
        );

        txtName = new JTextField();

        gbc.gridx = 3;

        formPanel.add(
                txtName,
                gbc
        );


        // ================= DEPARTMENT =================

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel("Department:"),
                gbc
        );

        cmbDepartment = new JComboBox<>(
                new String[]{
                        "CSE",
                        "ECE",
                        "IT",
                        "EEE",
                        "MECH",
                        "CIVIL"
                }
        );

        gbc.gridx = 1;

        formPanel.add(
                cmbDepartment,
                gbc
        );


        // ================= YEAR =================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Year:"),
                gbc
        );

        cmbYear = new JComboBox<>(
                new String[]{
                        "1",
                        "2",
                        "3",
                        "4"
                }
        );

        gbc.gridx = 3;

        formPanel.add(
                cmbYear,
                gbc
        );


        // ================= PHONE =================

        gbc.gridx = 0;
        gbc.gridy = 2;

        formPanel.add(
                new JLabel("Phone:"),
                gbc
        );

        txtPhone = new JTextField();

        gbc.gridx = 1;

        formPanel.add(
                txtPhone,
                gbc
        );


        // ================= GENDER =================

        gbc.gridx = 2;

        formPanel.add(
                new JLabel("Gender:"),
                gbc
        );

        cmbGender = new JComboBox<>(
                new String[]{
                        "MEN",
                        "WOMEN"
                }
        );

        gbc.gridx = 3;

        formPanel.add(
                cmbGender,
                gbc
        );


        // ================= BUTTONS =================

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
                        new GridLayout(1, 4, 10, 10)
                );

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.gridwidth = 4;

        formPanel.add(
                buttonPanel,
                gbc
        );

        add(
                formPanel,
                BorderLayout.CENTER
        );


        // ================= TABLE =================

        tableModel = new DefaultTableModel(

                new String[]{
                        "Student ID",
                        "Student Name",
                        "Department",
                        "Year",
                        "Phone",
                        "Gender"
                },
                0

        ) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };


        studentTable =
                new JTable(tableModel);

        studentTable.setRowHeight(30);

        studentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        studentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                14
                        )
                );

        studentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        studentTable
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        900,
                        300
                )
        );


        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Records"
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                tablePanel,
                BorderLayout.SOUTH
        );


        // ================= BUTTON ACTIONS =================

        btnAdd.addActionListener(
                e -> addStudent()
        );

        btnUpdate.addActionListener(
                e -> updateStudent()
        );

        btnDelete.addActionListener(
                e -> deleteStudent()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );


        // ================= TABLE CLICK =================

        studentTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                studentTable.getSelectedRow();

                        if (row >= 0) {

                            txtId.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            txtName.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            cmbDepartment.setSelectedItem(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            cmbYear.setSelectedItem(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );

                            txtPhone.setText(
                                    tableModel
                                            .getValueAt(row, 4)
                                            .toString()
                            );

                            cmbGender.setSelectedItem(
                                    tableModel
                                            .getValueAt(row, 5)
                                            .toString()
                            );
                        }
                    }
                });
    }


    // ================= LOAD STUDENTS =================

    private void loadStudents() {

        tableModel.setRowCount(0);

        List<Student> students =
                studentDAO.getAllStudents();

        for (Student student : students) {

            tableModel.addRow(
                    new Object[]{

                            student.getStudentId(),

                            student.getStudentName(),

                            student.getDepartment(),

                            student.getYear(),

                            student.getPhone(),

                            student.getGender()

                    }
            );
        }
    }


    // ================= ADD =================

    private void addStudent() {

        try {

            if (
                    txtId.getText().trim().isEmpty()
                    ||
                    txtName.getText().trim().isEmpty()
                    ||
                    txtPhone.getText().trim().isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill all fields."
                );

                return;
            }


            int id =
                    Integer.parseInt(
                            txtId.getText().trim()
                    );


            String name =
                    txtName.getText().trim();


            String department =
                    cmbDepartment
                            .getSelectedItem()
                            .toString();


            int year =
                    Integer.parseInt(
                            cmbYear
                                    .getSelectedItem()
                                    .toString()
                    );


            String phone =
                    txtPhone.getText().trim();


            String gender =
                    cmbGender
                            .getSelectedItem()
                            .toString();


            Student student =
                    new Student(
                            id,
                            name,
                            department,
                            year,
                            phone,
                            gender
                    );


            if (studentDAO.addStudent(student)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student added successfully!"
                );

                loadStudents();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to add student."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID must be a number."
            );
        }
    }


    // ================= UPDATE =================

    private void updateStudent() {

        try {

            int id =
                    Integer.parseInt(
                            txtId.getText().trim()
                    );


            String name =
                    txtName.getText().trim();


            String department =
                    cmbDepartment
                            .getSelectedItem()
                            .toString();


            int year =
                    Integer.parseInt(
                            cmbYear
                                    .getSelectedItem()
                                    .toString()
                    );


            String phone =
                    txtPhone.getText().trim();


            String gender =
                    cmbGender
                            .getSelectedItem()
                            .toString();


            Student student =
                    new Student(
                            id,
                            name,
                            department,
                            year,
                            phone,
                            gender
                    );


            if (studentDAO.updateStudent(student)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student updated successfully!"
                );

                loadStudents();

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student not found."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid student details."
            );
        }
    }


    // ================= DELETE =================

    private void deleteStudent() {

        try {

            int id =
                    Integer.parseInt(
                            txtId.getText().trim()
                    );


            int confirm =
                    JOptionPane.showConfirmDialog(

                            this,

                            "Delete this student?",

                            "Confirm Delete",

                            JOptionPane.YES_NO_OPTION

                    );


            if (
                    confirm ==
                    JOptionPane.YES_OPTION
            ) {

                if (
                        studentDAO
                                .deleteStudent(id)
                ) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student deleted successfully!"
                    );

                    loadStudents();

                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student not found."
                    );
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Select a valid student."
            );
        }
    }


    // ================= CLEAR =================

    private void clearFields() {

        txtId.setText("");

        txtName.setText("");

        txtPhone.setText("");

        cmbDepartment.setSelectedIndex(0);

        cmbYear.setSelectedIndex(0);

        cmbGender.setSelectedIndex(0);

        studentTable.clearSelection();
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            StudentFrame frame =
                    new StudentFrame();

            frame.setVisible(true);
        });
    }
}