package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Dialog<ButtonType> dialog = new Dialog<>();
        //dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Enter Account Details");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        LoginController controller = loader.getController();
        if(controller.checkDetails() == true){
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            primaryStage.setTitle("ATM");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();
        }else{
            System.out.println("Wrong");
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
