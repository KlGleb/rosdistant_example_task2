package at.gleb.javaeepractic;

import at.gleb.javaeepractic.data.AnimalDto;
import at.gleb.javaeepractic.data.AnimalTypeDto;
import at.gleb.javaeepractic.di.Dependencies;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "typeEditServlet", value = "/type")
public class AnimalTypeEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getQueryString());
        AnimalTypeDto type = Dependencies.getInstance().getAnimalsTypeGetter().getType(id);
        List<AnimalTypeDto> types = Dependencies.getInstance().getAnimalsTypeGetter().getTypes(null, null);
        PrintWriter out = resp.getWriter();

        // Установите тип контента в текстовый HTML
        resp.setContentType("text/html");

        // Создайте код формы и отправьте его в поток PrintWriter
        out.println("<form action=\"type\" method=\"post\">");
        out.println("  <label for=\"name\">Название:</label>");
        out.println("  <input type=\"text\" id=\"name\" name=\"name\" value=\"" + type.getName() + "\" required><br><br>");

        out.println("  <label for=\"id_type\">Приблизительное количество видов:</label>");
        out.println("  <input type=\"text\" id=\"approx_count\" name=\"approx_count\" value=\"" + type.getApproxCount() + "\" required><br><br>");
        out.println("  <input type=\"hidden\" name=\"id\" value=\"" + id + "\">"); // Скрытое поле для передачи ID животного

        out.println("  <input type=\"submit\" value=\"Сохранить\">");
        out.println("</form>");

        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // Получаем данные формы
        final int id = Integer.parseInt(req.getParameter("id"));
        final String name = req.getParameter("name");
        final int approxCount = Integer.parseInt(req.getParameter("approx_count"));

        // Редактируем животное с использованием полученных данных
        Dependencies.getInstance().getAnimalsTypeGetter().editType(id, name, approxCount);

        // Перенаправляем на animals.jsp
        resp.sendRedirect("types.jsp");
    }

}
