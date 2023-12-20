package Request;

public class Request {
    private final int clientId;
    private final RequestType requestType;
    private final int sum;

    public Request(int clientId, RequestType requestType, int sum) {
        this.clientId = clientId;
        this.requestType = requestType;
        this.sum = sum;
    }

    public int getClientId() {
        return clientId;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clientId=" + clientId +
                ", requestType=" + requestType +
                ", sum=" + sum +
                '}';
    }
}
