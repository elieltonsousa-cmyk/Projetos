package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EsqueciSenhaController {

    @FXML
    private TextField txtEmailRecuperacao;

    @FXML
    private Button btnEnviar;
    
    @FXML
    private Button btnVoltar;

    @FXML
    void enviarEmail(ActionEvent event) {
        String email = txtEmailRecuperacao.getText();

        if (email.isEmpty()) {
            System.out.println("Por favor, preencha o campo de email.");
            return;
        }

        System.out.println("Um email de recuperação foi enviado para: " + email);
    }
    
    @FXML
    void voltarLogin(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
    		Parent root = loader.load();
    		
    		Stage stage = (Stage) btnVoltar.getScene().getWindow();
    		
    		stage.setScene(new Scene(root));
    		
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
