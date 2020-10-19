/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricksproject.gui;

import tricksproject.logic.Student;
import tricksproject.logic.University;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 *
 * @author fgailly
 */
public class StudentListViewController  {
  
  private University model;
  
  @FXML
  private ListView<Student> studentListView;
  private ObservableList<Student> students;
  
  
  
  public void initialize() {
    model = University.getInstance();
    students = FXCollections.observableArrayList(model.getStudents());
    studentListView.setItems(students);
    
  }  


  void addStudent(Student student) {
    students.add(student);
  }

  
}
