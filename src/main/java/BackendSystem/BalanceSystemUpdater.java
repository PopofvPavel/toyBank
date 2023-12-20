package BackendSystem;

import java.util.Random;

public class BalanceSystemUpdater {
    private final int idBalanceSystemUpdater;
    public final int MAX_RANDOM_VALUE = 500;
    public final int MAX_DELAY = 3000;

    public BalanceSystemUpdater(int idBalanceSystemUpdater) {
        this.idBalanceSystemUpdater = idBalanceSystemUpdater;
    }


    public int getRandomBalance() throws InterruptedException {
        Thread.sleep(getRandomDelay());
        int randomBalance = generateRandomBalance();
        System.out.println("Генерируем рандомное значение: " + randomBalance + " в системе " + idBalanceSystemUpdater);
        return randomBalance;
    }

    private int generateRandomBalance() {
        return new Random().nextInt(MAX_RANDOM_VALUE);
    }

    private long getRandomDelay() {
        Random random = new Random();
        return random.nextInt(MAX_DELAY);

    }

}
