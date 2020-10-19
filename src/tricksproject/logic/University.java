/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricksproject.logic;

import tricksproject.db.DBStudent;
import tricksproject.db.DBException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tricksproject.db.DBException;
import tricksproject.db.DBStudent;

/**
 *
 * @author fgailly
 */
public class University {
  private ArrayList<Student> students;
  private static University university = new University();
  
  public University() {
    try {
      students = DBStudent.getStudents();
    } catch (DBException ex) {
      Logger.getLogger(University.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  public static University getInstance(){
    return university;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }
  
  public void addStudent(Student student) {
    try {
      students.add(student);
      DBStudent.save(student);
    } catch (DBException ex) {
      Logger.getLogger(University.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }
  
  
  
}
