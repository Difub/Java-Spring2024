public class Main {
    public static void main(String[] args) {
        // Создаем объекты
        Player bot = new Player();
        Player alex = new Player(Player.VARIANTS.SCISSORS, "Alex");

        // Получаем результат
        System.out.println(Player.whoWins(bot, alex));
    }
}