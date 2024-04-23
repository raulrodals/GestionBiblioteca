package com.cursojava.biblioteca.bibliotecarepositorio;

import java.time.LocalDate;
import java.util.Scanner;

import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.documentos.Libro;
import com.cursojava.biblioteca.documentos.Revista;
import com.cursojava.biblioteca.usuarios.Bibliotecario;
import com.cursojava.biblioteca.usuarios.Persona;
import com.cursojava.biblioteca.usuarios.Usuario;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoUsuario;

public class GestionBiblioteca {
	
	public static Biblioteca getBibliotecaPrueba() {
		Biblioteca biblioteca = new Biblioteca("Biblioteca Viewnext.");
		
		Bibliotecario bibliotecario = new Bibliotecario("000002340T", "Pedro", "Rosa", "1654897ASD");
		bibliotecario.setBiblioteca(biblioteca);
		Persona usuarioOcasional = new Usuario("00000000T", "Rosa", "Garcia", TipoUsuario.OCASIONAL);
		Persona usuarioSocio = new Usuario("44485125S", "Pepe", "Dominguez", TipoUsuario.SOCIO);
		
		biblioteca.setBibliotecario(bibliotecario);
		biblioteca.agregarUsuario(usuarioSocio);
		biblioteca.agregarUsuario(usuarioOcasional);
		
		Documento libro = new Libro("autor1", "libro1", LocalDate.now());
		Documento revista = new Revista("autor2", "revista1", 1);
		biblioteca.agregarDocumento(libro);
		biblioteca.agregarDocumento(revista);
		
		return biblioteca;
	}

	public static Documento buscarDocumentoPorTitulo(Biblioteca biblioteca, String titulo) {
		try {
			return biblioteca.getDocumentos().parallelStream().filter(d -> d.getTitulo().equalsIgnoreCase(titulo))
					.findFirst().get();
		} catch (Exception e) {
			return null;
		}
	}

	public static Documento buscarDocumentoPorCod(Biblioteca biblioteca, String cod) {
		try {
			return biblioteca.getDocumentos().parallelStream().filter(d -> d.getCod().equalsIgnoreCase(cod)).findFirst()
					.get();
		} catch (Exception e) {
			return null;
		}
	}

	public static String mostrar(String texto, Scanner scan) {
		System.out.println(texto);
		return scan.nextLine();
	}

	public static TipoUsuario getTipoUsuario(String texto, Scanner scan) {
		System.out.println(texto);
		Integer opcion = scan.nextInt();
		switch (opcion) {
		case 1: {
			return TipoUsuario.OCASIONAL;
		}
		case 2: {
			return TipoUsuario.SOCIO;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}
	
	public static Persona getUsuarioPorDni(Biblioteca biblioteca, String dni) {
		return biblioteca.getUsuarios().values().parallelStream().filter(user -> user.getDni().equalsIgnoreCase(dni)).findFirst().get();
	}

	public static Persona nuevoUsuario(Scanner scan) throws Exception {
		try {
			String dni = mostrar("Introduce el dni", scan);
			String nombre = mostrar("Introduce nombre", scan);
			String apellidos = mostrar("Introduce el apellido", scan);
			TipoUsuario tipoUsuario = getTipoUsuario("Selecciona una opion: \n\t 1 -> USUARIO OCASIONAL \n\t 2 -> SOCIO", scan);
			return new Usuario(dni, nombre, apellidos, tipoUsuario);
		} catch (Exception e) {
			throw new Exception("Error. no se ha podido crear un nuevo usuario.");
		}

	}
}
