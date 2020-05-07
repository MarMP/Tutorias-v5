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

public class ControladorBuscarCitas {
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private IControlador controladorMVC;
	private ObservableList<Sesion> sesiones = FXCollections.observableArrayList();
	private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
	private Cita cita = null;
	
	@FXML private TableView<Sesion> tvSesiones;
	@FXML private TableColumn<Sesion, String> tcProfesor;
	@FXML private TableColumn<Sesion, String> tcTutoria;
	@FXML private TableColumn<Sesion, String> tcFecha;
	@FXML private TableColumn<Sesion, String> tcInicio;
	@FXML private TableColumn<Sesion, String> tcFin;
	@FXML private TableColumn<Sesion, Integer> tcDuracion;
	@FXML private ListView<Alumno> lvAlumnos;
	@FXML private TextField tfHoraCita;
	@FXML private Button btnAceptar;
	@FXML private Button btnCancelar;
	 
	private class CeldaAlumno extends ListCell<Alumno> {
		@Override
		public void updateItem(Alumno alumno, boolean vacia) {
			super.updateItem(alumno, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(alumno.getNombre());
			}
		}
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		sesiones.setAll(FXCollections.observableArrayList(controladorMVC.getSesiones()));
		alumnos.setAll(FXCollections.observableArrayList(controladorMVC.getAlumnos()));
		tfHoraCita.setText("");
		compruebaCampoTexto("", tfHoraCita);
	}

	@FXML
	private void initialize() {
		tcProfesor.setCellValueFactory(
				citas -> new SimpleStringProperty(citas.getValue().getTutoria().getProfesor().getNombre()));
		tcTutoria.setCellValueFactory(citas -> new SimpleStringProperty(citas.getValue().getTutoria().getNombre()));
		tcFecha.setCellValueFactory(
				citas -> new SimpleStringProperty(FORMATO_FECHA.format(citas.getValue().getFecha())));
		tcInicio.setCellValueFactory(
				citas -> new SimpleStringProperty(FORMATO_HORA.format(citas.getValue().getHoraInicio())));
		tcFin.setCellValueFactory(
				citas -> new SimpleStringProperty(FORMATO_HORA.format(citas.getValue().getHoraFin())));
		tcDuracion.setCellValueFactory(
				citas -> new SimpleIntegerProperty(citas.getValue().getMinutosDuracion()).asObject());
		tfHoraCita.setDisable(false);
		tvSesiones.setItems(sesiones);
		lvAlumnos.setItems(alumnos);
		lvAlumnos.setCellFactory(l -> new CeldaAlumno());
	}

	public Cita getCitas() {
		return cita;
	}

	@FXML
	void buscarCita(ActionEvent event) {
		Alumno alumno = lvAlumnos.getSelectionModel().getSelectedItem();
		Sesion sesion = tvSesiones.getSelectionModel().getSelectedItem();
		try {
			LocalTime fecha = LocalTime.parse(tfHoraCita.getText());
			cita = new Cita(alumno, sesion, fecha);
			cita = controladorMVC.buscar(cita);
			((Stage) btnAceptar.getScene().getWindow()).close();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar cita", e.getMessage());
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnCancelar.getScene().getWindow()).close();
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
