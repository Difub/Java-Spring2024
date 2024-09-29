//import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;

        for (int num = 0; num < 1000; num++) {
            if (Div(3,num) || Div(5, num))
                count += num;
        }

        System.out.println(count);
    }

    static boolean Div(int divider, int num){
        if (num % divider == 0) return true;
        return false;
    }
}