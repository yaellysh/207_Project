package interface_adapter.login;

import interface_adapter.menu.MenuViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    public void returnToMenu(MenuViewModel menuViewModel) {
        loginUseCaseInteractor.returnToMenu(menuViewModel);
    }
}
