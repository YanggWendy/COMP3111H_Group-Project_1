package comp3111.popnames.controllers;

import comp3111.popnames.AnalyzeNames;
import comp3111.popnames.Year;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Table2Controller implements Initializable {
    @FXML private TableView<Year> t2table;
    @FXML private TableColumn<Year, Integer> yearCol;
    @FXML private TableColumn<Year, Integer> countCol;
    @FXML private TableColumn<Year, Integer> rankCol;
    @FXML private TableColumn<Year, String> percentCol;

    String name;
    String gender;
    int year0;
    int year1;

    public Table2Controller(String name, String gender, int year0, int year1) {
        this.name = name;
        this.gender = gender;
        this.year0 = year0;
        this.year1 = year1;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Year"));
        countCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Count"));
        rankCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Rank"));
        percentCol.setCellValueFactory(new PropertyValueFactory<Year, String>("Percentage"));
        t2table.setItems(AnalyzeNames.reportPopularity(name,gender, year0, year1));
    }
}
