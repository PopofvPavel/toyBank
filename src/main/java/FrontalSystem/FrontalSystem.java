package FrontalSystem;

import Request.Request;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FrontalSystem {
    /*
    Чтобы обеспечить потокобезопасность, надо использовать блокирующую очередь,
    в ArrayBlockingQueue фиксированный размер и элементы хранятся последовательно,
    а ее функционала вполне достаточно
     */
    private final ArrayBlockingQueue<Request> requestQueue = new ArrayBlockingQueue<Request>(2);

    public  void sendRequest(Request request) throws InterruptedException {
        requestQueue.put(request);
    }

    public  Request getRequest() throws InterruptedException {
        return requestQueue.take();
    }
}