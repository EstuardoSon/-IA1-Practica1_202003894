package com.backend.demo.models;

public class tag {
	public String nombre;
	public float porcentaje;
	
	public tag(String nombre, float porcentaje) {
		super();
		this.nombre = nombre;
		this.porcentaje = this.getPorcentaje(porcentaje);
	}
	
	private float getPorcentaje(float escala) {
		if (escala != 0) {
			return escala * 20;
		}
		return 0;
	}
}
