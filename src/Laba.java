//Ввести коды сотрудников из файла TREE.TXT, определить общее количество подчиненных у каждого сотрудника.
//Визуализировать дерево

import java.io.*;
import java.util.Scanner;

public class Laba {
    static BinaryTree tree;

    public static void main(String[] args) {
        System.out.println("Данная программа строит бинарное дерево и рассчитывает количество детей узлов");
        displayMenu();
    }

    static void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Ввод дерева");
        System.out.println("2. Выполнение задания и визуализация дерева");
        System.out.println("3. Выход из программы");
        System.out.println("Ваш выбор:");

        Scanner sc = new Scanner(System.in);
               while (!sc.hasNextInt()) {
            System.out.println("Это не число!");
            sc.next();
        }
         int i = sc.nextInt();
         checkUserSelection(i);
    }

    static void checkUserSelection(int selection) {
        switch (selection) {

            case 1: {
                tree = new BinaryTree();
                char choice = chooseMethod();
                inputData(choice);
                break;
            }
            case 2: {
                tree.printTree(BinaryTree.root);
                break;
            }
            case 3:
                System.exit(0);
            default: {
                System.out.println("Неправильный выбор! Введите от 1 до 3 включительно!");
                break;
            }
        }
        displayMenu();
    }

    static char chooseMethod() {
        String choice;
        boolean isNotCorrect;
        System.out.println("Пожалуйста, нажмите клавишу F, если вы хотите использовать файл.");
        System.out.println("Пожалуйста, нажмите клавишу С, если вы хотите использовать консоль.");
        Scanner scan = new Scanner(System.in);
        do {
            isNotCorrect = false;
            choice = scan.nextLine();
            if ((!choice.equals("F")) && (!choice.equals("C"))) {
                System.out.println("Вы можете ввести только F или C, пожалуйста попробуйте ещё раз.");
                isNotCorrect = true;
            }
        } while (isNotCorrect);
        return choice.charAt(0);
    }

    static void inputData(char choice) {
        if (choice == 'F') {
            fileInput();
        } else {
            consoleInput();
        }
    }

    // -- Ввод двнных с консоли

    static void consoleInput() {
        Scanner scan = new Scanner(System.in);
        String str;
        boolean isNotCorrect = false;
        do {
            System.out.println("Пожалуйста, введите целые числа через пробел: ");
            str = scan.nextLine();
            if (isOnlySpaces(str) || str.length() == 0) {
                System.err.println("Вы ввели пустую строку: ");
                isNotCorrect = true;
            }
        } while (isNotCorrect || isNumeric(str));
        str = deleteSpaces(str);
        String[] strArr = str.split(" ");
        int[] arr = new int[strArr.length];
        toArrayInt(arr, strArr);
        for (int a : arr) {
            tree.add(a);
        }
        System.out.println("Данные приняты!");
    }

    // -- Ввод двнных с файла

    static void fileInput() {
        String fileAddress;
        String seq = "";
        boolean isNotCorrect = true;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Пожалуйста, создайте файл Tree.txt и введите в него числа одной строкой через пробел");
            System.out.print("Пожалуйста, укажите адрес вашего входного файла: ");
            fileAddress = scan.next();
            try {
                BufferedReader input = new BufferedReader(new FileReader(fileAddress));
                seq = input.readLine();
                input.close();
                if (seq != null && !isOnlySpaces(seq)) {
                    isNotCorrect = false;
                    System.out.println("Данные из файла введены успешно");
                } else {
                    System.out.println("Вы пытаетесь ввести файл с пустой строкой");
                }
            } catch (FileNotFoundException e) {
                System.out.println("\nФайла с таким именем не существует. Пожалуйста, проверьте имя.");
                isNotCorrect = true;
            } catch (IOException e) {
                System.out.println("\nНе удается открыть этот файл. Пожалуйста, попробуйте еще раз.");
                isNotCorrect = true;
            }
        } while (isNotCorrect || isNumeric(seq));
        seq = deleteSpaces(seq);
        String[] strArr = seq.split(" ");
        int[] arr = new int[strArr.length];
        toArrayInt(arr, strArr);
        for (int a : arr) {
            tree.add(a);
        }
    }

    static boolean isNumeric(String str) { //проверка строки на содержание (строка и чисел через пробел)
        String st = deleteSpaces(str);
        String[] split = st.split(" ");
        boolean isNotNumeric = true;
        int i = 0;
        while (i < split.length && isNotNumeric) {
            String retval = split[i];
            try {
                Integer.parseInt(retval);
                i++;
            } catch (NumberFormatException nfe) {
                System.out.println("\nВаша строка состоит не из целых чисел");
                isNotNumeric = false;
            }
        }
        return !isNotNumeric;
    }

    static boolean isOnlySpaces(String str) { // поверка пустой строки
        boolean isTrue = true;
        int i = 0;
        while (i < str.length() && isTrue) {
            if (str.charAt(i) != ' ') {
                isTrue = false;
            }
            i++;
        }
        return isTrue;
    }

    static String deleteSpaces(String str) {  // удаление лишних пробелов
        while (str.contains("  ")) {
            str = str.replace("  ", " ");
        }
        if (str.charAt(0) == ' ') {
            str = str.substring(1);
        }
        if (str.charAt(str.length() - 1) == ' ') {
            str = str.substring(0, str.length() - 2);
        }
        return str;
    }

    static void toArrayInt(int[] array, String[] strArr) { // массив строк в массив чисел
        for (int i = 0; i < strArr.length; i++) {
            array[i] = Integer.parseInt(strArr[i]);
        }
    }

}