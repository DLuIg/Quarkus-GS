package fiap.tds.repositories;

import fiap.tds.Exception.UserNotFound;
import fiap.tds.models.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepository implements CrudRepository<Usuario,Long> {

    @Inject
    DataSource dataSource;

    public Usuario login(String login, String senha) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id_usuario, nome, email FROM USUARIOv2 WHERE email = ? AND senha = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, login);
                stmt.setString(2, senha);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Usuario user = new Usuario();
                        user.setId(rs.getLong("id_usuario"));
                        user.setNome(rs.getString("nome"));
                        user.setEmail(rs.getString("email"));
                        // A senha normalmente não é retornada por segurança
                        return user;
                    }
                }
            }
        } catch (UserNotFound | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Usuario entity) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO USUARIOv2 (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, entity.getNome());
                stmt.setString(2, entity.getEmail());
                stmt.setString(3, entity.getSenha());

                int linhasAfetadas = stmt.executeUpdate();
                return linhasAfetadas > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<Usuario> findById(Long aLong) {
        throw new UnsupportedOperationException("FindById Not supported.");
    }

    @Override
    public List<Usuario> findAll() {
        throw new UnsupportedOperationException("FindAll Not supported.");
    }

    @Override
    public boolean update(Usuario entity) {
        throw new UnsupportedOperationException("Update not supported.");
    }

    @Override
    public boolean deleteById(Long aLong) {
        throw new UnsupportedOperationException("Delete not supported.");

    }

}