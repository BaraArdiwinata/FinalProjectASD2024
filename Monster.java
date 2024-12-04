public class Monster {
    private int id; // ID monster
    private String name;
    private int value; // Coins yang dibutuhkan untuk mengalahkan monster
    private BinarySearchTree bst;

    public Monster(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.bst = new BinarySearchTree();
    }

    // Getter untuk ID monster
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    // Menambahkan nilai ke dalam BST
    public void addValueToBST(int value) {
        bst.insert(value);
    }

    // Metode untuk menyerang monster
    public void attack() {
        System.out.println("You are attacking " + name + "!");
    }

    // Metode untuk menemukan objek vital berdasarkan nilai tertentu
    public boolean findVitalObject(int attackValue) {
        return bst.search(attackValue); // Mencari apakah attackValue ada dalam BST
    }

    // Metode untuk mengalahkan monster
    public void defeatMonster() {
        System.out.println(name + " has been defeated!");
    }
}
