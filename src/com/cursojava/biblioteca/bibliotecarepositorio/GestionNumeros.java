package com.cursojava.biblioteca.bibliotecarepositorio;

import java.util.Scanner;

public class GestionNumeros {

	public static Integer scanNumero(String frase, Scanner scan) {
		Integer ret = -1;
		Boolean error = true;
		do {
			try {
				System.out.println(frase);
				ret = Integer.parseInt(scan.nextLine());
				error = false;
			} catch (Exception e) {
				System.out.println("Error. Solo se admiten numeros.");
				error = true;
			}
		} while (error);
		return ret;
	}
	
}
