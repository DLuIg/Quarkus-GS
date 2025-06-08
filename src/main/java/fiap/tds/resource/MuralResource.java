package fiap.tds.resource;

import fiap.tds.DTOS.MuralDTO;
import fiap.tds.DTOS.MuralMsgDto;
import fiap.tds.DTOS.MuralResponseDto;
import fiap.tds.Exception.ReportAlertException;
import fiap.tds.services.MuralService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/mural")
public class MuralResource {

    @Inject
    MuralService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response reportar_alerta(MuralDTO dto) {

        try{
            service.reportar_alerta(dto);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("message", "Reporte concluido");
            return Response.ok().entity(resposta).build();
        }catch (ReportAlertException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        try {
            List<MuralResponseDto> alertas = service.listar_alertas();
            return Response.ok(alertas).build();
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("message", "Erro ao listar alertas.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(resposta).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deletarReporte(@PathParam("id") long id, @QueryParam("idUsuario") long idUsuario) {
        boolean deletado = service.deletar(id, idUsuario);
        if (deletado) {
            return Response.ok().entity(Response.status(Response.Status.NO_CONTENT)).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("ERRO AO REMOVER REPORTE DE MURAL").build();

    }

    @PUT
    @Path("/{id}")
    public Response mensagem(@PathParam("id") Long id,
                             MuralMsgDto dto,
                             @QueryParam("idUsuario") long idUsuario) {
        try {
            boolean editarMsg = service.editarMsg(id, dto.getMsg(), idUsuario);
            if (editarMsg) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Erro ao editar mensagem.")
                        .build();
            }
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("message", "Ocorreu um erro ao tentar editar a mensagem.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(resposta).build();
        }

    }


}
