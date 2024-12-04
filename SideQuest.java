public class SideQuest {
    private String name;
    private int energyRequired;
    private int coinReward;
    private int energyRestored;

    public SideQuest(String name, int energyRequired, int coinReward, int energyRestored) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.coinReward = coinReward;
        this.energyRestored = energyRestored;
    }

    public String getName() {
        return name;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public int getEnergyRestored() {
        return energyRestored;
    }
}
