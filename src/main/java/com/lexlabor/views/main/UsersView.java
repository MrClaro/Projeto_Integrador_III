package com.lexlabor.views.main;

import com.lexlabor.controllers.UsuarioController;
import com.lexlabor.entities.Usuario;
import com.lexlabor.exceptions.BusinessException;
import com.lexlabor.views.component.BaseLayout;
import com.lexlabor.views.component.StyledButton;
import com.lexlabor.views.navigation.NavBarAction;
import com.lexlabor.views.navigation.NavigableView;
import com.lexlabor.views.navigation.actions.HomeAction;
import com.lexlabor.views.navigation.actions.LogoutAction;
import com.lexlabor.views.navigation.actions.ProfileAction;
import com.lexlabor.views.navigation.actions.UsersAction;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UsersView extends JFrame implements NavigableView {
    private final UsuarioController usuarioController;
    private JPanel contentPanel;
    private DefaultListModel<String> usersListModel;
    private JList<String> usersList;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField passwordField;

    public UsersView() {
        this.usuarioController = new UsuarioController();
        setTitle("Gerenciar Usuários");
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

        // Painel de Formulário
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        contentPanel.add(createUserFormPanel(), gbc);

        // Painel de Lista de Usuários
        gbc.gridy = 1;
        gbc.weighty = 0.7;
        contentPanel.add(createUsersListPanel(), gbc);
    }

    // ==================================
    // Painéis de Formulário
    // ==================================
    private JPanel createUserFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gerenciar Usuário"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Campos do formulário
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JTextField(20);

        panel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;

        JButton saveButton = createStyledButton("Salvar");
        saveButton.addActionListener(this::handleSave);

        JButton clearButton = createStyledButton("Limpar");
        clearButton.addActionListener(e -> clearForm());

        JButton editButton = createStyledButton("Editar");
        editButton.addActionListener(this::handleEdit);

        JButton deleteButton = createStyledButton("Deletar");
        deleteButton.addActionListener(this::handleDelete);

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel createUsersListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));

        usersListModel = new DefaultListModel<>();
        usersList = new JList<>(usersListModel);
        usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(usersList);
        panel.add(scrollPane, BorderLayout.CENTER);
        refreshUsersList();
        return panel;
    }
    // ==================================
    // Fim dos Painéis de Formulário
    // ==================================

    // ==================================
    // Métodos de Manipulação de Usuários
    // ==================================
    private void refreshUsersList() {
        usersListModel.clear();
        usuarioController.listarUsuarios().forEach(usuario -> {
            usersListModel.addElement(usuario.getNome() + " - " + usuario.getEmail());
        });
    }

    private void handleSave(ActionEvent e) {
        String newName = nameField.getText().trim();
        String newEmail = emailField.getText().trim();
        String newPassword = passwordField.getText().trim();

        try {
            usuarioController.cadastrarUsuario(
                    newName,
                    newEmail,
                    newPassword
            );

            refreshUsersList();
            clearForm();

            JOptionPane.showMessageDialog(this,
                    "Usuário cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleEdit(ActionEvent e) {
        int selectedIndex = usersList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um usuário para editar",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        JDialog editDialog = new JDialog(this, "Editar Usuário", true);
        editDialog.setLayout(new BorderLayout());
        editDialog.setSize(400, 220);
        editDialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField editNameField = new JTextField();
        JTextField editEmailField = new JTextField();
        JPasswordField editPasswordField = new JPasswordField();

        String selectedUser = usersListModel.getElementAt(selectedIndex);
        String[] parts = selectedUser.split(" - ");
        editNameField.setText(parts[0]);
        editEmailField.setText(parts[1]);

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        fieldsPanel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        editNameField.setPreferredSize(new Dimension(250, 25));
        fieldsPanel.add(editNameField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        fieldsPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        editEmailField.setPreferredSize(new Dimension(250, 25));
        fieldsPanel.add(editEmailField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        fieldsPanel.add(new JLabel("Nova Senha (Opcional):"), gbc);
        gbc.gridx = 1;
        editPasswordField.setPreferredSize(new Dimension(250, 25));
        fieldsPanel.add(editPasswordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton cancelButton = createStyledButton("Cancelar");
        cancelButton.addActionListener(ev -> editDialog.dispose());

        JButton confirmButton = createStyledButton("Confirmar Edição");
        confirmButton.addActionListener(confirmEvent -> {
            try {
                String newName = editNameField.getText().trim();
                String newEmail = editEmailField.getText().trim();
                String newPassword = new String(editPasswordField.getPassword()).trim();

                Long userId = getIdFromSelectedUser(selectedIndex);
                Usuario usuarioAtualizado = new Usuario();
                usuarioAtualizado.setNome(newName);
                usuarioAtualizado.setEmail(newEmail);

                if (!newPassword.isEmpty()) {
                    usuarioAtualizado.setSenha(newPassword);
                }

                usuarioController.atualizarUsuario(userId, usuarioAtualizado);
                refreshUsersList();

                JOptionPane.showMessageDialog(editDialog,
                        "Usuário atualizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                editDialog.dispose();

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(editDialog,
                        ex.getMessage(),
                        "Erro na Atualização",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(confirmButton);

        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.add(panel);

        editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        editDialog.setVisible(true);
    }

    private Long getIdFromSelectedUser(int selectedIndex) {
        List<Usuario> usuarios = usuarioController.listarUsuarios();
        if (selectedIndex >= 0 && selectedIndex < usuarios.size()) {
            return usuarios.get(selectedIndex).getId();
        }
        throw new RuntimeException("Índice inválido");
    }

    private void handleDelete(ActionEvent e) {
        int selectedIndex = usersList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja deletar este usuário?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Usuario usuario = usuarioController.listarUsuarios().get(selectedIndex);
                    usuarioController.removerUsuario(usuario.getId());
                    refreshUsersList();
                    JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
                } catch (BusinessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para deletar",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearForm() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
    // ==================================
    // Fim dos Métodos de Manipulação de Usuários
    // ==================================

    // ==================================
    // Implementação de Navegação
    // ==================================
    public void showHome() {
        HomeView homeView = new HomeView();
        homeView.setVisible(true);
        dispose();
    }

    public void showProfile() {
        ProfileView profileView = new ProfileView();
        profileView.setVisible(true);
        dispose();
    }

    public void showUsers() {
    }

    public void logout() {
        JOptionPane.showMessageDialog(this, "Você foi desconectado!");
        dispose();
    }
    // ==================================
    // Fim da navegação
    // ==================================

    // ==============================
    // Métodos de Estilo
    // ==============================
    private JButton createStyledButton(String text) {
        StyledButton button = new StyledButton(text);
        button.setCustomSize(180, 40);
        return button;
    }
    // ==============================
    // Fim dos Métodos de Estilo
    // ==============================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfileView().setVisible(true));
    }
}
