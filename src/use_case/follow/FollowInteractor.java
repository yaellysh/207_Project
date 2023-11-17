package use_case.follow;

import entity.User;

import java.util.List;

public class FollowInteractor implements FollowInputBoundary {
    final FollowUserDataAccessInterface userDataAccessObject;
    final FollowOutputBoundary followPresenter;

    public FollowInteractor(FollowUserDataAccessInterface userDataAccessInterface,
                            FollowOutputBoundary loginOutputBoundary) {
        userDataAccessObject = userDataAccessInterface;
        followPresenter = loginOutputBoundary;
    }

    public void execute(FollowInputData followInputData) {
        User follower = followInputData.getFollower();
        User userBeingFollowed = followInputData.getUserBeingFollowed();



        userDataAccessObject.updateFollowing(follower, userBeingFollowed);
        userDataAccessObject.updateUserBeingFollowed(follower, userBeingFollowed);
        // want to find the users that most of my following also follow
        // "for example, people you know also follow ..."
        List<User> followerFollowing = follower.getFollowing();
        List<User> userBeingFollowedFollowers = userBeingFollowed.getFollowers();

        for (User followerFollower : followerFollowing) {

        }


    }
}
