package br.com.wl.dao;

import br.com.wl.model.Pessoa;
import br.com.wl.util.DbUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(Pessoa pessoa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into pessoa(nome,cpf) values (?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from pessoa where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Pessoa pessoa) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update pessoa set nome=?, cpf=?"
                            + "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());

            preparedStatement.setInt(3, pessoa.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pessoa> getAllUsers() {
        List<Pessoa> lista = new ArrayList<Pessoa>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from pessoa");
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("CPF"));
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Pessoa getUserById(int userId) {
        Pessoa pessoa = new Pessoa();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from pessoa where id=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("CPF"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoa;
    }
}
