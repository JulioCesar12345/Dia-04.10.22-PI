/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import entidades.Categoria;
import entidades.Filme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public class FilmeDAO {

    public static List<Filme> listar() {
        List<Filme> lista = new ArrayList<Filme>();
        try {
            String sql = "Select filme. *,categoria.nome as categoria,categoria.tipo from filme INNER JOIN categoria on filme.categoria_id=categoria.id ";
            Connection conexao = Conexao.getConexao();
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("Categoria_id"));
                categoria.setNome(rs.getString("categoria"));
                categoria.setTipo(rs.getString("tipo").charAt(0));
                Filme filme = new Filme(categoria);
                filme.setId(rs.getInt("id"));
                filme.setDescricao(rs.getString("descricao"));
                filme.setDiretor(rs.getString("diretor"));
                filme.setDuracao(rs.getInt("duracao"));
                filme.setNumeroDias(rs.getInt("numeroDias"));
                filme.setPreco(rs.getDouble("preco"));
                filme.setTitulo(rs.getString("titulo"));
                lista.add(filme);
            }
            rs.close();
            st.close();;
        } catch (Exception e) {
            System.out.println("FilmeDao.listar");
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public static boolean inserir(Filme filme) {
        try {
            String sql = "INSERT INTO filme(titulo, descricao, preco, numeroDias, diretor, duracao, categoria_id)VALUES(?,?,?,?,?,?,?)";
            Connection con = Conexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getDescricao());
            ps.setDouble(3, filme.getPreco());
            ps.setInt(4, filme.getNumeroDias());
            ps.setString(5, filme.getDiretor());
            ps.setInt(6, filme.getDuracao());
            ps.setInt(7, filme.getCategoria().getId());
            ps.setInt(8, filme.getId());

            int inseridos = ps.executeUpdate();

            ps.close();

            return inseridos > 0;
        } catch (Exception e) {
            System.out.println("FilmeDAO.Inserir: " + e.getMessage());
            return false;
        }
    }

    public static boolean alterar(Filme filme) {
        try {
            String sql = "UPDATE filme set titulo=?, descricao=?, preco=?, numeroDias=?, diretor=?, duracao=?, categoria_id=? where id = ?";
            Connection con = Conexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, filme.getTitulo());
            ps.setString(2, filme.getDescricao());
            ps.setDouble(3, filme.getPreco());
            ps.setInt(4, filme.getNumeroDias());
            ps.setString(5, filme.getDiretor());
            ps.setInt(6, filme.getDuracao());
            ps.setInt(7, filme.getCategoria().getId());
            ps.setInt(8, filme.getId());

            int alterado = ps.executeUpdate();

            ps.close();

            return alterado > 0;
        } catch (Exception e) {
            System.out.println("FilmeDAO.alterar: " + e.getMessage());
            return false;
        }
    }

    public static boolean excluir(int id) {
        try {
            String sql = "DELETE FROM filme WHERE id = ?";
            Connection con = Conexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int excluidos = ps.executeUpdate();

            ps.close();

            return excluidos > 0;
        } catch (Exception e) {
            System.out.println("FilmeDAO.excluir: " + e.getMessage());
            return false;
        }
    }
}
