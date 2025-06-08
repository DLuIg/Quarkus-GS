package fiap.tds.DTOS;

import java.util.Date;

public class MuralDTO {
    private String localizacao;
    private String mensagem;
    private Long id_usuario;

    public MuralDTO(String localizacao, String mensagem, Long id_usuario) {
        this.localizacao = localizacao;
        this.mensagem = mensagem;
        this.id_usuario = id_usuario;

    }

    public MuralDTO() {
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


}
