package fiap.tds.repositories;


import fiap.tds.DTOS.MuralResponseDto;
import fiap.tds.models.Mural;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class MuralRepository implements CrudRepository<Mural,Long> {

    @Inject
    DataSource dataSource;



    @Override
    public boolean save(Mural entity) {
        String sql = "INSERT INTO Mural (localizacao, mensagem, id_usuario) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getLocalizacao());
            stmt.setString(2, entity.getMensagem());
            stmt.setLong(3, entity.getId_usuario());
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;  // true se inseriu alguma linha
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<MuralResponseDto> findAllMural() {
        List<MuralResponseDto> response = new ArrayList<>();
        String sql = "SELECT m.id_mural, m.localizacao, m.mensagem, m.date_time, m.id_usuario, u.nome AS nome_usuario " +
                "FROM Mural m " +
                "JOIN Usuariov2 u ON m.id_usuario = u.id_usuario " +
                "WHERE m.deleted = 0";


        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MuralResponseDto dto = new MuralResponseDto();
                dto.setLocalizacao(rs.getString("localizacao"));
                dto.setMensagem(rs.getString("mensagem"));
                dto.setDateTime(rs.getTimestamp("date_time").toLocalDateTime());
                dto.setNomeUsuario(rs.getString("nome_usuario"));
                dto.setID_MURAL(rs.getLong("id_mural"));
                dto.setId_usuario(rs.getLong("id_usuario"));

                response.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean marcarComoDeletado(long idMural, long idUsuario) {
        String sql = "UPDATE Mural SET deleted = 1 WHERE id_mural = ? AND id_usuario = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, idMural);
            stmt.setLong(2, idUsuario);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editarMsg(long idMural, String msg, long idUsuario) {
        String sql = "UPDATE Mural SET mensagem = ? WHERE id_mural = ? AND id_usuario = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, msg);
            stmt.setLong(2, idMural);
            stmt.setLong(3, idUsuario);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Mural> findById(Long aLong) {
        throw new UnsupportedOperationException("FindById Not supported.");
    }

    @Override
    public List<Mural> findAll() {
        throw new UnsupportedOperationException("FindAll Not supported.");
    }

    @Override
    public boolean update(Mural entity) {
        throw new UnsupportedOperationException("Update not supported.");
    }

    @Override
    public boolean deleteById(Long aLong) {
        throw new UnsupportedOperationException("Delete not supported.");

    }


}
