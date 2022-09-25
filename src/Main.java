
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger groupCountThree = new AtomicInteger(0);
    public static AtomicInteger groupCountFour = new AtomicInteger(0);
    public static AtomicInteger groupCountFive = new AtomicInteger(0);

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    //проверка на палиндром
    public static boolean isPolindrome(String str) {
        int leftPoiner = 0;
        int rightPointer = str.length() - 1;
        while (rightPointer > leftPoiner) {
            if (str.charAt(rightPointer) != str.charAt(leftPoiner)) {
                return false;
            }
            rightPointer--;
            leftPoiner++;
        }
        return true;
    }

    //Проверка на одинаковые буквы
    public static boolean isSameLetters(String str) {
        char template = str.charAt(0);
        int pointer = 1;
        while (pointer < str.length()) {
            if (str.charAt(pointer) != template) {
                return false;
            }
            pointer++;
        }
        return true;
    }

    //Проверка на повторение
    public static boolean isAlphabetic(String str) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        if (!alphabet.contains(str)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 3 && (isSameLetters(s) || isPolindrome(s) || isAlphabetic(s))) {
                    groupCountThree.getAndIncrement();
                }
            }
            System.out.printf("Красивых слов с длиной 3: %d \n", groupCountThree.get());
        }).start();

        new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 4 && (isSameLetters(s) || isPolindrome(s))) {
                    groupCountFour.getAndIncrement();
                }
            }
            System.out.printf("Красивых слов с длиной 4: %d \n", groupCountFour.get());
        }).start();

        new Thread(() -> {
            for (String s : texts) {
                if (s.length() == 5 && (isSameLetters(s) || isPolindrome(s))) {
                    groupCountFive.getAndIncrement();
                }
            }
            System.out.printf("Красивых слов с длиной 5: %d \n", groupCountFive.get());
        }).start();
    }
}