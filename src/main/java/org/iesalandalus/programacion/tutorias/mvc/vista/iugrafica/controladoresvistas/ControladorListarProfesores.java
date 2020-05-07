package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarProfesores {
	
	private IControlador controladorMVC;
	
	@FXML private TableColumn<Profesor, String> tcNombre;
    @FXML private TableColumn<Profesor, String> tcDni;
    @FXML private TableColumn<Profesor, String> tcCorreo;
    @FXML private TableView<Profesor> tvProfesores;
    @FXML private Button btnAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tvProfesores.setItems(FXCollections.observableArrayList(controladorMVC.getProfesores()));
	}

	@FXML
	private void initialize() {
		tcNombre.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getNombre()));
		tcDni.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getDni()));
		tcCorreo.setCellValueFactory(profesor -> new SimpleStringProperty(profesor.getValue().getCorreo()));
	}

	@FXML
	void aceptar() {
		Stage escena = (Stage) btnAceptar.getScene().getWindow();
		escena.close();
	}

}
