import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private List<MainQuest> mainQuests;
    private List<SideQuest> sideQuests;

    public GameEngine() {
        this.mainQuests = new ArrayList<>();
        this.sideQuests = new ArrayList<>();
    }

    // Metode untuk menyerang monster dengan nilai yang diberikan
    public boolean attack(Monster monster, int attackValue) {
        int coinsRequired = monster.getValue(); // Mendapatkan nilai coins yang dibutuhkan untuk mengalahkan monster
        if (attackValue >= coinsRequired) {
            System.out.println("Monster defeated!");
            return true; // Monster berhasil dikalahkan
        }
        System.out.println("Attack failed. Try again!");
        return false; // Jika attack gagal
    }

    // Metode untuk memulai permainan
    public void startGame(Potter potter, Monster monster) {
        System.out.println("Starting the game...");

        // Menyelesaikan Main Quests
        for (MainQuest quest : mainQuests) {
            if (potter.getEnergy() >= quest.getEnergyRequired()) {
                potter.decreaseEnergy(quest.getEnergyRequired());
                potter.addCoins(quest.getCoinReward());
                System.out.println(quest.getName() + " completed!");
            } else {
                System.out.println("Not enough energy for " + quest.getName());
            }
        }

        // Menyelesaikan Side Quests
        for (SideQuest sideQuest : sideQuests) {
            if (potter.getEnergy() >= sideQuest.getEnergyRequired()) {
                potter.decreaseEnergy(sideQuest.getEnergyRequired());
                potter.addCoins(sideQuest.getCoinReward());
                potter.setEnergy(potter.getEnergy() + sideQuest.getEnergyRestored());
                System.out.println(sideQuest.getName() + " completed!");
            } else {
                System.out.println("Not enough energy for " + sideQuest.getName());
            }
        }

        // Monster Encounter dan pencarian objek vital
        System.out.println("Monster encountered! Choose your next step.");

        // Pemain memilih apakah akan menggunakan pusaka atau berpetualang
        boolean usePusaka = true;  // Misalnya ini adalah pilihan pemain (true = memilih pusaka)

        if (usePusaka) {
            System.out.println("You chose to use the pusaka. Preparing for battle...");

            // Pemain langsung melawan monster setelah memilih pusaka
            int attackValue = 91;  // Misalnya angka yang dipilih pemain untuk serangan
            boolean defeated = attack(monster, attackValue);  // Serang monster dengan attack value

            if (defeated) {
                // Mendapatkan koin dan mengalahkan monster
                int rewardCoins = (monster.getId() == 1 || monster.getId() == 3) ? 60 : 50;
                potter.addCoins(rewardCoins);
                System.out.println("Potter received " + rewardCoins + " coins as a reward!");
            }
        } else {
            // Jika tidak menggunakan pusaka, pemain berpetualang dengan menyelesaikan quest
            System.out.println("You chose not to use the pusaka. Proceeding with quests...");
            // Lanjutkan dengan menyelesaikan quest
        }
    }

    // Metode untuk menangani pencarian objek vital berdasarkan kedalaman BST
    public void findVitalObject(int attackValue) {
        System.out.println("Finding vital object with attack value " + attackValue);
        // Dapatkan nilai berdasarkan attack value yang diberikan untuk pencarian objek vital
    }

    // Metode untuk mengalahkan monster
    public void defeatMonster(Monster monster, int coins) {
        if (coins >= monster.getValue()) {
            System.out.println("You have defeated the monster!");
            System.out.println("You have earned " + coins + " coins.");
        } else {
            System.out.println("Not enough coins to defeat the monster!");
        }
    }

    // Menambahkan Main Quest dan Side Quest
    public void addMainQuest(MainQuest quest) {
        mainQuests.add(quest);
    }

    public void addSideQuest(SideQuest quest) {
        sideQuests.add(quest);
    }
}
