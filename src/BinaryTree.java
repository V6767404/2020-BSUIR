//https://www.codeflow.site/ru/article/java-binary-tree

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    static Node root;

    void add(int value) {
        root = addRecursive(root, value);
    }

    static Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    //       void preOrder(Node node, ArrayList<Node> list) {
    //        if (node == null) {
    //            return;
    //        }
    //        list.add(node);
    //        node.level = lvl;
    //        height = lvl;
    //        lvl++;
    //        preOrder(node.left, list);
    //        preOrder(node.right, list);
    //        lvl--;
    //    }

    // программа расчета количества детей у узла
    //https://www.geeksforgeeks.org/count-full-nodes-binary-tree-iterative-recursive/

    public int countChildren(Node node) {
        if(root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int number = 0;
        while(!queue.isEmpty()) {
            Node tmpNode = queue.poll();
            if(tmpNode.left != null) {
                queue.add(tmpNode.left);
                number++;
            }
            if(tmpNode.right != null) {
                queue.add(tmpNode.right);
                number++;
            }
        }
        return number;
    }

    // вывод дерева на экран с визуализацией

    void printTree(Node root) {
        printBinaryTree(root, 0);
    }

    void printBinaryTree(Node root, int level) {
        if (root == null) {
            return;
        }
        printBinaryTree(root.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                System.out.print("|\t");
            System.out.println("|-------------*" + root.value + "(" +countChildren(root)+ ")");
        } else {
            System.out.println(root.value+ "(" +countChildren(root)+ ")");
        }
        printBinaryTree(root.left, level + 1);
    }
}