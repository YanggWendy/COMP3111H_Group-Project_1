package comp3111.popnames;
import org.apache.commons.csv.CSVRecord;

import jdk.dynalink.beans.StaticClass;

import javafx.util.Pair;

import java.util.*;



public class PredicReport {
	

	
// Task 4 Recommendation on Names for Newborn Babies
	public static String recomendName(String dadName, String momName, int dadYOB, int momYOB, int vintage_year)
	{
		Random rand = new Random();
		if(dadYOB<1880||dadYOB>2019||momYOB<1880||momYOB>2019) {
			return "information on the parents' year of birth is out of range";
 		}
		
		if(vintage_year<1880||vintage_year>2019) {
			return "information on the vintage year is out of range, please choose another year!";
 		}
		int dadRank = AnalyzeNames.getRank(dadYOB,dadName,"M");
		int momRank = AnalyzeNames.getRank(momYOB,momName,"F");
		if(dadRank == -1)
		{
			int uppernum = AnalyzeNames.get_total_Rank(dadYOB,"M");
			dadRank = rand.nextInt(uppernum); 
		}
		if(momRank == -1)
		{
			int uppernum = AnalyzeNames.get_total_Rank(momYOB,"F");
			momRank = rand.nextInt(uppernum);
		}
		String boyName = AnalyzeNames.getName(vintage_year,dadRank,"M");
		String girlName = AnalyzeNames.getName(vintage_year,momRank,"F");
		String Recommendation = "";
		Recommendation+= "BoyName is ";
		Recommendation+= boyName;
		Recommendation+= ". GirlName is ";
		Recommendation+= girlName;
		Recommendation+= ".";
		
		
		return Recommendation;
	}
	
//Task5 Recommendation	
	public static String recomendName_task5(String Name,  int YOB, String gender,String genderMate, String prefer) {
		
		Random rand = new Random();
		if(YOB<1880||YOB>2019||YOB<1880||YOB>2019) {
			return "information on the year of birth is out of range!";
 		}
		
		if(YOB==2019 && prefer=="Younger") {
			return "information on the year of birth cannot predict younger soulmate!";
 		}
		
		if(YOB==1880 && prefer=="Older") {
			return "information on the year of birth cannot predict older soulmate!";
 		}
		
		String soulgender = genderMate;
		
		int Rank = AnalyzeNames.getRank(YOB,Name,gender);
		//System.out.println(Rank);
		if(Rank == -1)
		{
			int uppernum = AnalyzeNames.get_total_Rank(YOB,gender);
			Rank = rand.nextInt(uppernum);;
		}
		String soulName;
		
		if(prefer=="Younger") {		
			soulName = AnalyzeNames.getName(YOB+1,Rank,soulgender);
			//System.out.println(soulName);
			if(soulName=="information on the name at the specified rank is not available") {
				soulName = AnalyzeNames.getName(YOB+1,1,soulgender);
			}
		}else {			
			soulName = AnalyzeNames.getName(YOB-1,Rank,soulgender);
			//System.out.println(soulName);
			if(soulName=="information on the name at the specified rank is not available") {
				soulName = AnalyzeNames.getName(YOB-1,1,soulgender);}
		}
	
		String Recommendation = "Soulmate Name is ";
		Recommendation += soulName;
		Recommendation += "!";
		return Recommendation;
	}

	
	
