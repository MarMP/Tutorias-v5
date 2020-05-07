package org.iesalandalus.programacion.tutorias.mvc.vista.iugrafica.controladoresvistas;


import org.iesalandalus.programacion.tutorias.mvc.controlador.IControlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class ControladorListarTutorias {
	
	private IControlador controladorMVC;
		
	@FXML private TableColumn<Tutoria, String> tcProfesor;
    @FXML private TableColumn<Tutoria, String> tcTutoria;
    @FXML private TableView<Tutoria> tvTutorias;
    @FXML private TextField tfTutoriaProfesor;
    
   private ObservableList<Tutoria> list = FXCollections.observableArrayList(); 
   private FilteredList<Tutoria> filter = new FilteredList<>(list,e->true);
    
 
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}


	public void inicializa() {
		list.setAll(FXCollections.observableArrayList(controladorMVC.getTutorias()));
	} 

	@FXML
	private void initialize() {
		tcProfesor.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getProfesor().getNombre()));
		tcTutoria.setCellValueFactory(tutoria -> new SimpleStringProperty(tutoria.getValue().getNombre()));
		
		tfTutoriaProfesor.textProperty().addListener((observable, oldValue, newValue) -> {
			filter.setPredicate(tutoria -> {	
				if(newValue == null || newValue.isEmpty()) {			
					return true;
				} 		
				if (tutoria.getProfesor().getDni().contains(newValue)) {
					return true;
				} else {
					return false;
				} 
			});
		});
		SortedList <Tutoria> sort = new SortedList<>(filter);
		sort.comparatorProperty().bind(tvTutorias.comparatorProperty());
		tvTutorias.setItems(sort);		
	}
	
}
