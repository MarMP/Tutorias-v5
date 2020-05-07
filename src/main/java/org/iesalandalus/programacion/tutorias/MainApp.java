package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de tutorías v4 del IES Al-Ándalus");
		IModelo modelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
		IVista vista = procesarArgumentos(args);
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

	private static IVista procesarArgumentos(String[] args) {
		IVista vista = FactoriaVista.IUGRAFICA.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vgrafica")) {
				vista = FactoriaVista.IUGRAFICA.crear();
			} else if (argumento.equalsIgnoreCase("-vtexto")) {
				vista = FactoriaVista.TEXTO.crear();
			}
		}
		return vista;
	}

}
