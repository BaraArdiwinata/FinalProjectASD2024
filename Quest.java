public class Quest {
    private String name;
    private int energyRequired;
    private int coinReward;
    private int energyRestored; // Hanya relevan untuk side quest
    private boolean isMainQuest; // Menentukan apakah quest ini main atau side quest

    // Constructor untuk MainQuest
    public Quest(String name, int energyRequired, int coinReward, boolean isMainQuest) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.coinReward = coinReward;
        this.energyRestored = 0; // Default untuk MainQuest
        this.isMainQuest = isMainQuest;
    }

    // Constructor untuk SideQuest
    public Quest(String name, int energyRequired, int coinReward, int energyRestored) {
        this.name = name;
        this.energyRequired = energyRequired;
        this.coinReward = coinReward;
        this.energyRestored = energyRestored;
        this.isMainQuest = false;
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

    public boolean isMainQuest() {
        return isMainQuest;
    }

    // Metode untuk menyelesaikan quest
    public void complete(Potter potter) {
        if (potter.getEnergy() >= energyRequired) {
            potter.decreaseEnergy(energyRequired);
            potter.addCoins(coinReward);

            if (!isMainQuest) {
                potter.setEnergy(potter.getEnergy() + energyRestored);
            }

            System.out.println(name + " completed! Reward: " + coinReward + " coins.");
            if (!isMainQuest) {
                System.out.println("Energy restored: " + energyRestored);
            }
        } else {
            System.out.println("Not enough energy for " + name);
        }
    }
}
