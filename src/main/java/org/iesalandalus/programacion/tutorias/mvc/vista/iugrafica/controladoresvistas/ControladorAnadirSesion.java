package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirSesion {
	
	private IControlador controladorMVC;
	private ObservableList<Tutoria> tutorias = FXCollections.observableArrayList();
	
	@FXML private ListView<Tutoria> lvTutoria;
    @FXML private TextField tfInicio;
    @FXML private TextField tfDuracion;
    @FXML private TextField tfFin;
    @FXML private DatePicker dpDia;
    @FXML private Button btnBorrar;
    @FXML private Button btnAceptar;
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

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {
		tfInicio.setDisable(false);
		tfFin.setDisable(false);
		tfDuracion.setDisable(false);
		lvTutoria.setItems(tutorias);
		lvTutoria.setCellFactory(l -> new CeldaTutoria());
	}

	public void inicializa() {
		tutorias.setAll(FXCollections.observableArrayList(controladorMVC.getTutorias()));
		dpDia.setValue(null);
		tfInicio.setText("");
		compruebaCampoTexto("", tfInicio);
		tfFin.setText("");
		compruebaCampoTexto("", tfFin);
		tfDuracion.setText("");
		compruebaCampoTexto("", tfDuracion);
	}

	@FXML
	void anadirSesion(ActionEvent event) {
		Tutoria tutoria = lvTutoria.getSelectionModel().getSelectedItem();
		LocalDate fechaSesion = dpDia.getValue();
		Sesion sesion = null;
		try {
			sesion = new Sesion(tutoria, fechaSesion,
					LocalTime.parse(tfInicio.getText(), DateTimeFormatter.ofPattern("HH:mm")),
					LocalTime.parse(tfFin.getText(), DateTimeFormatter.ofPattern("HH:mm")),
					Integer.parseInt(tfDuracion.getText()));
			controladorMVC.insertar(sesion);
			Stage propietario = ((Stage) btnAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Sesión", "Sesión insertada correctamente.", propietario);
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
		tfInicio.setText("");
		tfFin.setText("");
		tfDuracion.setText("");
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
