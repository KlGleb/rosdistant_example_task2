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

@WebServlet(name = "typesSelectorServlet", value = "/types-selector")
public class SelectorTypesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AnimalTypeDto> types = Dependencies.getInstance().getAnimalsTypeGetter().getTypes(null, null);
        PrintWriter out = resp.getWriter();
        out.println("  <select id=\"id_type\" name=\"id_type\" required>");

        for (AnimalTypeDto type : types) {
            out.println("    <option value=\"" + type.getId() + "\">" + type.getName() + "</option>");
        }

        out.println("  </select>");
    }

}
