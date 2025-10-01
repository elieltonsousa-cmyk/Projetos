package application;

import java.io.IOException;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private Label lblBoasVindas;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Button btnSair;

    @FXML
    private TextField txtFiltro;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    private UsuarioService usuarioService;

    private ObservableList<User> userList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        usuarioService = new UsuarioService();
        
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        loadUsers();

        btnEditar.setDisable(true);
        btnExcluir.setDisable(true);

        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnEditar.setDisable(false);
                btnExcluir.setDisable(false);
            } else {
                btnEditar.setDisable(true);
                btnExcluir.setDisable(true);
            }
        });

        FilteredList<User> filteredData = new FilteredList<>(userList, b -> true);

        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                }
                return false; 
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(userTable.comparatorProperty());

        userTable.setItems(sortedData);
    }

    private void loadUsers() {
        Map<String, Map<String, String>> usuarios = usuarioService.getAllUsers();
        
        for (Map.Entry<String, Map<String, String>> entry : usuarios.entrySet()) {
            String email = entry.getKey();
            String nome = entry.getValue().get("nome");
            userList.add(new User(nome, email));
        }
    }

    public void setUserName(String name) {
        lblBoasVindas.setText("Bem-vindo, " + name + "!");
    }

    @FXML
    void fazerLogout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnSair.getScene().getWindow();

            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarUsuario(ActionEvent event) {
        System.out.println("Adicionar usuário");
    }

    @FXML
    void editarUsuario(ActionEvent event) {
        System.out.println("Editar usuário");
    }

    @FXML
    void excluirUsuario(ActionEvent event) {
        System.out.println("Excluir usuário");
    }

    public static class User {
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;

        public User(String name, String email) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }
    }
}