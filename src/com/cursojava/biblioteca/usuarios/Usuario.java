package com.cursojava.biblioteca.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.cursojava.biblioteca.documentos.Documento;
import com.cursojava.biblioteca.prestamos.Prestamo;
import com.cursojava.biblioteca.utilidad.BibliotecaEnums.*;

public class Usuario extends Persona{

	private Integer limitePrestamos;

	private Long idSocio;
	
	private TipoUsuario tipoUsuario;
		
	public static Long numUsuarios = 0l;
	
	public Usuario(String dni, String nombre, String apellidos, TipoUsuario tipoUsuario) {
		super(dni, nombre, apellidos);
		this.numUsuarios ++;
		this.idSocio = this.numUsuarios;
	}
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [limitePrestamos=");
		builder.append(limitePrestamos);
		builder.append(", idSocio=");
		builder.append(idSocio);
		builder.append(", tipoUsuario=");
		builder.append(tipoUsuario);
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
