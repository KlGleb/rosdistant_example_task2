package at.gleb.javaeepractic.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalsGetterImpl implements AnimalsGetter {
    private final Connection connection;

    public AnimalsGetterImpl(Connection connection) {
        this.connection = connection;

       /* try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            connection = dataSource.getConnection();

        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public List<AnimalDto> getAnimals(String name, Integer typeId) {
        try {
            String selectSql = "SELECT * FROM animals WHERE true";

            if (name != null) {
                selectSql += " AND name = ?";
            }

            if (typeId != null) {
                selectSql += " AND typeId = ?";
            }

            PreparedStatement selectStatement = connection.prepareStatement(selectSql);

            int parameterIndex = 1;

            if (name != null) {
                selectStatement.setString(parameterIndex++, name);
            }

            if (typeId != null) {
                selectStatement.setInt(parameterIndex, typeId);
            }

            ResultSet resultSet = selectStatement.executeQuery();

            List<AnimalDto> animalDtos = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name1 = resultSet.getString("name");
                int typeId1 = resultSet.getInt("typeId");
                animalDtos.add(new AnimalDto(id, name1, typeId1));
            }

            return animalDtos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public AnimalDto getAnimal(int id) {
        try {
            String selectSql = "SELECT * FROM animals WHERE id = ? LIMIT 1";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, id);
            ResultSet resultSet = selectStatement.executeQuery();

            resultSet.next();
            int id2 = resultSet.getInt("id");
            String name1 = resultSet.getString("name");
            int typeId1 = resultSet.getInt("name");

            return  (new AnimalDto(id2, name1, typeId1));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createAnimal(String name, int typeId) {
        try {
            String insertSql = "INSERT INTO animals (name, type_id) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, name);
            insertStatement.setInt(2, typeId);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editAnimal(int id, String name, int typeId) {
        try {
            String insertSql = "UPDATE animals SET name = ?, type_id = ? WHERE id = ? LIMIT 1";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, name);
            insertStatement.setInt(2, typeId);
            insertStatement.setInt(3, id);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAnimal(int id) {
        try {
            String insertSql = "DELETE FROM animals WHERE id = ? LIMIT 1";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setInt(1, id);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
