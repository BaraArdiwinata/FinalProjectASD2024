import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MQInit();
        SQInit();
        MonsInit();

        // Inisialisasi graf untuk jalur perjalanan
        Graph graph = new Graph();
        graph.addEdge("S", "S1", 0);
        graph.addEdge("S", "S2", 0);
        graph.addEdge("S", "S3", 0);
        graph.addEdge("S1", SQInit().get(8-1), 15);
        graph.addEdge("S1", SQInit().get(2-1), 17);
        graph.addEdge("S1", MQInit().get(1-1), 10);
        graph.addEdge("S2", SQInit().get(2-1), 8);
        graph.addEdge("S2", SQInit().get(3-1), 14);
        graph.addEdge("S2", MQInit().get(5-1), 20);
        graph.addEdge("S3", SQInit().get(9-1), 8);
        graph.addEdge("S3", MQInit().get(5-1), 13);
        graph.addEdge(SQInit().get(8-1), SQInit().get(15-1), 8);
        graph.addEdge(SQInit().get(8-1), SQInit().get(1-1), 20);
        graph.addEdge(SQInit().get(2-1), MQInit().get(2-1), 12);
        graph.addEdge(MQInit().get(1-1), SQInit().get(1-1), 8);
        graph.addEdge(MQInit().get(1-1), MQInit().get(2-1), 17);
        graph.addEdge(SQInit().get(9-1), SQInit().get(13-1), 12);
        graph.addEdge(SQInit().get(9-1), MQInit().get(5-1), 10);
        graph.addEdge(SQInit().get(15-1), SQInit().get(11-1), 10);
        graph.addEdge(SQInit().get(15-1), SQInit().get(1-1), 14);
        graph.addEdge(SQInit().get(1-1), SQInit().get(11-1), 10);
        graph.addEdge(SQInit().get(1-1), MQInit().get(2-1), 10);
        graph.addEdge(MQInit().get(2-1), MQInit().get(3-1), 13);
        graph.addEdge(SQInit().get(3-1), SQInit().get(5-1), 20);
        graph.addEdge(SQInit().get(3-1), MQInit().get(2-1), 8);
        graph.addEdge(SQInit().get(3-1), MQInit().get(5-1), 4);
        graph.addEdge(MQInit().get(5-1), SQInit().get(4-1), 14);
        graph.addEdge(MQInit().get(5-1), SQInit().get(13-1), 13);
        graph.addEdge(SQInit().get(13-1), SQInit().get(4-1), 15);
        graph.addEdge(SQInit().get(4-1), SQInit().get(5-1), 8);
        graph.addEdge(SQInit().get(5-1), MQInit().get(3-1), 9);
        graph.addEdge(MQInit().get(3-1), SQInit().get(11-1), 9);
        graph.addEdge(SQInit().get(11-1), MonsInit().get(1-1), 15);
        graph.addEdge(MQInit().get(3-1), SQInit().get(6-1), 7);
        graph.addEdge(MQInit().get(3-1), SQInit().get(14-1), 10);
        graph.addEdge(SQInit().get(5-1), SQInit().get(7-1), 10);
        graph.addEdge(SQInit().get(4-1), MQInit().get(4-1), 14);
        graph.addEdge(SQInit().get(13-1), SQInit().get(10-1), 16);
        graph.addEdge(SQInit().get(6-1), MonsInit().get(1-1), 8);
        graph.addEdge(SQInit().get(6-1), MonsInit().get(2-1), 12);
        graph.addEdge(SQInit().get(14-1), MonsInit().get(2-1), 16);
        graph.addEdge(SQInit().get(14-1), SQInit().get(7-1), 9);
        graph.addEdge(MQInit().get(4-1), SQInit().get(7-1), 10);
        graph.addEdge(MQInit().get(4-1), SQInit().get(10-1), 8);
        graph.addEdge(SQInit().get(7-1), MonsInit().get(2-1), 13);
        graph.addEdge(MQInit().get(4-1), MonsInit().get(3-1), 17);
        graph.addEdge(MQInit().get(4-1), SQInit().get(12-1), 10);
        graph.addEdge(SQInit().get(10-1), SQInit().get(12-1), 15);
        graph.addEdge(SQInit().get(7-1), MonsInit().get(3-1), 15);
        graph.addEdge(SQInit().get(12-1), MonsInit().get(3-1), 9);

        // Energi awal pemain
        int initialEnergy = 250;

        // Step 1: Konfirmasi kesiapan bermain
        System.out.println("Are you ready to play the Quest Navigation game? (Yes/No): ");
        String ready = scanner.next();

        if (!ready.equalsIgnoreCase("Yes")) {
            System.out.println("See you next time!");
            return;
        }

        // Step 2 : Pemilihan Monster / Finish
        Monster chosenMonster = null;
        int randomIntMonster = (int) (Math.random() * 10 % 3);
        switch (randomIntMonster){
            case 0 -> chosenMonster = MonsInit().get(0);
            case 1 -> chosenMonster = MonsInit().get(1);
            case 2 -> chosenMonster = MonsInit().get(2);
        }
        System.out.println("You have chosen to fight " + chosenMonster.getName() + "!");

        // Step 3: Menampilkan pilihan jalur start
        System.out.println("Choose your starting path:");
        System.out.println("1. Field of Light");
        System.out.println("2. Fortress of Faith");
        System.out.println("3. Tower of Fate’s Dawn");

        int pathChoice = scanner.nextInt();

        if (pathChoice < 1 || pathChoice > 3) {
            System.out.println("Invalid path choice. Please select a valid path.");
            return;
        } else {
            System.out.println("You have chosen " + (pathChoice == 1 ? "Field of Light" :
                    pathChoice == 2 ? "Fortress of Faith" : "Tower of Fate’s Dawn") + " as your path!");
        }

        System.out.println("Your initial energy is: " + initialEnergy);

        Potter potter = new Potter(); // Asumsi Potter butuh energy saat inisialisasi
        GameEngine gameEngine = new GameEngine(potter);

