package com.lexlabor.views.navigation.actions;

import com.lexlabor.views.navigation.NavBarAction;
import com.lexlabor.views.navigation.NavigableView;

public class HomeAction implements NavBarAction {
    private final NavigableView view;

    public HomeAction(NavigableView view) {
        this.view = view;
    }

    @Override
    public void execute() {
        view.showHome();
    }
}