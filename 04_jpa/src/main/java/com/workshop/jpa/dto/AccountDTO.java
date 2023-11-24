package com.workshop.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private Long id;
    private BigDecimal balance;
}
