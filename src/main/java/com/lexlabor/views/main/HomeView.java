package com.lexlabor.views.main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.lexlabor.views.component.BaseLayout;
import com.lexlabor.views.component.StyledButton;

public class HomeView extends JFrame {

   private JPanel contentPanel;
    private DefaultListModel<String> lawListModel;
    private JList<String> lawList;
    private List<Integer> selectedLaws = new ArrayList<>();

    public HomeView() {
        setTitle("Home");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JPanel searchPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.gridwidth = 2;
        contentPanel.add(searchPanel, gbc);

        JTextField searchField = new JTextField(60);
        JButton searchButton = createStyledButtonOptions("Buscar");
        searchButton.addActionListener(e -> performSearch(searchField.getText()));

        int buttonHeight = searchButton.getPreferredSize().height;
        searchField.setPreferredSize(new Dimension(searchField.getPreferredSize().width, buttonHeight));

        GridBagConstraints searchGbc = new GridBagConstraints();
        searchGbc.gridx = 0;
        searchGbc.gridy = 0;
        searchGbc.weightx = 1.0;
        searchGbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchField, searchGbc);

        searchGbc.gridx = 1;
        searchGbc.weightx = 0;
        searchGbc.insets = new Insets(0, 5, 0, 0);
        searchPanel.add(searchButton, searchGbc);

