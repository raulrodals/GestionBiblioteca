package com.cursojava.biblioteca.menu;

import java.util.Scanner;

import com.cursojava.biblioteca.bibliotecarepositorio.Biblioteca;
import com.cursojava.biblioteca.bibliotecarepositorio.GestionBiblioteca;
import com.cursojava.biblioteca.bibliotecarepositorio.GestionNumeros;
import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.usuarios.Persona;

public class MenuBiblioteca {

	public static final Integer opSalir = 7;

	public static Integer mostrarMenu(Scanner scan) {
		System.out.println("1 - Seleccionar documento por titulo.");
		System.out.println("2 - Seleccionar documento por codigo.");
		System.out.println("3 - Prestar documento.");
		System.out.println("4 - Devolver documento.");
		System.out.println("5 - Agregar nuevo usuario.");
		System.out.println("6 - Eliminar usuario.");
		System.out.println("7 - Salir.");
		Integer opcion = GestionNumeros.scanNumero("Introduce un numero entre 1 y " + opSalir, scan);
		return opcion;
	}

	public static void menu(Biblioteca biblioteca, Scanner scan) {
		Integer seleccionada = -1;
		do {
			seleccionada = mostrarMenu(scan);
			//scan.nextLine();
			switch (seleccionada) {
			case 1: {
				seleccionarDocumentoPorTitulo(biblioteca, scan);
				break;
			}
			case 2: {
				selecciondarDocumentoPorCod(biblioteca, scan);
				break;
			}
			case 3: {
				prestarDocumento(biblioteca, scan);
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
			default:
				System.out.println("Error. Se debe seleccionar una opcion entre 1 y " + opSalir + ", vuelve a seleccionar una opcion.");
				//throw new IllegalArgumentException("Unexpected value: " + seleccionada);
			}
		} while (seleccionada != opSalir);
	}
	
	public static void seleccionarDocumentoPorTitulo(Biblioteca biblioteca, Scanner scan) {
		String titulo = GestionBiblioteca.mostrar("Introduce el titulo del documento a buscar.", scan);
		Documento documento = biblioteca.getBibliotecario().getDocumentoPorTitulo(titulo);
		biblioteca.setDocumentoSeleccionado(documento);
		System.out.println("Documento selecciondo: " + documento);
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
