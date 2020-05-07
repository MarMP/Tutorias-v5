package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.VistaIUGrafica;
import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO {
		public IVista crear() {
			return new VistaTexto();
		}
	},
	
	IUGRAFICA {
		public IVista crear() {
			return new VistaIUGrafica();
		}
	};

	public abstract IVista crear();

}