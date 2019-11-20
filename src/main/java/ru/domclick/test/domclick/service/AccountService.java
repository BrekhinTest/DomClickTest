package ru.domclick.test.domclick.service;

import ru.domclick.test.domclick.to.request.GetMoneyRequest;
import ru.domclick.test.domclick.to.request.PutMoneyRequest;
import ru.domclick.test.domclick.to.request.TransferMoneyRequest;

public interface AccountService {

    void transferMoney(TransferMoneyRequest transferMoneyRequest);

    void putMoneyToAccount(PutMoneyRequest putMoneyRequest);

    void getMoneyFromAccount(GetMoneyRequest getMoneyRequest);
}
