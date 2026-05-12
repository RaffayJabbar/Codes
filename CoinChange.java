public class CoinChange {
    public static void main(String[] args) {
        int amount = 1988;
        int[] denominations = {5000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};

        System.out.println("Denominations for Rs. " + amount + ":");
        for (int coin : denominations) {
            if (amount >= coin) {
                int count = amount / coin;
                amount %= coin;
                System.out.println("Rs. " + coin + " x " + count);
            }
        }
    }
}