import BackendSystem.BankBackEndSystem;
import Client.Client;
import FrontalSystem.FrontalSystem;
import Request.Request;
import Request.RequestType;
import RequestProcessor.RequestProcessor;

public class Main {
    public static void main(String[] args) {
        BankBackEndSystem bankBackEndSystem = new BankBackEndSystem(0);
        FrontalSystem frontalSystem = new FrontalSystem();
        Thread requestProcessorThread1 = new Thread(new RequestProcessor(1, frontalSystem, bankBackEndSystem));
        Thread requestProcessorThread2 = new Thread(new RequestProcessor(2, frontalSystem, bankBackEndSystem));

        Thread clientThread1 = new Thread(new Client(1,frontalSystem,
                new Request(1, RequestType.RECEIVE_MONEY,500)));
        Thread clientThread2 = new Thread(new Client(2,frontalSystem,
                new Request(2, RequestType.PAY_MONEY,500)));
        Thread clientThread3 = new Thread(new Client(3 ,frontalSystem,
                new Request(3, RequestType.RECEIVE_MONEY,500)));
        Thread clientThread4 = new Thread(new Client(4,frontalSystem,
                new Request(4, RequestType.RECEIVE_MONEY,1000)));
        Thread clientThread5 = new Thread(new Client(5,frontalSystem,
                new Request(5, RequestType.PAY_MONEY,25_000_000)));

        requestProcessorThread1.start();
        requestProcessorThread2.start();

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
        clientThread4.start();
        clientThread5.start();
    }
}
