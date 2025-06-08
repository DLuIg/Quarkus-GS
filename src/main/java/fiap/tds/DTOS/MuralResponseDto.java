package fiap.tds.DTOS;

import java.time.LocalDateTime;

public class MuralResponseDto {
    private long ID_MURAL;
    private String localizacao;
    private String mensagem;
    private LocalDateTime dateTime;
    private String nomeUsuario;
    private long id_usuario;


    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public MuralResponseDto() {
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public long getID_MURAL() {
        return ID_MURAL;
    }

    public void setID_MURAL(long ID_MURAL) {
        this.ID_MURAL = ID_MURAL;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public MuralResponseDto(String localizacao, String mensagem, LocalDateTime dateTime, String nomeUsuario) {
        this.localizacao = localizacao;
        this.mensagem = mensagem;
        this.dateTime = dateTime;
        this.nomeUsuario = nomeUsuario;
    }
}