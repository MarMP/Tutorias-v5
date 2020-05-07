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

public class ControladorAnadirTutorias {
	
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private IControlador controladorMVC;
	
	@FXML private Button btnAceptar;
	@FXML private Button btnLimpiar;
	@FXML private Button btnCancelar;
	@FXML private ListView <Profesor> lvProfesor;
	@FXML private TextField tfNombreTutoria;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

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

	public void inicializa() {
		profesores.setAll(FXCollections.observableArrayList(controladorMVC.getProfesores()));
		tfNombreTutoria.setText("");
		compruebaCampoTexto("", tfNombreTutoria);
	}

	@FXML
	private void aceptar(ActionEvent event) {
		Profesor profesor = lvProfesor.getSelectionModel().getSelectedItem();
		Tutoria tutoria = null;
		try {
			tutoria = new Tutoria(profesor, tfNombreTutoria.getText());
			controladorMVC.insertar(tutoria);
			Stage propietario = ((Stage) btnAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir tutoría", "Tutoría insertada correctamente.", propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir tutoría", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btnCancelar.getScene().getWindow()).close();
	}

	@FXML
	private void limpiar() {
		tfNombreTutoria.setText("");
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
