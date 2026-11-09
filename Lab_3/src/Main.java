import java.util.Scanner;

/**
 * Лабораторная работа №3
 * Thierno Adama Diongue
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дерево в прямом порядке (корень, левое, правое), 0 — пустое место:");

        BinaryTree tree = new BinaryTree();
        tree.read(scanner);

        System.out.println("Дерево до вращения (корень слева, правые потомки сверху):");
        tree.print();

        tree.findMostUnbalanced();
        if (tree.bestNode == null) {
            System.out.println("Все поддеревья сбалансированы, вращение не нужно.");
            return;
        }

        System.out.println("Самое разбалансированное поддерево: вершина " + tree.bestNode.value
                + ", баланс = " + tree.balance(tree.bestNode));

        tree.rotateMostUnbalanced();

        System.out.println("Дерево после вращения:");
        tree.print();
    }
}