package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorBuscarTutorias {
	
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private IControlador controladorMVC;
	private Tutoria tutoria = null;
	
	@FXML private Button btnAceptar;
	@FXML private Button btnLimpiar;
	@FXML private Button btnCancelar;
	@FXML private ListView <Profesor> lvProfesor;
	@FXML private TextField tfNombreTutoria;

	private class CeldaProfesor extends ListCell<Profesor> {
		@Override
		public void updateItem(Profesor profesor, boolean vacio) {
			super.updateItem(profesor, vacio);
			if (vacio) {
				setText("");
			} else {
				setText(profesor.getNombre());
			}
		}
	}

	@FXML
	private void initialize() {
		lvProfesor.setItems(profesores);
		lvProfesor.setCellFactory(l -> new CeldaProfesor());
		tfNombreTutoria.setDisable(false);
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		profesores.setAll(FXCollections.observableArrayList(controladorMVC.getProfesores()));
		tfNombreTutoria.setText("");
	}

	public Tutoria getTutoria() {
		return tutoria;
	}

	@FXML
	void buscarTutoria(ActionEvent event) {
		try {
			String dni = lvProfesor.getSelectionModel().getSelectedItem().getDni();
			tutoria = new Tutoria(Profesor.getProfesorFicticio(dni), tfNombreTutoria.getText());
			tutoria = controladorMVC.buscar(tutoria);
			((Stage) btnAceptar.getScene().getWindow()).close();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar Tutoría", "UPS! no ha introducido ningún campo");
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnCancelar.getScene().getWindow()).close();
	}

	@FXML
	private void limpiar() {
		tfNombreTutoria.setText("");
	}

}
