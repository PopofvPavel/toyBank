import BackendSystem.BalanceSystemUpdater;
import BackendSystem.BankBackEndSystem;
import Client.Client;
import FrontalSystem.FrontalSystem;
import Request.Request;
import Request.RequestType;
import RequestProcessor.RequestProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        BankBackEndSystem bankBackEndSystem = new BankBackEndSystem();

        FrontalSystem frontalSystem = new FrontalSystem();
        RequestProcessor requestProcessor1 = new RequestProcessor(1, frontalSystem, bankBackEndSystem);
        RequestProcessor requestProcessor2 = new RequestProcessor(2, frontalSystem, bankBackEndSystem);;


        List<Callable<Void>> tasks = new ArrayList<>();
        ExecutorService bankUpdateBalanceExecutorService = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 4; i++) {
            BalanceSystemUpdater balanceSystemUpdater = new BalanceSystemUpdater(i);
            tasks.add(()->{
                try {
                    int sum = balanceSystemUpdater.getRandomBalance();
                    bankBackEndSystem.updateBankBalance(sum);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            });
        }
        try {
            bankUpdateBalanceExecutorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            bankUpdateBalanceExecutorService.shutdown();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(requestProcessor1);
        executorService.submit(requestProcessor2);


        for (int i = 1; i <= 5; i++) {
            Request request = createRequest(i);
            Client client = new Client(i, frontalSystem, request);
            executorService.submit(client);
        }

        // Завершаем работу пула потоков после выполнения всех задач
        executorService.shutdown();
    }


    private static Request createRequest(int clientId) {
        switch (clientId) {
            case 1:
                return new Request(clientId, RequestType.RECEIVE_MONEY, 500);
            case 2:
                return new Request(clientId, RequestType.PAY_MONEY, 500);
            case 3:
                return new Request(clientId, RequestType.RECEIVE_MONEY, 300);
            case 4:
                return new Request(clientId, RequestType.RECEIVE_MONEY, 1000);
            case 5:
                return new Request(clientId, RequestType.PAY_MONEY, 25_000_000);
            default:
                throw new IllegalArgumentException("Invalid clientId: " + clientId);
        }
    }
}