        JPanel lawDataPanel = new JPanel(new BorderLayout());
        lawDataPanel.setBorder(BorderFactory.createTitledBorder("Leis Recentes"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        gbc.gridwidth = 2;
        contentPanel.add(lawDataPanel, gbc);

        lawListModel = new DefaultListModel<>();
        lawList = new JList<>(lawListModel);
        lawList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(lawList);
        lawDataPanel.add(listScrollPane, BorderLayout.CENTER);

        lawListModel.addElement("2023-10-26 - Lei 1234 - Código Tributário");
        lawListModel.addElement("2023-10-25 - Lei 5678 - Código Penal");
        lawListModel.addElement("2023-10-24 - Lei 9012 - Lei do Consumidor");

        lawList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedLaw = lawList.getSelectedValue();
                    showLawDetails(selectedLaw);
                }
            }
        });

        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.gridwidth = 2;
        contentPanel.add(actionsPanel, gbc);

        JButton saveButton = createStyledButtonForHome("Salvar Leis");
        saveButton.addActionListener(e -> saveSelectedLaws());
        JButton filterButton = createStyledButtonForHome("Adicionar Filtro");
        filterButton.addActionListener(e -> showFilterDialog());
        JButton exportButton = createStyledButtonForHome("Exportar");
        exportButton.addActionListener(e -> showExportDialog());

        actionsPanel.add(saveButton);
        actionsPanel.add(filterButton);
        actionsPanel.add(exportButton);
    }


    // Cria o modal de detalhes da lei
    private void showLawDetails(String lawTitle) {
        JDialog lawDialog = new JDialog(this, lawTitle, true);
        lawDialog.setSize(600, 400);
        lawDialog.setLocationRelativeTo(this);
        lawDialog.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
        JLabel titleLabel = new JLabel(lawTitle);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerPanel.add(titleLabel);

        JTextArea lawTextArea = new JTextArea("Conteúdo da " + lawTitle);
        lawTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(lawTextArea);

        JButton closeButton = createStyledButtonOptions("Fechar");
        closeButton.addActionListener(e -> lawDialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);

        lawDialog.add(headerPanel, BorderLayout.NORTH);
        lawDialog.add(scrollPane, BorderLayout.CENTER);
        lawDialog.add(buttonPanel, BorderLayout.SOUTH);
        lawDialog.setVisible(true);
    }


    private JButton createStyledButtonOptions(String text) {
        StyledButton button = new StyledButton(text);
        button.setCustomSize(120, 40);
        return button;
    }

    private JButton createStyledButtonForHome(String text) {
        StyledButton button = new StyledButton(text);
        button.setCustomSize(150, 40);
        return button;
    }

    private void showHome() {
        JOptionPane.showMessageDialog(this, "Página Inicial");
    }

     private void showProfile() {
        ProfileView profileView = new ProfileView();
        profileView.setVisible(true);
    }

   private void logout() {
        JOptionPane.showMessageDialog(this, "Você foi desconectado!");
        dispose();
    }

    private void performSearch(String searchText) {
        JOptionPane.showMessageDialog(this, "Buscando por: " + searchText);
    }

    private void saveSelectedLaws() {
        int[] selectedIndices = lawList.getSelectedIndices();
        selectedLaws.clear();

        if (selectedIndices.length == 0) {
            JOptionPane.showMessageDialog(this, "Nenhuma lei selecionada.");
        } else {
            for (int index : selectedIndices) {
                selectedLaws.add(index);
            }
            JOptionPane.showMessageDialog(this, "Leis selecionadas salvas.");
        }
    }

    
    private void showFilterDialog() {
        JDialog filterDialog = new JDialog(this, "Filtros", true);
        filterDialog.setSize(400, 300);
        filterDialog.setLocationRelativeTo(this);
        filterDialog.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        JCheckBox filter1 = new JCheckBox("Filtro 1");
        JCheckBox filter2 = new JCheckBox("Filtro 2");
        JCheckBox filter3 = new JCheckBox("Filtro 3");

        filterPanel.add(filter1, gbc);
        gbc.gridy++;
        filterPanel.add(filter2, gbc);
        gbc.gridy++;
        filterPanel.add(filter3, gbc);

        gbc.gridy++;
        JButton createFilterButton = createStyledButtonOptions("Criar Filtro");
        createFilterButton.addActionListener(e -> showCreateFilterDialog());
        filterPanel.add(createFilterButton, gbc);

        JButton applyButton = createStyledButtonOptions("Aplicar");
        applyButton.addActionListener(e -> filterDialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(applyButton);

        filterDialog.add(filterPanel, BorderLayout.CENTER);
        filterDialog.add(buttonPanel, BorderLayout.SOUTH);
        filterDialog.setVisible(true);
    }

    private void showCreateFilterDialog() {
        JDialog createFilterDialog = new JDialog(this, "Criar Filtro", true);
        createFilterDialog.setSize(300, 200);
        createFilterDialog.setLocationRelativeTo(this);
        createFilterDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        JLabel filterNameLabel = new JLabel("Nome do Filtro:");
        JTextField filterNameField = new JTextField(20);

        createFilterDialog.add(filterNameLabel, gbc);
        gbc.gridy++;
        createFilterDialog.add(filterNameField, gbc);

        gbc.gridy++;
        JButton createButton = createStyledButtonOptions("Criar");
        createButton.addActionListener(e -> {
            String filterName = filterNameField.getText();
            if (!filterName.isEmpty()) {
                // TODO : Adicionar lógica para os filtros
                JOptionPane.showMessageDialog(createFilterDialog, "Filtro '" + filterName + "' criado.");
                createFilterDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(createFilterDialog, "Por favor, insira um nome para o filtro.");
            }
        });
        createFilterDialog.add(createButton, gbc);

        createFilterDialog.setVisible(true);
    }

    private void showExportDialog() {
        JDialog exportDialog = new JDialog(this, "Exportar", true);
        exportDialog.setSize(400, 300);
        exportDialog.setLocationRelativeTo(this);
        exportDialog.setLayout(new BorderLayout());

        JPanel exportPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        JTextField fileNameField = new JTextField("nome_do_arquivo");
        exportPanel.add(fileNameField, gbc);

        gbc.gridy++;
        JComboBox<String> fileFormatComboBox = new JComboBox<>(new String[]{"PDF", "TXT", "CSV"});
        fileFormatComboBox.setPreferredSize(new Dimension(150, 30));
        exportPanel.add(fileFormatComboBox, gbc);

        gbc.gridy++;
        JButton selectFolderButton = createStyledButtonOptions("Selecionar Pasta");
        selectFolderButton.setPreferredSize(new Dimension(150, 30));
        exportPanel.add(selectFolderButton, gbc);

        gbc.gridy++;
        JLabel selectedFolderLabel = new JLabel("Pasta selecionada: ");
        exportPanel.add(selectedFolderLabel, gbc);

        selectFolderButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(exportDialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedFolderLabel.setText("Pasta selecionada: " + selectedFile.getAbsolutePath());
            }
    });

    JButton exportButton = createStyledButtonOptions("Exportar");
    // TODO: Adicionar lógica para exportar os arquivos
    exportButton.addActionListener(e -> exportDialog.dispose());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(exportButton);

    exportDialog.add(exportPanel, BorderLayout.CENTER);
    exportDialog.add(buttonPanel, BorderLayout.SOUTH);
    exportDialog.setVisible(true);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeView().setVisible(true));
    }
}
