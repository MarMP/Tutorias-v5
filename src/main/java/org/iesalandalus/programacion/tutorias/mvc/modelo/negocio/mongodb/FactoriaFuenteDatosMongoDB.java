package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import org.iesalandalus.programacion.tutorias.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class FactoriaFuenteDatosMongoDB implements IFuenteDatos {

	@Override
	public IAlumnos crearAlumnos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProfesores crearProfesores() {
		return new Profesores();
	}

	@Override
	public ITutorias crearTutorias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISesiones crearSesiones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICitas crearCitas() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
