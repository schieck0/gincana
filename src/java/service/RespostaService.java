package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Grupo;
import model.Placar;
import model.Resposta;

public class RespostaService {

    public static void save(Resposta resp) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement("insert into respostas(id_pergunta, id_grupo, data_hora, resposta) values (?,?,?,?)");
            pstm.setInt(1, resp.getPergunta().getId());
            pstm.setInt(2, resp.getGrupo().getId());
            pstm.setTimestamp(3, new Timestamp(resp.getDataHora().getTime()));
            pstm.setString(4, resp.getResposta());
            pstm.executeUpdate();
        }
    }
    
    public static List<Placar> getPlacar() {
        List<Placar> placar = new ArrayList<>();
//        placar.add(new Placar(new Grupo("Teste"), 50));
        
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs =conn.createStatement().executeQuery("select g.nome, SUM(r.pontos) as pontos from respostas r"
                    + " inner join grupos g on (g.id = r.id_grupo)"
                    + " group by g.nome order by pontos desc");
            while(rs.next()) {
                Placar p = new Placar();
                p.setGrupo(new Grupo(rs.getString(1)));
                p.setPontos(rs.getInt(2));
                placar.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return placar;
    }
}
