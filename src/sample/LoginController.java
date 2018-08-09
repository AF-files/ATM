package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField accnum;
    @FXML
    private TextField pinnum;

    public boolean checkDetails()
    {
        String pintemp = pinnum.getText();
        int pinNum = Integer.parseInt(pintemp);
        String acctemp = accnum.getText();
        int accNum = Integer.parseInt(acctemp);
        return Datasource.getInstance().checkCustomerPin(accNum,pinNum);
    }

    public void setCustomer(){
        String acctemp = accnum.getText();
        int accNum = Integer.parseInt(acctemp);
        Datasource.getInstance().getCustomer(accNum);
    }
}
