package at.gleb.javaeepractic;

import at.gleb.javaeepractic.data.AnimaXmlDto;
import at.gleb.javaeepractic.data.AnimalDto;
import at.gleb.javaeepractic.data.AnimalListXmlDto;
import at.gleb.javaeepractic.data.AnimalsGetter;
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

@WebServlet(name = "animalsXmlServlet", value = "/animals-xml-servlet")
public class AnimalsXmlServet extends HttpServlet {
    private static final String DTD = "<!DOCTYPE animalList[<!ELEMENT animalList (animal+)>\n" +
            "        <!ELEMENT animal (id, name, typeId)>\n" +
            "        <!ELEMENT id (#PCDATA)>\n" +
            "        <!ELEMENT name (#PCDATA)>\n" +
            "        <!ELEMENT typeId (#PCDATA)>]>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        final AnimalsGetter animalsGetter = Dependencies.getInstance().getAnimalsGetter();

        Map<String, String> queryParams = QueryStringParser.parseQueryString(req.getQueryString());
        String typeId = queryParams.get("id_type");
        String name = queryParams.get("name");
        List<AnimalDto> animals = animalsGetter.getAnimals(name == null || name.equals("") ? null : name, (typeId != null && !typeId.equals("")) ? Integer.parseInt(typeId) : null);

        AnimalListXmlDto animalList = new AnimalListXmlDto();
        animalList.setAnimals(animals.stream().map(animalDto -> {
            AnimaXmlDto animaXmlDto = new AnimaXmlDto();
            animaXmlDto.setId(animalDto.getId());
            animaXmlDto.setName(animalDto.getName());
            animaXmlDto.setTypeId(animaXmlDto.getTypeId());
            return animaXmlDto;
        }).collect(Collectors.toList()));

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AnimalListXmlDto.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(animalList, writer);
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
