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

        int startChoice = scanner.nextInt();

        if (startChoice < 1 || startChoice > 3) {
            System.out.println("Invalid path choice. Please select a valid path.");
            return;
        } else {
            System.out.println("You have chosen " + (startChoice == 1 ? "Field of Light" :
                    startChoice == 2 ? "Fortress of Faith" : "Tower of Fate’s Dawn") + " as your path!");
        }

        Potter potter = new Potter();
        System.out.println("Your initial energy is: " + potter.getEnergy());

        Walking walking = new Walking(potter, chosenMonster, MQInit(), SQInit(), graph);
        walking.startGame(startChoice);
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

        int[] vitalObjectF1 = {1, 2, 3, 4, 6, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 23, 24, 26, 27, 28, 30, 32, 33, 35, 39, 41, 42, 43, 44, 46, 50, 51, 52, 54, 55, 56, 58, 59, 60, 61, 64, 65, 67, 68, 69, 72, 73, 75, 76, 77, 79, 83, 84, 87, 89, 90, 91, 92, 94, 95, 97, 99
        };
        int[] vitalObjectF2 = {6, 8, 10, 12, 15, 19, 22, 24, 28, 30, 35, 38, 43, 45, 47, 50, 52, 54, 57, 60, 65, 68, 72, 76, 80, 83, 85, 88, 90, 95, 98
        };
        int[] vitalObjectF3 = {1, 2, 3, 7, 8, 9, 10, 11, 12, 14, 15, 16, 18, 19, 20, 21, 22, 23, 24, 26, 27, 28, 31, 32, 33, 34, 35, 36, 37, 39, 42, 44, 45, 48, 49, 51, 52, 55, 57, 58, 59, 61, 63, 65, 66, 67, 71, 72, 73, 74, 76, 77, 78, 81, 83, 87, 90, 91, 93, 95, 96, 97, 98};

        for (int i = 0; i<vitalObjectF1.length; i++){monsters.get(0).addVitalObject(i);}
        for (int i = 0; i<vitalObjectF2.length; i++){monsters.get(1).addVitalObject(i);}
        for (int i = 0; i<vitalObjectF3.length; i++){monsters.get(2).addVitalObject(i);}

        return monsters;
    }

}
