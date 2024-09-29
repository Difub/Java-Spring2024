import java.util.Random;

public class Player {
    // Перечисление возможных вариантов
    public enum VARIANTS { ROCK, PAPER, SCISSORS };

    private String name;  // Имя игрока
    private VARIANTS choice;  // Выбор игрока

    // Конструктор без параметров (для бота)
    public Player() {
        this.name = "Bot";
        Random random = new Random();
        int index = random.nextInt(3);  // Случайный выбор между 0, 1, 2
        this.choice = VARIANTS.values()[index];
    }

    // Конструктор с параметрами (для пользователя)
    public Player(VARIANTS choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    // Метод для определения победителя
    public static String whoWins(Player player1, Player player2) {
        if (player1.choice == player2.choice) {
            return "Ничья";
        } else if (
                (player1.choice == VARIANTS.ROCK && player2.choice == VARIANTS.SCISSORS) ||
                        (player1.choice == VARIANTS.PAPER && player2.choice == VARIANTS.ROCK) ||
                        (player1.choice == VARIANTS.SCISSORS && player2.choice == VARIANTS.PAPER)) {
            return player1.name + " выиграл!";
        } else {
            return player2.name + " выиграл!";
        }
    }
}