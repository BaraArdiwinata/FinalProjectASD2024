public class VitalObject {
    private BinarySearchTree bst;

    public VitalObject() {
        bst = new BinarySearchTree();
    }

    // Menambahkan nilai ke dalam BST
    public void addObject(int value) {
        bst.insert(value);
    }

    // Menemukan kedalaman dari objek vital berdasarkan nilai
    public int findDepth(int value) {
        return bst.findDepth(value);
    }
}
