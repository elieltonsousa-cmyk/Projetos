package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnVoltar;

    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        String confirmarSenha = txtConfirmarSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            System.out.println("As senhas não coincidem.");
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        usuarioService.cadastrarUsuario(nome, email, senha);

        System.out.println("Usuário cadastrado com sucesso!");

        voltarLogin(event);
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
