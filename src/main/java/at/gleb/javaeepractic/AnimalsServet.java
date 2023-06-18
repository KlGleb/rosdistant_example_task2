package at.gleb.javaeepractic;

import at.gleb.javaeepractic.data.AnimalDto;
import at.gleb.javaeepractic.data.AnimalTypeDto;
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
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "animalsServlet", value = "/animals-servlet")
public class AnimalsServet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final AnimalsGetter animalsGetter = Dependencies.getInstance().getAnimalsGetter();

        List<AnimalDto> animals = animalsGetter.getAnimals(null, null);

        List<AnimalTypeDto> types = Dependencies.getInstance().getAnimalsTypeGetter().getTypes(null, null);
        HashMap<Integer, String> typeNamesMap = new HashMap<>();

        for (AnimalTypeDto type : types) {
            typeNamesMap.put(type.getId(), type.getName());
        }

        PrintWriter out = response.getWriter();
        out.println("<table>");

        out.println("<tr>");
        out.println("<td>id</td>");
        out.println("<td>Название</td>");
        out.println("<td>Тип</td>");
        out.println("<td>Удалить</td>");
        out.println("</tr>");

        for (AnimalDto animal : animals) {
            String deleteForm = "<form action=\"animal-delete-servlet\"\" method=\"POST\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + +animal.getId() + "\">" +
                    "<input type=\"submit\" value=\"Удалить\">" +
                    "</form>";
            out.println("<tr>");
            out.println("<td>" + animal.getId() + "</td>");
            out.println("<td><a href='animal.jsp?" + animal.getId() + "'>" + animal.getName() + "</a></td>");
            out.println("<td>" + typeNamesMap.get(animal.getTypeId()) + "</td>");
            out.println("<td>" + deleteForm + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // получаем данные формы
        final String name = req.getParameter("name");
        final int typeId = Integer.parseInt(req.getParameter("id_type"));

        // создаем животное с помощью метода createAnimal() из класса AnimalsGetter
        Dependencies.getInstance().getAnimalsGetter().createAnimal(name, typeId);

        // перенаправляем пользователя на страницу animals.jsp
        resp.sendRedirect("animals.jsp");
    }
}
