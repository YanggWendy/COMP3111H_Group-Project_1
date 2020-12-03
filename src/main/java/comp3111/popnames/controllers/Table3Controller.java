package comp3111.popnames.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class Table3Controller implements Initializable {
	@FXML
	private TableView<String[]> t3table;
	String[][] arr;

	public Table3Controller(String arr[][]) {
		this.arr = arr;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(arr));
		data.remove(0);//remove titles from data
		for (int i = 0; i < arr[0].length; i++) {
			TableColumn tc = new TableColumn(arr[0][i]);
			final int colNo = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
            tc.setPrefWidth(150);
			t3table.getColumns().add(tc);
		}
		t3table.setItems(data);
	}
}
