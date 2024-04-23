package com.cursojava.biblioteca.mysql;

import java.sql.Connection;
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
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;

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
}
