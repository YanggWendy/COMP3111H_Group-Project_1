package comp3111.popnames;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import org.apache.commons.csv.*;
import edu.duke.*;
import org.json.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;


/**
 * The AnalyzeNames Program implements the methods that are used to calculate the report result. It includes
 * the report functions in tasks 1-3.
 * @author YCY Group
 * @since  2020-10-30
 */
public class AnalyzeNames {
	 /**
     * This method get the csv file in dataset with specific year
     * @param year the specific year in dataset
     * @return CSVParser return the csv in that particular year
     */
	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
 
	/**
     * This method generate the String of name Summary in task0 
     * @param year the specific year when summary will be made
     * @return String return the name Summary String
     */
	public static String getSummary(int year) {
		String oReport = "";	
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		int uniqueBoys = 0;
		int uniqueGirls = 0;
		
		oReport = String.format("Summary (Year of %d):\n", year);
		for (CSVRecord rec : getFileParser(year)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			totalNames += 1;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoys++;
			}
			else {
				totalGirls += numBorn;
				uniqueGirls++;
			}
		}
		
		oReport += String.format("Total Births = %,d\n", totalBirths);
		oReport += String.format("***Baby Girls = %,d\n", totalGirls);
		oReport += String.format("***Baby Boys = %,d\n", totalBoys);
		
		oReport += String.format("Total Number of Unique Names = %,d\n", totalNames);
		oReport += String.format("***Unique Names (baby girls) = %,d\n", uniqueGirls);
		oReport += String.format("***Unique Names (baby boys) = %,d\n", uniqueBoys);
		
