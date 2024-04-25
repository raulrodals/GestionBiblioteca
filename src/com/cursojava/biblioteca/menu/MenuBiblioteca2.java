package com.cursojava.biblioteca.menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.cursojava.biblioteca.bibliotecarepositorio.Biblioteca;
import com.cursojava.biblioteca.bibliotecarepositorio.GestionBiblioteca;
import com.cursojava.biblioteca.bibliotecarepositorio.GestionNumeros;
import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.documentos.Libro;
import com.cursojava.biblioteca.documentos.Revista;
import com.cursojava.biblioteca.mysql.GestionBBDD;
import com.cursojava.biblioteca.prestamos.Prestamo;
import com.cursojava.biblioteca.usuarios.Persona;
import com.cursojava.biblioteca.usuarios.Usuario;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoUsuario;

public class MenuBiblioteca2 {

	public static final Integer opSalir = 10;

	public static Integer mostrarMenu(Scanner scan) {
		System.out.println("1 - Seleccionar documento por titulo.");
		System.out.println("2 - Seleccionar documento por codigo.");
		System.out.println("3 - Prestar documento.");
		System.out.println("4 - Devolver documento.");
		System.out.println("5 - Agregar nuevo usuario.");
		System.out.println("6 - Eliminar usuario.");
		System.out.println("7 - Agregar Libro.");
		System.out.println("8 - Agregar Revista.");
		System.out.println("9 - Mostrar todos los prestamos.");
		System.out.println("11 - Salir.");
		Integer opcion = GestionNumeros.scanNumero("Introduce un numero entre 1 y " + opSalir, scan);
		return opcion;
	}

	public static void menu(Connection conn, Biblioteca biblioteca, Scanner scan) throws SQLException {
		Integer seleccionada = -1;
		do {
			seleccionada = mostrarMenu(scan);
			switch (seleccionada) {
			case 1: {
				seleccionarDocumentoPorTitulo(conn, biblioteca, scan);
				break;
			}
			case 2: {
				prestarDocumentoSeleccionado(conn, biblioteca, scan);
				break;
			}
			case 3: {
				prestarDocumentoSeleccionado(conn, biblioteca, scan);
				break;
			}
			case 4: {
				devolverDocumento(biblioteca, scan);
				break;
			}
			case 5: {
				agregarNuevoUsuario(biblioteca, scan);
				break;
			}
			case 6: {
				eliminarUsuario(biblioteca, scan);
				break;
			}
			case 7: {

				break;
			}
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			default:
				System.out.println("Error. Se debe seleccionar una opcion entre 1 y " + opSalir
						+ ", vuelve a seleccionar una opcion.");
				// throw new IllegalArgumentException("Unexpected value: " + seleccionada);
			}
		} while (seleccionada != opSalir);
	}

	public static void seleccionarDocumentoPorTitulo(Connection conn, Biblioteca biblioteca, Scanner scan)
			throws SQLException {
		List<Documento> documentosFiltrados = GestionBBDD.getDocumentosFiltrados(conn, scan);
		Documento documento = null;
		if (documentosFiltrados.size() >= 1) {
			Integer seleccionado = 1;
			if (documentosFiltrados.size() > 1) {
				System.out.println("Documentos encontrados : ");
				for (int i = 0; i < documentosFiltrados.size(); i++) {
					System.out.println((i + 1) + " - " + documentosFiltrados.get(i));
				}
				String frase = "Seleccionar un documento de la lista del 1 al " + documentosFiltrados.size();
				seleccionado = GestionNumeros.scanNumero(frase, scan);
			}
			documento = documentosFiltrados.get(seleccionado - 1);
			biblioteca.setDocumentoSeleccionado(documento);
			System.out.println("Documento selecciondo: " + documento);
		}
	}

	public static void prestarDocumentoSeleccionado(Connection conn, Biblioteca biblioteca, Scanner scan)
			throws SQLException {
		GestionBBDD.prestaDocumento(conn, scan, biblioteca.getDocumentoSeleccionado());
	}

	public static void selecciondarDocumentoPorCod(Biblioteca biblioteca, Scanner scan) {
		String codigo = GestionBiblioteca.mostrar("Introduce el codigo del documento a buscar.", scan);
		Documento documento = GestionBiblioteca.buscarDocumentoPorCod(biblioteca, codigo);
		biblioteca.setDocumentoSeleccionado(documento);
		System.out.println("Documento selecciondo: " + documento);
	}

	public static void prestarDocumento(Biblioteca biblioteca, Scanner scan) {
		String dni = GestionBiblioteca.mostrar("Introduce el dni del usuario.", scan);
		biblioteca.getBibliotecario().prestaDocumentoActual(dni);
		Documento seleccionado = biblioteca.getDocumentoSeleccionado();

		// comprobar dni y buscar usuario, if(usuario != null) agregamos prestamo a la
		// biblioteca
		/*
		 * Prestamo prestamo = new Prestamo(); prestamo.setDocumento(seleccionado);
		 * prestamo.setUsuarioPrestamo(usuario); // Insertar prestamo en BBDD
		 * biblioteca.agregarPrestamo(usuario, prestamo);
		 */
		System.out.println(String.format("Se ha prestado el documento: %s al usuario con dni: %s", seleccionado, dni));
	}

	public static void devolverDocumento(Biblioteca biblioteca, Scanner scan) {
		String dni = GestionBiblioteca.mostrar("Introduce el dni del usuario.", scan);
		Persona usuario = GestionBiblioteca.getUsuarioPorDni(biblioteca, dni);
		// Mostrar lista prestamos usuario y seleccionar uno para devolverlo
		biblioteca.getBibliotecario().devolverDocumento(dni, null);
		Documento seleccionado = biblioteca.getDocumentoSeleccionado();
		System.out.println(String.format("Se ha devuelto el documento: %s del usuario con dni: %s", seleccionado, dni));
	}

	public static void agregarNuevoUsuario(Biblioteca biblioteca, Scanner scan) {
		Persona usuario;
		try {
			usuario = GestionBiblioteca.nuevoUsuario(scan);
			biblioteca.getBibliotecario().agregarUsuario(usuario);
			System.out.println("Agregado nuevo usuario" + usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void eliminarUsuario(Biblioteca biblioteca, Scanner scan) {
		String dni = GestionBiblioteca.mostrar("Introduce dni de persona a eliminar.", scan);
		Persona usuario = GestionBiblioteca.getUsuarioPorDni(biblioteca, dni);
		biblioteca.getBibliotecario().eliminarUsuario(usuario);
		System.out.println("Eliminado usuario" + usuario);
	}

}
