package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Hostel Room Allocation System");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        JLabel title = new JLabel(
                "HOSTEL ROOM ALLOCATION SYSTEM",
                JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        // Buttons
        JButton studentButton =
                new JButton("STUDENT MANAGEMENT");

        JButton hostelButton =
                new JButton("HOSTEL MANAGEMENT");

        JButton roomButton =
                new JButton("ROOM MANAGEMENT");

        JButton allocationButton =
                new JButton("ROOM ALLOCATION");

        JButton exitButton =
                new JButton("EXIT");

        // Button panel
        JPanel panel = new JPanel(
                new GridLayout(5, 1, 15, 15)
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 80, 30, 80
                )
        );

        panel.add(studentButton);
        panel.add(hostelButton);
        panel.add(roomButton);
        panel.add(allocationButton);
        panel.add(exitButton);

        // Add components
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        // Button actions
        studentButton.addActionListener(e -> {
            new StudentFrame().setVisible(true);
        });

        hostelButton.addActionListener(e -> {
            new HostelFrame().setVisible(true);
        });

        roomButton.addActionListener(e -> {
            new RoomFrame().setVisible(true);
        });

        allocationButton.addActionListener(e -> {
            new AllocationFrame().setVisible(true);
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {

        new MainFrame().setVisible(true);
    }
}