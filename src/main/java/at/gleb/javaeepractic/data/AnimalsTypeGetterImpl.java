package at.gleb.javaeepractic.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalsTypeGetterImpl implements AnimalsTypeGetter {
    private final Connection connection;

    public AnimalsTypeGetterImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<AnimalTypeDto> getTypes(String name, Integer minCount) {
        List<AnimalTypeDto> types = new ArrayList<>();

        try {
            String query = "SELECT * FROM types";
            if (name != null && minCount != null) {
                query += " WHERE name = ? AND approx_count >= ?";
            } else if (name != null) {
                query += " WHERE name = ?";
            } else if (minCount != null) {
                query += " WHERE approx_count >= ?";
            }

            PreparedStatement statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            if (name != null) {
                statement.setString(parameterIndex++, name);
            }
            if (minCount != null) {
                statement.setInt(parameterIndex, minCount);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String typeName = resultSet.getString("name");
                int approxCount = resultSet.getInt("approx_count");
                AnimalTypeDto type = new AnimalTypeDto(id, typeName, approxCount);
                types.add(type);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    @Override
    public AnimalTypeDto getType(int id) {
        AnimalTypeDto type = null;

        try {
            String query = "SELECT * FROM types WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String typeName = resultSet.getString("name");
                int approxCount = resultSet.getInt("approx_count");
                type = new AnimalTypeDto(id, typeName, approxCount);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

    @Override
    public void createType(String name, int approxCount) {
        try {
            String query = "INSERT INTO types (name, approx_count) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, approxCount);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editType(int id, String name, int approxCount) {
        try {
            String query = "UPDATE types SET name = ?, approx_count = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, approxCount);
            statement.setInt(3, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeType(int id) {
        try {
            String query = "DELETE FROM types WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
