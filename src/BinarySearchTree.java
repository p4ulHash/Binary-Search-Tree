
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree implements BSTInterface {

    // Innere Klasse, die einen Knoten im Baum darstellt (Schritt 2)
    private class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private TreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    //Leert den Suchbaum
    @Override
    public void clear() {
        this.root = null;
    }

    //Prueft, ob ein Wert im Baum existiert (rekursiv)//
    @Override
    public boolean exists(int value) {
        return existsRecursive(this.root, value);
    }

    // Rekursive Hilfsmethode fuer exists (Schritt 3)
    private boolean existsRecursive(TreeNode node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        } else if (value < node.value) {
            return existsRecursive(node.left, value);
        } else {
            return existsRecursive(node.right, value);
        }
    }

    //Fuegt einen Wert in den Baum ein, falls er noch nicht existiert (rekursiv)
    @Override
    public void insert(int value) throws ElementExistsException {
        // Pruefen, ob Element bereits vorhanden ist (Schritt 4)
        if (exists(value)) {
            throw new ElementExistsException("Element " + value + " existiert bereits im Baum.");
        }

        if (this.root == null) {
            this.root = new TreeNode(value);
        } else {
            insertRecursive(this.root, value);
        }
    }

    // Rekursive Hilfsmethode fuer insert (Schritt 3)
    private void insertRecursive(TreeNode current, int value) {
        if (value < current.value) {
            if (current.left == null) {
                TreeNode newNode = new TreeNode(value);
                current.left = newNode;
                newNode.parent = current; // Elternreferenz setzen
            } else {
                insertRecursive(current.left, value);
            }
        } else if (value > current.value) {
            if (current.right == null) {
                TreeNode newNode = new TreeNode(value);
                current.right = newNode;
                newNode.parent = current; // Elternreferenz setzen
            } else {
                insertRecursive(current.right, value);
            }
        }
    }

    //Entfernt einen Wert aus dem Baum
    @Override
    public void remove(int value) throws NoSuchElementException {
        TreeNode nodeToRemove = find(this.root, value);
        if (nodeToRemove == null) {
            throw new NoSuchElementException("Element " + value + " wurde im Baum nicht gefunden.");
        }
        removeNode(nodeToRemove);
    }

    // --- Hilfsmethoden fuer remove (Schritt 5)

    // Findet den Knoten mit dem gesuchten Wert
    private TreeNode find(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (value == node.value) {
            return node;
        } else if (value < node.value) {
            return find(node.left, value);
        } else {
            return find(node.right, value);
        }
    }

    // Bestimmt die Anzahl der Kinder eines Knotens
    private int numChildren(TreeNode node) {
        int count = 0;
        if (node.left != null) count++;
        if (node.right != null) count++;
        return count;
    }

    // Ersetzt die Referenz auf einen Knoten im Elternknoten
    private void replaceInParent(TreeNode node, TreeNode newNode) {
        if (node.parent == null) {
            // Knoten ist die Wurzel
            this.root = newNode;
        } else if (node == node.parent.left) {
            node.parent.left = newNode;
        } else if (node == node.parent.right) {
            node.parent.right = newNode;
        }

        // Elternreferenz des neuen Knotens aktualisieren
        if (newNode != null) {
            newNode.parent = node.parent;
        }
    }

    // Interne Methode zum Entfernen eines Knotens (Implementierung der 3 Faelle)
    private void removeNode(TreeNode node) {
        int children = numChildren(node);

        if (children == 0) {
            // Fall 1: Keine Kinder (Blatt)
            replaceInParent(node, null);
        } else if (children == 1) {
            // Fall 2: Ein Kind
            TreeNode child = (node.left != null) ? node.left : node.right;
            replaceInParent(node, child);
        } else {
            // Fall 3: Zwei Kinder
            // Nachfolger finden (kleinster Knoten im rechten Teilbaum)
            TreeNode successor = findMin(node.right);
            // Wert ersetzen
            node.value = successor.value;
            // Nachfolger-Knoten entfernen (dieser hat 0 oder 1 Kind)
            removeNode(successor);
        }
    }

    // Hilfsmethode: Findet den Knoten mit dem kleinsten Wert in einem Teilbaum
    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Traversierungsmethoden

    @Override
    public List<Integer> inOrderList() {
        List<Integer> result = new ArrayList<>();
        inOrderRecursive(this.root, result);
        return result;
    }

    private void inOrderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.value);
            inOrderRecursive(node.right, result);
        }
    }

    @Override
    public List<Integer> preOrderList() {
        List<Integer> result = new ArrayList<>();
        preOrderRecursive(this.root, result);
        return result;
    }

    private void preOrderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrderRecursive(node.left, result);
            preOrderRecursive(node.right, result);
        }
    }

    @Override
    public List<Integer> postOrderList() {
        List<Integer> result = new ArrayList<>();
        postOrderRecursive(this.root, result);
        return result;
    }

    private void postOrderRecursive(TreeNode node, List<Integer> result) {
        if (node != null) {
            postOrderRecursive(node.left, result);
            postOrderRecursive(node.right, result);
            result.add(node.value);
        }
    }

    // Main-Methode zum Testen (Schritt 6)
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        try {
            System.out.println("Fuege Elemente ein: 50, 30, 20, 40, 70, 60, 80");
            bst.insert(50);
            bst.insert(30);
            bst.insert(20);
            bst.insert(40);
            bst.insert(70);
            bst.insert(60);
            bst.insert(80);

            // Test der Traversierungen
            System.out.println("In-Order (Sortiert): " + bst.inOrderList());
            System.out.println("Pre-Order: " + bst.preOrderList());
            System.out.println("Post-Order: " + bst.postOrderList());

            // Test von Exists
            System.out.println("Existiert 40? " + bst.exists(40));
            System.out.println("Existiert 99? " + bst.exists(99));

            // Test Einfuegen eines Duplikats
            try {
                System.out.println("Versuche 50 erneut einzufuegen...");
                bst.insert(50);
            } catch (ElementExistsException e) {
                System.out.println("Erwartete Exception gefangen: " + e.getMessage());
            }

            // Test Entfernen (Blatt)
            System.out.println("Entferne 20 (Blatt)...");
            bst.remove(20);
            System.out.println("In-Order: " + bst.inOrderList());

            // Test Entfernen (1 Kind)
            System.out.println("Entferne 30 (Knoten mit 1 Kind [40])...");
            bst.remove(30);
            System.out.println("In-Order: " + bst.inOrderList());

            // Test Entfernen (2 Kinder)
            System.out.println("Entferne 50 (Wurzel mit 2 Kindern)...");
            bst.remove(50);
            System.out.println("In-Order: " + bst.inOrderList());
            System.out.println("Pre-Order (ueberpruefung der neuen Wurzel): " + bst.preOrderList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}