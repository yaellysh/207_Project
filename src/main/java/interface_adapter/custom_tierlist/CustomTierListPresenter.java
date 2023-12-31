package interface_adapter.custom_tierlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.selector.SelectorViewModel;
import interface_adapter.tierlist.TierListState;
import interface_adapter.tierlist.TierListViewModel;
import use_case.custom_tierlist.CustomTierListOutputBoundary;
import use_case.custom_tierlist.CustomTierListOutputData;

public class CustomTierListPresenter implements CustomTierListOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final CustomTierListViewModel customTierListViewModel;
    private final TierListViewModel tierListViewModel;
    private final SelectorViewModel selectorViewModel;

    public CustomTierListPresenter(ViewManagerModel viewManagerModel, CustomTierListViewModel customTierListViewModel, TierListViewModel tierListViewModel, SelectorViewModel selectorViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.customTierListViewModel = customTierListViewModel;
        this.tierListViewModel = tierListViewModel;
        this.selectorViewModel = selectorViewModel;

    }

    @Override
    public void prepareSuccessView(CustomTierListOutputData data) {
        CustomTierListState state = customTierListViewModel.getState();
        state.setError(null);
        customTierListViewModel.setState(state);

        TierListState tierListState = tierListViewModel.getState();
        tierListState.setTierList(data.getTierList());
        tierListState.setUser(data.getUser());
        tierListViewModel.setState(tierListState);
        tierListViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(tierListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CustomTierListState customTierListState = customTierListViewModel.getState();
        customTierListState.setError(error);

        customTierListViewModel.firePropertyChanged();

    }

    @Override
    public void prepareBackView() {
        CustomTierListState state = customTierListViewModel.getState();
        state.setError(null);
        state.setTitle(null);
        customTierListViewModel.setState(state);

        viewManagerModel.setActiveView(selectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
