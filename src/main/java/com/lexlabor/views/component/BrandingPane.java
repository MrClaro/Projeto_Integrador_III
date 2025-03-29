package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

public class BrandingPane extends JPanel {
    public BrandingPane() {
        setBackground(new Color(179, 194, 242));
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel logoLabel = new JLabel(new ImageIcon("Logo"));
        add(logoLabel, gbc);

        gbc.gridy = 1;
        JLabel titleLabel = new JLabel("LexLabor");
        titleLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, gbc);

        gbc.gridy = 2;
        JLabel copyright = new JLabel("© LexLabor All rights reserved");
        copyright.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        copyright.setForeground(new Color(126, 0, 123));
        add(copyright, gbc);
    }
}

