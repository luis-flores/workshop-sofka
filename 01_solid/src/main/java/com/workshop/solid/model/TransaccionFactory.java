package com.workshop.solid.model;

import org.springframework.stereotype.Component;

@Component
public class TransaccionFactory {
	public Transaccion crearTransaccion(TipoTransaccion tipo, Double monto) {
		switch (tipo) {	
			case DepositoCajero: return new Transaccion().setMonto(monto).setCosto(2.0);
			case DepositoCuenta: return new Transaccion().setMonto(monto).setCosto(1.5);
			case DepositoSucursal: return new Transaccion().setMonto(monto).setCosto(0.0);
			case CompraFisica: return new Transaccion().setMonto(-monto).setCosto(2.0);
			case CompraWeb: return new Transaccion().setMonto(-monto).setCosto(5.0);
			case RetiroCajero: return new Transaccion().setMonto(-monto).setCosto(1.0);
			default: throw new IllegalArgumentException("Tipo de deposito invalido");
		}
	}
}
