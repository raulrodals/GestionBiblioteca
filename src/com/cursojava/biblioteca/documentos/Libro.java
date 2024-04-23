package com.cursojava.biblioteca.documentos;

import java.time.LocalDate;

import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;

public class Libro extends Documento{

	private LocalDate anhoPublicacion;

	public Libro(String autor, String titulo, LocalDate anhoPublicacion) {
		super(autor, titulo);
		this.anhoPublicacion = anhoPublicacion;
	}

	public Libro(String cod, String autor, String titulo, Boolean disponible, LocalDate anhoPublicacion, TipoDocumento tipoDocumento) {
		super(cod, autor, titulo, disponible, tipoDocumento);
		this.anhoPublicacion = anhoPublicacion;
	}

	public LocalDate getAnhoPublicacion() {
		return anhoPublicacion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Libro [anhoPublicacion=");
		builder.append(anhoPublicacion);
		builder.append(", getCod()=");
		builder.append(getCod());
		builder.append(", getAutor()=");
		builder.append(getAutor());
		builder.append(", getTitulo()=");
		builder.append(getTitulo());
		builder.append("]");
		return builder.toString();
	}
	
	
}
