package use_case.generate.custom_tierlist;

import entity.Item;
import entity.TierList;
import entity.User;
import use_case.generate.GenerateTierListDataAccessInterface;

import java.util.Arrays;
import java.util.List;

public class CustomTierListInteractor implements CustomTierListInputBoundary {

    private final CustomTierListOutputBoundary outputBoundary;
    private final GenerateTierListDataAccessInterface dataAccessInterface;

    public CustomTierListInteractor(CustomTierListOutputBoundary outputBoundary,
                                    GenerateTierListDataAccessInterface dataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void execute(CustomTierListInputData data) {
        String[] inputs = data.getInputs();
        String name = data.getListName();
        User user = data.getUser();

        // Fail if the name already exists
        if (user.getTierList(name) != null) {
            this.outputBoundary.prepareFailView();
            return;
        }

        // Fail if they left one of the strings blank
        for (String input : inputs) {
            if (input.isEmpty()) {
                this.outputBoundary.prepareFailView();
                return;
            }
        }

        List<Item> items = Arrays.stream(inputs).map(Item::new).toList();

        TierList list = new TierList(name, items);
        user.addTierList(list);
        dataAccessInterface.save();
        this.outputBoundary.prepareSuccessView(new CustomTierListOutputData(user, name));
    }
    public void execute() {
        outputBoundary.prepareBackView();
    }
}
