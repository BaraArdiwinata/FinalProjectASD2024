import java.util.*;

public class Walking {
    private Potter potter;
    private Monster monster;
    private List<Quest> allQuest;
    private Graph graph;
    private Map<Object, List<Object>> questGraph = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);


    public Walking(Potter potter, Monster monster, List<Quest> mainQuest, List<Quest> sideQuest, Graph graph){
        this.potter = potter;
        this.monster = monster;
        this.allQuest = new ArrayList<>(mainQuest);
        this.allQuest.addAll(sideQuest);
        this.graph = graph;
    }

    public void startGame(int startChoice){
        String start = "";
        switch (startChoice){
            case 1 -> start = "S1";
            case 2 -> start = "S2";
            case 3 -> start = "S3";
        }

        System.out.println("Starting the game...");
        Quest currentQuest = new Quest(start);
        this.allQuest.addFirst(currentQuest);
        boolean finishStage = false;


        while (!finishStage){
            if (findQuest(currentQuest) != null) {
                executeQuest(currentQuest);
                potter.printStatus();
            }

            //Simpan Quest berikutnya dari currentQuest dalam List
            List<Object> nextQuests = questGraph.getOrDefault(currentQuest, Collections.emptyList());
            if (nextQuests == null || nextQuests.isEmpty()) {
                System.out.println("You have reached the finish point of your journey!");
                finishStage = true;
            }

            //Tampilkan Quest berikutnya
            System.out.println("Here are your quest options:");
            for (int i = 0; i < nextQuests.size(); i++) {
                Quest quest = findQuest((Quest) nextQuests.get(i));
                System.out.printf("%d. %s (Energy required: %d)%n", i + 1, quest.getName(), Graph.Edge.getEnergyCost);
            }
            int pusakaNumber = nextQuests.size() + 1;
            System.out.println(pusakaNumber + ". Use Pusaka");

            System.out.print("Choose your next quest: ");
            boolean choiceValid = true;
            while (!choiceValid){
                int choice = scanner.nextInt();
                if (choice > 1 || choice < nextQuests.size()) {
                    currentQuest = getNextQuest(currentQuest, choice);
                    if (currentQuest.equals("Invalid")) {
                        System.out.println("Invalid path. Exiting game.");
                        break;
                    }
                } else if (choice == nextQuests.size()+1){
                    //Gunakan Pusaka
                } else{
                    System.out.println("Invalid choice. Exiting game.");
                    choiceValid = true;
                }
            }

        }
    }

    private Quest findQuest(Quest currentQuest){
        for (Quest quest : allQuest) {
            if (quest.getName().equals(currentQuest)) {
                return quest;
            }
        }
        return null;
    }

    private void executeQuest(Quest quest) {
        int energyCost = Graph.Edge.getEnergyCost;

        if (potter.getEnergy() >= energyCost) {
            potter.decreaseEnergy(energyCost);
            potter.addCoins(quest.getCoinReward());
            potter.increaseEnergy(quest.getEnergyRestored());
            System.out.println(quest.getName() + " completed!");
        } else {
            System.out.println("Not enough energy for " + quest.getName());
        }
    }

    private Quest getNextQuest(Quest currentQuest, int questChoice){
        // Ambil daftar quest yang tersedia dari questGraph
        List<Object> options = questGraph.getOrDefault(currentQuest, Collections.emptyList());

        // Validasi pilihan pengguna
        if (questChoice < 1 || questChoice > options.size()) {
            System.out.println("Invalid quest choice. Please choose a valid option.");
            return null; // Mengembalikan null jika pilihan tidak valid
        }

        // Ambil quest berikutnya berdasarkan pilihan
        Object nextQuestNode = options.get(questChoice - 1); // questChoice-1 karena daftar dimulai dari indeks 0
        return findQuest((Quest) nextQuestNode);
    }

    private void finalStage(){

    }
}