	//Task6 score prediction	
		public static String recomendName_task6(String Name, String mateName, int YOB, String gender, String mategender, String prefer) {
			
			Random rand = new Random();
			if(YOB<1880||YOB>2019||YOB<1880||YOB>2019) {
				return "information on the year of birth is out of range!";
	 		}
			
			if(YOB==2019 && prefer=="Younger") {
				return "information on the year of birth cannot predict younger soulmate!";
	 		}
			
			if(YOB==1880 && prefer=="Older") {
				return "information on the year of birth cannot predict older soulmate!";
	 		}
			
			Double Rank = Double.valueOf(AnalyzeNames.getRank(YOB,Name,gender));
			
			if(Rank == -1)
			{
				int uppernum = AnalyzeNames.get_total_Rank(YOB,gender);
				Rank = Double.valueOf(rand.nextInt(uppernum) + 1);
			}
			//System.out.println("rank "+Rank);
			Double Rank_mate;
			
			if(prefer.equals("Younger")) {		
				Rank_mate = Double.valueOf(AnalyzeNames.getRank(YOB+1,mateName,mategender));
				
				if(Rank_mate == -1)
				{
					int uppernum = AnalyzeNames.get_total_Rank(YOB+1,mategender);
					Rank_mate = Double.valueOf(rand.nextInt(uppernum) + 1);
				}
				//System.out.println(Rank_mate);
			}else {			
				Rank_mate = Double.valueOf(AnalyzeNames.getRank(YOB-1,mateName,mategender));
				
				if(Rank_mate == -1)
				{
					int uppernum = AnalyzeNames.get_total_Rank(YOB-1,mategender);
					Rank_mate = Double.valueOf(rand.nextInt(uppernum));
				}
				//System.out.println(Rank_mate);
			}
			//System.out.println("rank_mate "+Rank_mate);
		
			Double score = ((1.0 - Math.abs((Rank-Rank_mate)) / (Rank+Rank_mate)) * 100.0);
			score = Double.valueOf(Math.round(score * 100)) / 100.0;
			String Recommendation = "The mathing score between "+Name+" and " + mateName+ " is ";
			Recommendation += Double.toString(score);
			Recommendation += "%!";
			return Recommendation;
		}
	
		// Additional feature: Similar name recommendation
		private static int minDistance(String word1, String word2) {
	        int m = word1.length(), n = word2.length();
	        int[][] dp = new int[m+1][n+1];
	        for (int i = 0; i <= m; ++i) dp[i][0] = i;
	        for (int i = 0; i <= n; ++i) dp[0][i] = i;
	        for (int i = 1; i <= m; ++i) {
	            for (int j = 1; j <= n; ++j) {
	                if (word1.charAt(i-1) == word2.charAt(j-1)) {
	                    dp[i][j] = dp[i-1][j-1];
	                } else {
	                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
	                }
	            }
	        }
	        return dp[m][n];
	    }
		
		public static List<Pair<String, Integer>> similarNames(String Name, String gender, int popularity){
			int year0 = 2000, year1 = 2019;
			PriorityQueue<Pair<String,Integer> > editDistance=
	                new PriorityQueue<Pair<String,Integer>>(Comparator.comparing(Pair::getValue));
			Set<String> nameSet = new HashSet<>();
			for(int year = year0; year <= year1; year++) {
				int rankStart, rankEnd;
				int totalRank = AnalyzeNames.get_total_Rank(year, gender);
				switch(popularity) {
				case 0:
					rankStart = 1;
					rankEnd = (int) (totalRank * 0.3);
					break;
				case 1:
					rankStart = (int) (totalRank * 0.3) + 1;
					rankEnd = (int) (totalRank * 0.7);
					break;
				case 2:
				default:
					rankStart = (int) (totalRank * 0.7) + 1;
					rankEnd = totalRank;
				}
				int rank = 0;
				for (CSVRecord rec: AnalyzeNames.getFileParser(year)) {
					if (rec.get(1).equals(gender)) {
						rank++;
						if(rank < rankStart) continue;
						if(rank > rankEnd) break;
						String name = rec.get(0);
						if(nameSet.contains(name)) continue;
						int distance = minDistance(name, Name);
						Pair<String, Integer> pair = new Pair<String, Integer>(name, distance);
						editDistance.offer(pair);
						nameSet.add(name);
					}
				}
			}
			List<Pair<String, Integer>> returnList = new ArrayList<>();
			for(int i = 1; i <= 10; i++) {
				returnList.add(editDistance.poll());
				if(returnList.get(i-1).getValue() == 0) returnList.remove(--i);
			}
			return returnList;
		}
	
}
