package com.lexlabor.views.init;

import com.lexlabor.controllers.UsuarioController;
import com.lexlabor.exceptions.BusinessException;
import com.lexlabor.utils.ValidationUtil;
import com.lexlabor.views.component.BrandingPane;
import com.lexlabor.views.component.StyledButton;
import com.lexlabor.views.main.HomeView;

import javax.swing.*;
import java.awt.*;

public class SignUpView extends JFrame {

  private JTextField nameField, emailField;
  private JPasswordField passwordField;
  private JButton signUpButton, loginButton;

  public SignUpView() {
    setTitle("Sign Up");
    setSize(800, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    initComponents();
  }

  private void initComponents() {
    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel leftPanel = new BrandingPane();
    JPanel rightPanel = createFormPanel();

    mainPanel.add(leftPanel, BorderLayout.WEST);
    mainPanel.add(rightPanel, BorderLayout.CENTER);

    add(mainPanel);
  }

  // Cria o painel de SignUp
  private JPanel createFormPanel() {
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setPreferredSize(new Dimension(400, 500));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

    JLabel signUpLabel = new JLabel("REGISTRAR-SE");
    signUpLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
    signUpLabel.setForeground(new Color(144, 0, 179));
    signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(Color.WHITE);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    JLabel nameLabel = new JLabel("Nome Completo:");
    nameField = new JTextField(20);
    JLabel emailLabel = new JLabel("Email:");
    emailField = new JTextField(20);
    JLabel passwordLabel = new JLabel("Senha:");
    passwordField = new JPasswordField(20);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(nameLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(nameField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(emailLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(emailField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(passwordLabel, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;
    formPanel.add(passwordField, gbc);

    signUpButton = new StyledButton("Registrar");
    signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    signUpButton.addActionListener(e -> {
        try {
            validateAndRegister();
        } catch (BusinessException ex) {
            throw new RuntimeException(ex);
        }
    });

    JPanel loginPanel = new JPanel();
    loginPanel.setBackground(Color.WHITE);
    JLabel loginLabel = new JLabel("Já tem uma conta? ");
    loginButton = new StyledButton("Login");
    loginButton.setPreferredSize(new Dimension(100, 30));
    loginButton.setForeground(Color.BLACK);
    loginButton.addActionListener(e -> switchToLogin());
    loginPanel.add(loginLabel);
    loginPanel.add(loginButton);

    panel.add(signUpLabel);
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(formPanel);
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(signUpButton);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(loginPanel);

    return panel;
  }

  private void validateAndRegister() throws BusinessException {
    String fullName = nameField.getText().trim();
    String email = emailField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();
    if (email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
      JOptionPane.showMessageDialog(
          this,
          "Por favor, preencha todos os campos!",
          "Campos obrigatórios",
          JOptionPane.WARNING_MESSAGE);
    } else {
      UsuarioController usuarioController = new UsuarioController();
      usuarioController.cadastrarUsuario(fullName, email, password);

      JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
      redirectToHome();
    }
  }

  private void redirectToHome() {
    new HomeView().setVisible(true);
    dispose();
  }

  private void switchToLogin() {
    new LoginView().setVisible(true);
    dispose();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new SignUpView().setVisible(true));
  }
}
