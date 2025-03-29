package com.lexlabor.views.main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.lexlabor.views.component.BaseLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProfileView extends JFrame {

    private JPanel contentPanel;
    private DefaultListModel<String> savedLawsListModel;
    private JList<String> savedLawsList;

    public ProfileView() {
        setTitle("Perfil do Usuário");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {
        BaseLayout baseLayout = new BaseLayout(e -> showHome(), e -> showProfile(), e -> logout());
        contentPanel = baseLayout.getContentPanel();
        setupLayout();
        add(baseLayout);
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Painel de Dados do Usuário
        JPanel userDataPanel = new JPanel(new GridBagLayout());
        userDataPanel.setBorder(BorderFactory.createTitledBorder("Dados do Usuário"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        contentPanel.add(userDataPanel, gbc);

        GridBagConstraints userDataGbc = new GridBagConstraints();
        userDataGbc.insets = new Insets(5, 5, 5, 5);
        userDataGbc.fill = GridBagConstraints.HORIZONTAL;
        userDataGbc.gridx = 0;
        userDataGbc.gridy = 0;

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField(20);
        userDataPanel.add(nameLabel, userDataGbc);
        userDataGbc.gridx = 1;
        userDataPanel.add(nameField, userDataGbc);

        userDataGbc.gridx = 0;
        userDataGbc.gridy++;
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        userDataPanel.add(emailLabel, userDataGbc);
        userDataGbc.gridx = 1;
        userDataPanel.add(emailField, userDataGbc);

        userDataGbc.gridx = 0;
        userDataGbc.gridy++;
        JButton saveButton = createStyledButtonForHome("Salvar Dados");
        saveButton.addActionListener(e -> saveUserData(nameField.getText(), emailField.getText()));
        userDataPanel.add(saveButton, userDataGbc);

        // Painel de Alterar Senha
        JPanel changePasswordPanel = new JPanel(new GridBagLayout());
        changePasswordPanel.setBorder(BorderFactory.createTitledBorder("Alterar Senha"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        contentPanel.add(changePasswordPanel, gbc);

        GridBagConstraints changePasswordGbc = new GridBagConstraints();
        changePasswordGbc.insets = new Insets(5, 5, 5, 5);
        changePasswordGbc.fill = GridBagConstraints.HORIZONTAL;
        changePasswordGbc.gridx = 0;
        changePasswordGbc.gridy = 0;

        JLabel oldPasswordLabel = new JLabel("Senha Antiga:");
        JPasswordField oldPasswordField = new JPasswordField(20);
        changePasswordPanel.add(oldPasswordLabel, changePasswordGbc);
        changePasswordGbc.gridx = 1;
        changePasswordPanel.add(oldPasswordField, changePasswordGbc);

        changePasswordGbc.gridx = 0;
        changePasswordGbc.gridy++;
        JLabel newPasswordLabel = new JLabel("Nova Senha:");
        JPasswordField newPasswordField = new JPasswordField(20);
        changePasswordPanel.add(newPasswordLabel, changePasswordGbc);
        changePasswordGbc.gridx = 1;
        changePasswordPanel.add(newPasswordField, changePasswordGbc);

        changePasswordGbc.gridx = 0;
        changePasswordGbc.gridy++;
        JButton changePasswordButton = createStyledButtonForHome("Alterar Senha");
        changePasswordButton.addActionListener(e -> changePassword(oldPasswordField.getPassword(), newPasswordField.getPassword()));
        changePasswordPanel.add(changePasswordButton, changePasswordGbc);

        // Painel de Leis Salvas
        JPanel savedLawsPanel = new JPanel(new BorderLayout());
        savedLawsPanel.setBorder(BorderFactory.createTitledBorder("Leis Salvas"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        contentPanel.add(savedLawsPanel, gbc);

        savedLawsListModel = new DefaultListModel<>();
        savedLawsList = new JList<>(savedLawsListModel);
        JScrollPane listScrollPane = new JScrollPane(savedLawsList);
        savedLawsPanel.add(listScrollPane, BorderLayout.CENTER);

        // Simulação de leis salvas
        savedLawsListModel.addElement("2023-10-20 - Lei 1001 - Lei Ambiental");
        savedLawsListModel.addElement("2023-10-18 - Lei 1002 - Lei do Inquilinato");
        savedLawsListModel.addElement("2023-10-15 - Lei 1003 - Lei de Crimes Hediondos");

        // Painel de Ordenação
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        savedLawsPanel.add(sortPanel, BorderLayout.SOUTH);

        JButton sortByNameButton = createStyledButtonForHome("Ordenar por Nome");
        sortByNameButton.addActionListener(e -> sortLawsByName());
        sortPanel.add(sortByNameButton);

        JButton sortByDateButton = createStyledButtonForHome("Ordenar por Data");
        sortByDateButton.addActionListener(e -> sortLawsByDate());
        sortPanel.add(sortByDateButton);
    }


    private void saveUserData(String name, String email) {
        // TODO: Implementar lógica para salvar os dados do usuário
        JOptionPane.showMessageDialog(this, "Dados do usuário salvos.");
    }

    private void changePassword(char[] oldPassword, char[] newPassword) {
        // TODO: Implementar lógica para alterar a senha
        JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.");
    }

    private void sortLawsByName() {
        // TODO: Implementar lógica para ordenar as leis por nome
        JOptionPane.showMessageDialog(this, "Leis ordenadas por nome.");
    }

    private void sortLawsByDate() {
        // TODO: Implementar lógica para ordenar as leis por data
        JOptionPane.showMessageDialog(this, "Leis ordenadas por data.");
    }

        // Cria os botões para o painel de Home 
    private JButton createStyledButtonForHome(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(115, 83, 186));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 40));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return button;
    }

     private void showHome() {
        HomeView homeView = new HomeView();
        homeView.setVisible(true);
    }

    private void showProfile() {
        JOptionPane.showMessageDialog(this, "Perfil do Usuário");
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Você foi desconectado!");
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfileView().setVisible(true));
    }
}
