package at.gleb.javaeepractic;

import at.gleb.javaeepractic.data.AnimalDto;
import at.gleb.javaeepractic.data.AnimalsGetter;
import at.gleb.javaeepractic.di.Dependencies;
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
import java.util.List;

@WebServlet(name = "animalsServlet", value = "/animals-servlet")
public class AnimalsServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        final AnimalsGetter animalsGetter = Dependencies.getInstance().getAnimalsGetter();

        List<AnimalDto> animals = animalsGetter.getAnimals(null, null);

        PrintWriter out = response.getWriter();
        out.println("<table>");

        out.println("<tr>");
        out.println("<td>id</td>");
        out.println("<td>name</td>");
        out.println("<td>type</td>");
        out.println("</tr>");

        for (AnimalDto animal : animals) {
            out.println("<tr>");
            out.println("<td>" + animal.getId() + "</td>");
            out.println("<td>" + animal.getName() + "</td>");
            out.println("<td>" + animal.getTypeId() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }
}
