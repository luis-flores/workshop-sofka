package com.workshop.mvc.dto;

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
public class AccountDTO {
    @NotNull
    private int number;
    private BigDecimal balance;
}
