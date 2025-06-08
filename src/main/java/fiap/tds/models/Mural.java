package fiap.tds.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Mural {
    private Long id;
    private String localizacao;
    private String mensagem;
    private Long id_usuario;
    private LocalDateTime date_time;

    public Mural(Long id, String localizacao, String mensagem, Long id_usuario, LocalDateTime date_time) {
        this.id = id;
        this.localizacao = localizacao;
        this.mensagem = mensagem;
        this.id_usuario = id_usuario;
        this.date_time = date_time;
    }

    public Mural() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }


    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }
}
