package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label accNumDisplay;

    private void closeMain(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root = loader.load();
            Stage closeStage = (Stage) mainPane.getScene().getWindow();
            closeStage.setScene(new Scene(root));
            closeStage.close();
        }catch (IOException e){
            System.out.println("Couldn't close window: "+e.getMessage());
        }
    }
    public void openWithdraw(){
        try{
            FXMLLoader loaderNew = new FXMLLoader(getClass().getResource("withdraw.fxml"));
            Parent rootNew = loaderNew.load();
            Stage openStage = new Stage();
            openStage.setScene(new Scene(rootNew, 300, 275));
            openStage.show();
            closeMain();
        }catch (IOException e){
            System.out.println("Couldn't open window: "+e.getMessage());
        }
    }
    public void openLodgement(){
        try{
            FXMLLoader loaderNew = new FXMLLoader(getClass().getResource("lodgement.fxml"));
            Parent rootNew = loaderNew.load();
            Stage openStage = new Stage();
            openStage.setScene(new Scene(rootNew, 300, 275));
            openStage.show();
            closeMain();
        }catch (IOException e){
            System.out.println("Couldn't open window: "+e.getMessage());
        }
    }


}
