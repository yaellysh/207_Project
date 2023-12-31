package use_case.tierlist;

import entity.Tier;

public class TierListInputData {

    private final String user;
    private final String tierList;
    private final String item;
    private final Tier tier;
    private final String viewUser;

    public TierListInputData(String user, String tierList, String item, Tier newTier) {
        this.user = user;
        this.tierList = tierList;
        this.item = item;
        this.tier = newTier;
        this.viewUser = user;
    }

    public TierListInputData(String user, String tierList, String viewUser) {
        this.user = user;
        this.tierList = tierList;
        this.viewUser = viewUser;
        this.item = null;
        this.tier = null;
    }


    public String getTierList() {
        return tierList;
    }

    public String getUser() {
        return user;
    }

    public String getItem() {
        return item;
    }

    public Tier getTier() {
        return tier;
    }

    public String getViewUser() {
        return viewUser;
    }
}
