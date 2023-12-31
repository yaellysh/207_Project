package use_case.random_tierlist;

import entity.Item;
import entity.TierList;
import entity.User;
import use_case.tierlist.TierListDataAccessInterface;

import java.util.List;

public class RandomTierListInteractor implements RandomTierListInputBoundary {

    private final RandomTierListDataAccessInterface randomTierListDataAccessInterface;
    private final TierListDataAccessInterface generateTierListDataAccessInterface;
    private final RandomTierListOutputBoundary outputBoundary;

    public RandomTierListInteractor(RandomTierListDataAccessInterface dataAccessInterface,
                                    TierListDataAccessInterface dataAccessInterface1,
                                    RandomTierListOutputBoundary outputBoundary) {
        this.randomTierListDataAccessInterface = dataAccessInterface;
        this.generateTierListDataAccessInterface = dataAccessInterface1;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(RandomTierListInputData data) {
        String prompt = data.getPrompt();
        User user = data.getUser();
        if (data.getPrompt() == null) {
            outputBoundary.prepareFailView("Please ensure your input is not empty.");
            return;
        } else if (user.getTierList(prompt) != null) {
            this.outputBoundary.prepareFailView("A tierlist already exists with that name. Please try again.");
            return;
        }

        List<Item> items = randomTierListDataAccessInterface.generateTierList("list " + TierList.LENGTH + " popular " + prompt);

        if (items == null) {
            outputBoundary.prepareFailView("Something went wrong, please try again.");
            return;
        }

        TierList list = new TierList(prompt, items);
        user.addTierList(list);
        generateTierListDataAccessInterface.save();
        outputBoundary.prepareSuccessView(new RandomTierListOutputData(user, prompt));
    }
    public void execute() {
        outputBoundary.prepareBackView();
    }

}
