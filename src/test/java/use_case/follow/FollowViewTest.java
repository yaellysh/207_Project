package use_case.follow;

import data_access.FileUserDataAccessObject;
import entity.User;
import factory.FollowFactory;
import factory.SearchFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.follow.FollowState;
import interface_adapter.follow.FollowViewModel;
import interface_adapter.search_user.SearchViewModel;
import interface_adapter.tierlist.TierListViewModel;
import org.junit.Before;
import org.junit.Test;
import view.FollowView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class FollowViewTest {

    private User userA;
    private User userB;

    private User userC;
    private User userD;
    private User userE;
    private User userF;

    private User tim;

    private User terry;




    private FileUserDataAccessObject userRepository;

    @Before
    public void setUp() {
        userA = new User("User A");
        userB = new User("User B");
        userC = new User("User C");
        userD = new User("User D");
        userE = new User("User E");
        userF = new User("User F");
        tim = new User("lt_rui");
        terry = new User("terryfufu");

        userA.addFollowing("lt_rui");
        userB.addFollowing("lt_rui");
        userC.addFollowing("lt_rui");
        userD.addFollowing("User A");
        userE.addFollowing("User A");
        userF.addFollowing("User A");
        userE.addFollowing("User B");
        userF.addFollowing("User B");
        userF.addFollowing("User C");

        tim.addFollowing("User A");
        tim.addFollowing("User B");
        tim.addFollowing("User C");

        tim.addFollowers("User A");
        tim.addFollowers("User B");
        tim.addFollowers("User C");

        terry.addFollowing("User D");
        terry.addFollowing("User E");
        terry.addFollowing("User F");

        userRepository = new FileUserDataAccessObject("src/test/resources/users.json");
        userRepository.addUser(userA);
        userRepository.addUser(userB);
        userRepository.addUser(userC);
        userRepository.addUser(userD);
        userRepository.addUser(userE);
        userRepository.addUser(userF);
        userRepository.addUser(tim);
        userRepository.addUser(terry);
    }


    @Test
    public void followViewTest() {

        JFrame application = new JFrame();

        application.setSize(700, 650);
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);
 
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        FollowViewModel followViewModel = new FollowViewModel("View User");
        SearchViewModel searchViewModel = new SearchViewModel("Search User");
        TierListViewModel tierListViewModel = new TierListViewModel("Tier List");

        User user = new User("terryfufu");
        User user2 = new User("lt_rui");

        FollowState testing = new FollowState(user, user2, false, new ArrayList<>());
        followViewModel.setState(testing);

        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("src/test/resources/users.json");

        FollowView followView = FollowFactory.create(viewManagerModel, followViewModel, userDataAccessObject, tierListViewModel);
        views.add(followView, followView.viewName);

        SearchView searchView = SearchFactory.create(viewManagerModel, searchViewModel, followViewModel, userDataAccessObject);
        views.add(searchView, searchView.viewName);

        //application.add(followView);
        //System.out.println(followView.viewName);

        //viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.setActiveView(followView.viewName);
        viewManagerModel.firePropertyChanged();

        cardLayout.show(views, "Search User");

        application.setVisible(true);

        assertSame("View User", viewManagerModel.getActiveView());
    }

}