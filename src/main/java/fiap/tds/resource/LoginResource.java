package fiap.tds.resource;

import fiap.tds.DTOS.LoginDTO;
import fiap.tds.DTOS.LoginResponseDto;
import fiap.tds.Exception.UserNotFound;
import fiap.tds.models.Usuario;
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
import java.util.Optional;

@Path("/login")
public class LoginResource {

    @Inject
    UsuarioService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO dto) {

        try {


            Usuario user = userService.login(dto.getEmail(), dto.getSenha());
            if (user != null) {
                var responseDTO = new LoginResponseDto(user.getId(), user.getNome());
                return Response.ok(responseDTO).build();
            } else {
                Map<String, String> resposta = new HashMap<>();
                resposta.put("message", "Credenciais inválidas!");
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(resposta).build();
            }


        } catch (UserNotFound e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("message", e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(resposta).build();
        }


    }}