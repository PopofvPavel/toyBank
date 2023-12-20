package FrontalSystem;

import Request.Request;

import java.util.ArrayDeque;
import java.util.Queue;

public class FrontalSystem {
    private final Queue<Request> requestQueue = new ArrayDeque<>();
    public synchronized void sendRequest(Request request) throws InterruptedException {
        while (requestQueue.size() >= 2) {
            wait();
        }
        requestQueue.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException {
        while (requestQueue.isEmpty()) {
            wait();
        }
        Request request = requestQueue.poll();
        notifyAll();
        return request;
    }
}
