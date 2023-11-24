package com.workshop.solid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MenuBuilder {
	private List<String> textos;
	private Map<Integer, Object> opciones;

	public MenuBuilder() {
		super();
		textos = new ArrayList<String>();
		opciones = new LinkedHashMap<Integer, Object>();
	}
	
	public MenuBuilder agregarOpcion(String texto) {
		return agregarOpcion(texto, null);
	}
	
	public MenuBuilder agregarOpcion(String texto, Object tipo) {
		textos.add(texto);
		
		if (tipo != null)
			opciones.put(textos.size() - 1, tipo);
		
		return this;
	}
	
	public String build() {
		StringBuilder menu = new StringBuilder();
		for (String texto : textos) {
			menu.append(texto + "\n");
		}
		return menu.toString();
	}
	
	public Object obtenerOpcion(int i) {
		return opciones.get(i);
	}
}
