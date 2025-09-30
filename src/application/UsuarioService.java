package application;

import java.util.HashMap;
import java.util.Map;

public class UsuarioService {

    // Mapa aninhado: email -> { "nome": "...", "senha": "..." }
    private static final Map<String, Map<String, String>> usuarios = new HashMap<>();

    public void cadastrarUsuario(String nome, String email, String senha) {
        Map<String, String> dadosUsuario = new HashMap<>();
        dadosUsuario.put("nome", nome);
        dadosUsuario.put("senha", senha);
        usuarios.put(email, dadosUsuario);
    }

    /**
     * Verifica as credenciais do usuário.
     * @return O nome do usuário se as credenciais forem válidas, caso contrário, null.
     */
    public String verificarCredenciais(String email, String senha) {
        Map<String, String> dadosUsuario = usuarios.get(email);
        if (dadosUsuario != null) {
            String senhaArmazenada = dadosUsuario.get("senha");
            if (senhaArmazenada != null && senhaArmazenada.equals(senha)) {
                return dadosUsuario.get("nome"); // Retorna o nome do usuário
            }
        }
        return null; // Usuário não encontrado ou senha incorreta
    }

    public Map<String, Map<String, String>> getAllUsers() {
        return usuarios;
    }

    public String getSenhaPorEmail(String email) {
        Map<String, String> dadosUsuario = usuarios.get(email);
        if (dadosUsuario != null) {
            return dadosUsuario.get("senha");
        }
        return null;
    }
}