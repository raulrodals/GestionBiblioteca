package com.cursojava.biblioteca.usuarios;

import java.util.List;
import java.util.NoSuchElementException;

import com.cursojava.biblioteca.bibliotecarepositorio.Biblioteca;
import com.cursojava.biblioteca.bibliotecarepositorio.GestionBiblioteca;
import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.funciones.FuncionesBibliotecario;
import com.cursojava.biblioteca.prestamos.InformePrestamo;
import com.cursojava.biblioteca.prestamos.Prestamo;

public class Bibliotecario extends Persona implements FuncionesBibliotecario{

	private String NSS;
	
	Biblioteca biblioteca;
	
	public Bibliotecario(String dni, String nombre, String apellidos, String nSS) {
		super(dni, nombre, apellidos);
		NSS = nSS;
	}
	
	public String getNSS() {
		return NSS;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	@Override
	public Documento seleccionarDocumento(Documento documento) {
		this.biblioteca.setDocumentoSeleccionado(documento);
		return documento;
	}

	@Override
	public Documento seleccionarDocumentoPorCod(String cod) {
		Documento seleccionado = GestionBiblioteca.buscarDocumentoPorCod(biblioteca, cod);
		this.biblioteca.setDocumentoSeleccionado(seleccionado);
		return seleccionado;
	}

	@Override
	public Documento prestaDocumentoActual(String dni) {
		Persona usuarioPrestar = GestionBiblioteca.getUsuarioPorDni(biblioteca, dni);
		Documento documento = this.biblioteca.getDocumentoSeleccionado();
		return documento;
	}

	@Override
	public void devolverDocumento(String dni, Documento documento) {
		Persona usuario = GestionBiblioteca.getUsuarioPorDni(biblioteca, dni);
		List<Prestamo> prestamosUsuario = biblioteca.getPrestamosBiblioteca().get(usuario);
		Prestamo prestamoUsuario = prestamosUsuario.parallelStream().filter(d -> d.getDocumento().getCod().equals(documento.getCod())).findFirst().get();
	}
	
	@Override
	public InformePrestamo getInformePrestamos(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarUsuario(Persona p) {
		this.biblioteca.agregarUsuario(p);
	}

	@Override
	public void eliminarUsuario(Persona p) {
		this.biblioteca.eliminarUsuario(p);
	}

	@Override
	public Documento buscarDocumento(String nombre) {
		try {
			return GestionBiblioteca.buscarDocumentoPorTitulo(biblioteca, nombre);
		} catch (Exception e) {
			throw new NoSuchElementException("El documento con titulo: " + nombre + " no existe en la biblioteca.");
		}
	}

	@Override
	public Documento buscarDocumentoPorCod(String cod) {
		try {
			return GestionBiblioteca.buscarDocumentoPorCod(biblioteca, cod);
		} catch (Exception e) {
			throw new NoSuchElementException("El documento con codigo: " + cod + " no existe en la biblioteca.");
		}
	}
	
	public Documento getDocumentoPorTitulo(String titulo) {
		return GestionBiblioteca.buscarDocumentoPorTitulo(biblioteca, titulo);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bibliotecario [NSS=");
		builder.append(NSS);
		builder.append(", getDni()=");
		builder.append(getDni());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getApellidos()=");
		builder.append(getApellidos());
		builder.append(", getDireccion()=");
		builder.append(getDireccion());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append("]");
		return builder.toString();
	}


	


}
