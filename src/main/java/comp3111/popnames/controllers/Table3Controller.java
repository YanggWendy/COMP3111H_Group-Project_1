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
	@FXML
	private TableView<String[]> t3table2;
	String[][] arr0;
	String[][] arr1;

	public Table3Controller(String arr0[][], String arr1[][]) {
		this.arr0 = arr0;
		this.arr1 = arr1;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(arr0));
		data.remove(0);//remove titles from data
		for (int i = 0; i < arr0[0].length; i++) {
			TableColumn tc = new TableColumn(arr0[0][i]);
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

		data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(arr1));
		data.remove(0);//remove titles from data
		for (int i = 0; i < arr1[0].length; i++) {
			TableColumn tc = new TableColumn(arr1[0][i]);
			final int colNo = i;
			tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
					return new SimpleStringProperty((p.getValue()[colNo]));
				}
			});
			tc.setPrefWidth(150);
			t3table2.getColumns().add(tc);
		}
		t3table2.setItems(data);
	}
}
