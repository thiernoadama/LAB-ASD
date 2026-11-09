import java.util.Scanner;

public class Task9 {

    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    static class CharStack {
        private Node top;
        CharStack() {
            top = null;
        }

        void push(char c) {
            Node newNode = new Node(c);
            newNode.next = top;
            top = newNode;
        }

        char pop() {
            char value = top.data;
            top = top.next;
            return value;
        }

        boolean isEmpty() {
            return top == null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку a (чётное число символов): ");
        String a = scanner.nextLine();

        int n = a.length();
        if (n % 2 != 0) {
            System.out.println("Ошибка: длина строки должна быть чётной.");
            return;
        }

        int half = n / 2;

        String firstHalf = a.substring(0, half);
        String secondHalf = a.substring(half);

        String b = reverseManually(firstHalf) + reverseManually(secondHalf);

        System.out.println("b = " + b);
    }

    private static String reverseManually(String s) {
        CharStack stack = new CharStack();

        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}

