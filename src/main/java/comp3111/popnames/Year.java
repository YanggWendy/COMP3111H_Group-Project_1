package comp3111.popnames;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Year {
    private SimpleIntegerProperty year, count, rank;
    private SimpleStringProperty percentage;

    public Year(int year, int count, int rank, float percentage) {
        this.year = new SimpleIntegerProperty(year);
        this.count = new SimpleIntegerProperty(count);
        this.rank = new SimpleIntegerProperty(rank);
        this.percentage = new SimpleStringProperty(String.format("%.2f", percentage*100)+"%");
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public int getRank() {
        return rank.get();
    }

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank.set(rank);
    }

    public String getPercentage() {
        return percentage.get();
    }

    public SimpleStringProperty percentageProperty() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage.set(percentage);
    }
}
