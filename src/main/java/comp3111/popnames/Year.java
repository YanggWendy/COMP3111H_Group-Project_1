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
	 * @param year
	 * @param count
	 * @param rank
	 * @param percentage
	 */
    public Year(int year, int count, int rank, float percentage) {
        this.year = new SimpleIntegerProperty(year);
        this.count = new SimpleIntegerProperty(count);
        this.rank = new SimpleIntegerProperty(rank);
        this.percentage = new SimpleStringProperty(String.format("%.2f", percentage*100)+"%");
    }

    /**
	 * get year
	 * @param nothing
	 * @return int year that get
	 */
    public int getYear() {
        return year.get();
    }

    /**
  	 * year Property
  	 * @param nothing
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty yearProperty() {
        return year;
    }
    
    /**
  	 * set Year
  	 * @param year
  	 * @return nothing
  	 */
    public void setYear(int year) {
        this.year.set(year);
    }
   
    /**
  	 * get Count
  	 * @param nothing
  	 * @return int
  	 */
    public int getCount() {
        return count.get();
    }

    /**
  	 * get Ccount Property
  	 * @param nothing
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty countProperty() {
        return count;
    }

    /**
  	 * set Count
  	 * @param count
  	 * @return nothing
  	 */
    public void setCount(int count) {
        this.count.set(count);
    }

    /**
  	 * get Rank
  	 * @param count
  	 * @return nothing
  	 */
    public int getRank() {
        return rank.get();
    }
    
    /**
  	 * get rank Property
  	 * @param nothing
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    /**
  	 * set rank
  	 * @param rank
  	 * @return nothing
  	 */
    public void setRank(int rank) {
        this.rank.set(rank);
    }

    /**
  	 * get Percentage
  	 * @param nothing
  	 * @return String
  	 */
    public String getPercentage() {
        return percentage.get();
    }

    /**
  	 * get percentage Property
  	 * @param nothing
  	 * @return SimpleIntegerProperty
  	 */
    public SimpleStringProperty percentageProperty() {
        return percentage;
    }
    
    /**
  	 * set Percentage
  	 * @param percentage
  	 * @return nothing
  	 */
    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }
}
