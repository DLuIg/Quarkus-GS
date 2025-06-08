package fiap.tds.DTOS;

public class LoginDTO {
    private String email;
    private String senha;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.senha = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

