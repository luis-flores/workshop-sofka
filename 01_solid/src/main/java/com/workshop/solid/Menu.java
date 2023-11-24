package com.workshop.solid;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.workshop.solid.model.Cuenta;
import com.workshop.solid.model.TipoTransaccion;
import com.workshop.solid.model.Transaccion;
import com.workshop.solid.model.TransaccionFactory;

@Component
public class Menu {
	@Autowired
	private MenuBuilder menu;
	
	@Autowired
	private Cuenta cuenta;
	
	@Autowired
	private TransaccionFactory transaccionFactory;
	
	public void ejecutar() {
		System.out.println("Saldo Inicial: " + cuenta.getSaldo());
		
		menu.agregarOpcion("¿Qué desea hacer?")
			.agregarOpcion("1. Deposito desde sucursal", TipoTransaccion.DepositoSucursal)
			.agregarOpcion("2. Deposito desde cajero", TipoTransaccion.DepositoCajero)
			.agregarOpcion("3. Deposito desde cuenta", TipoTransaccion.DepositoCuenta)
			.agregarOpcion("4. Compra fisica", TipoTransaccion.CompraFisica)
			.agregarOpcion("5. Compra web", TipoTransaccion.CompraWeb)
			.agregarOpcion("6. Retiro en cajero", TipoTransaccion.RetiroCajero)
			.agregarOpcion("7. Salir");
		
		int opcion = 0;
		do {
			opcion = mostrarMenu();
			
			if (opcion >= 1 && opcion <= 6)
				procesar(opcion);
		} while (opcion != 7);
	}
	
	private int mostrarMenu() {
		Scanner in = new Scanner(System.in);
		int opcion;
	       
		System.out.println(menu.build());
		
		opcion = in.nextInt();
		System.out.println("Opcion Seleccionada: " + opcion);
		
		return opcion;
	}
	
	private void procesar(int numeroOpcion) {
		Scanner in = new Scanner(System.in);
		System.out.println("Monto: ");
		double monto = in.nextDouble();
		double costo = 0;
		
		Transaccion transaccion = transaccionFactory.crearTransaccion((TipoTransaccion) menu.obtenerOpcion(numeroOpcion), monto); 
		cuenta.procesar(transaccion);
		costo = transaccion.getCosto();
			
		System.out.println("Costo: " + costo);
		System.out.println("Saldo: " + cuenta.getSaldo());
	}
}
