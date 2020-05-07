package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.utilidades.Dialogos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorAnadirProfesor {

	private static final String ER_NOMBRE = "^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\']+[\\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])+[\\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\\'])?$";
	private static final String ER_DNI = "([0-9]{8})([A-Za-z])";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*@\\w+\\.\\w{2,5}";

	private IControlador controladorMVC;
	
	@FXML private TextField tfNombre;
	@FXML private TextField tfCorreo;
	@FXML private TextField tfDni;
	@FXML private Button btnAceptar;
	@FXML private Button btnLimpiar;
	@FXML private Button btnCancelar;

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML
	private void initialize() {
		tfNombre.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfDni.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_DNI, tfDni));
		tfCorreo.textProperty().addListener((ob, ov, nv) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
	}

	@FXML
	private void aceptar() {
		Profesor profesor = null;
		try {
			profesor = getProfesor();
			controladorMVC.insertar(profesor);
			Stage propietario = ((Stage) btnAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Profesor", "El profesor se ha añadido correctamente.",
					propietario);
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Añadir Profesor", e.getMessage());
		}
	}

	@FXML
	private void cancelar() {
		((Stage) btnCancelar.getScene().getWindow()).close();
	}

	@FXML
	private void limpiar() {
		tfNombre.setText("");
		tfDni.setText("");
		tfCorreo.setText("");
	}

	public void inicializa() {
		tfNombre.setText("");
		compruebaCampoTexto(ER_NOMBRE, tfNombre);
		tfDni.setText("");
		compruebaCampoTexto(ER_DNI, tfDni);
		tfCorreo.setText("");
		compruebaCampoTexto(ER_CORREO, tfCorreo);
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {
		String texto = campoTexto.getText();
		if (texto.matches(er)) {
			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 5;");
		} else {
			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 5;");
		}
	}

	private Profesor getProfesor() {
		String nombre = tfNombre.getText();
		String dni = tfDni.getText();
		String correo = tfCorreo.getText();
		return new Profesor(nombre, dni, correo);
	}
}
