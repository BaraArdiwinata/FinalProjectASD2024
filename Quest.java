public class Quest {
    private String name;
    private int coinReward;
    private int energyRestored; // Hanya relevan untuk side quest
    private boolean isMainQuest; // Menentukan apakah quest ini main atau side quest

    // Constructor untuk MainQuest
    public Quest(String name) {
        this.name = name;
        this.coinReward = 0;
        this.energyRestored = 0;
        this.isMainQuest = true;
    }

    // Constructor untuk SideQuest
    public Quest(String name, int coinReward, int energyRestored) {
        this.name = name;
        this.coinReward = coinReward;
        this.energyRestored = energyRestored;
        this.isMainQuest = false;
    }

    public String getName() {
        return name;
    }

    public int getCoinReward() {
        return coinReward;
    }

    public int getEnergyRestored() {
        return energyRestored;
    }

    public boolean isMainQuest() {
        return isMainQuest;
    }

}
