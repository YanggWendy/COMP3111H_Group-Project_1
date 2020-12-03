/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames.controllers;

import comp3111.popnames.AnalyzeNames;
import comp3111.popnames.PredicReport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MainController Program implements the methods in ui.fxml. It includes
 * the report or recommend functions in tasks 1-6 as well as the pop up window 
 * that may include bar or line chart.
 * @author YCY Group
 * @since  2020-10-30
 */

public class MainController {

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

    /**
     * This method pop up warning when users input some invalid information 
     * @param nothing
     * @return nothing
     */
    void popupWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Year out of range");
        alert.setHeaderText(null);
        alert.setContentText("Please input years between 1880 and 2019!");
        alert.showAndWait();
    }
    
    // task1
    @FXML private TextField t1TopN;
    @FXML private RadioButton t1genderM;
    @FXML private RadioButton t1genderF;
    @FXML private TextField t1year2;
    @FXML private TextField t1year1;
    
    /**
     * This method implements the Task1 and will be executed after users click the report button. 
     * It enable an pop up window for a visual table.
     * @param nothing
     * @return nothing
     * @see IOException
     */
    @FXML
    void doT1Report() {
    	t1genderM.setUserData("M");
    	t1genderF.setUserData("F");	
    	int top_n = Integer.parseInt(t1TopN.getText());
    	String gender = T1.getSelectedToggle().getUserData().toString();
    	int year0 = Integer.parseInt(t1year1.getText());
    	int year1 = Integer.parseInt(t1year2.getText());

        // popup window
        try {
            if (year0<1880 || year1<1880 || year0>2019 || year1>2019) {
                popupWarning();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/tab1result.fxml"));
            loader.setController(new Table1Controller(AnalyzeNames.reportTopname(top_n, gender, year0, year1)));
            Stage stage = new Stage();
            stage.setTitle("Result for Top " + t1TopN.getText());
            Pane root = (Pane) loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML private TextField t2name;
    @FXML private TextField t2year0;
    @FXML private TextField t2year1;
    @FXML private RadioButton t2genderM;
    @FXML private RadioButton t2genderF;
    
    /**
     * This method implements the Task2 and will be executed after users click the report button in task2. 
     * It enable an pop up window for a visual table and charts.
     * @param nothing
     * @return nothing
     * @see IOException
     */
    // task2
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
            if (year0<1880 || year1<1880 || year0>2019 || year1>2019) {
                popupWarning();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/tab2result.fxml"));
            loader.setController(new Table2Controller(name, gender, year0, year1));
            Stage stage = new Stage();
            stage.setTitle("Result for "+name);
            Pane root = (Pane) loader.load();
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
    
    
    /**
     * This method implements the Task3 and will be executed after users click the report button in task3. 
     * It enable an pop up window for a visual table and charts.
     * @param nothing
     * @return nothing
     * @see IOException
     */
    // task2
    @FXML
    void doT3Report() {
    	t3genderM.setUserData("M");
    	t3genderF.setUserData("F");	
    	String  gender = T111.getSelectedToggle().getUserData().toString();
    	int year0 = Integer.parseInt(t3year0.getText());
    	int year1 = Integer.parseInt(t3year1.getText());
    	
    	// popup window
//        try {
            if (year0<1880 || year1<1880 || year0>2019 || year1>2019) {
                popupWarning();
                return;
            }
            textAreaConsole.setText(AnalyzeNames.reportTrend(gender, year0, year1)); // radio button not ready so input F here for now.
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	
    }
    
    
 // Task 4 Recommendation on Names for Newborn Babies
 	@FXML
     private TextField t4dadName;

     @FXML
     private TextField t4momName;

     @FXML
     private TextField t4dadYOB;

     @FXML
     private TextField t4momYOB;

     @FXML
     private TextField t4vintageYear;

    @FXML
    private TextArea a1output;
     
    /**
     * This method implements the Task4 and will be executed after users click the recommend button in task4. 
     * It predicts the baby's names and shows the output in specific TextArea.
     * @param nothing
     * @return nothing
     */
     @FXML
 	 void doT4Recommend() {
 		String dadName = t4dadName.getText();
 		String momName = t4momName.getText();
 		String dadYOB_s = t4dadYOB.getText();
 		String momYOB_s = t4momYOB.getText();
 		int dadYOB;
 		int momYOB;
 		if(dadYOB_s.length()==0||momYOB_s.length()==0) {
 			a1output.setText("Please enter valid parent YOB!");
            return;
 		}else {
 			dadYOB = Integer.parseInt(t4dadYOB.getText());
 	 		momYOB = Integer.parseInt(t4momYOB.getText());
 		}
 		
 		String vintageYear_s = t4vintageYear.getText();
 			
 		if (dadName.length() == 0 || momName.length() == 0) {
            a1output.setText("Please enter valid parent names!");
            return;
        }
 		
 		
 		if(vintageYear_s.length() == 0) {
            a1output.setText("Set the default vintage year 2019\n"+ PredicReport.recomendName(dadName, momName, dadYOB, momYOB,2019));
 		}
 		else{ 
 			int vintageYear = Integer.parseInt(t4vintageYear.getText());
            a1output.setText(PredicReport.recomendName(dadName, momName, dadYOB, momYOB,vintageYear));
 		}
 	}
    
     
     //T5 Recommendation
     @FXML
     private ToggleGroup A21;

     @FXML
     private ToggleGroup A22;

     @FXML
     private TextField t5YOB;

     @FXML
     private TextField t5Name;

     @FXML
     private TextArea a2output;
     
     @FXML
     private RadioButton t5genderM;

     @FXML
     private RadioButton t5genderF;

     @FXML
     private RadioButton t5Younge;

     @FXML
     private RadioButton t5Old;
     
     @FXML
     private RadioButton t5genderM1;

     @FXML
     private ToggleGroup A23;

     @FXML
     private RadioButton t5genderF1;

    
     @FXML
     private RadioButton t5genderM1;

     @FXML
     private ToggleGroup A23;

     @FXML
     private RadioButton t5genderF1;

     /**
      * This method implements the Task5 and will be executed after users click the recommend button in task5. 
      * It predicts the soulmate names and shows the output in specific TextArea.
      * @param nothing
      * @return nothing
      */
     @FXML
 	 void doT5Recommend() {
    	t5genderM.setUserData("M");
     	t5genderF.setUserData("F"); 
     	t5Younge.setUserData("Younger");
     	t5Old.setUserData("Older"); 
     	t5genderM1.setUserData("M");
     	t5genderF1.setUserData("F"); 
     	
    	String gender = A21.getSelectedToggle().getUserData().toString();
 		String name = t5Name.getText();
 		//check YOB
 		String YOB_s= t5YOB.getText().toString();
 		int YOB;
 		if(YOB_s.length()==0) {
 			a2output.setText("Please enter valid YOB!");
 			return; 
 		}
 		else{YOB = Integer.parseInt(t5YOB.getText());}
 		
 		String preference = A22.getSelectedToggle().getUserData().toString();
 		String genderMate = A23.getSelectedToggle().getUserData().toString();
 		//System.out.println(YOB);
 		if (name.length() == 0) {
 			a2output.setText("Please enter valid name!");
            return;
        }
 		else{ 
 			a2output.setText(PredicReport.recomendName_task5(name, YOB, gender,genderMate,preference));
 		}
 	} 
     
     //T6
     @FXML
     private ToggleGroup A31;

     @FXML
     private ToggleGroup A33;

     @FXML
     private TextField a3name;

     @FXML
     private TextField a3yob;

     @FXML
     private TextArea A3output;

     @FXML
     private TextField a3matename;

     @FXML
     private ToggleGroup A32;
     
     @FXML
     private RadioButton t6male;

     @FXML
     private RadioButton t6female;

     @FXML
     private RadioButton t6Young;

     @FXML
     private RadioButton t6Old;
     
     @FXML
     private RadioButton t6mateM;

     @FXML
     private RadioButton t6mateF;
     
     /**
      * This method implements the Task6 and will be executed after users click the recommend button in task6. 
      * It predicts on Scores for Compatible Pairs and shows the output in specific TextArea.
      * @param nothing
      * @return nothing
      */
     @FXML
 	 void doT6Recommend() {
    	t6male.setUserData("M");
    	t6female.setUserData("F"); 
    	t6mateM.setUserData("M");
    	t6mateF.setUserData("F");
    	t6Young.setUserData("Younger");
    	t6Old.setUserData("Older");  
    	
 		String Name = a3name.getText();
 		String mateName = a3matename.getText();
 		String gender = A31.getSelectedToggle().getUserData().toString(); 
 		String mate_gender = A32.getSelectedToggle().getUserData().toString();
 		String preference = A33.getSelectedToggle().getUserData().toString();
 		int YOB;
 		String YOB_s = a3yob.getText();
 		if (Name.length() == 0 || mateName.length() == 0) {
 			A3output.setText("Please enter valid names!");
            return;
        }
 		if(YOB_s.length() == 0) {
 			A3output.setText("Please enter valid YOB!");
            return;
 		}
 		else{ 
 			YOB = Integer.parseInt(a3yob.getText());
 		}
 		A3output.setText(PredicReport.recomendName_task6(Name, mateName, YOB, gender,mate_gender,preference));
 		
 	} 
     
    // T7
     @FXML
     private TextField t7name;

     @FXML
     private RadioButton t7male;

     @FXML
     private ToggleGroup A4;

     @FXML
     private RadioButton t7female;

     @FXML
     private RadioButton t7pop0;

     @FXML
     private ToggleGroup A41;

     @FXML
     private RadioButton t7pop1;

     @FXML
     private RadioButton t7pop2;

     @FXML
     private TextArea T7output;
     
     
     /**
      * This method implements the Task7 and will be executed after users click the recommend button in task7. 
      * It recommends similar name and shows the output in specific TextArea.
      * @param nothing
      * @return nothing
      */
     @FXML
     void doT7Recommend() {
    	t7male.setUserData("M");
     	t7female.setUserData("F"); 
     	t7pop0.setUserData("0");
     	t7pop1.setUserData("1");
     	t7pop2.setUserData("2");
     	  
     	
  		String Name = t7name.getText();
  		String gender = A4.getSelectedToggle().getUserData().toString(); 
  		int popularity = Integer.valueOf(A41.getSelectedToggle().getUserData().toString());
  		System.out.println("name: " + Name + " gender: " + gender + " popularity: " + popularity);
  		List<Pair<String, Integer>> nameList = PredicReport.similarNames(Name, gender, popularity);
  		String outputString = "";
  		for(Pair<String, Integer> pair: nameList) {
  			outputString += pair.getKey() + " " + pair.getValue() + "\n";
  		}
  		T7output.setText(outputString);
     }
     
     @FXML
     private Button button;
     // do welcome interface
     
     /**
      * This method implements the welcome window and will be executed after users click the start button in welcome window. 
      * It welcomes users and guides them to do the name analysis task.
      * @param nothing
      * @return nothing
      */
     @FXML
     void doWelcome() throws IOException {
    	Stage primaryStage = (Stage)button.getScene().getWindow();

    	FXMLLoader loader = new FXMLLoader();
 		loader.setLocation(getClass().getResource("/ui.fxml"));
		VBox root = (VBox) loader.load();
		Scene scene =  new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Team T-30H: Popular Names ");
		stage.show();  
		primaryStage.hide();
    	 
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

