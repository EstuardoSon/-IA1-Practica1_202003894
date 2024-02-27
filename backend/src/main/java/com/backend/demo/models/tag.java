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
		if (escala == 2) {
			return 20;
		}
		else if (escala == 3) {
			return 35;
		}
		else if (escala == 4) {
			return 60;
		}
		else if (escala == 5) {
			return 80;
		}
		return 0;
	}
}
