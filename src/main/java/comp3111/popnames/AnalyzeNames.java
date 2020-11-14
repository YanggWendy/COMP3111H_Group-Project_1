package comp3111.popnames;

import org.apache.commons.csv.*;
import edu.duke.*;
import org.json.*;

public class AnalyzeNames {

	public static CSVParser getFileParser(int year) {
     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
     return fr.getCSVParser(false);
	}
 
	
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
	
	/* Task 1: report the certain gender Top N name in a certain time range
	 * Return in json format. (For potentially future visualization purpose)
	 * e.g.
	 * {"Top N":"12", "gender":"male", "year begin":"2000", "year end":"2001", "data":[
	 * 	{"year":"2000", "Top Name":["Top1":Jack, "Top2":Mark,..."Top12":Jimmy]},
	 * 	{"year":"2001", "Top Name":["Top1":Andy, "Top2":Mark,..."Top12":Jimmy]}]
	 * }
	 * */
	public static String reportTopname(int top_N, String gender, int year0, int year1) {
		//check whether input years are within required year range
		if(year0<1880||year1<1880||year0>2019||year1>2019)
		{return "information on the top name at the specified year range is not available";}
		
		//change the value if year0 > year1
		if(year0>year1)
		{
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}
		JSONObject mainObj = new JSONObject();
		mainObj.put("Top N", top_N);
		mainObj.put("gender", gender);
		mainObj.put("year begin", year0);
		mainObj.put("year end", year1);
		JSONArray arrObj = new JSONArray();
		
		for (int year = year0; year <= year1; year++) {
			int rank = 1;
			JSONObject innerObj = new JSONObject();
			innerObj.put("year", year);
			JSONArray innerarrObj = new JSONArray();
			JSONObject top_n_Obj = new JSONObject();
			for (CSVRecord rec: getFileParser(year)) {
				if(rank<=top_N) 
				{
					if (rec.get(1).equals(gender)) 
					{
						top_n_Obj.put(String.format("Top %d", rank), rec.get(0));
						rank++;
					}
				}
			}
			if(rank<top_N) 
			{
				return String.format("information on the top name at the specified top N is not available in year %d.",year);
			}
			innerarrObj.put(top_n_Obj);
			innerObj.put("Top Name", innerarrObj);
			arrObj.put(innerObj);	
			
		}
		
		mainObj.put("data", arrObj);
		return mainObj.toString();
	}	
	

	/* Task 2: report the popularity of a given name in a certain time range
	 * Return in json format. (For potentially future visualization purpose)
	 * e.g.
	 * {"name":"David", "gender":"male", "data":[
	 * 	{"year":2012, "rank":123, "count":66666, "percentage":3.2},
	 * 	{"year":2013, "rank":101, "count":67776, "percentage":4.2}]}
	 * */
	public static String reportPopularity(String name, String gender, int year0, int year1) {
		//check whether input years are within required year range
		if (year0<1880 || year1<1880 || year0>2019 || year1>2019)
			return "information on the top name at the specified year range is not available";
		
		//change the value if year0 > year1
		if (year0 >year1)
		{
			int temp = year0;
			year0 = year1;
			year1 = temp;
		}

		JSONObject mainObj = new JSONObject();
		mainObj.put("name", name);
		mainObj.put("gender", gender);
		JSONArray arrObj = new JSONArray();
		
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
					}
					rank++;
					totalNum += Integer.parseInt(rec.get(2));
				}
			}
			
			if (found) {
				JSONObject entryObj = new JSONObject();
				entryObj.put("year", year);
				entryObj.put("rank", oRank);
				entryObj.put("count", count);
				entryObj.put("percentage", count / (float)totalNum);
				arrObj.put(entryObj);
			}			
		}
		
		mainObj.put("data", arrObj);
		return mainObj.toString();
	}

 
}