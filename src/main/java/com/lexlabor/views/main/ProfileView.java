package com.lexlabor.views.main;

import com.lexlabor.views.component.BaseLayout;
import com.lexlabor.views.component.StyledButton;
import com.lexlabor.views.navigation.NavBarAction;
import com.lexlabor.views.navigation.NavigableView;
import com.lexlabor.views.navigation.actions.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProfileView extends JFrame implements NavigableView {
    private JPanel contentPanel;

    public ProfileView() {
        setTitle("Perfil do Usuário");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        NavBarAction homeAction = new HomeAction(this);
        NavBarAction profileAction = new ProfileAction(this);
        NavBarAction usersAction = new UsersAction(this);
        NavBarAction logoutAction = new LogoutAction(this);

        BaseLayout baseLayout = new BaseLayout(homeAction, profileAction, usersAction, logoutAction);
        contentPanel = baseLayout.getContentPanel();
        setupLayout();
        add(baseLayout);
    }

    private void setupLayout() {
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Painel de Dados do Usuário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        contentPanel.add(createUserDataPanel(), gbc);

        // Painel de Alterar Senha
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        contentPanel.add(createPasswordPanel(), gbc);

        // Painel de Leis Salvas
        gbc.gridy = 2;
        gbc.weighty = 0.4;
        contentPanel.add(createSavedLawsPanel(), gbc);
    }

    // =============================
    // Painéis de Dados do Usuário
    // ==============================
    private JPanel createUserDataPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dados do Usuário"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(20);
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton saveButton = createStyledButton("Salvar Dados");
        saveButton.addActionListener(e -> saveUserData(nameField.getText(), emailField.getText()));
        panel.add(saveButton, gbc);

        return panel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Alterar Senha"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel oldPasswordLabel = new JLabel("Senha Antiga:");
        JPasswordField oldPasswordField = new JPasswordField(20);
        panel.add(oldPasswordLabel, gbc);
        gbc.gridx = 1;
        panel.add(oldPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel newPasswordLabel = new JLabel("Nova Senha:");
        JPasswordField newPasswordField = new JPasswordField(20);
        panel.add(newPasswordLabel, gbc);
        gbc.gridx = 1;
        panel.add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton changePasswordButton = createStyledButton("Alterar Senha");
        changePasswordButton.addActionListener(e ->
                changePassword(oldPasswordField.getPassword(), newPasswordField.getPassword()));
        panel.add(changePasswordButton, gbc);

        return panel;
    }

    private JPanel createSavedLawsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Leis Salvas"));

        DefaultListModel<String> savedLawsListModel = new DefaultListModel<>();
        JList<String> savedLawsList = new JList<>(savedLawsListModel);
        JScrollPane listScrollPane = new JScrollPane(savedLawsList);
        panel.add(listScrollPane, BorderLayout.CENTER);

        // Simulação de leis salvas
        savedLawsListModel.addElement("2023-10-20 - Lei 1001 - Lei Ambiental");
        savedLawsListModel.addElement("2023-10-18 - Lei 1002 - Lei do Inquilinato");
        savedLawsListModel.addElement("2023-10-15 - Lei 1003 - Lei de Crimes Hediondos");

        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton sortByNameButton = createStyledButton("Ordenar por Nome");
        sortByNameButton.addActionListener(e -> sortLawsByName());
        sortPanel.add(sortByNameButton);

        JButton sortByDateButton = createStyledButton("Ordenar por Data");
        sortByDateButton.addActionListener(e -> sortLawsByDate());
        sortPanel.add(sortByDateButton);

        panel.add(sortPanel, BorderLayout.SOUTH);
        return panel;
    }
    // =============================
    // Fim dos Painéis de Dados do Usuário
    // ==============================

    // ==================================
    // Métodos de Lógica
    // ==================================
    private void saveUserData(String name, String email) {
        // TODO: Implementar lógica para salvar os dados do usuário
        JOptionPane.showMessageDialog(this, "Dados do usuário salvos.");
    }

    private void changePassword(char[] oldPassword, char[] newPassword) {
        // TODO: Implementar validação da senha antiga
        if (Arrays.equals(oldPassword, "1234".toCharArray())) { // Exemplo simples
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Senha antiga incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortLawsByName() {
        // TODO: Implementar lógica para ordenar as leis por nome
        JOptionPane.showMessageDialog(this, "Leis ordenadas por nome.");
    }

    private void sortLawsByDate() {
        // TODO: Implementar lógica para ordenar as leis por data
        JOptionPane.showMessageDialog(this, "Leis ordenadas por data.");
    }

    // =============================
    // Implementação de Navegação
    // ==============================
    public void showHome() {
        HomeView homeView = new HomeView();
        homeView.setVisible(true);
        dispose();
    }

    public void showProfile() {
    }

    public void showUsers() {
        UsersView usersView = new UsersView();
        usersView.setVisible(true);
        dispose();
    }

    public void logout() {
        JOptionPane.showMessageDialog(this, "Você foi desconectado!");
        dispose();
    }
    // ==============================
    // Fim da navegação
    // ==============================

    private JButton createStyledButton(String text) {
        StyledButton button = new StyledButton(text);
        button.setCustomSize(180, 40);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfileView().setVisible(true));
    }
}