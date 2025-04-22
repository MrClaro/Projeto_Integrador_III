package com.lexlabor.views.init;

import com.lexlabor.utils.ValidationUtil;
import com.lexlabor.views.component.BrandingPane;
import com.lexlabor.views.component.StyledButton;
import com.lexlabor.views.main.HomeView;

import javax.swing.*;
import java.awt.*;


public class LoginView extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, signUpButton;

    public LoginView() {
        setTitle("LOGIN");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = createFormPanel();
        JPanel rightPanel = new BrandingPane();

        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.WEST);

        add(mainPanel);
    }

    // Cria o Painel de formulario
    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(400, 500));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setForeground(new Color(144, 0, 179));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel para os campos de entrada com GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField(20);

        // Adicionando elementos ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        loginButton = new StyledButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> validateAndLogin());


        // Painel de Sign Up
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        JLabel signUpLabel = new JLabel("Ainda não tem uma conta? ");
        signUpButton = new StyledButton("Registrar-se");
        signUpButton.setPreferredSize(new Dimension(130, 30));
        signUpButton.setForeground(Color.BLACK);
        signUpButton.addActionListener(e -> switchToSignUp());

        signUpPanel.add(signUpLabel);
        signUpPanel.add(signUpButton);

        // Adicionando elementos ao painel principal
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(formPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(signUpPanel);

        return panel;
    }

    private void validateAndLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor, preencha todos os campos!",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE
            );
        }  else {
            JOptionPane.showMessageDialog(this, "Bem-vindo devolta");
            redirectToHome();
        }
    }


    private void redirectToHome() {
        new HomeView().setVisible(true);
        dispose();
    }

    private void switchToSignUp() {
        new SignUpView().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
