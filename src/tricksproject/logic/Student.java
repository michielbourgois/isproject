package tricksproject.logic;


import java.util.ArrayList;

public class Student {
  private String name;
  private int number;
  private boolean fullTime;
  private boolean graduate;
  private String summary;
  
  public Student(String name, int number, boolean fullTime,
			boolean graduate, String summary) {
    this.name = name;
    this.number = number;
    this.fullTime = fullTime;
    this.graduate = graduate;
    this.summary = summary;
  }
  
  public String getName() {
    return name;
  }
  
  public int getNumber() {
    return number;
  }
  
  public boolean isFullTime() {
    return fullTime;
  }
  
  public boolean isPartTime() {
    return !isFullTime();
  }
  
  public boolean isGraduate() {
    return graduate;
  }
  
  public boolean isUnderGraduate() {
    return !isGraduate();
  }
  
  public String getSummary() {
    return summary;
  }
  
  public void setName(String s) {
    name = s;
  }
  
  public void setNumber(int i) {
    number = i;
  }
  
  public void setFullTime(boolean b) {
    fullTime = b;
  }
  
  public void setPartTime(boolean b) {
    fullTime = !b;
  }
  
  public void setGraduate(boolean b) {
    graduate = b;
  }
  
  public void setUnderGraduate(boolean b) {
    graduate = !b;
  }
  
  public void setSummary(String s) {
    summary = s;
  }
  
  public String toString(){
    return name + "(" + number + ")";
  }
}