package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.bson.Document;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.mongodb.utilidades.MongoDB;

import com.mongodb.client.MongoCollection;

public class Alumnos implements IAlumnos {

	private static final String COLECCION = "alumnos";
	private MongoCollection<Document> coleccionAlumnos;

	@Override
	public void comenzar() {
		coleccionAlumnos = MongoDB.getBD().getCollection(COLECCION);
		calcularUltimoIdentificador();

	}

	private void calcularUltimoIdentificador() {
		int ultimoIdentificador = 0;
		for (Alumno alumno : get()) {
			int identificador = Integer.parseInt(alumno.getExpediente().split("_")[2]);
			if (identificador > ultimoIdentificador) {
				ultimoIdentificador = identificador;
			}
			Alumno.identificadorFichero(ultimoIdentificador);
		}
	}

	@Override
	public void terminar() {
		MongoDB.cerrarConexion();

	}

	@Override
	public List<Alumno> get() {
		List<Alumno> alumnos = new ArrayList<>();
		for (Document documentoAlumnos : coleccionAlumnos.find().sort(MongoDB.getCriterioOrdenacionAlumno())) {
			alumnos.add(MongoDB.getAlumno(documentoAlumnos));
		}
		return alumnos;
	}

	@Override
	public int getTamano() {
		return (int) coleccionAlumnos.countDocuments();
	}

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: no se puede insertar un alumno nulo.");
		}
		if (buscar(alumno) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con el mismo correo.");
		} else {
			coleccionAlumnos.insertOne(MongoDB.getDocumento(alumno));
		}

	}

	@Override
	public Alumno buscar(Alumno alumno) {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		Document documentoAlumno = coleccionAlumnos.find().filter(eq(MongoDB.CORREO, alumno.getCorreo())).first();
		return MongoDB.getAlumno(documentoAlumno);
	}

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		if (buscar(alumno) != null) {
			coleccionAlumnos.deleteOne(eq(MongoDB.CORREO, alumno.getCorreo()));
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
		}

	}

}
