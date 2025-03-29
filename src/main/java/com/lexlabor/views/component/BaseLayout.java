package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BaseLayout extends JPanel {

    private JPanel contentPanel;

    public BaseLayout(ActionListener homeListener, ActionListener profileListener, ActionListener logoutListener) {
        setLayout(new BorderLayout());

        NavBar navBar = new NavBar(homeListener, profileListener, logoutListener);
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(240, 240, 240));

        add(navBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
