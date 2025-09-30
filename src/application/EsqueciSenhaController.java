package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lblSenhaRecuperada;

    @FXML
    void enviarEmail(ActionEvent event) {
        String email = txtEmailRecuperacao.getText();

        if (email.isEmpty()) {
            lblSenhaRecuperada.setText("Por favor, preencha o campo de email.");
            lblSenhaRecuperada.setVisible(true);
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        String senha = usuarioService.getSenhaPorEmail(email);

        if (senha != null) {
            lblSenhaRecuperada.setText("Sua senha é: " + senha);
            lblSenhaRecuperada.setVisible(true);
        } else {
            lblSenhaRecuperada.setText("Email não encontrado.");
            lblSenhaRecuperada.setVisible(true);
        }
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
