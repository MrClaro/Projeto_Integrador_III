package com.lexlabor.views.component;

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

            BufferedImage whiteLogo = convertToWhite(logoImage);
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
        JLabel copyright = new JLabel("© LexLabor All rights reserved");
        copyright.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        // TODO: Alterar a cor para um tom mais claro
        copyright.setForeground(new Color(126, 0, 123));
        add(copyright, gbc);
    }

    /**
     * Converte o logo colorido para uma versão branca mantendo a transparência.
     */
    private BufferedImage convertToWhite(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                // Mantém a transparência do PNG
                int alpha = (pixel >> 24) & 0xFF;

                // Define a cor branca (R=255, G=255, B=255)
                int whitePixel = (alpha << 24) | (255 << 16) | (255 << 8) | 255;
                newImage.setRGB(x, y, whitePixel);
            }
        }
        return newImage;
    }
}

