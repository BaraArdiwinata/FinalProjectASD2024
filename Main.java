import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi graf untuk jalur perjalanan
        Graph graph = new Graph();

        // Menambahkan edge ke dalam graf sesuai data yang diberikan sebelumnya
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
        graph.addEdge("SQ8", "SQ15", 8);
        graph.addEdge("SQ8", "SQ1", 20);
        graph.addEdge("SQ2", "M2", 12);
        graph.addEdge("M1", "SQ1", 8);
        graph.addEdge("M1", "M2", 17);
        graph.addEdge("SQ9", "SQ13", 12);
        graph.addEdge("SQ9", "M5", 10);
        graph.addEdge("SQ15", "SQ11", 10);
        graph.addEdge("SQ15", "SQ1", 14);
        graph.addEdge("SQ1", "SQ11", 10);
        graph.addEdge("SQ1", "M2", 10);
        graph.addEdge("M2", "M3", 13);
        graph.addEdge("SQ3", "SQ5", 20);
        graph.addEdge("SQ3", "M2", 8);
        graph.addEdge("SQ3", "M5", 4);
        graph.addEdge("M5", "SQ4", 14);
        graph.addEdge("M5", "SQ13", 13);
        graph.addEdge("SQ13", "SQ4", 15);
        graph.addEdge("SQ4", "SQ5", 8);
        graph.addEdge("SQ5", "M3", 9);
        graph.addEdge("M3", "SQ11", 9);
        graph.addEdge("SQ11", "F1", 15);
        graph.addEdge("M3", "SQ6", 7);
        graph.addEdge("M3", "SQ14", 10);
        graph.addEdge("SQ5", "SQ7", 10);
        graph.addEdge("SQ4", "M4", 14);
        graph.addEdge("SQ13", "SQ10", 16);
        graph.addEdge("SQ6", "F1", 8);
        graph.addEdge("SQ6", "F2", 12);
        graph.addEdge("SQ14", "F2", 16);
        graph.addEdge("SQ14", "SQ7", 9);
        graph.addEdge("M4", "SQ7", 10);
        graph.addEdge("M4", "SQ10", 8);
        graph.addEdge("SQ7", "F2", 13);
        graph.addEdge("M4", "F3", 17);
        graph.addEdge("M4", "SQ12", 10);
        graph.addEdge("SQ10", "SQ12", 15);
        graph.addEdge("SQ7", "F3", 15);
        graph.addEdge("SQ12", "F3", 9);

        // (kode penambahan edge tetap sama seperti sebelumnya)

        int initialEnergy = 250; // Energi awal pemain

        // Step 1: Konfirmasi kesiapan bermain
        System.out.println("Are you ready to play the Quest Navigation game? (Yes/No): ");
        String ready = scanner.next();

        if (!ready.equalsIgnoreCase("Yes")) {
            System.out.println("See you next time!");
            return;
        }

        // Step 2: Menampilkan pilihan jalur start
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

        // Step 5: Menampilkan pilihan monster
        System.out.println("Choose the monster to defeat (Enter the number):");
        System.out.println("1. Infernal Dragon");
        System.out.println("2. Dark Leviathan");
        System.out.println("3. Blood Wraith");

        int choice = scanner.nextInt();
        Monster chosenMonster = null;

        switch (choice) {
            case 1 -> chosenMonster = new Monster(1, "Infernal Dragon", 60);
            case 2 -> chosenMonster = new Monster(2, "Dark Leviathan", 50);
            case 3 -> chosenMonster = new Monster(3, "Blood Wraith", 60);
            default -> {
                System.out.println("Invalid choice. Please select a valid monster.");
                return;
            }
        }

        System.out.println("You have chosen to fight " + chosenMonster.getName() + "!");

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
                displayQuestOptions(currentQuest);
                int questChoice = scanner.nextInt();

                if (questChoice < 1 || questChoice > 3) {
                    System.out.println("Invalid quest choice. Please select a valid quest.");
                    continue;
                }

                currentQuest = getNextQuest(currentQuest, questChoice);

                if (currentQuest.equals("Finish")) {
                    System.out.println("You have reached the finish point of your journey!");
                    break;
                }
            }
        }
    }

    // Menampilkan quest options berdasarkan jalur quest
    private static void displayQuestOptions(String currentQuest) {
        switch (currentQuest) {
            case "S1" -> {
                System.out.println("1. SQ8 (Energy required: 15)");
                System.out.println("2. SQ2 (Energy required: 17)");
                System.out.println("3. M1 (Energy required: 10)");
            }
            // Case tambahan lainnya
        }
    }

    // Mendapatkan quest berikutnya berdasarkan pilihan
    private static String getNextQuest(String currentQuest, int questChoice) {
        switch (currentQuest) {
            case "S1":
                if (questChoice == 1) return "SQ8";
                if (questChoice == 2) return "SQ2";
                if (questChoice == 3) return "M1";
                break;
            case "S2":
                if (questChoice == 1) return "SQ2";
                if (questChoice == 2) return "SQ3";
                if (questChoice == 3) return "M5";
                break;
            case "S3":
                if (questChoice == 1) return "SQ9";
                if (questChoice == 2) return "M5";
                break;
            case "SQ8":
                if (questChoice == 1) return "SQ15";
                if (questChoice == 2) return "SQ1";
                break;
            case "SQ2":
                if (questChoice == 1) return "M2";
                if (questChoice == 2) return "SQ9";
                if (questChoice == 3) return "SQ10";
                break;
            case "M1":
                if (questChoice == 1) return "SQ6";
                if (questChoice == 2) return "M3";
                break;
            case "SQ3":
                if (questChoice == 1) return "M4";
                if (questChoice == 2) return "SQ4";
                break;
            case "SQ9":
                if (questChoice == 1) return "M6";
                if (questChoice == 2) return "SQ12";
                break;
            case "SQ15":
                if (questChoice == 1) return "Finish";
                if (questChoice == 2) return "SQ13";
                break;
            case "SQ1":
                if (questChoice == 1) return "M7";
                if (questChoice == 2) return "Finish";
                break;
            case "SQ6":
                if (questChoice == 1) return "SQ5";
                if (questChoice == 2) return "M8";
                break;
            case "SQ12":
                if (questChoice == 1) return "M9";
                if (questChoice == 2) return "SQ7";
                break;
            case "M5":
                if (questChoice == 1) return "SQ11";
                if (questChoice == 2) return "SQ14";
                break;
            case "SQ4":
                if (questChoice == 1) return "SQ15";
                if (questChoice == 2) return "Finish";
                break;
            case "M8":
                if (questChoice == 1) return "SQ16";
                if (questChoice == 2) return "SQ17";
                break;
            case "SQ7":
                if (questChoice == 1) return "SQ18";
                if (questChoice == 2) return "Finish";
                break;
            case "SQ5":
                if (questChoice == 1) return "M10";
                if (questChoice == 2) return "Finish";
                break;
            case "M3":
                if (questChoice == 1) return "Finish";
                if (questChoice == 2) return "SQ19";
                break;
            case "SQ14":
                if (questChoice == 1) return "M11";
                if (questChoice == 2) return "Finish";
                break;
            case "SQ17":
                if (questChoice == 1) return "SQ20";
                if (questChoice == 2) return "Finish";
                break;
            case "SQ19":
                if (questChoice == 1) return "SQ21";
                if (questChoice == 2) return "Finish";
                break;
        }
        return "Finish"; // Default jika tidak ada pilihan yang cocok
    }

}
