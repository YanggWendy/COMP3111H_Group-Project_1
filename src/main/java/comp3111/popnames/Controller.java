/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;

    @FXML
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;
    
    
    // task1
    @FXML
    private TextField t1TopN;

    @FXML
    private RadioButton t1genderM;

    @FXML
    private RadioButton t1genderF;

    @FXML
    private TextField t1year2;

    @FXML
    private TextField t1year1;
    
    @FXML
    void doT1Report() {
    	t1genderM.setUserData("M");
    	t1genderF.setUserData("F");	
    	int top_n = Integer.parseInt(t1TopN.getText());
    	String gender = T1.getSelectedToggle().getUserData().toString();
    	int year0 = Integer.parseInt(t1year1.getText());
    	int year1 = Integer.parseInt(t1year2.getText());
//    	textAreaConsole.setText(AnalyzeNames.reportTopname(top_n, gender, year0, year1));
    }
    
    
    
    // task2
    @FXML
    private TextField t2name;
    
    @FXML
    private TextField t2year0;    
    
    @FXML
    private TextField t2year1;
    
    @FXML
    private Button t2button;
    
    @FXML
    private RadioButton t2genderM;

    @FXML
    private RadioButton t2genderF;

    @FXML
    private TableView t2Table;

    @FXML
    void doT2Report() {
    	t2genderM.setUserData("M");
    	t2genderF.setUserData("F");	
    	String name = t2name.getText();
    	String  gender = T11.getSelectedToggle().getUserData().toString();
    	int year0 = Integer.parseInt(t2year0.getText());
    	int year1 = Integer.parseInt(t2year1.getText());

    	// popup window
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/tab2result.fxml"));
            Pane root = (Pane) loader.load();

            TableColumn year = new TableColumn("Year");
            TableColumn count = new TableColumn("Count");
            TableColumn percentage = new TableColumn("Percentage");
            TableColumn rank = new TableColumn("Rank");

            Stage stage = new Stage();
            stage.setTitle("Result for "+name);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



 // task3    
    @FXML
    private TextField t3year0;    
    
    @FXML
    private TextField t3year1;
    
    @FXML
    private RadioButton t3genderM;

    @FXML
    private RadioButton t3genderF;
    
    @FXML
    void doT3Report() {
    	t3genderM.setUserData("M");
    	t3genderF.setUserData("F");	
    	String  gender = T111.getSelectedToggle().getUserData().toString();
    	int year0 = Integer.parseInt(t3year0.getText());
    	int year1 = Integer.parseInt(t3year1.getText());
    	textAreaConsole.setText(AnalyzeNames.reportTrend(gender, year0, year1)); // radio button not ready so input F here for now.
    }
    
    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }
    

}

