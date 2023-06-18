package at.gleb.javaeepractic.di;

import at.gleb.javaeepractic.data.AnimalsGetter;
import at.gleb.javaeepractic.data.AnimalsGetterImpl;
import at.gleb.javaeepractic.data.AnimalsTypeGetter;
import at.gleb.javaeepractic.data.AnimalsTypeGetterImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DependencyFabric {
    Connection connection() {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            return dataSource.getConnection();

        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    AnimalsGetter animalsGetter() {
        return new AnimalsGetterImpl(connection());
    }

    AnimalsTypeGetter typeGetter() {
        return new AnimalsTypeGetterImpl(connection());
    }
}
