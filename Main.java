import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Step 1: Mulai permainan
        System.out.println("Starting the game...");

        // Step 2: Menampilkan pilihan monster
        System.out.println("Choose the monster to defeat (Enter the number):");
        System.out.println("1. Monster 1");
        System.out.println("2. Monster 2");
        System.out.println("3. Monster 3");

        // Step 3: Input pilihan monster dari pemain
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Step 4: Validasi pilihan dan tampilkan monster yang dipilih
        if (choice == 1 || choice == 2 || choice == 3) {
            System.out.println("You have chosen to fight Monster " + choice + "!");
        } else {
            System.out.println("Invalid choice. Please select a valid monster.");
            return; // Exit the game if an invalid choice is made
        }

        // Step 5: Menampilkan energi awal pemain
        int initialEnergy = 250;
        System.out.println("Your initial energy is: " + initialEnergy);

        // Step 6: Memberikan pilihan apakah pemain ingin menggunakan pusaka atau tidak
        System.out.println("Do you want to use the pusaka? (Yes/No): ");
        String usePusaka = scanner.next();

        // Step 7: Jika pemain memilih untuk menggunakan pusaka
        if (usePusaka.equalsIgnoreCase("Yes")) {
            System.out.println("You chose to use the pusaka. You will fight the monster directly.");
            initialEnergy -= 100; // Mengurangi energi pemain sebanyak 100
            System.out.println("Your energy is reduced by 100. Remaining energy: " + initialEnergy);

            // Step 8: Pemain memasukkan angka untuk melawan monster
            System.out.println("Enter a random number between 1 and 100 to attack the monster: ");
            int playerChoice = scanner.nextInt();

            // Step 9: Mencari depth dari angka yang dimasukkan dalam BST VitalObject
            VitalObject vitalObject = new VitalObject();
            int depth = vitalObject.findDepth(playerChoice);

            if (depth == -1) {
                System.out.println("The number " + playerChoice + " is not found in the BST. You failed to defeat the monster.");
                return; // Game ends if the number is not found in the BST
            }

            // Step 10: Menghitung koin yang didapatkan berdasarkan depth
            int coinsEarned = depth * 10;
            System.out.println("You earned " + coinsEarned + " coins for reaching depth " + depth);

            // Step 11: Mengecek apakah koin yang dimiliki cukup untuk mengalahkan monster
            int requiredCoins = (choice == 2) ? 50 : 60;
            if (coinsEarned >= requiredCoins) {
                System.out.println("Monster " + choice + " defeated!");
                System.out.println("You received " + coinsEarned + " coins as a reward.");
            } else {
                System.out.println("You do not have enough coins to defeat Monster " + choice + ".");
                System.out.println("You need " + requiredCoins + " coins to defeat the monster.");
            }
        } else {
            // Step 12: Jika pemain memilih tidak menggunakan pusaka, maka lanjutkan ke quest
            System.out.println("You chose not to use the pusaka. You will embark on side quests and main quests.");
            // Logika untuk quest bisa diterapkan di sini
        }
    }
}
