package comp3111.popnames.controllers;

import comp3111.popnames.Year;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


/**
 * The Table1Controller Program implements the methods in tab1result.fxml. It includes
 * the report functions in tasks 1 as well as the visual table that includes .
 * @author YCY Group
 * @since  2020-10-30
 */
public class Table1Controller implements Initializable {
    @FXML private TableView<String[]> t1table;
    String[][] arr;

    /**
	 * Table1Controller class constructor that initiates the variables
	 * @param arr[][]
	 */
    public Table1Controller(String arr[][]) {
        this.arr = arr;
    }
    
    /**
	 * Table1Controller class initialize
	 * @param location
	 * @param resources
	 * @return void
	 */
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
//            tc.setPrefWidth(90);
            t1table.getColumns().add(tc);
        }
        t1table.setItems(data);
    }
}
