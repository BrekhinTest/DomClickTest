package ru.domclick.test.domclick;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.domclick.test.domclick.entity.AccountEntity;
import ru.domclick.test.domclick.exception.AccountBadRequestException;
import ru.domclick.test.domclick.repository.AccountRepository;
import ru.domclick.test.domclick.service.AccountService;
import ru.domclick.test.domclick.to.request.GetMoneyRequest;
import ru.domclick.test.domclick.to.request.PutMoneyRequest;
import ru.domclick.test.domclick.to.request.TransferMoneyRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DomclickApplication.class)
public class DomclickApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void testPutMoney() throws Exception {
        accountRepository.save(new AccountEntity(1L, 1000L));
        accountService.putMoneyToAccount(new PutMoneyRequest(1L, 3000L));
        AccountEntity byId = accountRepository.findById(1L).orElseThrow(() -> new Exception("test case error"));
        Assert.assertEquals(String.valueOf(4000L), String.valueOf(byId.getBalance()));
    }

    @Test
    public void testGetMoney() throws Exception {
        accountRepository.save(new AccountEntity(1L, 1000L));
        accountService.getMoneyFromAccount(new GetMoneyRequest(1L, 500L));
        AccountEntity account = accountRepository.findById(1L).orElseThrow(() -> new Exception("test case error"));
        Assert.assertEquals(500, account.getBalance().intValue());
    }

    @Test(expected = AccountBadRequestException.class)
    public void testGetMoneyException() throws Exception {
        accountRepository.save(new AccountEntity(1L, 1000L));
        accountService.getMoneyFromAccount(new GetMoneyRequest(1L, 5000L));
    }

    @Test
    public void testTransferMoneyException() throws Exception {
        accountRepository.save(new AccountEntity(1L, 6000L));
        accountRepository.save(new AccountEntity(2L, 1000L));
        accountService.transferMoney(new TransferMoneyRequest(1L, 2L, 5000L));

        AccountEntity fromAccount = accountRepository.findById(1L).orElseThrow(() -> new Exception("test case error"));
        AccountEntity toAccount = accountRepository.findById(2L).orElseThrow(() -> new Exception("test case error"));
        Assert.assertEquals(1000, fromAccount.getBalance().intValue());
        Assert.assertEquals(6000, toAccount.getBalance().intValue());
    }


}
