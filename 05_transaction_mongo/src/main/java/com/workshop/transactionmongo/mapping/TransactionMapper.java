package com.workshop.transactionmongo.mapping;

import com.workshop.transactionmongo.documents.Transaction;
import com.workshop.transactionmongo.dto.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TransactionMapper {
    List<TransactionDTO> toDTOs(List<Transaction> transactions);

    /*@Mappings({
        @Mapping(source = "id", target = "id"),
        @Mapping(source = "type", target = "type"),
        @Mapping(source = "amount", target = "amount"),
        @Mapping(source = "cost", target = "cost"),
        @Mapping(source = "client", target = "client"),
    })*/
    TransactionDTO toDTO(Transaction transaction);

    /*@Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "type", target = "type"),
            @Mapping(source = "amount", target = "amount"),
            @Mapping(source = "cost", target = "cost"),
            @Mapping(source = "client", target = "client"),
    })*/
    Transaction fromDTO(TransactionDTO transactionDTO);
}
