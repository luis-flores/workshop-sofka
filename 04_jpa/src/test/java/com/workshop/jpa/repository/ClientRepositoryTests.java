package com.workshop.jpa.repository;

import com.workshop.jpa.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest(showSql = true)
public class ClientRepositoryTests {
    @Autowired
    private IClientRepository clientRepository;
    
    @Test
    public void findByIdTest() {
        Optional<Client> client = clientRepository.findById(1L);

        assertThat(client).isPresent();
        assertThat(client.get().getId()).isEqualTo(1);
    }
}
