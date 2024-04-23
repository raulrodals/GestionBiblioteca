package com.cursojava.biblioteca.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMysql {

	private static final String BASE = "jdbc:mysql://";
	private static final String USER = "root";
	private static final String PASS = "abc123.";
	private static final String SERVIDOR = "localhost";
	private static final String ESQUEMA_BBDD = "biblioteca2";
	private static final String PUERTO = "3306";
	private static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
	
	private static Connection conn = null;
	
	public static Connection getConexion() throws SQLException, ClassNotFoundException {
		if(conn == null) {
			try {
				Class.forName(DRIVER_CLASSNAME);
				String rutaCompleta = BASE + SERVIDOR + ":" + PUERTO + "/" + ESQUEMA_BBDD;
				conn = DriverManager.getConnection(rutaCompleta, USER, PASS);
				System.out.println("Conexion establecida en " + rutaCompleta);
			} catch (SQLException e) {
				System.out.println("Error de conexión con servidor.");
				throw new SQLException(e);
			}catch (ClassNotFoundException e) {
				System.out.println("Error. " + DRIVER_CLASSNAME + " clase no encontrada.");
				throw new ClassNotFoundException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void cerrarConexion() throws SQLException {
		if(conn != null) {
			conn.close();
		}
	}
	
}
