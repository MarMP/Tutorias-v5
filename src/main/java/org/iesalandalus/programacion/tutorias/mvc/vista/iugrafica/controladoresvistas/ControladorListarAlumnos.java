package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarAlumnos {
	
	private IControlador controladorMVC;
	
    @FXML private TableColumn<Alumno,String> tcNombre;
    @FXML private TableColumn<Alumno,String> tcCorreoAlumno;
    @FXML private TableColumn<Alumno,String> tcExpediente;
    @FXML private TableView<Alumno> tvAlumnos;
    @FXML private Button btnAceptar;
    
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvAlumnos.setItems(FXCollections.observableArrayList(controladorMVC.getAlumnos()));
	}

	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getNombre()));
		tcCorreoAlumno.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getCorreo()));
		tcExpediente.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getExpediente()));
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btnAceptar.getScene().getWindow();
		escena.close();
	}

}
