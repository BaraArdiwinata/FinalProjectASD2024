public class Potter {
    private String name;
    private int energy;
    private int coins;

    // Constructor default
    public Potter() {
        this.name = "Potter";
        this.energy = 250; // Nilai default energi
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
        this.energy = Math.max(energy, 0); // Pastikan tidak negatif
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = Math.max(coins, 0); // Pastikan tidak negatif
    }

    // Mengurangi energi dengan pengamanan agar tidak negatif
    public void decreaseEnergy(int amount) {
        this.energy = Math.max(this.energy - amount, 0);
    }

    // Menambahkan energi
    public void increaseEnergy(int amount) {
        this.energy += amount;
    }

    // Menambahkan koin
    public void addCoins(int amount) {
        this.coins += amount;
    }

    // Mengurangi koin dengan pengamanan agar tidak negatif
    public void decreaseCoins(int amount) {
        this.coins = Math.max(this.coins - amount, 0);
    }

    // Cetak status Potter
    public void printStatus() {
        System.out.println("Potter: " + name + " | Energy: " + energy + " | Coins: " + coins);
    }
}
