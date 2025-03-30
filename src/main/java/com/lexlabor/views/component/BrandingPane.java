package com.lexlabor.views.component;

import com.lexlabor.views.utils.ChangeImageColorUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BrandingPane extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(47, 25, 95);

    public BrandingPane() {
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Carregar a imagem do logo e transformar em branco
        ImageIcon logoIcon = null;
        try {
            BufferedImage logoImage = ImageIO.read(
                    Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Logo-Lex.png"))
            );

            BufferedImage whiteLogo = ChangeImageColorUtil.convertToWhite(logoImage);
            Image scaledImage = whiteLogo.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(scaledImage);
        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao carregar a imagem Logo-Lex.png: " + e.getMessage());
            logoIcon = new ImageIcon();
        }

        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel, gbc);

        // Adicionar tag de Copyright
        gbc.gridy = 1;
        JLabel copyright = new JLabel("© LexLabor Todos Direitos Reservados");
        copyright.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        copyright.setForeground(new Color(150, 150, 150));
        add(copyright, gbc);
    }
}