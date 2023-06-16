package at.gleb.javaeepractic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "animalsServlet", value = "/animals-servlet")
public class AnimalsServet extends HttpServlet {
    private final StringBuilder sb = new StringBuilder();
    private String message;
    private Connection connection;
    public void init() {
        try {
            sb.append("Устанавливается соединение с БД...<br/>");
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/mysql");
            connection = dataSource.getConnection();
            sb.append("Соединение с БД установлено<br/>");
            sb.append("<br/>");
        } catch (SQLException | NamingException e) {
            sb.append(e);
        } finally {
            message = sb.toString();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        try {
            // ... (получение соединения и другие настройки)

            String selectSql = "SELECT * FROM animals";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = selectStatement.executeQuery();

            sb.append("Содержимое таблицы animals:<br/>");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                sb.append("ID: ").append(id).append(", Name: ").append(name).append("<br/>");
            }

        } catch (SQLException e) {
            sb.append(e);
        } finally {
            message = sb.toString();
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<div>" + message + "</div>");
        out.println("</body></html>");
    }

    @Override
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
