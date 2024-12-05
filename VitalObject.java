public class VitalObject {
    private BinarySearchTree bst;

    public VitalObject() {
        bst = new BinarySearchTree(); // Inisialisasi BinarySearchTree
    }

    // Menambahkan nilai ke dalam BST
    public void addObject(int value) {
        bst.insert(value); // Menyisipkan objek vital ke dalam BST
    }

    // Menemukan kedalaman dari objek vital berdasarkan nilai
    public int findDepth(int value) {
        return bst.findDepth(value); // Mengambil kedalaman objek dari nilai yang dicari
    }
}
