package at.gleb.javaeepractic;

import at.gleb.javaeepractic.data.*;
import at.gleb.javaeepractic.di.Dependencies;
import at.gleb.javaeepractic.util.QueryStringParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "typesXmlServlet", value = "/types-xml-servlet")
public class AnimalsTypeXmlServet extends HttpServlet {
    private static final String DTD = "<!DOCTYPE typeList[<!ELEMENT typeList (type+)>\n" +
            "        <!ELEMENT type (id, name, approximateCount)>\n" +
            "        <!ELEMENT id (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        <!ELEMENT approximateCount (#PCDATA)>]>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> queryParams = QueryStringParser.parseQueryString(req.getQueryString());
        String approxCount = queryParams.get("approx_count");
        String name = queryParams.get("name");

        List<AnimalTypeDto> types = Dependencies.getInstance().getAnimalsTypeGetter().getTypes(name == null || name.equals("") ? null : name, approxCount == null || approxCount.equals("") ? null : Integer.parseInt(approxCount));

        AnimalTypeListXmlDto typesList = new AnimalTypeListXmlDto();
        typesList.setTypes(types.stream().map(typeDto -> {
            AnimalTypeXmlDto animalTypeXmlDto = new AnimalTypeXmlDto();
            animalTypeXmlDto.setId(typeDto.getId());
            animalTypeXmlDto.setApproximateCount(typeDto.getApproxCount());
            animalTypeXmlDto.setName(typeDto.getName());
            return animalTypeXmlDto;
        }).collect(Collectors.toList()));

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnimalTypeListXmlDto.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(typesList, writer);
            String xmlString = writer.toString().replaceFirst("<\\?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"\\?>", "");

            response.setContentType("application/xml");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<?xml version=\"1.0\"?>");
            out.println(DTD);
            out.println(xmlString);
        } catch (Exception e) {
            // Обработка ошибки JAXB
            e.printStackTrace();
            // Отправка HTTP-ответа с ошибкой, если требуется
            PrintWriter out = response.getWriter();
            out.println(e);
        }
    }

}
