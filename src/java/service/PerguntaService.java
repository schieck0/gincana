package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Grupo;
import model.Pergunta;

public class PerguntaService {

    public static Pergunta getNovaPergunta(Grupo grupo) {
        Pergunta pergunta = null;
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("select p.* from perguntas p"
                    + " where p.id not in ("
                    + "select r.id_pergunta from respostas r where r.id_grupo=" + grupo.getId() + ")");

            if (rs.next()) {
                pergunta = new Pergunta();
                pergunta.setId(rs.getInt(1));
                pergunta.setDescricao(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pergunta;
    }

    public static void save(Pergunta pergunta) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement("insert into perguntas(descricao) values(?)");
            pstm.setString(1, pergunta.getDescricao());
            pstm.executeUpdate();
        }
    }
}
