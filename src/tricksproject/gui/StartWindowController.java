/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricksproject.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import tricksproject.logic.University;

/**
 * FXML Controller class
 *
 * @author fgailly
 */
public class StartWindowController implements Initializable {

  @FXML
  private Button studentenBtn;
  @FXML
  private Button addStudentBttn;
  
  private University model;
  @FXML
  private AnchorPane dataPane;
  

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    model = University.getInstance();
    System.out.println("Model set");
  }  

  @FXML
  private void loadStudentenPane(ActionEvent event) {
    try {
      
      AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/tricksproject/gui/StudentListGUI.fxml"));
      dataPane.getChildren().setAll(pane);
      
      
    } catch (IOException ex) {
      Logger.getLogger(StartWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  private void loadAddStudentPane(ActionEvent event) {
    try {
      AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("/tricksproject/gui/StudentAdd.fxml"));
      dataPane.getChildren().setAll(pane);
    } catch (IOException ex) {
      Logger.getLogger(StartWindowController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  
  
}
