package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavBar extends JPanel {

    public NavBar(ActionListener homeListener, ActionListener profileListener, ActionListener logoutListener) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(47, 25, 95));

        JButton homeButton = createStyledButton("Home");
        JButton profileButton = createStyledButton("Profile");
        JButton logoutButton = createStyledButton("Logout");

        homeButton.addActionListener(homeListener);
        profileButton.addActionListener(profileListener);
        logoutButton.addActionListener(logoutListener);

        add(homeButton);
        add(profileButton);
        add(logoutButton);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(115, 83, 186));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }
}
