package com.workshop.solid.model;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class Cuenta {
	@Getter private double saldo;

	public Cuenta() {
		super();
		this.saldo = 0;
	}

	public void procesar(Transaccion transaccion) {
		saldo += transaccion.getMonto();
		saldo -= transaccion.getCosto();
	}
}
