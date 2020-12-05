package comp3111.popnames;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This Year is used to generate table in task1-3
 * @author YCY Group
 * @since  2020-10-30
 */
public class Year {
    private SimpleIntegerProperty year, count, rank;
    private SimpleStringProperty percentage;

    /**
	 * Year class constructor that initiates the variables
	 * @param year Year
	 * @param count Count of the name in this year
	 * @param rank Rank of the name in this year
	 * @param percentage Percentage of the name in this year
	 */
    public Year(int year, int count, int rank, float percentage) {
        this.year = new SimpleIntegerProperty(year);
        this.count = new SimpleIntegerProperty(count);
        this.rank = new SimpleIntegerProperty(rank);
        this.percentage = new SimpleStringProperty(String.format("%.2f", percentage*100)+"%");
    }

    /**
	 * get year
	 * @return int year that get
	 */
    public int getYear() {
        return year.get();
    }

    /**
  	 * year Property
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty yearProperty() {
        return year;
    }
    
    /**
  	 * set Year
  	 * @param year Year
  	 * @return void
  	 */
    public void setYear(int year) {
        this.year.set(year);
    }
   
    /**
  	 * get Count
  	 * @return int
  	 */
    public int getCount() {
        return count.get();
    }

    /**
  	 * get Ccount Property
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty countProperty() {
        return count;
    }

    /**
  	 * set Count
  	 * @param count Count
  	 * @return void
  	 */
    public void setCount(int count) {
        this.count.set(count);
    }

    /**
  	 * get Rank
  	 * @return int
  	 */
    public int getRank() {
        return rank.get();
    }
    
    /**
  	 * get rank Property
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    /**
  	 * set rank
  	 * @param rank Rank
  	 * @return void
  	 */
    public void setRank(int rank) {
        this.rank.set(rank);
    }

    /**
  	 * get Percentage
  	 * @return String
  	 */
    public String getPercentage() {
        return percentage.get();
    }

    /**
  	 * get percentage Property
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleStringProperty percentageProperty() {
        return percentage;
    }
    
    /**
  	 * set Percentage
  	 * @param percentage Percentage
  	 * @return void
  	 */
    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }
}
