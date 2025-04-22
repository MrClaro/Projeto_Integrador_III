package com.lexlabor.views.component;

import javax.swing.*;
import java.awt.*;

import com.lexlabor.views.navigation.NavBarAction;

public class BaseLayout extends JPanel {
    private JPanel contentPanel;

    public BaseLayout(NavBarAction homeAction, NavBarAction profileAction,
                      NavBarAction usersAction, NavBarAction logoutAction) {
        setLayout(new BorderLayout());

        NavBar navBar = new NavBar(homeAction, profileAction, usersAction, logoutAction);
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(new Color(240, 240, 240));

        add(navBar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
