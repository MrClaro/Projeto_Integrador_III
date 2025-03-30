package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import com.lexlabor.views.utils.ChangeImageColorUtil;

public class NavBar extends JPanel {

    public NavBar(ActionListener homeListener, ActionListener profileListener, ActionListener logoutListener) {
        setLayout(new BorderLayout());
        setBackground(new Color(47, 25, 95));
        setPreferredSize(new Dimension(800, 80));

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(47, 25, 95));
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.Y_AXIS));

        JLabel logoLabel = new JLabel(loadLogo());
        logoPanel.add(Box.createVerticalGlue());
        logoPanel.add(logoLabel);
        logoPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        buttonPanel.setBackground(new Color(47, 25, 95));

        StyledButton homeButton = new StyledButton("Home");
        StyledButton profileButton = new StyledButton("Profile");
        StyledButton logoutButton = new StyledButton("Logout");

        homeButton.addActionListener(homeListener);
        profileButton.addActionListener(profileListener);
        logoutButton.addActionListener(logoutListener);

        buttonPanel.add(homeButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);

        add(logoPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
    }

    private ImageIcon loadLogo() {
        try {
            BufferedImage logoImage = ImageIO.read(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Logo-Lex.png"))
            );

            BufferedImage whiteLogo = ChangeImageColorUtil.convertToWhite(logoImage);
            Image scaledImage = whiteLogo.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao carregar a imagem Logo-Lex.png: " + e.getMessage());
            return new ImageIcon();
        }
    }
}