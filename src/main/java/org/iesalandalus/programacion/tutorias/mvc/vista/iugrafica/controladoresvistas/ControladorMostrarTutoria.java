package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarTutoria {
	
	private IControlador controladorMVC;
	private Tutoria tutoria;
	
    @FXML private Label lbNombreProfesor;
    @FXML private Label lbDni;
    @FXML private Label lbCorreo;
    @FXML private Label lbNombreTutoria;
    @FXML private Button btnBorrar;
    @FXML private Button btnAceptar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setTutoria(Tutoria tutoria) {
		this.tutoria = tutoria;
		lbNombreProfesor.setText(tutoria.getProfesor().getNombre());
		lbDni.setText(tutoria.getProfesor().getDni());
		lbCorreo.setText(tutoria.getProfesor().getCorreo());
		lbNombreTutoria.setText(tutoria.getNombre());
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
			if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
					"¿Estás seguro de que desea eliminar la tutoría:  " + tutoria + "?", null)) {
				controladorMVC.borrar(tutoria);
				Dialogos.mostrarDialogoInformacion("Borrar tutoría", "Tutoría borrada satisfactoriamente", propietario);
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar tutoría", e.getMessage(), propietario);
		}
	}

}
