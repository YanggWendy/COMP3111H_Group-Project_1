package comp3111.popnames;
import java.util.Random;


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
	public static String recomendName_task5(String Name,  int YOB, String gender, String prefer) {
		
		Random rand = new Random();
		if(YOB<1880||YOB>2019||YOB<1880||YOB>2019) {
			return "information on the year of birth is out of range!";
 		}
		
		if(YOB==1880 && prefer=="Younger") {
			return "information on the year of birth cannot predict younger soulmate!";
 		}
		
		if(YOB==2019 && prefer=="Older") {
			return "information on the year of birth cannot predict older soulmate!";
 		}
		
		String soulgender;
		if(gender=="M") {soulgender = "F";}
		else {soulgender = "M";}
		
		int Rank = AnalyzeNames.getRank(YOB,Name,gender);
		//System.out.println(Rank);
		if(Rank == -1)
		{
			int uppernum = AnalyzeNames.get_total_Rank(YOB,gender);
			Rank = rand.nextInt(uppernum);;
		}
		String soulName;
		
		if(prefer=="Younger") {		
			soulName = AnalyzeNames.getName(YOB-1,Rank,soulgender);
			//System.out.println(soulName);
			if(soulName=="information on the name at the specified rank is not available") {
				soulName = AnalyzeNames.getName(YOB-1,1,soulgender);
			}
		}else {			
			soulName = AnalyzeNames.getName(YOB+1,Rank,soulgender);
			//System.out.println(soulName);
			if(soulName=="information on the name at the specified rank is not available") {
				soulName = AnalyzeNames.getName(YOB+1,1,soulgender);}
		}
	
		String Recommendation = "Soulmate Name is ";
		Recommendation += soulName;
		Recommendation += "!";
		return Recommendation;
	}

	
	
	//Task5 Recommendation	
		public static String recomendName_task6(String Name, String mateName, int YOB, String gender, String mategender, String prefer) {
			
			Random rand = new Random();
			if(YOB<1880||YOB>2019||YOB<1880||YOB>2019) {
				return "information on the year of birth is out of range!";
	 		}
			
			if(YOB==1880 && prefer=="Younger") {
				return "information on the year of birth cannot predict younger soulmate!";
	 		}
			
			if(YOB==2019 && prefer=="Older") {
				return "information on the year of birth cannot predict older soulmate!";
	 		}
			
			float Rank = AnalyzeNames.getRank(YOB,Name,gender);
			
			if(Rank == -1)
			{
				int uppernum = AnalyzeNames.get_total_Rank(YOB,gender);
				Rank = rand.nextInt(uppernum);
			}
			System.out.println("rank "+Rank);
			float Rank_mate;
			
			if(prefer=="Younger") {		
				Rank_mate = AnalyzeNames.getRank(YOB-1,mateName,mategender);
				
				if(Rank_mate == -1)
				{
					int uppernum = AnalyzeNames.get_total_Rank(YOB-1,mategender);
					Rank_mate = rand.nextInt(uppernum);
				}
				//System.out.println(Rank_mate);
			}else {			
				Rank_mate = AnalyzeNames.getRank(YOB+1,mateName,mategender);
				
				if(Rank_mate == -1)
				{
					int uppernum = AnalyzeNames.get_total_Rank(YOB+1,mategender);
					Rank_mate = rand.nextInt(uppernum);
				}
				//System.out.println(Rank_mate);
			}
		
			int score = (int) ((1 - Math.abs((Rank - Rank_mate))/ (Rank+Rank_mate)) * 100);
			String Recommendation = "The mathing score between "+Name+" and " + mateName+ " is ";
			Recommendation +=score;
			Recommendation += "%!";
			return Recommendation;
		}
	
	
}
