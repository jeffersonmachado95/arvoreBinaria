class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}


public class BinaryTree {
    Node root;

    
    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.data)
            root.left = insertRec(root.left, key);
        else if (key > root.data)
            root.right = insertRec(root.right, key);

        return root;
    }

    
    public void remove(int key) {
        root = removeRec(root, key);
    }

    private Node removeRec(Node root, int key) {
        if (root == null) return root;

        if (key < root.data)
            root.left = removeRec(root.left, key);
        else if (key > root.data)
            root.right = removeRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = removeRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    
    public void printInOrder() {
        System.out.print("Em ordem: ");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    
    public void printPreOrder() {
        System.out.print("Pré-ordem: ");
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    
    public void printPostOrder() {
        System.out.print("Pós-ordem: ");
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    
    public boolean isStrictlyBinary(Node node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.right != null)
            return isStrictlyBinary(node.left) && isStrictlyBinary(node.right);
        return false;
    }

    
    public boolean isFull(Node node) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.right != null)
            return isFull(node.left) && isFull(node.right);
        return false;
    }

    
    public boolean isComplete() {
        int nodeCount = countNodes(root);
        return isCompleteRec(root, 0, nodeCount);
    }

    private boolean isCompleteRec(Node node, int index, int nodeCount) {
        if (node == null) return true;
        if (index >= nodeCount) return false;
        return isCompleteRec(node.left, 2 * index + 1, nodeCount)
                && isCompleteRec(node.right, 2 * index + 2, nodeCount);
    }

    private int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    
    public int getHeight() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return -1; // altura de árvore vazia é -1
        return 1 + Math.max(height(node.left), height(node.right));
    }

    
    public int getDegree(int value) {
        Node node = searchNode(root, value);
        if (node == null) return -1;
        int degree = 0;
        if (node.left != null) degree++;
        if (node.right != null) degree++;
        return degree;
    }

    private Node searchNode(Node node, int value) {
        if (node == null || node.data == value)
            return node;
        if (value < node.data)
            return searchNode(node.left, value);
        else
            return searchNode(node.right, value);
    }

    
    public boolean search(int key) {
        return searchRec(root, key) != null;
    }

    private Node searchRec(Node root, int key) {
        if (root == null || root.data == key)
            return root;
        if (key < root.data)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserção de dados
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.printInOrder();
        tree.printPreOrder();
        tree.printPostOrder();

        System.out.println("Altura da árvore: " + tree.getHeight());

        System.out.println("Grau do nó 30: " + tree.getDegree(30));
        System.out.println("Elemento 60 encontrado? " + tree.search(60));

        System.out.println("Árvore é estritamente binária? " + tree.isStrictlyBinary(tree.root));
        System.out.println("Árvore é cheia? " + tree.isFull(tree.root));
        System.out.println("Árvore é completa? " + tree.isComplete());

        
        tree.remove(20);
        tree.printInOrder();
    }
}
