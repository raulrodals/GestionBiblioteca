package com.cursojava.biblioteca.documentos;

import com.cursojava.biblioteca.utilidad.BibliotecaEnums.TipoDocumento;

public class Revista extends Documento{

	private Integer numPaginas;

	public Revista(String autor, String titulo, Integer numPaginas) {
		super(autor, titulo);
		this.numPaginas = numPaginas;
	}

	public Revista(String cod, String autor, String titulo, Boolean disponible, TipoDocumento tipoDocumento, Integer numPaginas) {
		super(cod, autor, titulo, disponible, tipoDocumento);
		this.numPaginas = numPaginas;
	}

	public Integer getNumPaginas() {
		return numPaginas;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Revista [numPaginas=");
		builder.append(numPaginas);
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
