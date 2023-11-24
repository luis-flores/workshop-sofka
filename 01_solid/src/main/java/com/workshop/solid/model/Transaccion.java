package com.workshop.solid.model;

import org.springframework.stereotype.Component;

@Component
public class Transaccion {
	private Double monto;
	private Double costo;

	public double getCosto() {
		return costo;
	}

	public double getMonto() {
		return monto;
	}

	public Transaccion setMonto(Double monto) {
		this.monto = monto;
		return this;
	}

	public Transaccion setCosto(Double costo) {
		this.costo = costo;
		return this;
	}
}
