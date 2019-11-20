package ru.domclick.test.domclick.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domclick.test.domclick.entity.Account;
import ru.domclick.test.domclick.exception.AccountBadRequestException;
import ru.domclick.test.domclick.exception.AccountNotFoundException;
import ru.domclick.test.domclick.repository.AccountRepository;
import ru.domclick.test.domclick.service.AccountService;
import ru.domclick.test.domclick.to.request.GetMoneyRequest;
import ru.domclick.test.domclick.to.request.PutMoneyRequest;
import ru.domclick.test.domclick.to.request.TransferMoneyRequest;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transferMoney(TransferMoneyRequest transferMoneyRequest) {
        if (transferMoneyRequest.getToAccount().equals(transferMoneyRequest.getFromAccount())) {
            throw new AccountBadRequestException("operation cannot be performed");
        }
        if (transferMoneyRequest.getSum() < 0) {
            throw new AccountBadRequestException("value cannot be negative");
        }

        Account accountFrom = accountRepository.findById(transferMoneyRequest.getFromAccount()).orElseThrow(() ->
                new AccountNotFoundException(String.format("account with id=%s not found", transferMoneyRequest.getFromAccount())));
        Account accountTo = accountRepository.findById(transferMoneyRequest.getToAccount()).orElseThrow(() ->
                new AccountNotFoundException(String.format("account with id=%s not found", transferMoneyRequest.getFromAccount())));

        if (accountFrom.getBalance() < transferMoneyRequest.getSum()) {
            throw new AccountBadRequestException("insufficient funds");
        }

        Long tempBalanceOfAccountFrom = accountFrom.getBalance();
        Long tempBalanceOfAccountTo = accountTo.getBalance();
        accountFrom.setBalance(tempBalanceOfAccountFrom - transferMoneyRequest.getSum());
        accountTo.setBalance(tempBalanceOfAccountTo + transferMoneyRequest.getSum());

        accountRepository.saveAll(List.of(accountFrom, accountTo));
    }

    public void putMoneyToAccount(PutMoneyRequest putMoneyRequest) {
        if (putMoneyRequest.getSum() < 0) {
            throw new AccountBadRequestException("value cannot be negative");
        }
        Account account = accountRepository.findById(putMoneyRequest.getAccountId()).orElseThrow(() ->
                new AccountNotFoundException(String.format("account with id=%s not found", putMoneyRequest.getAccountId())));
        Long tempBalance = account.getBalance();
        account.setBalance(tempBalance + putMoneyRequest.getSum());

        accountRepository.save(account);
    }

    public void getMoneyFromAccount(GetMoneyRequest getMoneyRequest) {
        if (getMoneyRequest.getSum() < 0) {
            throw new AccountBadRequestException("value cannot be negative");
        }
        Account account = accountRepository.findById(getMoneyRequest.getAccountId()).orElseThrow(() ->
                new AccountNotFoundException(String.format("account with id=%s not found", getMoneyRequest.getAccountId())));
        Long tempBalance = account.getBalance();
        if (tempBalance < getMoneyRequest.getSum()) {
            throw new AccountBadRequestException("insufficient funds");
        }
        account.setBalance(tempBalance - getMoneyRequest.getSum());

        accountRepository.save(account);
    }
}
