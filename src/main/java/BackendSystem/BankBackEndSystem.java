package BackendSystem;

import Request.RequestType;
import Request.Request;

import java.util.concurrent.atomic.AtomicLong;

public class BankBackEndSystem {

    private final AtomicLong bankBalance = new AtomicLong();



    public void processRequest(Request request, int idProcessor) {
        RequestType requestType = request.getRequestType();
        switch (requestType) {
            case PAY_MONEY:
                increaseBankBalance(request, idProcessor);
                break;
            case RECEIVE_MONEY:
                decreaseBankBalance(request, idProcessor);
                break;

        }

    }

    private  void decreaseBankBalance(Request request, int idProcessor) {
        int sum = request.getSum();
        if (bankBalance.get() >= sum) {
            bankBalance.addAndGet(-sum);
            System.out.println("Бэк система: Заявка" + request + " УСПЕШНО ВЫПОЛНЕНА. Получена от обработчика заявок "
                    + idProcessor + ". Баланс банка:" + bankBalance);

        } else {
            System.out.println("Бэк система: Заявка" + request + " НЕ ВЫПОЛНЕНА. Получена от обработчика заявок "
                    + idProcessor + ".Сумма больше баланса банка. Баланс банка:" + bankBalance);
        }
    }


    private  void increaseBankBalance(Request request, int idProcessor) {
        bankBalance.addAndGet(request.getSum());
        System.out.println("Бэк система: Заявка" + request + " УСПЕШНО ВЫПОЛНЕНА. Получена от обработчика заявок "
                + idProcessor + ". Баланс банка:" + bankBalance);

    }

    public  void updateBankBalance(int sum) {
        bankBalance.addAndGet(sum);
    }
}
