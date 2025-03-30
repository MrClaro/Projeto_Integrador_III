package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;

public class StyledButton extends JButton {

    public StyledButton(String text) {
        super(text);
        initialize();
    }

    private void initialize() {
        setBackground(new Color(115, 83, 186));
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setPreferredSize(new Dimension(150, 40));
        setFocusPainted(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public void setCustomSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void setCustomFont(Font font) {
        setFont(font);
    }

    public void setCustomColors(Color background, Color foreground) {
        setBackground(background);
        setForeground(foreground);
    }
}