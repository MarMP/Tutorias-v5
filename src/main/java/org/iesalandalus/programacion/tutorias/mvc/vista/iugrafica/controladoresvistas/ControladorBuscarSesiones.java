package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.LocalDate;


import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControladorBuscarSesiones {
	
	private IControlador controladorMVC;
	private ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
	private Sesion sesion = null;

	@FXML private Button btnAceptar;
	@FXML private DatePicker dpFecha;
	@FXML private ListView<Tutoria> lvTutorias;
	@FXML private Button btnCancelar;

	private class CeldaTutoria extends ListCell<Tutoria> {
		@Override
		public void updateItem(Tutoria tutoria, boolean vacia) {
			super.updateItem(tutoria, vacia);
			if (vacia) {
				setText("");
			} else {
				setText(tutoria.getNombre());
			}
		}
	}

	@FXML
	private void initialize() {
		lvTutorias.setItems(tutorias);
		lvTutorias.setCellFactory(l -> new CeldaTutoria());
	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		tutorias.setAll(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		dpFecha.setValue(null);
	}

	public Sesion getSesion() {
		return sesion;
	}

	@FXML
	void buscarSesion(ActionEvent event) {
		Tutoria tutoria = lvTutorias.getSelectionModel().getSelectedItem();
		LocalDate fecha = dpFecha.getValue();
		try {
			sesion = controladorMVC.buscar(Sesion.getSesionFicticia(tutoria, fecha));
			((Stage) btnAceptar.getScene().getWindow()).close();
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Buscar sesión", e.getMessage());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		((Stage) btnCancelar.getScene().getWindow()).close();
	}

}
