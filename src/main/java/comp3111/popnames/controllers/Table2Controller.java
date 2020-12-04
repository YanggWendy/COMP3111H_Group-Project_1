package comp3111.popnames.controllers;

import comp3111.popnames.AnalyzeNames;
import comp3111.popnames.Year;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The Table2Controller Program implements the methods in tab2result.fxml. It includes
 * the pop up report and related function in tasks 2 as well as the visual table and charts that includes .
 * @author YCY Group
 * @since  2020-10-30
 */
public class Table2Controller implements Initializable {
    //table part
	@FXML private TableView<Year> t2table;
    @FXML private TableColumn<Year, Integer> yearCol;
    @FXML private TableColumn<Year, Integer> countCol;
    @FXML private TableColumn<Year, Integer> rankCol;
    @FXML private TableColumn<Year, String> percentCol;

    //Line Chart part
    @FXML
    private LineChart<?, ?> t2LineChart;

    @FXML
    private CategoryAxis t2line_X;

    @FXML
    private NumberAxis t2line_Y;
 
    
    //Bar Chart part
    @FXML
    private BarChart<?, ?> t2BarChart;

    @FXML
    private CategoryAxis t2bar_X;

    @FXML
    private NumberAxis t2bar_Y;
    

    String name;
    String gender;
    int year0;
    int year1;


	/**
	 * Table2Controller class constructor that initiates the variables
	 * @param name
	 * @param gender
	 * @param year0
	 * @param year1
	 */
    public Table2Controller(String name, String gender, int year0, int year1) {
        this.name = name;
        this.gender = gender;
        this.year0 = year0;
        this.year1 = year1;
    }
    
    
    /**
	 * Table2Controller class initialize
	 * @param location
	 * @param resources
	 * @return void
	 */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        yearCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Year"));
        countCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Count"));
        rankCol.setCellValueFactory(new PropertyValueFactory<Year, Integer>("Rank"));
        percentCol.setCellValueFactory(new PropertyValueFactory<Year, String>("Percentage"));
        t2table.setItems(AnalyzeNames.reportPopularity(name,gender, year0, year1));

        
        XYChart.Series series = new XYChart.Series();    
        t2LineChart.getData().add(AnalyzeNames.get_line_series(name,gender, year0, year1,series));
        
        AnalyzeNames.get_bar_series(name,gender, year0, year1,t2BarChart);
        
        
        
    }
    
    

    
}
