import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi graf untuk jalur perjalanan
        Graph graph = new Graph();

        // Menambahkan edge ke dalam graf
        graph.addEdge("S", "S1", 0);
        graph.addEdge("S", "S2", 0);
        graph.addEdge("S", "S3", 0);
        graph.addEdge("S1", "SQ8", 15);
        graph.addEdge("S1", "SQ2", 17);
        graph.addEdge("S1", "M1", 10);
        graph.addEdge("S2", "SQ2", 8);
        graph.addEdge("S2", "SQ3", 14);
        graph.addEdge("S2", "M5", 20);
        graph.addEdge("S3", "SQ9", 8);
        graph.addEdge("S3", "M5", 13);

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
            case 0 -> chosenMonster = new Monster(0, "Infernal Dragon", 60);
            case 1 -> chosenMonster = new Monster(1, "Dark Leviathan", 50);
            case 2 -> chosenMonster = new Monster(2, "Blood Wraith", 60);
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

        // Step 8: Menggunakan pusaka atau tidak
        System.out.println("Do you want to use the pusaka? (Yes/No): ");
        String usePusaka = scanner.next();

        if (usePusaka.equalsIgnoreCase("Yes")) {
            System.out.println("You chose to use the pusaka. You will fight the monster directly.");
            initialEnergy -= 100;
            System.out.println("Your energy is reduced by 100. Remaining energy: " + initialEnergy);

            // Step 9: Pemain memasukkan angka untuk melawan monster
            System.out.println("Enter a random number between 1 and 100 to attack the monster: ");
            int playerChoice = scanner.nextInt();

            // Logika BST tetap
            VitalObject vitalObject = new VitalObject();
            int depth = vitalObject.findDepth(playerChoice);

            if (depth == -1) {
                System.out.println("The number " + playerChoice + " is not found in the BST. You failed to defeat the monster.");
                return;
            }

            int coinsEarned = depth * 10;
            System.out.println("You earned " + coinsEarned + " coins for reaching depth " + depth);

            int requiredCoins = chosenMonster.getValue();
            if (coinsEarned >= requiredCoins) {
                System.out.println(chosenMonster.getName() + " defeated!");
                System.out.println("You received " + coinsEarned + " coins as a reward.");
            } else {
                System.out.println("You do not have enough coins to defeat " + chosenMonster.getName() + ".");
                System.out.println("You need " + requiredCoins + " coins to defeat the monster.");
            }
        } else {
            // Step 10: Quest tanpa pusaka
            System.out.println("You chose not to use the pusaka. Here are your quest options:");
            String currentQuest = pathChoice == 1 ? "S1" : pathChoice == 2 ? "S2" : "S3";

            while (!currentQuest.equals("Finish")) {
                gameEngine.displayQuestOptions(currentQuest); // Memanggil dari GameEngine
                int questChoice = scanner.nextInt();

                if (questChoice < 1 || questChoice > 5) {
                    System.out.println("Invalid quest choice. Please select a valid quest.");
                    continue;
                }

                currentQuest = gameEngine.getNextQuest(currentQuest, questChoice); // Memanggil dari GameEngine

                if (currentQuest.equals("Finish")) {
                    System.out.println("You have reached the finish point of your journey!");
                    break;
                }
            }

        }
    }

}
