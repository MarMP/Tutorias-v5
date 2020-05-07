package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarSesion {
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private IControlador controladorMVC;
	private Sesion sesion;
	
	@FXML private Label lbFecha;
	@FXML private Label lbNombreProfesor;
	@FXML private Label lbTutoria;
	@FXML private Label lbCorreo;
	@FXML private Label lbDni;
	@FXML private Label lbInicio;
	@FXML private Label lbFin;
	@FXML private Label lbDuracion;
	@FXML private Label lbNombreTutoria;
	@FXML private Button btnAceptar;
	@FXML private Button btnBorrar;
	 
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
		lbNombreProfesor.setText(sesion.getTutoria().getProfesor().getNombre());
		lbDni.setText(sesion.getTutoria().getProfesor().getDni());
		lbCorreo.setText(sesion.getTutoria().getProfesor().getCorreo());
		lbNombreTutoria.setText(sesion.getTutoria().getNombre());
		lbFecha.setText(FORMATO_FECHA.format(sesion.getFecha()));
		lbInicio.setText(FORMATO_HORA.format(sesion.getHoraInicio()));
		lbFin.setText(FORMATO_HORA.format(sesion.getHoraFin()));
		lbDuracion.setText(Integer.toString(sesion.getMinutosDuracion()));
	}

	@FXML
	void aceptar() {
		Stage escena = (Stage) btnAceptar.getScene().getWindow();
		escena.close();
	}

	@FXML
	void borrar() {
		Stage propietario = (Stage) btnBorrar.getScene().getWindow();
		try {
			if (Dialogos.mostrarDialogoConfirmacion("Confirmar",
					"¿Estás seguro de que desea eliminar la sesión:  " + sesion + "?", null)) {
				controladorMVC.borrar(sesion);
				Dialogos.mostrarDialogoInformacion("Borrar sesión", "Sesión borrada satisfactoriamente", propietario);
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar sesión", e.getMessage(), propietario);
		}
	}

}
