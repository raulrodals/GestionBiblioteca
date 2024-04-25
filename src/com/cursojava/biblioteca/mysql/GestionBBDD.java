package com.cursojava.biblioteca.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cursojava.biblioteca.bibliotecarepositorio.GestionBiblioteca;
import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.documentos.Libro;
import com.cursojava.biblioteca.documentos.Revista;
import com.cursojava.biblioteca.usuarios.Usuario;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoUsuario;


public class GestionBBDD {

	public static List<Documento> getDocumentosFiltrados(Connection conn, Scanner scan) throws SQLException{
		String titulo = GestionBiblioteca.mostrar("Introduce el titulo del documento a buscar.", scan);
		String query = "SELECT * FROM biblioteca2.documento WHERE titulo like ?";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "%" + titulo + "%");

		ResultSet rs = pstmt.executeQuery();

		Documento documento = null;
		List<Documento> documentosFiltrados = new ArrayList();
		while (rs.next()) {
			String cod = rs.getString("cod");
			String autor = rs.getString("autor");
			String title = rs.getString("titulo");
			boolean disponible = rs.getBoolean("disponible");
			LocalDate anhoPublicacion = rs.getDate("anho_publicacion") != null
					? rs.getDate("anho_publicacion").toLocalDate()
					: LocalDate.now();
			Integer numPaginas = rs.getInt("num_paginas");
			String tipoDocumentoString = rs.getString("tipo");
			TipoDocumento tipo = TipoDocumento.valueOf(tipoDocumentoString);
			if (tipo.name().equalsIgnoreCase("LIBRO")) {
				documento = new Libro(cod, autor, title, disponible, anhoPublicacion, tipo);
			}
			if (tipo.name().equalsIgnoreCase("REVISTA")) {
				documento = new Revista(cod, autor, title, disponible, tipo, numPaginas);
			}
			documentosFiltrados.add(documento);
		}
		return documentosFiltrados;
	}
	
	public static Usuario getUsuarioPorDni (Connection conn, Scanner scan) throws SQLException {
		String dni = GestionBiblioteca.mostrar("Introduce el dni del usuario", scan);
		String query = "SELECT dni_persona, nombre, apellidos, tipo_usuario FROM biblioteca2.usuario JOIN biblioteca2.persona ON dni_persona = dni WHERE dni_persona = ?";

		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, dni);
		Usuario usuario = null;
		
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			usuario = new Usuario(rs.getString("dni_persona"), rs.getString("nombre"), rs.getString("apellidos"), TipoUsuario.valueOf(rs.getString("tipo_usuario")));
		}
		return usuario;
	}
	
	public static void prestaDocumento (Connection conn, Scanner scan, Documento documento) throws SQLException {
		Usuario usuario = getUsuarioPorDni(conn, scan);
		
		String query = "INSERT INTO biblioteca2.prestamo(fecha_prestamo, fecha_devolucion, id_documento, dni_usuario, dias_prestamo) VALUES (?, ?, ?, ?, ?)";
		
		try (
				PreparedStatement statement = conn.prepareStatement(query);) {
			statement.setDate(1, Date.valueOf(LocalDate.now()));
			statement.setDate(2, Date.valueOf(LocalDate.now().plusDays(30)));
			statement.setString(3, documento.getCod());
			statement.setString(4, usuario.getDni());
			statement.setInt(5, 30);
			statement.executeUpdate();
		}
	}
}
