package at.gleb.javaeepractic;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private Connection connection;

    private final StringBuilder sb = new StringBuilder();
    public void init() {
        message = "Это сервлет";
        try {
            sb.append("Устанавливается соединение с БД...<br/>");
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            connection = dataSource.getConnection();
            sb.append("Соединение с БД установлено<br/>");


            sb.append("Выполняется запрос на создание таблицы animals...<br/>");

            String sql = "CREATE TABLE IF NOT EXISTS animals (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name CHAR(30) NOT NULL, " +
                    "PRIMARY KEY (id))";

            PreparedStatement statement = connection.prepareStatement(sql);
            boolean isExecuted = statement.execute();
            if (isExecuted) {
                sb.append("Таблица animals создана <br/>");
            } else {
                sb.append("Таблица animals не была создана, так как она уже существует<br/>");
            }
            sb.append("<br/>");
        } catch (SQLException | NamingException e) {
            sb.append(e);
        } finally {
            message = sb.toString();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        addAnimalToDb();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<a href=\"animals-servlet\">Сервлет, показывающий содержимое таблицы animals</a><br/>");
        out.println("</body></html>");
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAnimalToDb() {
        try {
            String animal = generateRandomAnimal();

            String insertSql = "INSERT INTO animals (name) VALUES (?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, animal);
            insertStatement.executeUpdate();

            sb.append("Животное добавлено в таблицу animals: ").append(animal).append("<br/>");
            message = sb.toString();

        } catch (SQLException e) {
            sb.append(e);
        } finally {
            message = sb.toString();
        }
    }

    // Генерирует случайное животное
    private String generateRandomAnimal() {
        String[] animals = {"Кошка", "Собака", "Лев", "Тигр", "Заяц", "Лось", "Медведь"};
        Random random = new Random();
        int index = random.nextInt(animals.length);
        return animals[index];
    }
}