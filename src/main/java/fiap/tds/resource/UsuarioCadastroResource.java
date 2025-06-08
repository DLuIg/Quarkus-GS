package fiap.tds.resource;

import fiap.tds.DTOS.UsuarioDTO;
import fiap.tds.services.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/usuario")
public class UsuarioCadastroResource {
    @Inject
    UsuarioService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UsuarioDTO dto) {
        try {
            userService.register(dto);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("message", "Usuário Cadastrado com Sucesso!");
            return Response.status(Response.Status.CREATED).entity(resposta).build();
        }
        catch (IllegalArgumentException ex) {
            Map<String, String> erro = new HashMap<>();
            erro.put("message", "Algo de errado ao registrar!");
            return Response.status(Response.Status.BAD_REQUEST).entity(erro).build();
        }
    }
}
