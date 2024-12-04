public class MainQuest {
    private String name;
    private int energyRequired;
    private int coinReward;

    public MainQuest(String name, int energyRequired, int coinReward) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.coinReward = coinReward;
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
}
