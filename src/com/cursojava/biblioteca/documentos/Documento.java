package com.cursojava.biblioteca.documentos;

import com.cursojava.biblioteca.usuarios.Persona;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;

public class Documento {

	private String cod;
	
	private String autor;
	
	private String titulo;
	
	Boolean disponible;
	
	private TipoDocumento tipoDocumento;
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public Documento(String autor, String titulo) {
		super();
		this.autor = autor;
		this.titulo = titulo;
	}

	public Documento(String cod, String autor, String titulo, Boolean disponible, TipoDocumento tipoDocumento) {
		super();
		this.cod = cod;
		this.autor = autor;
		this.titulo = titulo;
		this.disponible = disponible;
		this.tipoDocumento = tipoDocumento;
	}

	public String getCod() {
		return cod;
	}

	public String getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Documento [cod=");
		builder.append(cod);
		builder.append(", autor=");
		builder.append(autor);
		builder.append(", titulo=");
		builder.append(titulo);
		builder.append("]");
		return builder.toString();
	}



	
}
