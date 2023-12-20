package BackendSystem;

import Request.RequestType;
import Request.Request;

public class BankBackEndSystem {

    private long bankBalance;

    public BankBackEndSystem(long bankBalance) {
        this.bankBalance = bankBalance;
    }

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

    private synchronized void decreaseBankBalance(Request request, int idProcessor) {
        int sum = request.getSum();
        if (bankBalance >= sum) {
            bankBalance -= sum;
            System.out.println("Бэк система: Заявка" + request + " УСПЕШНО ВЫПОЛНЕНА. Получена от обработчика заявок "
                    + idProcessor + ". Баланс банка:" + bankBalance);

        } else {
            System.out.println("Бэк система: Заявка" + request + " НЕ ВЫПОЛНЕНА. Получена от обработчика заявок "
                    + idProcessor + ".Сумма больше баланса банка. Баланс банка:" + bankBalance);
        }
    }


    private synchronized void increaseBankBalance(Request request, int idProcessor) {
        bankBalance += request.getSum();
        System.out.println("Бэк система: Заявка" + request + " УСПЕШНО ВЫПОЛНЕНА. Получена от обработчика заявок "
                + idProcessor + ". Баланс банка:" + bankBalance);

    }
}
