package com.cursojava.biblioteca.funciones;

import com.cursojava.biblioteca.documentos.Libro;

public interface FuncionesSocio {

	public boolean solicitarPrestamo(Libro libro);
	
	public boolean solicitarPrestamo(String tituloLibro);
	
	public boolean solicitarDevolucion(Libro libro);
	
	public boolean solicitarDevolucion(String tituloLibro);
	
	
}
