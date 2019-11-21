package ru.domclick.test.domclick.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.domclick.test.domclick.service.AccountService;
import ru.domclick.test.domclick.to.request.GetMoneyRequest;
import ru.domclick.test.domclick.to.request.PutMoneyRequest;
import ru.domclick.test.domclick.to.request.TransferMoneyRequest;
import ru.domclick.test.domclick.to.response.TransferMoneyResponse;

@RestController
@Api(value="Операции со счетами")
public class MainController {

    private final AccountService accountService;

    @Autowired
    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "Перевод между счетами", response = TransferMoneyResponse.class)
    @PostMapping(path = "/transfer-money")
    public ResponseEntity<TransferMoneyResponse> transferMoneyBetweenAccounts(@RequestBody TransferMoneyRequest transferMoneyRequest) throws Exception {
        accountService.transferMoney(transferMoneyRequest);
        return new ResponseEntity<>(new TransferMoneyResponse("success"), HttpStatus.OK);
    }

    @ApiOperation(value = "Пополнение счета", response = TransferMoneyResponse.class)
    @PostMapping(path = "/put-money-to-account")
    public ResponseEntity<TransferMoneyResponse> putMoneyToAccount(@RequestBody PutMoneyRequest putMoneyRequest) throws Exception {
        accountService.putMoneyToAccount(putMoneyRequest);
        return new ResponseEntity<>(new TransferMoneyResponse("success"), HttpStatus.OK);
    }

    @ApiOperation(value = "Снятие со счета", response = TransferMoneyResponse.class)
    @PostMapping(path = "/get-money-from-account")
    public ResponseEntity<TransferMoneyResponse> getMoneyToAccount(@RequestBody GetMoneyRequest getMoneyRequest) throws Exception {
        accountService.getMoneyFromAccount(getMoneyRequest);
        return new ResponseEntity<>(new TransferMoneyResponse("success"), HttpStatus.OK);
    }
}
