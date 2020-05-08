package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class MainApp {

	public static void main(String[] args) {

		System.out.println("Programa para la gestión de tutorías v5 del IES Al-Ándalus");

		IModelo modelo = new Modelo(procesarArgumentosFuenteDatos(args));
		IVista vista = procesarArgumentosVista(args);
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

	private static IVista procesarArgumentosVista(String[] args) {
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

	private static IFuenteDatos procesarArgumentosFuenteDatos(String[] args) {
		IFuenteDatos fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fdficheros")) {
				fuenteDatos = FactoriaFuenteDatos.FICHEROS.crear();
			} else if (argumento.equalsIgnoreCase("-fdmongodb")) {
				fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
			}
		}
		return fuenteDatos;
	}

}
