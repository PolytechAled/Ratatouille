package fr.polytech.ihm.td4menu.ratatouille.datas;

import java.util.List;

public class Week {
    List<Day> days;
    int weekNumber;
    String origin;
    String diet;

    public Week(List<Day> days , int weekNumber) {
        this.days = days;
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public List<Day> getDays() {
        return days;
    }

    public Day getDay(int i) {
        return this.days.get(i);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }
}