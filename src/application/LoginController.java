package application;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField visiblePasswordField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Button btnEntrar;

    @FXML
    private Hyperlink hlEsqueciSenha;
    
    @FXML
    private Button hlCadastrar; 
    
    @FXML
    private Button btnInstagram;

    @FXML
    public void initialize() {
        visiblePasswordField.managedProperty().bind(showPasswordCheckBox.selectedProperty());
        visiblePasswordField.visibleProperty().bind(showPasswordCheckBox.selectedProperty());

        txtSenha.managedProperty().bind(showPasswordCheckBox.selectedProperty().not());
        txtSenha.visibleProperty().bind(showPasswordCheckBox.selectedProperty().not());

        visiblePasswordField.textProperty().bindBidirectional(txtSenha.textProperty());
    }

    @FXML
    void fazerLogin(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        String nomeUsuario = usuarioService.verificarCredenciais(email, senha);

        if (nomeUsuario != null) {
            System.out.println("Login bem-sucedido para " + nomeUsuario + "!");
            abrirTelaPrincipal(nomeUsuario);
        } else {
            System.out.println("Email ou senha inválidos.");
        }
    }
    
    @FXML
    void esqueciSenha(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EsqueciSenha.fxml"));
    		Parent root = loader.load();
    		
    		Stage stage = (Stage) hlEsqueciSenha.getScene().getWindow();
    		
    		stage.setScene(new Scene(root));
    		
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void abrirCadastro(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
    		Parent root = loader.load();
    		
    		Stage stage = (Stage) hlCadastrar.getScene().getWindow();
    		
    		stage.setScene(new Scene(root));
    		
    		stage.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    private void abrirTelaPrincipal(String nomeUsuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root = loader.load();

            PrincipalController principalController = loader.getController();
            principalController.setUserName(nomeUsuario);

            Stage stage = (Stage) btnEntrar.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void abrirInstagram(ActionEvent event) {
    	try {
    		URI uri = new URI("https://www.instagram.com/seu_usuario");
    		java.awt.Desktop.getDesktop().browse(uri);
    	} catch (IOException | URISyntaxException e) {
    		e.printStackTrace();
    	}
    }

}
