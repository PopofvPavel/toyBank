package RequestProcessor;

import BackendSystem.BankBackEndSystem;
import FrontalSystem.FrontalSystem;
import Request.Request;

public class RequestProcessor implements Runnable {
    private final int idProcessor;
    private final FrontalSystem frontalSystem;
    private final BankBackEndSystem bankBackEndSystem;

    public RequestProcessor(int idProcessor, FrontalSystem frontalSystem, BankBackEndSystem bankBackEndSystem) {
        this.idProcessor = idProcessor;
        this.frontalSystem = frontalSystem;
        this.bankBackEndSystem = bankBackEndSystem;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request request = frontalSystem.getRequest();
                System.out.println("Обработчик заявок №" + idProcessor + ": Получена заявка на обраотку по клиенту "
                        + request.getClientId());
                bankBackEndSystem.processRequest(request, idProcessor);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //getRequest();
        //processRequest();
    }
}
