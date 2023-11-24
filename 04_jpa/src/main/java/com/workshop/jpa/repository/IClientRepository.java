package com.workshop.jpa.repository;

import com.workshop.jpa.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}
