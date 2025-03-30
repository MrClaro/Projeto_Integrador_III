package com.lexlabor;

import com.lexlabor.views.init.LoginView;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    LoginView LoginFrame = new LoginView();
    LoginFrame.setVisible(true);
    LoginFrame.pack();
    LoginFrame.setLocationRelativeTo(null);
  }
}
