package use_case.search_user;

import entity.User;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary {

    final SearchUserDataAccessInterface userDataAccessObject;
    final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchUserDataAccessInterface userDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        searchPresenter = searchOutputBoundary;
    }
    public void execute(SearchInputData searchInputData) {
        String search = searchInputData.getSearch();

        if (!userDataAccessObject.existsByName(search)) {
            searchPresenter.prepareFailView("No accounts exist by the username " + search);
        }

        else {


            String selectedUsername = searchInputData.getSearch();
            User selectedUser = this.userDataAccessObject.getUser(searchInputData.getSearch());;
//            int followerCount = selectedUser.getFollowers().size();
//            int followingCount = selectedUser.getFollowing().size();
            List<String> tierLists = selectedUser.getTierListsAsStrings();
            System.out.println("tierlists" + tierLists);

            SearchOutputData searchOutputData = new SearchOutputData(false, selectedUser, tierLists);
            searchPresenter.prepareSuccessView(searchOutputData);

//            ViewUserOutputData viewUserOutputData = new ViewUserOutputData(selectedUsername, tierLists, followerCount, followingCount);
//            viewUserPresenter.prepareSuccessView(viewUserOutputData);

//            searchPresenter.prepareSuccessView(searchOutputData);
        }

    }
}
