package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;


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

public class ControladorListarCitas {
	
	private IControlador controladorMVC;
	
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	
	@FXML private TableView<Cita> tvCitas;
	@FXML private TableColumn<Cita, String> tcTutoria;
	@FXML private TableColumn<Cita, String> tcProfesor;
	@FXML private TableColumn<Cita, Integer> tcDuracion;
	@FXML private TableColumn<Cita, String> tcCorreo;
	@FXML private TableColumn<Cita, String> tcFecha;
	@FXML private TableColumn<Cita, String> tcAlumno;
	@FXML private TableColumn<Cita, String> tcInicio;
	@FXML private TableColumn<Cita, String> tcFin;
	@FXML private TableColumn<Cita, String> tcHoraCita;
	@FXML private TextField tfCitaAlumno;
	@FXML private TextField tfCitasSesion;
	
	private ObservableList<Cita> list = FXCollections.observableArrayList();
	private FilteredList<Cita> filter = new FilteredList<>(list, e -> true);
	
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void inicializa() {
		list.setAll(FXCollections.observableArrayList(controladorMVC.getCitas()));
	}
	
	@FXML
	private void initialize() {
		tcAlumno.setCellValueFactory(
				cita -> new SimpleStringProperty(cita.getValue().getAlumno().getNombre()));
		tcCorreo.setCellValueFactory(
				cita -> new SimpleStringProperty(cita.getValue().getAlumno().getCorreo()));
		tcTutoria.setCellValueFactory(
				cita -> new SimpleStringProperty(cita.getValue().getSesion().getTutoria().getNombre()));
		tcProfesor.setCellValueFactory(
				cita -> new SimpleStringProperty(cita.getValue().getSesion().getTutoria().getProfesor().getNombre()));
		tcFecha.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_FECHA.format(cita.getValue().getSesion().getFecha())));
		tcInicio.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getSesion().getHoraInicio())));
		tcFin.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getSesion().getHoraFin())));
		tcDuracion.setCellValueFactory(
				cita -> new SimpleIntegerProperty(cita.getValue().getSesion().getMinutosDuracion()).asObject());
		tcHoraCita.setCellValueFactory(
				cita -> new SimpleStringProperty(FORMATO_HORA.format(cita.getValue().getHora())));


		tfCitaAlumno.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(cita -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				if (cita.getAlumno().getCorreo().toLowerCase().contains(newValue)) {
					return true;
				} else {
					return false;
				}
			});
		});
		

		tfCitasSesion.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(cita -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String fecha = FORMATO_FECHA.format(cita.getSesion().getFecha());
				if (fecha.contains(newValue)) {
					return true;
				} else {
					return false;
				}
			});
		});
		
		
		SortedList<Cita> sort = new SortedList<>(filter);
		sort.comparatorProperty().bind(tvCitas.comparatorProperty());
		tvCitas.setItems(sort);
	}
}