		return oReport;
	}
	
	/**
     * This method calculates the count of a specific name with specific gender in specific year 
     * @param year the specific year when count will be calculated
     * @param gender the gender of name
     * @param name the name that will be counted
     * @return int return the count of a name with gender in year
     */
	public static int get_count(int year, String gender,String name) {
		
		boolean found = false;
		int count = 0;
	    for (CSVRecord rec : getFileParser(year)) {
	        // Increment rank if gender matches param
	        if (rec.get(1).equals(gender)) {
	            // Return rank if name matches param
	            if (rec.get(0).equals(name)) {
	            	found = true;
	            	count = Integer.parseInt(rec.get(2));
	            	break;
	            }
	         
	        }
	    }
	    if (found)
	    	return count;
	    else
	    	return -1;
	}
	
	/**
     * This method calculates the total rank in specific year with specific gender
     * @param year the specific year when total rank will be calculated
     * @param gender the gender of the total rank
     * @return int return the total rank in year with gender 
     */
	public static int get_total_Rank(int year, String gender) {
	
		int uniqueGirls = 0;
		int uniqueBoys = 0;
		
		for (CSVRecord rec : getFileParser(year)) 
			if (rec.get(1).equals("M")) {
				uniqueBoys++;
			}
			else {
				uniqueGirls++;
			}
		
		if(gender=="F") {return uniqueGirls;}
		else {return uniqueBoys;}	
	
	}

	/**
     * This method calculates the rank of a specific name with specific gender in specific year 
     * @param year the specific year when rank will be calculated
     * @param gender the gender of name
     * @param name the name that will be ranked
     * @return int return the rank of a name with gender in year
     */
	public static int getRank(int year, String name, String gender) {
	    boolean found = false;
	    int oRank = 0;
		int rank = 1;
	    for (CSVRecord rec : getFileParser(year)) {
	        // Increment rank if gender matches param
	        if (rec.get(1).equals(gender)) {
	            // Return rank if name matches param
	            if (rec.get(0).equals(name)) {
	            	found = true;
	            	oRank = rank;
	            	break;
	            }
	            rank++;
	        }
	    }
	    if (found)
	    	return oRank;
	    else
	    	return -1;
	}

	/**
     * This method get the name of a specific rank with specific gender in specific year 
     * @param year the specific year when rank will be calculated
     * @param gender the gender of name
     * @param rank the rank of the name 
     * @return String return the name of a rank with gender in year
     */
	public static String getName(int year, int rank, String gender) {
		boolean found = false;
	    String oName = "";
	    int currentRank = 0;
	    
	    // For every name entry in the CSV file
	    for (CSVRecord rec : getFileParser(year)) {
	        // Get its rank if gender matches param
	        if (rec.get(1).equals(gender)) {
	            // Get the name whose rank matches param
	        	currentRank++;
	           if (currentRank == rank) {
	            	found = true;
	            	oName = rec.get(0);
	               break;
	           }
	        }
	    }     
	    if (found)
	    	return oName;
	    else
	    	return "information on the name at the specified rank is not available";
	}

	/**
	 * Task 1: report the certain gender Top N name in a certain time range
	 * @param top_N Top N names to show
	 * @param gender gender
	 * @param year0 year range start
	 * @param year1
	 * @return the table in array format
	 */
	public static String[][] reportTopname(int top_N, String gender, int year0, int year1) {
		//change the value if year0 > year1
		if(year0>year1)
		{
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}

		String[][] arr = new String[year1-year0+2][top_N+1];
		arr[0][0] = "Year";
		for (int i = 1; i < top_N+1; i++) {
			arr[0][i] = "Top" + Integer.toString(i);
		}
		
		for (int year = year0; year <= year1; year++) {
			int rank = 1;
			for (CSVRecord rec: getFileParser(year)) {
				if(rank <= top_N) {
					if (rec.get(1).equals(gender)) {
						int i = year-year0+1;
						arr[i][0] = Integer.toString(year);
						arr[i][rank] = rec.get(0);
						rank++;
					}
				}
			}
		}

		return arr;
	}

	/**
	 * Task 2: report the popularity of a given name in a certain time range
	 * @param name
	 * @param gender
	 * @param year0
	 * @param year1
	 * @return ObservaleList<Year>
	 */
	public static ObservableList<Year> reportPopularity(String name, String gender, int year0, int year1) {
		ObservableList<Year> years = FXCollections.observableArrayList();
		
		//change the value if year0 > year1
		if (year0 >year1)
		{
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}

		for (int year = year0; year <= year1; year++) {
			boolean found = false;
			int totalNum = 0;
			int count = 0;
			int rank = 1;
			int oRank = 0;
			for (CSVRecord rec: getFileParser(year)) {
				if (rec.get(1).equals(gender)) {
					if (rec.get(0).equals(name)) {
						found = true;
						oRank = rank;
						count = Integer.parseInt(rec.get(2));
						break;
					}
					rank++;
					totalNum += Integer.parseInt(rec.get(2));
				}
			}
			
			if (found) {
				years.add(new Year(year, count, rank, count / (float)totalNum));
			}			
		}

		return years;
	}
	
	/**
	 * Task 2: get the data in line chart
	 * @param name
	 * @param gender
	 * @param year0
	 * @param year1
	 * @param series
	 * @return XYChart.Series
	 */
	public static XYChart.Series get_line_series(String name, String gender, int year0, int year1, XYChart.Series series){
		
		int count;
	
		for(int i = year0; i<=year1;i++)
		{
			String year = Integer.toString(i);
			count = get_count(i, gender,name);
			series.getData().add(new XYChart.Data(year, count));
		}
		
		
		return series;
		
	}
	
	/**
	 * Task 2: get the data in bar chart and add data to barchart
	 * @param name
	 * @param gender
	 * @param year0
	 * @param year1
	 * @param BarChart<?, ?>
	 * @return void
	 */
	public static void get_bar_series(String name, String gender, int year0, int year1,BarChart<?, ?> t2BarChart){
		
		int rank;
	
		XYChart.Series series = new XYChart.Series();
		for(int i = year0; i<=year1;i++)
		{
			String year = Integer.toString(i);		
			rank = getRank(i,name,gender);
			series.getData().add(new XYChart.Data(year, rank));		
		}
		
		t2BarChart.getData().add(series);
		
	}

	/**
	 * Task 3: report the names that have shown the largest rise/fall in popularity over a given period
	 * @param gender
	 * @param year0
	 * @param year1
	 * @return table as a 2d array
	 */
	public static String[][] reportTrend(String gender, int year0, int year1) {
		//change the value if year0 > year1
		if(year0 > year1) {
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}
		
		Map<String, List<Pair<Integer, Integer>>> map = new HashMap<>();
		
		for(int year = year0; year <= year1; year++) {
			int rank = 0;
			for (CSVRecord rec: getFileParser(year)) {
				if (rec.get(1).equals(gender)) {
					rank++;
					String name = rec.get(0);
					Pair<Integer, Integer> pair = new Pair<Integer, Integer>(year, rank);
					if(map.containsKey(name)) {
						map.get(name).add(pair);
					}
					else {
						List<Pair<Integer, Integer>> list = new ArrayList<>();
						list.add(pair);
						map.put(name, list);
					}
				}
			}
		}

		String[][] arr = new String[3][4];
		arr[0][0] = "Name";
		arr[0][1] = "Lowest Rank";
		arr[0][2] = "Highest Rank";
		arr[0][3] = "Trend";

		
		String nameRise = "", nameFall = "";
		Pair<Integer, Integer> rankRise = new Pair<>(0, 0);
		Pair<Integer, Integer> yearRise = new Pair<>(0, 0);
		Pair<Integer, Integer> rankFall = new Pair<>(0, 0);
		Pair<Integer, Integer> yearFall = new Pair<>(0, 0);
		int maxNameRise = 0, maxNameFall = 0;
		for(String name: map.keySet()) {
			List<Pair<Integer, Integer>> list = map.get(name);
			Pair<Integer, Integer> tmpMax = list.get(0), tmpMin = list.get(0);
			Pair<Integer, Integer> lastPair = list.get(0);
			for(Pair<Integer, Integer> pair: list) {
				if(pair == list.get(0)) continue;
				int rank = pair.getValue();
				int year = pair.getKey();
				if(rank - lastPair.getValue() > maxNameRise) {
					nameRise = name;
					rankRise = new Pair<Integer, Integer>(lastPair.getValue(), rank);
					yearRise = new Pair<Integer, Integer>(lastPair.getKey(), year);
					maxNameRise = rank - lastPair.getValue();
				}
				if(lastPair.getValue() - rank > maxNameFall) {
					nameFall = name;
					rankFall = new Pair<Integer, Integer>(lastPair.getValue(), rank);
					yearFall = new Pair<Integer, Integer>(lastPair.getKey(), year);
					maxNameFall = lastPair.getValue() - rank;
				}
				lastPair = pair;
				if(rank > tmpMax.getValue()) tmpMax = pair;
				if(rank < tmpMin.getValue()) tmpMin = pair;
			}
		}

		arr[1][0] = nameFall;
		arr[1][1] = rankFall.getKey() + " in "+ yearFall.getKey();
		arr[1][2] = rankFall.getValue() + " in "+ yearFall.getValue();
		arr[1][3] = String.valueOf(-maxNameFall);

		arr[2][0] = nameRise;
		arr[2][1] = rankRise.getKey() + " in "+ yearRise.getKey();
		arr[2][2] = rankRise.getValue() + " in "+ yearRise.getValue();
		arr[2][3] = "+"+ maxNameRise;
		return arr;
	}

	/**
	 * Additional Algorithm: Report the largest difference in a range rather than consecutive two years.
	 * @param gender
	 * @param year0
	 * @param year1
	 * @return
	 */
	public static String[][] reportTrend2(String gender, int year0, int year1) {
		//change the value if year0 > year1
		if (year0 > year1) {
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}

		Map<String, List<Pair<Integer, Integer>>> map = new HashMap<>();

		for (int year = year0; year <= year1; year++) {
			int rank = 0;
			for (CSVRecord rec : getFileParser(year)) {
				if (rec.get(1).equals(gender)) {
					rank++;
					String name = rec.get(0);
					Pair<Integer, Integer> pair = new Pair<Integer, Integer>(year, rank);
					if (map.containsKey(name)) {
						map.get(name).add(pair);
					} else {
						List<Pair<Integer, Integer>> list = new ArrayList<>();
						list.add(pair);
						map.put(name, list);
					}
				}
			}
		}

		String nameRise = "", nameFall = "";
		Pair<Integer, Integer> rankRise = new Pair<>(0, 0);
		Pair<Integer, Integer> yearRise = new Pair<>(0, 0);
		Pair<Integer, Integer> rankFall = new Pair<>(0, 0);
		Pair<Integer, Integer> yearFall = new Pair<>(0, 0);
		int maxNameRise = 0, maxNameFall = 0;
		for (String name : map.keySet()) {
			List<Pair<Integer, Integer>> list = map.get(name);
			Pair<Integer, Integer> tmpMax = list.get(0), tmpMin = list.get(0);
			for (Pair<Integer, Integer> pair : list) {
				int rank = pair.getValue();
				int year = pair.getKey();
				if (rank - tmpMin.getValue() > maxNameRise) {
					nameRise = name;
					rankRise = new Pair<Integer, Integer>(tmpMin.getValue(), rank);
					yearRise = new Pair<Integer, Integer>(tmpMin.getKey(), year);
					maxNameRise = rank - tmpMin.getValue();
				}
				if (tmpMax.getValue() - rank > maxNameFall) {
					nameFall = name;
					rankFall = new Pair<Integer, Integer>(tmpMax.getValue(), rank);
					yearFall = new Pair<Integer, Integer>(tmpMax.getKey(), year);
					maxNameFall = tmpMax.getValue() - rank;
				}
				if (rank > tmpMax.getValue()) tmpMax = pair;
				if (rank < tmpMin.getValue()) tmpMin = pair;
			}
		}

		String[][] arr = new String[3][4];
		arr[0][0] = "Name";
		arr[0][1] = "Lowest Rank";
		arr[0][2] = "Highest Rank";
		arr[0][3] = "Trend";

		arr[1][0] = nameFall;
		arr[1][1] = rankFall.getKey() + " in " + yearFall.getKey();
		arr[1][2] = rankFall.getValue() + " in " + yearFall.getValue();
		arr[1][3] = String.valueOf(-maxNameFall);

		arr[2][0] = nameRise;
		arr[2][1] = rankRise.getKey() + " in " + yearRise.getKey();
		arr[2][2] = rankRise.getValue() + " in " + yearRise.getValue();
		arr[2][3] = "+" + maxNameRise;
		return arr;
	}
}