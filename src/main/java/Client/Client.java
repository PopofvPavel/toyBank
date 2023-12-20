package Client;

import FrontalSystem.FrontalSystem;
import Request.Request;

public class Client implements Runnable {

    private final int clientId;
    private final FrontalSystem frontalSystem;
    private final Request request;

    public Client(int clientId, FrontalSystem frontalSystem, Request request) {
        this.clientId = clientId;
        this.frontalSystem = frontalSystem;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            System.out.println("Клиент №" + clientId + ": Заявка " + request.toString() + "тправлена в банк");
            frontalSystem.sendRequest(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
