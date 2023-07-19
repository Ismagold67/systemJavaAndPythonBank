package org.example;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String cpf;
    private String nome;
    private String data_nascimento;
    private String endereco;

    // Construtor
    public Usuario(String nome, String data_nascimento, String cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;

    }

    // Getter para o CPF
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    // Setter para o CPF (se necessário)

    public static Usuario otherFilterUser(String cpf, List<Usuario> usuarios) {
        List<Usuario> usuariosFiltrados = new ArrayList<>();

        // Filtrar os usuários com base no CPF
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                usuariosFiltrados.add(usuario);
            }
        }

        return usuariosFiltrados.isEmpty() ? null : usuariosFiltrados.get(0);
    }
}
