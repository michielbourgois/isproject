package tricksproject.db;
import tricksproject.logic.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBStudent {
  public static void createTables() throws DBException {
    try {
      // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
      // worden via phpmyadmin
      Connection con = DBConnector.getConnection();
      Statement stmt = con.createStatement();
      String sql = "CREATE TABLE Students ("
	+ "number int(11) NOT NULL, "
	+ "name varchar(50) NOT NULL, "
	+ "fullTime boolean NOT NULL, "
	+ "graduate boolean NOT NULL, "
	+ "summary varchar(50) NOT NULL, "
	+ "PRIMARY KEY (number)" + ")";
      stmt.executeUpdate(sql);
      DBConnector.closeConnection(con);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static Student getStudent(int stuNum) throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT number, name, fullTime, graduate, summary "
	+ "FROM Students "
	+ "WHERE number = " + stuNum;

        // let op de spatie na 'summary' en 'Students' in voorgaande SQL
      ResultSet srs = stmt.executeQuery(sql);
      String name, summary;
      int number;
      boolean fullTime, graduate;
      
      if (srs.next()) {
	number = srs.getInt("number");
	name = srs.getString("name");
	
	fullTime = srs.getBoolean("fullTime");
	graduate = srs.getBoolean("graduate");
	summary = srs.getString("summary");
      } else {// we verwachten slechts 1 rij...
	DBConnector.closeConnection(con);
	return null;
      }
      Student student = new Student(name, number, fullTime, graduate, summary);
      DBConnector.closeConnection(con);
      return student;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
  }
  public static void save(Student s) throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT number "
              + "FROM Students "
              + "WHERE number = " + s.getNumber();
      ResultSet srs = stmt.executeQuery(sql);
      if (srs.next()) {
        // UPDATE
	sql = "UPDATE Students "
                + "SET name = '" + s.getName() + "'"
		+ ", fullTime = " + s.isFullTime()
		+ ", graduate = " + s.isGraduate()
		+ ", summary = '" + s.getSummary() + "'"
                + "WHERE number = " + s.getNumber();
        stmt.executeUpdate(sql);
      } else {
	// INSERT
	sql = "INSERT into Students "
                + "(number, name, fullTime, graduate, summary) "
		+ "VALUES (" + s.getNumber()
		+ ", '" + s.getName() + "'"
		+ ", " + s.isFullTime()
                + ", " + s.isGraduate()
		+ ", '" + s.getSummary() + "')";
        //System.out.println(sql);
        stmt.executeUpdate(sql);
      }
      DBConnector.closeConnection(con);
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
  }
  
  public static ArrayList<Student> getStudents() throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT number "
              + "FROM Students";
      ResultSet srs = stmt.executeQuery(sql);
      ArrayList<Student> studenten = new ArrayList<Student>();
      while (srs.next())
        studenten.add(getStudent(srs.getInt("number")));
      DBConnector.closeConnection(con);
      return studenten;
    } catch (DBException dbe) {
      dbe.printStackTrace();
      DBConnector.closeConnection(con);
      throw dbe;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
  }
  
  public static ArrayList<Student> getGraduates() throws DBException {
    Connection con = null;
    try {
      con = DBConnector.getConnection();
      Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
      
      String sql = "SELECT number "
              + "FROM Students "
              + "WHERE graduate=" + true;
      ResultSet srs = stmt.executeQuery(sql);
      
      ArrayList<Student> studenten = new ArrayList<Student>();
      while (srs.next())
        studenten.add(getStudent(srs.getInt("number")));
      DBConnector.closeConnection(con);
      return studenten;
    } catch (DBException dbe) {
      dbe.printStackTrace();
      DBConnector.closeConnection(con);
      throw dbe;
    } catch (Exception ex) {
      ex.printStackTrace();
      DBConnector.closeConnection(con);
      throw new DBException(ex);
    }
                
  }
  public static void main(String[] args){
    try {
      DBStudent.save(new Student("StudentA", 1, true, true, "Test1"));
    } catch (DBException ex) {
      Logger.getLogger(DBStudent.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}