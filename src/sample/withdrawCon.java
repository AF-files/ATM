package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class withdrawCon {
    @FXML
    private BorderPane withdrawPane;

    @FXML
    public void returnOrLeave(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(withdrawPane.getScene().getWindow());
        dialog.setTitle("Are you finished with your transactions?");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent()&& result.get() == ButtonType.NO) {
            closeWindow();
            openMainWindow();
        }else if (result.isPresent()&& result.get() == ButtonType.YES){
            closeWindow();
        }
    }

    private void closeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
            Parent root = loader.load();
            Stage closeStage = (Stage) withdrawPane.getScene().getWindow();
            closeStage.setScene(new Scene(root));
            closeStage.close();
        }catch (IOException e){
            System.out.println("Couldn't close window: "+e.getMessage());
        }
    }
    private void openMainWindow(){
        try{
            FXMLLoader loaderNew = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent rootNew = loaderNew.load();
            Stage openStage = new Stage();
            openStage.setScene(new Scene(rootNew));
            openStage.show();
        }catch (IOException e){
            System.out.println("Couldn't open window: "+e.getMessage());
        }
    }
}
