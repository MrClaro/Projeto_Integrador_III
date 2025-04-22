package com.lexlabor.views.navigation.actions;

import com.lexlabor.views.navigation.NavBarAction;
import com.lexlabor.views.navigation.NavigableView;

public class LogoutAction implements NavBarAction {
    private final NavigableView view;

    public LogoutAction(NavigableView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.logout();
    }
}
