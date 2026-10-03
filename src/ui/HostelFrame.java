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

import dao.HostelDAO;
import model.Hostel;

public class HostelFrame extends JFrame {

    private JTextField txtHostelId;
    private JTextField txtHostelName;

    private JComboBox<String> cmbHostelType;
    private JComboBox<String> cmbBlock;

    private JTable table;
    private DefaultTableModel model;

    private HostelDAO dao;

    public HostelFrame() {

        dao = new HostelDAO();

        setTitle("Hostel Room Allocation System - Hostel Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
                15, 15, 15, 15
        ));

        // Header
        JLabel title = new JLabel(
                "HOSTEL ROOM ALLOCATION SYSTEM",
                JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(30, 60, 100));

        mainPanel.add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel lblHostelId = new JLabel("Hostel ID");
        JLabel lblHostelName = new JLabel("Hostel Name");
        JLabel lblHostelType = new JLabel("Hostel Type");
        JLabel lblBlock = new JLabel("Block");

        txtHostelId = new JTextField();
        txtHostelName = new JTextField();

        cmbHostelType = new JComboBox<>(
                new String[] {"MEN", "WOMEN"}
        );

        cmbBlock = new JComboBox<>(
                new String[] {"A", "B", "C"}
        );

        formPanel.add(lblHostelId);
        formPanel.add(txtHostelId);

        formPanel.add(lblHostelName);
        formPanel.add(txtHostelName);

        formPanel.add(lblHostelType);
        formPanel.add(cmbHostelType);

        formPanel.add(lblBlock);
        formPanel.add(cmbBlock);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JButton btnAdd = new JButton("ADD");
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnDelete = new JButton("DELETE");
        JButton btnClear = new JButton("CLEAR");

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10)
        );

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table
        model = new DefaultTableModel();

        model.setColumnIdentifiers(
                new String[] {
                        "Hostel ID",
                        "Hostel Name",
                        "Hostel Type",
                        "Block"
                }
        );

        table = new JTable(model);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

        centerPanel.add(formPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Load records
        loadHostels();

        // ADD
        btnAdd.addActionListener(e -> {

            try {

                int hostelId = Integer.parseInt(
                        txtHostelId.getText()
                );

                String hostelName =
                        txtHostelName.getText();

                String hostelType =
                        cmbHostelType.getSelectedItem().toString();

                String block =
                        cmbBlock.getSelectedItem().toString();

                Hostel hostel = new Hostel(
                        hostelId,
                        hostelName,
                        hostelType,
                        block
                );

                boolean success =
                        dao.addHostel(hostel);

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Hostel added successfully!"
                    );

                    loadHostels();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to add hostel."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hostel ID must be a number."
                );
            }
        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            try {

                int hostelId = Integer.parseInt(
                        txtHostelId.getText()
                );

                String hostelName =
                        txtHostelName.getText();

                String hostelType =
                        cmbHostelType.getSelectedItem().toString();

                String block =
                        cmbBlock.getSelectedItem().toString();

                Hostel hostel = new Hostel(
                        hostelId,
                        hostelName,
                        hostelType,
                        block
                );

                boolean success =
                        dao.updateHostel(hostel);

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Hostel updated successfully!"
                    );

                    loadHostels();
                    clearFields();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Hostel not found."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Hostel ID must be a number."
                );
            }
        });

        // DELETE
        btnDelete.addActionListener(e -> {

            try {

                int hostelId = Integer.parseInt(
                        txtHostelId.getText()
                );

                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this hostel?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {

                    boolean success =
                            dao.deleteHostel(hostelId);

                    if (success) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Hostel deleted successfully!"
                        );

                        loadHostels();
                        clearFields();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "Hostel not found."
                        );
                    }
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid Hostel ID."
                );
            }
        });

        // CLEAR
        btnClear.addActionListener(e -> clearFields());

        // Table Row Selection
        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && table.getSelectedRow() != -1) {

                int row = table.getSelectedRow();

                txtHostelId.setText(
                        model.getValueAt(row, 0).toString()
                );

                txtHostelName.setText(
                        model.getValueAt(row, 1).toString()
                );

                cmbHostelType.setSelectedItem(
                        model.getValueAt(row, 2).toString()
                );

                cmbBlock.setSelectedItem(
                        model.getValueAt(row, 3).toString()
                );
            }
        });
    }

    // Load hostel records into table
    private void loadHostels() {

        model.setRowCount(0);

        List<Hostel> hostels =
                dao.getAllHostels();

        for (Hostel h : hostels) {

            model.addRow(
                    new Object[] {
                            h.getHostelId(),
                            h.getHostelName(),
                            h.getHostelType(),
                            h.getBlock()
                    }
            );
        }
    }

    // Clear form
    private void clearFields() {

        txtHostelId.setText("");
        txtHostelName.setText("");

        cmbHostelType.setSelectedIndex(0);
        cmbBlock.setSelectedIndex(0);

        table.clearSelection();
    }

    // Main method
    public static void main(String[] args) {

        HostelFrame frame = new HostelFrame();

        frame.setVisible(true);
    }
}