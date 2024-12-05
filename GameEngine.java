import java.util.*;

public class GameEngine {
    private List<Quest> quests; // Menggabungkan MainQuest dan SideQuest
    private Map<String, List<String>> questGraph; // Menyimpan jalur quest
    private Potter potter;

    public GameEngine(Potter potter) {
        this.quests = new ArrayList<>();
        this.questGraph = new HashMap<>();
        this.potter = potter;
        Object displayQuestOptions;
        initializeGraph(); // Inisialisasi graph quest
    }

    // Metode untuk memulai permainan
    public void startGame(Monster monster, String start) {
        System.out.println("Starting the game...");
//        Pusaka pusaka = new Pusaka(start, potter);
        String currentQuest = start; // Quest awal
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Temukan Quest saat ini berdasarkan nama Quest
            Quest current = findQuestByName(currentQuest);
            if (current != null) {
                executeQuest(current);
                potter.printStatus();
            }

            //Simpan Quest berikutnya dari currentQuest dalam List
            List<String> nextQuests = questGraph.getOrDefault(currentQuest, Collections.emptyList());
            if (nextQuests == null || nextQuests.isEmpty()) {
                System.out.println("You have reached the finish point of your journey!");
                break;
            }

            //Tampilkan Quest berikutnya
            System.out.println("Here are your quest options:");
            for (int i = 0; i < nextQuests.size(); i++) {
                Quest quest = findQuestByName(nextQuests.get(i));
                System.out.printf("%d. %s (Energy required: %d)%n", i + 1, quest.getName(), quest.getEnergyRequired());
            }
            int pusakaNumber = nextQuests.size() + 1;
            System.out.println(pusakaNumber + ". Use Pusaka");

            System.out.print("Choose your next quest: ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > nextQuests.size()+1) {
                System.out.println("Invalid choice. Exiting game.");
                break;
            }

            if (choice == nextQuests.size()+1){
                usePusaka();
            }

            currentQuest = getNextQuest(currentQuest, choice);
            if (currentQuest.equals("Invalid")) {
                System.out.println("Invalid path. Exiting game.");
                break;
            }
        }

        // Pertemuan dengan monster
        System.out.println("Monster encountered! Choose your next step.");
        boolean usePusaka = true; // Contoh input (true = menggunakan pusaka)

