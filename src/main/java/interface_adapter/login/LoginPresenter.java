package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.selector.SelectorState;
import interface_adapter.selector.SelectorViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final SelectorViewModel selectorViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.selectorViewModel = selectorViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        SelectorState state = selectorViewModel.getState();
        state.setUser(response.getUser());
        this.selectorViewModel.setState(state);
        this.selectorViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(selectorViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);

        loginViewModel.firePropertyChanged();
    }

    @Override
    public void returnToMenu(MenuViewModel menuViewModel) {
        this.viewManagerModel.setActiveView(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
