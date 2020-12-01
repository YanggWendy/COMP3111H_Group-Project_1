package comp3111.popnames;

import org.junit.Test;

import javafx.util.Pair;

import static org.junit.Assert.*;

import java.util.List;

public class AnalyzeNamesTest {
	
    @Test 
    public void testGetRankNotFound() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "XXX", "M");
		assertEquals(i, -1);
    }
    
    @Test 
    public void testGetRankMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "David", "M");
    	assertTrue(i==27);
    }
    
    @Test 
    public void testGetRankFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	int i = a.getRank(2019, "Desire", "F");
    	assertTrue(i==2192);
    }
    	
    @Test 
    public void testGetNameMale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 27, "M");
    	assertTrue(name.equals("David"));
    }
    
    @Test 
    public void testGetNameFemale() {
    	AnalyzeNames a = new AnalyzeNames();
    	String name = a.getName(2019, 2192, "F");
    	assertTrue(name.equals("Desire"));
    }
    
    // Test Task 1
    @Test 
    public void testReportTopnameMale() {
    	String[][] arr = AnalyzeNames.reportTopname(3, "M", 1000, 1002);
    	assertTrue(arr[1][0].equals("1000") && arr[2][0].equals("1001") && arr[3][0].equals("1002")
    			&& arr[1][1].equals("Ma") && arr[1][2].equals("Mb") && arr[1][3].equals("Mc")
    			&& arr[2][1].equals("Mf") && arr[2][2].equals("Mb") && arr[2][3].equals("Mc")
    			&& arr[3][1].equals("Me") && arr[3][2].equals("Mc") && arr[3][3].equals("Mb"));
    }
    @Test 
    public void testReportTopnameMale2() {
    	String[][] arr = AnalyzeNames.reportTopname(3, "M", 1002, 1000);
    	assertTrue(arr[1][0].equals("1000") && arr[2][0].equals("1001") && arr[3][0].equals("1002")
    			&& arr[1][1].equals("Ma") && arr[1][2].equals("Mb") && arr[1][3].equals("Mc")
    			&& arr[2][1].equals("Mf") && arr[2][2].equals("Mb") && arr[2][3].equals("Mc")
    			&& arr[3][1].equals("Me") && arr[3][2].equals("Mc") && arr[3][3].equals("Mb"));
    }
    @Test 
    public void testReportTopnameFemale() {
    	String[][] arr = AnalyzeNames.reportTopname(3, "F", 1000, 1002);
    	assertTrue(arr[1][0].equals("1000") && arr[2][0].equals("1001") && arr[3][0].equals("1002")
    			&& arr[1][1].equals("Fa") && arr[1][2].equals("Fb") && arr[1][3].equals("Fc")
    			&& arr[2][1].equals("Ff") && arr[2][2].equals("Fb") && arr[2][3].equals("Fc")
    			&& arr[3][1].equals("Fe") && arr[3][2].equals("Fc") && arr[3][3].equals("Fb"));
    }
    @Test 
    public void testReportTopnameFemale2() {
    	String[][] arr = AnalyzeNames.reportTopname(3, "F", 1002, 1000);
    	assertTrue(arr[1][0].equals("1000") && arr[2][0].equals("1001") && arr[3][0].equals("1002")
    			&& arr[1][1].equals("Fa") && arr[1][2].equals("Fb") && arr[1][3].equals("Fc")
    			&& arr[2][1].equals("Ff") && arr[2][2].equals("Fb") && arr[2][3].equals("Fc")
    			&& arr[3][1].equals("Fe") && arr[3][2].equals("Fc") && arr[3][3].equals("Fb"));
    }
    
    // Test Task 4
    @Test 
    public void testRecomendNameInvalidParentYOB() {
    	String string = PredicReport.recomendName("David", "May", 1000, 1990, 2019);
    	assertTrue(string.equals("information on the parents' year of birth is out of range"));
    }
    @Test 
    public void testRecomendNameInvalidVintageYear() {
    	String string = PredicReport.recomendName("David", "May", 1990, 1990, 1000);
    	assertTrue(string.equals("information on the vintage year is out of range, please choose another year!"));
    }
    @Test 
    public void testRecomendName() {
    	String string = PredicReport.recomendName("David", "Mary", 1990, 1990, 2018);
    	assertTrue(string.equals("BoyName is Benjamin. GirlName is Addison."));
    }
    @Test 
    public void testRecomendNameDadRankNotFound() {
    	String string = PredicReport.recomendName("Zhegemmingzimeiyou", "Mary", 1990, 1990, 2018);
    	assertTrue(string.contains("BoyName") && string.contains("GirlName is Addison")
    			&& string.length() > 33);
    }
    @Test 
    public void testRecomendNameMomRankNotFound() {
    	String string = PredicReport.recomendName("David", "Zhegemmingzimeiyou", 1990, 1990, 2018);
    	assertTrue(string.contains("GirlName") && string.contains("BoyName is Benjamin")
    			&& string.length() > 33);
    }
    
    // Test Task 5
    @Test 
    public void testrecomendName_task5InvalidYOB() {
    	String string = PredicReport.recomendName_task5("Wendy", 1000, "F", "Younger");
    	assertTrue(string.equals("information on the year of birth is out of range!"));
    }
    @Test 
    public void testrecomendName_task5InvalidYounger() {
    	String string = PredicReport.recomendName_task5("Wendy", 2019, "F", "Younger");
    	assertTrue(string.equals("information on the year of birth cannot predict younger soulmate!"));
    }
    @Test 
    public void testrecomendName_task5InvalidOlder() {
    	String string = PredicReport.recomendName_task5("Wendy", 1880, "F", "Older");
    	assertTrue(string.equals("information on the year of birth cannot predict older soulmate!"));
    }
    @Test 
    public void testrecomendName_task5YoungerNotFound() {
    	String string = PredicReport.recomendName_task5("Zyona", 2018, "F", "Younger");
    	assertTrue(string.equals("Soulmate Name is Liam!"));
    }
    @Test 
    public void testrecomendName_task5OlderNotFound() {
    	String string = PredicReport.recomendName_task5("Zyona", 2018, "F", "Older");
    	assertTrue(string.equals("Soulmate Name is Liam!"));
    }
    @Test 
    public void testrecomendName_task5RankNotFound() {
    	String string = PredicReport.recomendName_task5("Zhegemingzimeiyou", 2018, "F", "Older");
    	assertTrue(string.contains("Soulmate Name is") && string.length() > 15);
    }
    @Test
    public void testrecomendName_task5() {
    	String string = PredicReport.recomendName_task5("Wendy", 2000, "F", "Older");
    	assertTrue(string.equals("Soulmate Name is Morgan!"));
    }
    
    // Test Task 6
    @Test 
    public void testrecomendName_task6InvalidYOB() {
    	String string = PredicReport.recomendName_task6("John", "Mary", 1000, "M", "F", "Younger");
    	assertTrue(string.equals("information on the year of birth is out of range!"));
    }
    @Test 
    public void testrecomendName_task6InvalidYounger() {
    	String string = PredicReport.recomendName_task6("John", "Mary", 2019, "M", "F", "Younger");
    	assertTrue(string.equals("information on the year of birth cannot predict younger soulmate!"));
    }
    @Test 
    public void testrecomendName_task6InvalidOlder() {
    	String string = PredicReport.recomendName_task6("John", "Mary", 1880, "M", "F", "Older");
    	assertTrue(string.equals("information on the year of birth cannot predict older soulmate!"));
    }
    @Test 
    public void testrecomendName_task6RankNotFound() {
    	String string = PredicReport.recomendName_task6("Zhegemingzimeiyou", "Mary", 1998, "M", "F", "Younger");
    	assertTrue(string.contains("Zhegemingzimeiyou"));
    }
    @Test 
    public void testrecomendName_task6YoungerNotFound() {
    	String string = PredicReport.recomendName_task6("John", "Zhegemingzimeiyou", 1998, "M", "F", "Younger");
    	assertTrue(string.contains("Zhegemingzimeiyou"));
    }
    @Test 
    public void testrecomendName_task6OlderNotFound() {
    	String string = PredicReport.recomendName_task6("John", "Zhegemingzimeiyou", 1998, "M", "F", "Older");
    	assertTrue(string.contains("Zhegemingzimeiyou"));
    }
    @Test
    public void testrecomendName_task6() {
    	String string = PredicReport.recomendName_task6("John", "Mary", 1998, "M", "F", "Younger");
    	assertTrue(string.equals("The mathing score between John and Mary is 50.0%!"));
    }
    
    // Test additional feature
    @Test
    public void testSimilarNames0() {
    	List<Pair<String, Integer>> list = PredicReport.similarNames("John", "M", 0);
    	assertTrue(list.get(0).getKey().equals("Jon"));
    }
    @Test
    public void testSimilarNames1() {
    	List<Pair<String, Integer>> list = PredicReport.similarNames("John", "M", 1);
    	assertTrue(list.get(0).getKey().equals("Joon"));
    }
    @Test
    public void testSimilarNames2() {
    	List<Pair<String, Integer>> list = PredicReport.similarNames("John", "M", 2);
    	assertTrue(list.get(0).getKey().equals("Jonn"));
    }
    

//    @Test
//    public void testReportPopularity() {
//        AnalyzeNames a = new AnalyzeNames();
//        String result = a.reportPopularity("Moe", "female", 1949, 1959);
//        assertTrue(result.equals("{\"gender\":\"female\",\"data\":[],\"name\":\"Moe\"}"));
//    }
}
