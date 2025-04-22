package com.lexlabor.views.navigation.actions;

import com.lexlabor.views.navigation.NavBarAction;
import com.lexlabor.views.navigation.NavigableView;

public class ProfileAction implements NavBarAction {
    private final NavigableView view;

    public ProfileAction(NavigableView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.showProfile();
    }
}
