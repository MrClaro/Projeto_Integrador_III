package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import com.lexlabor.views.utils.ChangeImageColorUtil;

import com.lexlabor.views.navigation.NavBarAction;

public class NavBar extends JPanel {
    public NavBar(NavBarAction homeAction, NavBarAction profileAction,
                  NavBarAction usersAction, NavBarAction logoutAction) {
        setLayout(new BorderLayout());
        setBackground(new Color(47, 25, 95));
        setPreferredSize(new Dimension(800, 80));

        JPanel logoPanel = createLogoPanel();
        JPanel buttonPanel = createButtonPanel(homeAction, profileAction, usersAction, logoutAction);

        add(logoPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.EAST);
    }

    private JPanel createLogoPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(47, 25, 95));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel logoLabel = new JLabel(loadLogo());
        panel.add(Box.createVerticalGlue());
        panel.add(logoLabel);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createButtonPanel(NavBarAction homeAction, NavBarAction profileAction,
                                     NavBarAction usersAction, NavBarAction logoutAction) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panel.setBackground(new Color(47, 25, 95));

        StyledButton homeButton = new StyledButton("Inicio");
        StyledButton profileButton = new StyledButton("Perfil");
        StyledButton usersButton = new StyledButton("Usuários");
        StyledButton logoutButton = new StyledButton("Sair");

        homeButton.addActionListener(e -> homeAction.execute());
        profileButton.addActionListener(e -> profileAction.execute());
        usersButton.addActionListener(e -> usersAction.execute());
        logoutButton.addActionListener(e -> logoutAction.execute());

        panel.add(homeButton);
        panel.add(profileButton);
        panel.add(usersButton);
        panel.add(logoutButton);

        return panel;
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