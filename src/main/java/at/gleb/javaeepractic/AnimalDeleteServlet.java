package at.gleb.javaeepractic;

import at.gleb.javaeepractic.di.Dependencies;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "animalDeleteServlet", value = "/animal-delete-servlet")
public class AnimalDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // получаем данные формы
        final int id = Integer.parseInt(req.getParameter("id"));
        Dependencies.getInstance().getAnimalsGetter().removeAnimal(id);

        // перенаправляем пользователя на страницу animals.jsp
        resp.sendRedirect("animals.jsp");
    }
}