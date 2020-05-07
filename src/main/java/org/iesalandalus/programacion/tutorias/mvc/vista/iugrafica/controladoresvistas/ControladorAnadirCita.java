package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirCita {
		
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private IControlador controladorMVC;
	
	private ObservableList<Sesion> sesiones = FXCollections.observableArrayList();
	private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
	
	@FXML private TableView<Sesion> tvSesiones;
	@FXML private TableColumn<Sesion, String> tcProfesor;
	@FXML private TableColumn<Sesion, String> tcTutoria;
	@FXML private TableColumn<Sesion, String> tcFecha;
	@FXML private TableColumn<Sesion, String> tcInicio;
	@FXML private TableColumn<Sesion, String> tcFin;
	@FXML private TableColumn<Sesion, Integer> tcDuracion;
	@FXML private ListView<Alumno> lvAlumno;
	@FXML private TextField tfHoraCita;
	@FXML private Button btnBorrar;
	@FXML private Button btnAceptar;
	@FXML private Button btnCancelar;

	private class CeldaAlumno extends ListCell<Alumno> {
		@Override
		public void updateItem(Alumno alumno, boolean vacio) {
			super.updateItem(alumno, vacio);
			if (vacio) {
				setText("");
			} else {
				setText(alumno.getNombre());
			}
		}
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {
		tcProfesor.setCellValueFactory(
				cita -> new SimpleStringProperty(cita.getValue().getTutoria().getProfesor().getNombre()));
		tcTutoria.setCellValueFactory(cita -> new SimpleStringProperty(cita.getValue().getTutoria().getNombre()));
		tcFecha.setCellValueFactory(cita -> new SimpleStringProperty(FORMATO_FECHA.format(cita.getValue().getFecha())));
		tcInicio.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getHoraInicio())));
		tcFin.setCellValueFactory(cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getHoraFin())));
		tcDuracion.setCellValueFactory(
				cita -> new SimpleIntegerProperty(cita.getValue().getMinutosDuracion()).asObject());
		tfHoraCita.setDisable(false);
		tvSesiones.setItems(sesiones);
		lvAlumno.setItems(alumnos);
		lvAlumno.setCellFactory(l -> new CeldaAlumno());
	}

	public void inicializa() {
		sesiones.setAll(FXCollections.observableArrayList(controladorMVC.getSesiones()));
		alumnos.setAll(FXCollections.observableArrayList(controladorMVC.getAlumnos()));
		tfHoraCita.setText("");
		compruebaCampoTexto("", tfHoraCita);
	}

	@FXML
	void anadirCita(ActionEvent event) {
		Sesion sesion = tvSesiones.getSelectionModel().getSelectedItem();
		Alumno alumno = lvAlumno.getSelectionModel().getSelectedItem();
		Cita cita = null;
		try {
			cita = new Cita(alumno, sesion,
					LocalTime.parse(tfHoraCita.getText(), DateTimeFormatter.ofPattern("HH:mm")));
			controladorMVC.insertar(cita);
			Stage propietario = ((Stage) btnAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Cita", "Cita insertada correctamente.", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir sesión", e.getMessage());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnCancelar.getScene().getWindow()).close();
	}

	@FXML
	void borrar(ActionEvent event) {
		tfHoraCita.setText("");
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

}
