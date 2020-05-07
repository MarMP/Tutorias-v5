package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControladorListarSesiones {
	
	private IControlador controladorMVC;
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	@FXML private TableView<Sesion> tvSesiones;
	@FXML private TableColumn<Sesion, String> tcTutoria;
	@FXML private TableColumn<Sesion, String> tcProfesor;
	@FXML private TableColumn<Sesion, Integer> tcDuracion;
	@FXML private TableColumn<Sesion, String> tcFecha;
	@FXML private TableColumn<Sesion, String> tcInicio;
	@FXML private TableColumn<Sesion, String> tcFin;
	@FXML private TextField tfTutoriaProfesor;
	 
	private ObservableList<Sesion> list = FXCollections.observableArrayList();
	private FilteredList<Sesion> filter = new FilteredList<>(list, e -> true);

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		list.setAll(FXCollections.observableArrayList(controladorMVC.getSesiones()));
	}

	@FXML
	private void initialize() {
		tcProfesor.setCellValueFactory(
				sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getProfesor().getNombre()));
		tcTutoria.setCellValueFactory(sesion -> new SimpleStringProperty(sesion.getValue().getTutoria().getNombre()));
		tcFecha.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_FECHA.format(sesion.getValue().getFecha())));
		tcInicio.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_HORA.format(sesion.getValue().getHoraInicio())));
		tcFin.setCellValueFactory(
				sesion -> new SimpleStringProperty(FORMATO_HORA.format(sesion.getValue().getHoraFin())));
		tcDuracion.setCellValueFactory(
				sesion -> new SimpleIntegerProperty(sesion.getValue().getMinutosDuracion()).asObject());

		tfTutoriaProfesor.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(sesion -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				if (sesion.getTutoria().getNombre().toLowerCase().contains(newValue)) {
					return true;
				} else {
					return false;
				}
			});
		});
		SortedList<Sesion> sort = new SortedList<>(filter);
		sort.comparatorProperty().bind(tvSesiones.comparatorProperty());
		tvSesiones.setItems(sort);
	}
}
