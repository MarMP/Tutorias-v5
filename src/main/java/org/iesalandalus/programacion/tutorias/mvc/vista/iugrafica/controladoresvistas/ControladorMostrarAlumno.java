package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarAlumno {
	
	private IControlador controladorMVC;
	private Alumno alumno;
	
    @FXML private Label lbNombre;
    @FXML private Label lbCorreo;
    @FXML private Label lbExpediente;
    @FXML private Button btnAceptar;
    @FXML private Button btnBorrar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		lbNombre.setText(alumno.getNombre());
		lbCorreo.setText(alumno.getCorreo());
		lbExpediente.setText(alumno.getExpediente());
	}

	@FXML
	private void aceptar() {
		Stage escena = (Stage) btnAceptar.getScene().getWindow();
		escena.close();
	}

	@FXML
	private void borrar() {
		Stage propietario = (Stage) btnBorrar.getScene().getWindow();
		try {
			controladorMVC.borrar(alumno);
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar alumno", e.getMessage(), propietario);
		}
		Dialogos.mostrarDialogoInformacion("Borrar alumno", "Alumno eliminado correctamente", propietario);
	}

}
