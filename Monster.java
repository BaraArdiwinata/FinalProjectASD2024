public class Monster {
    private int id; // ID monster
    private String name;
    private int requiredCoins; // Coins yang dibutuhkan untuk mengalahkan monster
    private BinarySearchTree bst;
    private int value; // Nilai monster

    public Monster(int id, String name, int requiredCoins) {
        this.id = id;
        this.name = name;
        this.requiredCoins = requiredCoins;
        this.bst = new BinarySearchTree();  // Inisialisasi Binary Search Tree
    }

    // Getter untuk ID monster
    public int getId() {
        return id;
    }

    // Getter untuk nama monster
    public String getName() {
        return name;
    }

    // Getter untuk nilai monster
    public int getValue() {
        return value;
    }

    // Getter untuk coins yang dibutuhkan
    public int getRequiredCoins() {
        return requiredCoins;
    }

    // Menambahkan nilai ke dalam BST
    public void addVitalObject(int value) {
        bst.insert(value);
    }

    // Menyerang monster
    public void attack() {
        System.out.println("You are attacking " + name + "!");
    }

    // Menemukan objek vital berdasarkan nilai tertentu
    public boolean findVitalObject(int attackValue) {
        return bst.search(attackValue); // Mencari apakah attackValue ada dalam BST
    }

    // Mendapatkan depth dari objek vital di BST
    public int getDepth(int value) {
        return bst.findDepth(value);
    }

    // Mengalahkan monster
    public void defeatMonster() {
        System.out.println(name + " has been defeated!");
    }

    // Setter untuk menetapkan nilai monster
    public void setValue(int value) {
        this.value = value;
    }
}

