package ru.domclick.test.domclick.to.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@Setter
public class TransferMoneyRequest {

    @NonNull
    private Long fromAccount;

    @NonNull
    private Long toAccount;

    @NonNull
    private Long sum;
}
