package ru.domclick.test.domclick.to.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetMoneyRequest {

    @NonNull
    private Long accountId;

    @NonNull
    private Long sum;
}
