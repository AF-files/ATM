package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Dialog<ButtonType> dialog = new Dialog<>();
        //dialog.initOwner(mainPane.getScene().getWindow());
        dialog.setTitle("Enter Account Details");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        try{
            dialog.getDialogPane().setContent(loader.load());
        }catch(IOException e)
        {
            System.out.print("broke");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        Datasource.getInstance().open();
        if(result.isPresent()&& result.get() == ButtonType.OK)
        {
            LoginController controller = loader.getController();
            if(controller.checkDetails()){
                controller.setCustomer();
                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                primaryStage.setTitle("ATM");
                primaryStage.setScene(new Scene(root, 300, 275));
                primaryStage.show();
            }else{
                System.out.println("Wrong");
                Datasource.getInstance().close();
            }
        }
        Datasource.getInstance().updateCustomer();
        Datasource.getInstance().close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
