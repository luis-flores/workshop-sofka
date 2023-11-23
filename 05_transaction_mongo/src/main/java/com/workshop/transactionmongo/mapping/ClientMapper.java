package com.workshop.transactionmongo.mapping;

import com.workshop.transactionmongo.documents.Client;
import com.workshop.transactionmongo.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClientMapper {
    List<ClientDTO> toDTOs(List<Client> clients);

    ClientDTO toDTO(Client client);
//    Client fromDTO(ClientDTO clientDTO);
}