        if (usePusaka) {
            int attackValue = 91; // Contoh input untuk serangan
            boolean defeated = attack(monster, attackValue);
            if (defeated) {
                int rewardCoins = (monster.getId() == 1 || monster.getId() == 3) ? 60 : 50;
                potter.addCoins(rewardCoins);
                System.out.println("Potter received " + rewardCoins + " coins as a reward!");
            }
        } else {
            System.out.println("You chose not to use the pusaka. Proceeding with quests...");
        }
        scanner.close();
    }

    private void usePusaka(){

    }

    // Menyelesaikan quest dan memberikan reward
    private void executeQuest(Quest quest) {
        if (potter.getEnergy() >= quest.getEnergyRequired()) {
            potter.decreaseEnergy(quest.getEnergyRequired());
            potter.addCoins(quest.getCoinReward());
            potter.increaseEnergy(quest.getEnergyRestored());
            System.out.println(quest.getName() + " completed!");
        } else {
            System.out.println("Not enough energy for " + quest.getName());
        }
    }

    // Menemukan quest berdasarkan nama
    private Quest findQuestByName(String questName) {
        for (Quest quest : quests) {
            if (quest.getName().equals(questName)) {
                return quest;
            }
        }
        return null;
    }

    // Menambahkan jalur quest ke graph
    private void initializeGraph() {
        addEdge("S1", Arrays.asList("SQ8", "SQ2", "M1"));
        addEdge("SQ8", Arrays.asList("SQ15", "SQ1"));
        addEdge("SQ15", Arrays.asList("SQ11", "SQ1"));
        addEdge("SQ1", Arrays.asList("SQ11", "M2", "M1"));
        // Tambahkan jalur lain sesuai kebutuhan
    }

    private void addEdge(String from, List<String> toList) {
        questGraph.put(from, new ArrayList<>(toList));
    }

    // Metode untuk menyerang monster
    public boolean attack(Monster monster, int attackValue) {
        int coinsRequired = monster.getValue();
        if (attackValue >= coinsRequired) {
            System.out.println("Monster defeated!");
            return true;
        }
        System.out.println("Attack failed. Try again!");
        return false;
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }
    public void displayQuestOptions(String currentQuest) {
        switch (currentQuest) {
            case "S1" -> {
                System.out.println("1. SQ8 Defend Against Bandits (Energy required: 15)");
                System.out.println("2. SQ2 Repair the Broken Cart (Energy required: 17)");
                System.out.println("3. M1 (Energy required: 10)");
            }
            case "S2" -> {
                System.out.println("1. SQ2 Repair the Broken Cart (Energy required: )");
                System.out.println("2. SQ3 Assist the Villagers (Energy required: )");
                System.out.println("3. M5 (Energy required: )");
            }
            case "S3" -> {
                System.out.println("1. M5 (Energy required: )");
                System.out.println("2. SQ9 (Energy required: )");
            }
            case "SQ1" -> {
                System.out.println("1. M1 (Energy required: )");
                System.out.println("2. M2 (Energy required: )");
                System.out.println("3. SQ8 (Energy required: )");
                System.out.println("4. SQ11 (Energy required: )");
                System.out.println("5. SQ15 (Energy required: )");
            }
            case "SQ2" -> {
                System.out.println("1. M2 (Energy required: )");
            }
            case "SQ3" -> {
                System.out.println("1. M2 (Energy required: )");
                System.out.println("2. M5 (Energy required: )");
                System.out.println("3. SQ5 (Energy required: )");
            }
            case "SQ4" -> {
                System.out.println("1. M4 (Energy required: )");
                System.out.println("2. M5 (Energy required: )");
                System.out.println("3. SQ5 (Energy required: )");
                System.out.println("4. SQ13 (Energy required: )");
            }
            case "SQ5" -> {
                System.out.println("1. M3 (Energy required: )");
                System.out.println("2. SQ3 (Energy required: )");
                System.out.println("3. SQ4 (Energy required: )");
                System.out.println("4. SQ7 (Energy required: )");
            }
            case "SQ6" -> {
                System.out.println("1. M3 (Energy required: )");
                System.out.println("2. F1 (Energy required: )");
                System.out.println("3. F2 (Energy required: )");
            }
            case "SQ7" -> {
                System.out.println("1. M4 (Energy required: )");
                System.out.println("2. SQ5 (Energy required: )");
                System.out.println("3. SQ14 (Energy required: )");
                System.out.println("3. F2 (Energy required: )");
                System.out.println("3. F3 (Energy required: )");
            }
            case "SQ8" -> {
                System.out.println("1. SQ1 (Energy required: )");
                System.out.println("2. SQ15 (Energy required: )");
            }
            case "SQ9" -> {
                System.out.println("1. M5 (Energy required: )");
                System.out.println("2. SQ13 (Energy required: )");
            }
            case "SQ10" -> {
                System.out.println("1. M4 (Energy required: )");
                System.out.println("2. SQ12 (Energy required: )");
                System.out.println("3. SQ13 (Energy required: )");
            }
            case "SQ11" -> {
                System.out.println("1. M3 (Energy required: )");
                System.out.println("2. SQ1 (Energy required: )");
                System.out.println("3. SQ15 (Energy required: )");
                System.out.println("4. F1 (Energy required: )");
            }
            case "SQ12" -> {
                System.out.println("1. M4 (Energy required: )");
                System.out.println("2. SQ10 (Energy required: )");
                System.out.println("3. F3 (Energy required: )");
            }
            case "SQ13" -> {
                System.out.println("1. M5 (Energy required: )");
                System.out.println("2. SQ4 (Energy required: )");
                System.out.println("3. SQ9 (Energy required: )");
                System.out.println("4. SQ10 (Energy required: )");
            }
            case "SQ14" -> {
                System.out.println("1. M3 (Energy required: )");
                System.out.println("2. SQ7 (Energy required: )");
                System.out.println("3. F2 (Energy required: )");
            }
            case "SQ15" -> {
                System.out.println("1. SQ1 (Energy required: )");
                System.out.println("2. SQ8 (Energy required: )");
                System.out.println("3. SQ11 (Energy required: )");
            }
            case "M1" -> {
                System.out.println("1. M2 (Energy required: )");
                System.out.println("2. SQ1 (Energy required: )");
            }
            case "M2" -> {
                System.out.println("1. M1 (Energy required: )");
                System.out.println("2. M3 (Energy required: )");
                System.out.println("3. SQ1 (Energy required: )");
                System.out.println("4. SQ2 (Energy required: )");
                System.out.println("5. SQ3 (Energy required: )");
            }
            case "M3" -> {
                System.out.println("1. M2 (Energy required: )");
                System.out.println("2. SQ5 (Energy required: )");
                System.out.println("3. SQ6 (Energy required: )");
                System.out.println("4. SQ11 (Energy required: )");
                System.out.println("5. SQ14 (Energy required: )");
            }
            case "M4" -> {
                System.out.println("1. SQ4 (Energy required: )");
                System.out.println("2. SQ7 (Energy required: )");
                System.out.println("3. SQ10 (Energy required: )");
                System.out.println("4. SQ12 (Energy required: )");
                System.out.println("5. F3 (Energy required: )");
            }
            case "M5" -> {
                System.out.println("1. SQ3 (Energy required: )");
                System.out.println("2. SQ4 (Energy required: )");
                System.out.println("3. SQ9 (Energy required: )");
                System.out.println("4. SQ13 (Energy required: )");
            }
        }
    }

    // Mendapatkan jalur quest selanjutnya
    public String getNextQuest(String currentQuest, int questChoice) {
        List<String> options = questGraph.getOrDefault(currentQuest, Collections.emptyList());

        if (questChoice >= 1 && questChoice <= options.size()) {
            return options.get(questChoice - 1);
        }
        switch (currentQuest) {
            case "S":
                // Jalur pertama untuk pilihan awal
                return questChoice == 1 ? "S1" : questChoice == 2 ? "S2" : questChoice == 3 ? "S3" : "Invalid";
            case "S1":
                return questChoice == 1 ? "M1" : questChoice == 2 ? "SQ2" : questChoice == 3 ? "SQ8" : "Invalid";
            case "S2":
                return questChoice == 1 ? "SQ2" : questChoice == 2 ? "SQ3" : questChoice == 3 ? "M5" : "Invalid";
            case "S3":
                return questChoice == 1 ? "M5" : questChoice == 2 ? "SQ9" : "Invalid";
            case "SQ1":
                return questChoice == 1 ? "M1" : questChoice == 2 ? "M2" : questChoice == 3 ? "SQ8" : questChoice == 4 ? "SQ11" : questChoice == 5 ? "SQ15" : "Invalid";
            case "SQ2":
                return questChoice == 1 ? "M2" : "Invalid";
            case "SQ3":
                return questChoice == 1 ? "M2" : questChoice == 2 ? "M5" : questChoice == 3 ? "SQ5" : "Invalid";
            case "SQ4":
                return questChoice == 1 ? "M4" : questChoice == 2 ? "M5" : questChoice == 3 ? "SQ5" : questChoice == 4 ? "SQ13" : "Invalid";
            case "SQ5":
                return questChoice == 1 ? "M3" : questChoice == 2 ? "SQ3" : questChoice == 3 ? "SQ4" : questChoice == 4 ? "SQ7" :"Invalid";
            case "SQ6":
                return questChoice == 1 ? "M3" : questChoice == 2 ? "F1" : questChoice == 3 ? "F2" : "Invalid";
            case "SQ7":
                return questChoice == 1 ? "M4" : questChoice == 2 ? "SQ5" : questChoice == 3 ? "SQ14" : questChoice == 4 ? "F2" : questChoice == 5 ? "F3" : "Invalid";
            case "SQ8":
                return questChoice == 1 ? "SQ1" : questChoice == 2 ? "SQ15" : "Invalid";
            case "SQ9":
                return questChoice == 1 ? "M5" : questChoice == 2 ? "SQ13" : "Invalid";
            case "SQ10":
                return questChoice == 1 ? "M4" : questChoice == 2 ? "SQ12" : questChoice == 3 ? "SQ13" : "Invalid";
            case "SQ11":
                return questChoice == 1 ? "M3" : questChoice == 2 ? "SQ1" : questChoice == 3 ? "SQ15" : questChoice == 4 ? "F1" : "Invalid";
            case "SQ12":
                return questChoice == 1 ? "M4" : questChoice == 2 ? "SQ10" : questChoice == 3 ? "F3" : "Invalid";
            case "SQ13":
                return questChoice == 1 ? "M5" : questChoice == 2 ? "SQ4" : questChoice == 3 ? "SQ9" : questChoice == 4 ? "SQ10" : "Invalid";
            case "SQ14":
                return questChoice == 1 ? "M3" : questChoice == 2 ? "SQ7" : questChoice == 3 ? "F2" : "Invalid";
            case "SQ15":
                return questChoice == 1 ? "SQ1" : questChoice == 2 ? "SQ8" : questChoice == 3 ? "SQ11" : "Invalid";
            case "M1":
                return questChoice == 1 ? "M2" : questChoice == 2 ? "SQ1" : "Invalid";
            case "M2":
                return questChoice == 1 ? "M1" : questChoice == 2 ? "M3" : questChoice == 3 ? "SQ1" : questChoice == 4 ? "SQ2" : questChoice == 5 ? "SQ3" : "Invalid";
            case "M3":
                return questChoice == 1 ? "M2" : questChoice == 2 ? "SQ5" : questChoice == 3 ? "SQ6" : questChoice == 4 ? "SQ11" : questChoice == 5 ? "SQ14" : "Invalid";
            case "M4":
                return questChoice == 1 ? "SQ4" : questChoice == 2 ? "SQ7" : questChoice == 3 ? "SQ10" : questChoice == 4 ? "SQ12" : questChoice == 5 ? "F3" : "Invalid";
            case "M5":
                return questChoice == 1 ? "SQ3" : questChoice == 2 ? "SQ4" : questChoice == 3 ? "SQ9" : questChoice == 4 ? "SQ13" : "Invalid";
            default:
                return "Invalid";
        }
    }
}
