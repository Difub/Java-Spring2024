public class Main {
    public static void main(String[] args) {
        int[][] numbers =  { {20, 34, 2}, {9, 12, 18}, {3, 4, 5}, {9, 0, -18} };
        int min = numbers[0][0];

        for (int[] num: numbers) {
            for (int i: num) {
                if (i < min)
                    min = i;
            }
        }

        System.out.println(min);
    }
}