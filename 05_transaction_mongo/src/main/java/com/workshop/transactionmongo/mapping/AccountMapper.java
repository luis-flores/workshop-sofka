package com.workshop.transactionmongo.mapping;

import com.workshop.transactionmongo.documents.Account;
import com.workshop.transactionmongo.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    AccountDTO toDto(Account account);
//    Account fromDto(AccountDTO accountDTO);
}
