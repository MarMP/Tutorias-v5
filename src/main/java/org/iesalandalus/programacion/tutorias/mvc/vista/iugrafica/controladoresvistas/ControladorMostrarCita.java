package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorMostrarCita {
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	private IControlador controladorMVC;
	private Cita cita;
	
	@FXML private Label lbFecha;
	@FXML private Label lbNombreProfesor;
	@FXML private Label lbTutoria;
	@FXML private Label lbCorreo;
	@FXML private Label lbInicio;
	@FXML private Label lbFin;
	@FXML private Label lbHoraCita;
	@FXML private Label lbNombreTutoria;
	@FXML private Label lbAlumno;
	@FXML private Label lbExpediente;
	@FXML private Label lbDni;
	@FXML private Label lbCorreoAlumno;
	@FXML private Label lbDuracion;
	@FXML private Button btnAceptar;
	@FXML private Button btnBorrar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
		lbNombreProfesor.setText(cita.getSesion().getTutoria().getProfesor().getNombre());
		lbDni.setText(cita.getSesion().getTutoria().getProfesor().getDni());
		lbCorreo.setText(cita.getSesion().getTutoria().getProfesor().getCorreo());
		lbAlumno.setText(cita.getAlumno().getNombre());
		lbCorreoAlumno.setText(cita.getAlumno().getCorreo());
		lbExpediente.setText(cita.getAlumno().getExpediente());
		lbNombreTutoria.setText(cita.getSesion().getTutoria().getNombre());
		lbFecha.setText(FORMATO_FECHA.format(cita.getSesion().getFecha()));
		lbInicio.setText(FORMATO_HORA.format(cita.getSesion().getHoraInicio()));
		lbFin.setText(FORMATO_HORA.format(cita.getSesion().getHoraFin()));
		lbDuracion.setText(Integer.toString(cita.getSesion().getMinutosDuracion()));
		lbHoraCita.setText(FORMATO_HORA.format(cita.getHora()));
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
					"¿Estás seguro de que desea eliminar la cita:  " + cita + "?", null)) {
				controladorMVC.borrar(cita);
				Dialogos.mostrarDialogoInformacion("Borrar cita", "Cita borrada satisfactoriamente", propietario);
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Borrar cita", e.getMessage(), propietario);
		}
	}

}
