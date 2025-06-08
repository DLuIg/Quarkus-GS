package fiap.tds.services;

import fiap.tds.DTOS.MuralDTO;
import fiap.tds.DTOS.MuralResponseDto;
import fiap.tds.Exception.ReportAlertException;
import fiap.tds.models.Mural;
import fiap.tds.repositories.MuralRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import oracle.sql.BOOLEAN;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MuralService {

    @Inject
    MuralRepository repository;


    public void reportar_alerta(MuralDTO dto) {
        var mural = new Mural();
        mural.setId_usuario(dto.getId_usuario());
        mural.setLocalizacao(dto.getLocalizacao());
        mural.setMensagem(dto.getMensagem());


        if (!repository.save(mural)){
            throw new ReportAlertException("Falha ao salvar o reporte no banco de dados.");
        }
    }

    public List<MuralResponseDto> listar_alertas() {
        return repository.findAllMural();
    }

    public Boolean deletar(long id, long idUsuario) {
        return repository.marcarComoDeletado(id,idUsuario);

    }

    public boolean editarMsg(Long idMural,String msg, long idUsuario) {
        return repository.editarMsg(idMural,msg,idUsuario);
    }
}
