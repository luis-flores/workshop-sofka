package com.workshop.mvc.dto;

import com.workshop.mvc.entity.Account;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    @NotNull
    private final Account account;
    @NotNull
    private final String type;
    @NotNull
    private final BigDecimal amount;
    private final BigDecimal cost;
}
