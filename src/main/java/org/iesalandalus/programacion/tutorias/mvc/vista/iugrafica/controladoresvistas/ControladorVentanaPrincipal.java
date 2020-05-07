package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.io.IOException;


import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControlador controladorMVC;
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
	//Variables de fxml ventana principal
	@FXML Button btnAnadirProfesor;
	@FXML Button btnBuscarProfesor;
	@FXML Button btnBorrarProfesor;
	@FXML Button btnListarProfesor;
	@FXML Button btnAnadirAlumno;
	@FXML Button btnBuscarAlumno;
	@FXML Button btnBorrarAlumno;
	@FXML Button btnListarAlumnos;
	@FXML Button btnListarTutorias;
	@FXML Button btnAnadirTutoria;
	@FXML Button btnBuscarTutoria;
	@FXML Button btnBorrarTutoria;
	@FXML Button btnListarSesiones;
	@FXML Button btnAnadirSesion;
	@FXML Button btnBuscarSesion;
	@FXML Button btnBorrarSesion;
	@FXML Button btnAnadirCita;
	@FXML Button btnListarCitas;
	@FXML Button btnBuscarCita;
	@FXML Button btnBorrarCita;
	
	
	private Stage anadirProfesor;
    private ControladorAnadirProfesor cAnadirProfesor;
    private Stage mostrarProfesor;
    private ControladorMostrarProfesor cMostrarProfesor;
    private Stage listarProfesores;
    private ControladorListarProfesores cListarProfesores;
    private Stage anadirAlumno;
    private ControladorAnadirAlumno cAnadirAlumno;
    private Stage mostrarAlumno;
    private ControladorMostrarAlumno cMostrarAlumno;
    private Stage listarAlumnos;
    private ControladorListarAlumnos cListarAlumnos;
    private Stage anadirTutorias;
    private ControladorAnadirTutorias cAnadirTutorias;
    private Stage buscarTutoria;
    private ControladorBuscarTutorias cBuscarTutorias;
    private Stage mostrarTutoria;
    private ControladorMostrarTutoria cMostrarTutoria;
    private Stage listarTutorias;
    private ControladorListarTutorias cListarTutorias;
    private Stage anadirSesion;
    private ControladorAnadirSesion cAnadirSesion;
    private Stage listarSesiones;
    private ControladorListarSesiones cListarSesiones;
    private Stage mostrarSesion;
    private ControladorMostrarSesion cMostrarSesion;
    private Stage buscarSesion;
    private ControladorBuscarSesiones cBuscarSesion;
    private Stage anadirCita;
    private ControladorAnadirCita cAnadirCita;
    private Stage listarCitas;
    private ControladorListarCitas cListarCitas;
    private Stage mostrarCita;
    private ControladorMostrarCita cMostrarCita;
    private Stage buscarCita;
    private ControladorBuscarCitas cBuscarCita;
    
	@FXML
	void salir() {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?",
				null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}

	@FXML
	void acercaDe() throws IOException {
		VBox contenido = FXMLLoader.load(getClass().getResource("../vistas/AcercaDe.fxml"));
		Dialogos.mostrarDialogoInformacionPersonalizado("Tutorias", contenido);
	}

	// PROFESORES
	@FXML
	private void anadirProfesor() throws IOException {
		crearAnadirProfesor();
		anadirProfesor.showAndWait();
	}

	@FXML
	private void buscarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar Profesor", "DNI:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscar(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					crearMostrarProfesor(profesor);
					mostrarProfesor.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Profesor no encontrado", "No existe ningún profesor con ese DNI");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("El DNI no es válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarProfesor() {
		String dni = Dialogos.mostrarDialogoTexto("Buscar profesor", "DNI:");
		if (dni != null) {
			Profesor profesor = null;
			try {
				profesor = controladorMVC.buscar(Profesor.getProfesorFicticio(dni));
				if (profesor != null) {
					if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
							"¿Estás seguro de que desea eliminar al profesor:  " + profesor + "?", null)) {
						controladorMVC.borrar(profesor);
						Dialogos.mostrarDialogoAdvertencia("", "El profesor se ha eliminado correctamente");
					}
				} else {
					Dialogos.mostrarDialogoError("El profesor no se ha encontrado",
							"No existe ningún profesor con ese DNI");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("DNI no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void listarProfesores() throws IOException {
		crearListarProfesores();
		listarProfesores.showAndWait();
	}

	// ALUMNOS
	@FXML
	private void anadirAlumno() throws IOException {
		crearAnadirAlumno();
		anadirAlumno.showAndWait();
	}

	@FXML
	private void buscarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar Alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscar(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					crearMostrarAlumno(alumno);
					mostrarAlumno.showAndWait();
				} else {
					Dialogos.mostrarDialogoError("Alumno no encontrado", "No existe ningún alumno con ese correo");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("El formato del correo no es válido", e.getMessage());
			}
		}
	}

	@FXML
	private void borrarAlumno() {
		String correo = Dialogos.mostrarDialogoTexto("Buscar alumno", "Correo:");
		if (correo != null) {
			Alumno alumno = null;
			try {
				alumno = controladorMVC.buscar(Alumno.getAlumnoFicticio(correo));
				if (alumno != null) {
					if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
							"¿Estás seguro de que desea eliminar al alumno:  " + alumno + "?", null)) {
						controladorMVC.borrar(alumno);
						Dialogos.mostrarDialogoAdvertencia("", "El alumno se ha eliminado correctamente");
					}
				} else {
					Dialogos.mostrarDialogoError("El alumnono no se ha encontrado",
							"No existe ningún alumno con ese correo");
				}
			} catch (Exception e) {
				Dialogos.mostrarDialogoError("Correo no válido", e.getMessage());
			}
		}
	}

	@FXML
	private void listarAlumnos() throws IOException {
		crearListarAlumnos();
		listarAlumnos.showAndWait();
	}

	// TUTORÍAS

	@FXML
	private void anadirTutoria() throws IOException {
		crearAnadirTutorias();
		anadirTutorias.showAndWait();
	}

	@FXML
	private void buscarTutoria() throws IOException {
		crearBuscarTutoria();
		buscarTutoria.showAndWait();
		Tutoria tutoria = cBuscarTutorias.getTutoria();
		if (tutoria != null) {
			crearMostrarTutoria(tutoria);
			mostrarTutoria.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Tutoria no encontrada", "No existe ninguna tutoria");
		}
	}

	@FXML
	private void listarTutorias() throws IOException {
		crearListarTutorias();
		listarTutorias.showAndWait();
	}

	@FXML
	private void borrarTutoria() {
		try {
			buscarTutoria();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());
		}
	}

	// SESIÓN
	@FXML
	private void anadirSesion() throws IOException {
		crearAnadirSesion();
		anadirSesion.showAndWait();
	}

	@FXML
	private void listarSesiones() throws IOException {
		crearListarSesiones();
		listarSesiones.showAndWait();
	}

	@FXML
	private void buscarSesion() throws IOException {
		crearBuscarSesion();
		buscarSesion.showAndWait();
		Sesion sesion = cBuscarSesion.getSesion();
		if (sesion != null) {
			crearMostrarSesion(sesion);
			mostrarSesion.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Sesión no encontrada",
					"No existe ninguna sesión con ese nombre de tutoría y fecha.");
		}
	}

	@FXML
	private void borrarSesion() {
		try {
			buscarSesion();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());
		}
	}

	// CITAS
	@FXML
	private void anadirCita() throws IOException {
		crearAnadirCita();
		anadirCita.showAndWait();
	}

	@FXML
	private void listarCitas() throws IOException {
		crearListarCitas();
		listarCitas.showAndWait();
	}

	@FXML
	private void buscarCita() throws IOException {
		crearBuscarCita();
		buscarCita.showAndWait();
		Cita cita = cBuscarCita.getCitas();
		if (cita != null) {
			crearMostrarCita(cita);
			mostrarCita.showAndWait();
		} else {
			Dialogos.mostrarDialogoError("Cita no encontrada", "No existe ninguna cita para esa hora.");
		}
	}

	@FXML
	private void borrarCita() {
		try {
			buscarCita();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());
		}
	}

	private void crearAnadirProfesor() throws IOException {
		if (anadirProfesor == null) {
			anadirProfesor = new Stage();
			FXMLLoader cargadorAnadirProfesor = new FXMLLoader(getClass().getResource("../vistas/AnadirProfesor.fxml"));
			VBox raizAnadirProfesor = cargadorAnadirProfesor.load();
			cAnadirProfesor = cargadorAnadirProfesor.getController();
			cAnadirProfesor.setControladorMVC(controladorMVC);
			cAnadirProfesor.inicializa();
			Scene escenaAnadirProfesor = new Scene(raizAnadirProfesor);
			anadirProfesor.setTitle("Añadir Profesor");
			anadirProfesor.initModality(Modality.APPLICATION_MODAL);
			anadirProfesor.setScene(escenaAnadirProfesor);
		} else {
			cAnadirProfesor.inicializa();
		}
	}

	private void crearAnadirAlumno() throws IOException {
		if (anadirAlumno == null) {
			anadirAlumno = new Stage();
			FXMLLoader cargadorAnadirAlumno = new FXMLLoader(getClass().getResource("../vistas/AnadirAlumnos.fxml"));
			VBox raizAnadirAlumno = cargadorAnadirAlumno.load();
			cAnadirAlumno = cargadorAnadirAlumno.getController();
			cAnadirAlumno.setControladorMVC(controladorMVC);
			cAnadirAlumno.inicializa();
			Scene escenaAnadirAlumno = new Scene(raizAnadirAlumno);
			anadirAlumno.setTitle("Añadir Alumno");
			anadirAlumno.initModality(Modality.APPLICATION_MODAL);
			anadirAlumno.setScene(escenaAnadirAlumno);
		} else {
			cAnadirAlumno.inicializa();
		}
	}

	private void crearAnadirTutorias() throws IOException {
		if (anadirTutorias == null) {
			anadirTutorias = new Stage();
			FXMLLoader cargadorAnadirTutorias = new FXMLLoader(getClass().getResource("../vistas/AnadirTutorias.fxml"));
			VBox raizAnadirTutorias = cargadorAnadirTutorias.load();
			cAnadirTutorias = cargadorAnadirTutorias.getController();
			cAnadirTutorias.setControladorMVC(controladorMVC);
			cAnadirTutorias.inicializa();
			Scene escenaAnadirTutoria = new Scene(raizAnadirTutorias);
			anadirTutorias.setTitle("Añadir Tutoría");
			anadirTutorias.initModality(Modality.APPLICATION_MODAL);
			anadirTutorias.setScene(escenaAnadirTutoria);
		} else {
			cAnadirTutorias.inicializa();
		}
	}

	private void crearAnadirSesion() throws IOException {
		if (anadirSesion == null) {
			anadirSesion = new Stage();
			FXMLLoader cargadorAnadirSesion = new FXMLLoader(getClass().getResource("../vistas/AnadirSesiones.fxml"));
			VBox raizAnadirSesion = cargadorAnadirSesion.load();
			cAnadirSesion = cargadorAnadirSesion.getController();
			cAnadirSesion.setControladorMVC(controladorMVC);
			cAnadirSesion.inicializa();
			Scene escenaAnadirSesion = new Scene(raizAnadirSesion);
			anadirSesion.setTitle("Añadir sesión");
			anadirSesion.initModality(Modality.APPLICATION_MODAL);
			anadirSesion.setScene(escenaAnadirSesion);
		} else {
			cAnadirSesion.inicializa();
		}
	}

	private void crearAnadirCita() throws IOException {
		if (anadirCita == null) {
			anadirCita = new Stage();
			FXMLLoader cargadorAnadirCita = new FXMLLoader(getClass().getResource("../vistas/AnadirCitas.fxml"));
			VBox raizAnadirCita = cargadorAnadirCita.load();
			cAnadirCita = cargadorAnadirCita.getController();
			cAnadirCita.setControladorMVC(controladorMVC);
			cAnadirCita.inicializa();
			Scene escenaAnadirCita = new Scene(raizAnadirCita);
			anadirCita.setTitle("Añadir cita");
			anadirCita.initModality(Modality.APPLICATION_MODAL);
			anadirCita.setScene(escenaAnadirCita);
		} else {
			cAnadirCita.inicializa();
		}
	}

	private void crearMostrarProfesor(Profesor profesor) throws IOException {
		if (mostrarProfesor == null) {
			mostrarProfesor = new Stage();
			FXMLLoader cargadorMostrarProfesor = new FXMLLoader(
					getClass().getResource("../vistas/MostrarProfesor.fxml"));
			VBox raizMostrarProfesor = cargadorMostrarProfesor.load();
			cMostrarProfesor = cargadorMostrarProfesor.getController();
			cMostrarProfesor.setControladorMVC(controladorMVC);
			cMostrarProfesor.setProfesor(profesor);
			Scene escenaMostrarProfesor = new Scene(raizMostrarProfesor);
			mostrarProfesor.setTitle("Mostrar Profesor");
			mostrarProfesor.initModality(Modality.APPLICATION_MODAL);
			mostrarProfesor.setScene(escenaMostrarProfesor);
		} else {
			cMostrarProfesor.setProfesor(profesor);
		}
	}

	private void crearMostrarAlumno(Alumno alumno) throws IOException {
		if (mostrarAlumno == null) {
			mostrarAlumno = new Stage();
			FXMLLoader cargadorMostrarAlumno = new FXMLLoader(getClass().getResource("../vistas/MostrarAlumno.fxml"));
			VBox raizMostrarAlumno = cargadorMostrarAlumno.load();
			cMostrarAlumno = cargadorMostrarAlumno.getController();
			cMostrarAlumno.setControladorMVC(controladorMVC);
			cMostrarAlumno.setAlumno(alumno);
			Scene escenaMostrarAlumno = new Scene(raizMostrarAlumno);
			mostrarAlumno.setTitle("Mostrar Alumno");
			mostrarAlumno.initModality(Modality.APPLICATION_MODAL);
			mostrarAlumno.setScene(escenaMostrarAlumno);
		} else {
			cMostrarAlumno.setAlumno(alumno);
		}
	}

	private void crearMostrarTutoria(Tutoria tutoria) throws IOException {
		if (mostrarTutoria == null) {
			mostrarTutoria = new Stage();
			FXMLLoader cargadorMostrarTutoria = new FXMLLoader(
					getClass().getResource("../vistas/MostrarTutorias.fxml"));
			VBox raizMostrarTutoria = cargadorMostrarTutoria.load();
			cMostrarTutoria = cargadorMostrarTutoria.getController();
			cMostrarTutoria.setControladorMVC(controladorMVC);
			cMostrarTutoria.setTutoria(tutoria);
			Scene escenaMostrarTutoria = new Scene(raizMostrarTutoria);
			mostrarTutoria.setTitle("Mostrar Reserva");
			mostrarTutoria.initModality(Modality.APPLICATION_MODAL);
			mostrarTutoria.setScene(escenaMostrarTutoria);
		} else {
			cMostrarTutoria.setTutoria(tutoria);
		}
	}

	private void crearMostrarSesion(Sesion sesion) throws IOException {
		if (mostrarSesion == null) {
			mostrarSesion = new Stage();
			FXMLLoader cargadorMostrarSesion = new FXMLLoader(getClass().getResource("../vistas/MostrarSesion.fxml"));
			VBox raizMostrarSesion = cargadorMostrarSesion.load();
			cMostrarSesion = cargadorMostrarSesion.getController();
			cMostrarSesion.setControladorMVC(controladorMVC);
			cMostrarSesion.setSesion(sesion);
			Scene escenaMostrarSesion = new Scene(raizMostrarSesion);
			mostrarSesion.setTitle("Mostrar Sesión");
			mostrarSesion.initModality(Modality.APPLICATION_MODAL);
			mostrarSesion.setScene(escenaMostrarSesion);
		} else {
			cMostrarSesion.setSesion(sesion);
		}
	}

	private void crearMostrarCita(Cita cita) throws IOException {
		if (mostrarCita == null) {
			mostrarCita = new Stage();
			FXMLLoader cargadorMostrarCita = new FXMLLoader(getClass().getResource("../vistas/MostrarCita.fxml"));
			VBox raizMostrarCita = cargadorMostrarCita.load();
			cMostrarCita = cargadorMostrarCita.getController();
			cMostrarCita.setControladorMVC(controladorMVC);
			cMostrarCita.setCita(cita);
			Scene escenaMostrarCita = new Scene(raizMostrarCita);
			mostrarCita.setTitle("Mostrar cita");
			mostrarCita.initModality(Modality.APPLICATION_MODAL);
			mostrarCita.setScene(escenaMostrarCita);
		} else {
			cMostrarCita.setCita(cita);
		}
	}

	private void crearListarProfesores() throws IOException {
		if (listarProfesores == null) {
			listarProfesores = new Stage();
			FXMLLoader cargadorListarProfesores = new FXMLLoader(
					getClass().getResource("../vistas/ListarProfesores.fxml"));
			VBox raizListarProfesores = cargadorListarProfesores.load();
			cListarProfesores = cargadorListarProfesores.getController();
			cListarProfesores.setControladorMVC(controladorMVC);
			cListarProfesores.inicializa();
			Scene escenaListarProfesores = new Scene(raizListarProfesores);
			listarProfesores.setTitle("Listar Profesores");
			listarProfesores.initModality(Modality.APPLICATION_MODAL);
			listarProfesores.setScene(escenaListarProfesores);
		} else {
			cListarProfesores.inicializa();
		}
	}

	private void crearListarAlumnos() throws IOException {
		if (listarAlumnos == null) {
			listarAlumnos = new Stage();
			FXMLLoader cargadorListarAlumnos = new FXMLLoader(getClass().getResource("../vistas/ListarAlumnos.fxml"));
			VBox raizListarAlumnos = cargadorListarAlumnos.load();
			cListarAlumnos = cargadorListarAlumnos.getController();
			cListarAlumnos.setControladorMVC(controladorMVC);
			cListarAlumnos.inicializa();
			Scene escenaListarAlumnos = new Scene(raizListarAlumnos);
			listarAlumnos.setTitle("Listar Alumnos");
			listarAlumnos.initModality(Modality.APPLICATION_MODAL);
			listarAlumnos.setScene(escenaListarAlumnos);
		} else {
			cListarAlumnos.inicializa();
		}
	}

	private void crearListarTutorias() throws IOException {
		if (listarTutorias == null) {
			listarTutorias = new Stage();
			FXMLLoader cargadorListarTutorias = new FXMLLoader(getClass().getResource("../vistas/ListarTutorias.fxml"));
			VBox raizListarTutorias = cargadorListarTutorias.load();
			cListarTutorias = cargadorListarTutorias.getController();
			cListarTutorias.setControladorMVC(controladorMVC);
			cListarTutorias.inicializa();
			Scene escenaListarTutorias = new Scene(raizListarTutorias);
			listarTutorias.setTitle("Listar Tutorías");
			listarTutorias.initModality(Modality.APPLICATION_MODAL);
			listarTutorias.setScene(escenaListarTutorias);
		} else {
			cListarTutorias.inicializa();
		}
	}

	private void crearListarSesiones() throws IOException {
		if (listarSesiones == null) {
			listarSesiones = new Stage();
			FXMLLoader cargadorListarSesiones = new FXMLLoader(getClass().getResource("../vistas/ListarSesiones.fxml"));
			VBox raizListarSesiones = cargadorListarSesiones.load();
			cListarSesiones = cargadorListarSesiones.getController();
			cListarSesiones.setControladorMVC(controladorMVC);
			cListarSesiones.inicializa();
			Scene escenaListarSesiones = new Scene(raizListarSesiones);
			listarSesiones.setTitle("Listar Sesiones");
			listarSesiones.initModality(Modality.APPLICATION_MODAL);
			listarSesiones.setScene(escenaListarSesiones);
		} else {
			cListarSesiones.inicializa();
		}
	}

	private void crearListarCitas() throws IOException {
		if (listarCitas == null) {
			listarCitas = new Stage();
			FXMLLoader cargadorListarCitas = new FXMLLoader(getClass().getResource("../vistas/ListarCitas.fxml"));
			VBox raizListarCitas = cargadorListarCitas.load();
			cListarCitas = cargadorListarCitas.getController();
			cListarCitas.setControladorMVC(controladorMVC);
			cListarCitas.inicializa();
			Scene escenaListarCitas = new Scene(raizListarCitas);
			listarCitas.setTitle("Listar Citas");
			listarCitas.initModality(Modality.APPLICATION_MODAL);
			listarCitas.setScene(escenaListarCitas);
		} else {
			cListarCitas.inicializa();
		}
	}

	private void crearBuscarTutoria() throws IOException {
		if (buscarTutoria == null) {
			buscarTutoria = new Stage();
			FXMLLoader cargadorBuscarTutoria = new FXMLLoader(getClass().getResource("../vistas/BuscarTutorias.fxml"));
			VBox raizBuscarTutoria = cargadorBuscarTutoria.load();
			cBuscarTutorias = cargadorBuscarTutoria.getController();
			cBuscarTutorias.setControladorMVC(controladorMVC);
			cBuscarTutorias.inicializa();
			Scene escenaBuscarProfesor = new Scene(raizBuscarTutoria);
			buscarTutoria.setTitle("Buscar Tutoria");
			buscarTutoria.initModality(Modality.APPLICATION_MODAL);
			buscarTutoria.setScene(escenaBuscarProfesor);
		} else {
			cBuscarTutorias.inicializa();
		}
	}

	private void crearBuscarSesion() throws IOException {
		if (buscarSesion == null) {
			buscarSesion = new Stage();
			FXMLLoader cargadorBuscarSesion = new FXMLLoader(getClass().getResource("../vistas/BuscarSesiones.fxml"));
			VBox raizBuscarSesion = cargadorBuscarSesion.load();
			cBuscarSesion = cargadorBuscarSesion.getController();
			cBuscarSesion.setControladorMVC(controladorMVC);
			cBuscarSesion.inicializa();
			Scene escenaBuscarSesion = new Scene(raizBuscarSesion);
			buscarSesion.setTitle("Buscar Sesión");
			buscarSesion.initModality(Modality.APPLICATION_MODAL);
			buscarSesion.setScene(escenaBuscarSesion);
		} else {
			cBuscarSesion.inicializa();
		}
	}

	private void crearBuscarCita() throws IOException {
		if (buscarCita == null) {
			buscarCita = new Stage();
			FXMLLoader cargadorBuscarCita = new FXMLLoader(getClass().getResource("../vistas/BuscarCitas.fxml"));
			VBox raizBuscarCita = cargadorBuscarCita.load();
			cBuscarCita = cargadorBuscarCita.getController();
			cBuscarCita.setControladorMVC(controladorMVC);
			cBuscarCita.inicializa();
			Scene escenaBuscarCita = new Scene(raizBuscarCita);
			buscarCita.setTitle("Buscar cita");
			buscarCita.initModality(Modality.APPLICATION_MODAL);
			buscarCita.setScene(escenaBuscarCita);
		} else {
			cBuscarCita.inicializa();
		}
	}

}
