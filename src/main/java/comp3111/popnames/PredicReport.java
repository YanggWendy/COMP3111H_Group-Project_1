package comp3111.popnames;

import org.json.JSONObject;

public class PredicReport {
	
// Task 4 Recommendation on Names for Newborn Babies
	public static String recomendName(String dadName, String momName, int dadYOB, int momYOB, int vintage_year)
	{
		if(dadYOB<1880||dadYOB>2019||momYOB<1880||momYOB>2019) {
			return "information on the parents' year of birth is out of range";
 		}
		int dadRank = AnalyzeNames.getRank(dadYOB,dadName,"M");
		int momRank = AnalyzeNames.getRank(momYOB,momName,"F");
		if(dadRank == -1)
		{
			dadRank = 1;
		}
		if(momRank == -1)
		{
			momRank = 1;
		}
		String boyName = AnalyzeNames.getName(vintage_year,dadRank,"M");
		String girlName = AnalyzeNames.getName(vintage_year,momRank,"F");
		JSONObject mainObj = new JSONObject();
		mainObj.put("boyName", boyName);
		mainObj.put("girlName", girlName);
		return mainObj.toString();
	}
}
