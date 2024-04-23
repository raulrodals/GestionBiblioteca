package com.cursojava.biblioteca.funciones;

import java.util.List;

import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.prestamos.InformePrestamo;
import com.cursojava.biblioteca.usuarios.Persona;

public interface FuncionesBibliotecario {

	public Documento seleccionarDocumento(Documento d);
	
	public Documento seleccionarDocumentoPorCod(String cod);
	
	public Documento prestaDocumentoActual(String dni);
	
	public void devolverDocumento(String dni, Documento documento);
	
	public Documento buscarDocumento(String nombre);
	
	public Documento buscarDocumentoPorCod(String cod);
	
	public InformePrestamo getInformePrestamos(String dni);
	
	public void agregarUsuario(Persona p);
	
	public void eliminarUsuario(Persona p);
	
	
}
