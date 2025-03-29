package com.lexlabor.views.init;

import com.lexlabor.views.component.BrandingPane;
import com.lexlabor.views.main.HomeView;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginView extends JFrame {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

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
        JLabel passwordLabel = new JLabel("Password:");
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

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(179, 194, 242));
        loginButton.setForeground(Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> validateAndLogin());

        // Painel de Sign Up
        JPanel signUpPanel = new JPanel();
        signUpPanel.setBackground(Color.WHITE);
        JLabel signUpLabel = new JLabel("I don't have an account");
        signUpButton = new JButton("Sign Up");
        signUpButton.setForeground(Color.RED);
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
    // TODO: Implement the switch to HomePage view
    /*
    private void handleSignUp() {
    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");

    HomeView homeView = new HomeView();
    homeView.setVisible(true);

    // Fecha a tela de cadastro
    dispose();
     }
    */
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
        } else  if(!validateEmail(email)){
            JOptionPane.showMessageDialog(this, "Email inválido!");
        } else {
            redirectToHome();
        }
    }

    public static boolean validateEmail(String email){
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void redirectToHome() {
        JOptionPane.showMessageDialog(this, "Bem-vindo devolta");
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
