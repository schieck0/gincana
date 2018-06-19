package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Grupo;

public class GrupoService {

    public static Grupo validaLogin(String nome, String senha) {
        Grupo grupo = null;
//        if (nome != null && nome.equals("admin") && senha != null && senha.equals("123")) {
//            g = new Grupo(nome);
//            g.setSenha(senha);
//        }
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement pstm = conn.prepareStatement("select g.* from grupos g where g.nome=? and g.senha=?");
            pstm.setString(1, nome);
            pstm.setString(2, senha);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                grupo = new Grupo();
                grupo.setId(rs.getInt(1));
                grupo.setNome(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grupo;
    }
}
