import java.util.Scanner;

public class BinaryTree {
    Node root;
    Node bestNode;
    int bestBalance;

    void read(Scanner scanner) {
        root = build(scanner);
    }

    private Node build(Scanner scanner) {
        int x = scanner.nextInt();
        if (x == 0) {
            return null;
        }
        Node node = new Node(x);
        node.left = build(scanner);
        node.right = build(scanner);
        return node;
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + MathUtils.max(height(node.left), height(node.right));
    }

    int balance(Node node) {
        return height(node.left) - height(node.right);
    }

    void findMostUnbalanced() {
        bestNode = null;
        bestBalance = 0;
        search(root);
    }

    private void search(Node node) {
        if (node == null) {
            return;
        }
        int b = MathUtils.abs(balance(node));
        if (b > bestBalance) {
            bestBalance = b;
            bestNode = node;
        }
        search(node.left);
        search(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node rebalance(Node node) {
        int b = balance(node);
        if (b > 0) {
            if (height(node.left.left) < height(node.left.right)) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (b < 0) {
            if (height(node.right.right) < height(node.right.left)) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    void rotateMostUnbalanced() {
        root = rotateAt(root, bestNode);
    }

    private Node rotateAt(Node current, Node target) {
        if (current == null) {
            return null;
        }
        if (current == target) {
            return rebalance(current);
        }
        current.left = rotateAt(current.left, target);
        current.right = rotateAt(current.right, target);
        return current;
    }

    void print() {
        printTree(root, 0);
    }

    private void printTree(Node node, int depth) {
        if (node == null) {
            return;
        }
        printTree(node.right, depth + 1);
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(node.value);
        printTree(node.left, depth + 1);
    }
}