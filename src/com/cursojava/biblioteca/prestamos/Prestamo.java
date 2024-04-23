package com.cursojava.biblioteca.prestamos;

import java.time.LocalDateTime;

import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.usuarios.Persona;

public class Prestamo {
	
	private LocalDateTime fechaPrestamo;
	
	private LocalDateTime fechaDevolucion;

	private Documento documento;
	
	private Persona usuarioPrestamo;
	
	private Integer diasPrestamo;

	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Persona getUsuarioPrestamo() {
		return usuarioPrestamo;
	}

	public void setUsuarioPrestamo(Persona usuarioPrestamo) {
		this.usuarioPrestamo = usuarioPrestamo;
	}

	public Integer getDiasPrestamo() {
		return diasPrestamo;
	}

	public void setDiasPrestamo(Integer diasPrestamo) {
		this.diasPrestamo = diasPrestamo;
	}
	
}
