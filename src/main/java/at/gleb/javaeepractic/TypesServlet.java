package at.gleb.javaeepractic;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import at.gleb.javaeepractic.data.AnimalDto;
import at.gleb.javaeepractic.data.AnimalTypeDto;
import at.gleb.javaeepractic.di.Dependencies;
import at.gleb.javaeepractic.util.QueryStringParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(name = "typesServlet", value = "/type-servlet")
public class TypesServlet extends HttpServlet {
    private final StringBuilder sb = new StringBuilder();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> queryParams = QueryStringParser.parseQueryString(request.getQueryString());
        String approxCount = queryParams.get("approx_count");
        String name = queryParams.get("name");

        List<AnimalTypeDto> types = Dependencies.getInstance().getAnimalsTypeGetter().getTypes(name == null || name.equals("") ? null : name, approxCount == null || approxCount.equals("") ? null : Integer.parseInt(approxCount));

        PrintWriter out = response.getWriter();
        out.println("<table>");

        out.println("<tr>");
        out.println("<td>id</td>");
        out.println("<td>Название</td>");
        out.println("<td>Приблизительное количество видов</td>");
        out.println("<td>Удалить</td>");
        out.println("<td>Поиск по типу</td>");
        out.println("</tr>");


        for (AnimalTypeDto type : types) {
            String deleteForm = "<form action=\"type-delete-servlet\"\" method=\"POST\">" +
                    "<input type=\"hidden\" name=\"id\" value=\"" + +type.getId() + "\">" +
                    "<input type=\"submit\" value=\"Удалить\">" +
                    "</form>";
            out.println("<tr>");
            out.println("<td>" + type.getId() + "</td>");
            out.println("<td><a href='type.jsp?" + type.getId() + "'>" + type.getName() + "</a></td>");
            out.println("<td>" + type.getApproxCount() + "</td>");
            out.println("<td>" + deleteForm + "</td>");
            out.println("<td> <a href='animals.jsp?id_type=" + type.getId() + "'>Открыть животных этого типа</a></td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // получаем данные формы
        final String name = req.getParameter("name");
        final int approxCount = Integer.parseInt(req.getParameter("approx_count"));

        // создаем животное с помощью метода createAnimal() из класса AnimalsGetter
        Dependencies.getInstance().getAnimalsTypeGetter().createType(name, approxCount);

        // перенаправляем пользователя на страницу animals.jsp
        resp.sendRedirect("types.jsp");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getQueryString());
        Dependencies.getInstance().getAnimalsTypeGetter().removeType(id);
        resp.sendRedirect("types.jsp");
    }
}