//        // Step 8: Menggunakan pusaka atau tidak
//        System.out.println("Do you want to use the pusaka? (Yes/No): ");
//        String usePusaka = scanner.next();
//
//        if (usePusaka.equalsIgnoreCase("Yes")) {
//            System.out.println("You chose to use the pusaka. You will fight the monster directly.");
//            initialEnergy -= 100;
//            System.out.println("Your energy is reduced by 100. Remaining energy: " + initialEnergy);
//
//            // Step 9: Pemain memasukkan angka untuk melawan monster
//            System.out.println("Enter a random number between 1 and 100 to attack the monster: ");
//            int playerChoice = scanner.nextInt();
//
//            // Logika BST tetap
//            VitalObject vitalObject = new VitalObject();
//            int depth = vitalObject.findDepth(playerChoice);
//
//            if (depth == -1) {
//                System.out.println("The number " + playerChoice + " is not found in the BST. You failed to defeat the monster.");
//                return;
//            }
//
//            int coinsEarned = depth * 10;
//            System.out.println("You earned " + coinsEarned + " coins for reaching depth " + depth);
//
//            int requiredCoins = chosenMonster.getValue();
//            if (coinsEarned >= requiredCoins) {
//                System.out.println(chosenMonster.getName() + " defeated!");
//                System.out.println("You received " + coinsEarned + " coins as a reward.");
//            } else {
//                System.out.println("You do not have enough coins to defeat " + chosenMonster.getName() + ".");
//                System.out.println("You need " + requiredCoins + " coins to defeat the monster.");
//            }
//        } else {
//            // Step 10: Quest tanpa pusaka
//            System.out.println("You chose not to use the pusaka. Here are your quest options:");
//            String currentQuest = pathChoice == 1 ? "S1" : pathChoice == 2 ? "S2" : "S3";
//
//            while (!currentQuest.equals("Finish")) {
//                gameEngine.displayQuestOptions(currentQuest); // Memanggil dari GameEngine
//                int questChoice = scanner.nextInt();
//
//                if (questChoice < 1 || questChoice > 5) {
//                    System.out.println("Invalid quest choice. Please select a valid quest.");
//                    continue;
//                }
//
//                currentQuest = gameEngine.getNextQuest(currentQuest, questChoice); // Memanggil dari GameEngine
//
//                if (currentQuest.equals("Finish")) {
//                    System.out.println("You have reached the finish point of your journey!");
//                    break;
//                }
//            }
//
//        }
    }

    static List<Quest> MQInit() {
        List<Quest> quests = new ArrayList<>();
        quests.add(new Quest("Hurricane Shoes"));
        quests.add(new Quest("Taranis Shield"));
        quests.add(new Quest("Phoenix Mantle"));
        quests.add(new Quest("Gandiva Arrow"));
        quests.add(new Quest("Helm of Kuuga"));
        return quests;
    }

    static List<Quest> SQInit(){
        List<Quest> quests = new ArrayList<>();
        quests.add(new Quest("Gather Food Supplies", 0, 17));
        quests.add(new Quest("Repair the Broken Cart", 0, 5));
        quests.add(new Quest("Assist the Villagers", 13, 5));
        quests.add(new Quest("Upgrade Village Walls", 0, 8));
        quests.add(new Quest("Scout the Nearby Forest", 0, 5));
        quests.add(new Quest("Find the Lost Tools", 12, 5));
        quests.add(new Quest("Build the Watchtower", 15, 0));
        quests.add(new Quest("Defend Against Bandits", 0, 5));
        quests.add(new Quest("Rescue the Stranded Traveler", 14, 11));
        quests.add(new Quest("Trade Goods with the Caravan", 0, 7));
        quests.add(new Quest("Locate the Hidden Spring", 15, 10));
        quests.add(new Quest("Train the Village Guard", 22, 0));
        quests.add(new Quest("Collect Medicinal Herbs", 0, 7));
        quests.add(new Quest("Repel the Night Raid", 0, 5));
        quests.add(new Quest("Retrieve the Ancient Relic", 17, 15));
        return quests;
    }

    static List<Monster> MonsInit(){
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster("Infernal Dragon", 60));
        monsters.add(new Monster("Dark Leviathan", 60));
        monsters.add(new Monster("Blood Wraith", 60));

        int[] vitalObjectF1 = {1, 2, 3};
        int[] vitalObjectF2 = {1, 2, 3};
        int[] vitalObjectF3 = {1, 2, 3};

        for (int i = 0; i<vitalObjectF1.length; i++){monsters.get(0).addVitalObject(i);}
        for (int i = 0; i<vitalObjectF2.length; i++){monsters.get(1).addVitalObject(i);}
        for (int i = 0; i<vitalObjectF3.length; i++){monsters.get(2).addVitalObject(i);}

        return monsters;
    }

}
