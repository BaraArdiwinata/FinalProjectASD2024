public class Potter {
    private String name;
    private int energy;
    private int coins;

    // Constructor default
    public Potter() {
        this.name = "Potter";
        this.energy = 100; // Nilai default energi
        this.coins = 0; // Nilai default koin
    }

    // Constructor dengan parameter
    public Potter(String name, int energy, int coins) {
        this.name = name;
        this.energy = energy;
        this.coins = coins;
    }

    // Getter dan setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void decreaseEnergy(int amount) {
        energy -= amount;
    }

    public void addCoins(int amount) {
        coins += amount;
    }
}
