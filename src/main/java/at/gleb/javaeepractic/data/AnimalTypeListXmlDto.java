package at.gleb.javaeepractic.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "typeList")
public class AnimalTypeListXmlDto {
    private List<AnimalTypeXmlDto> types;

    // Конструктор, геттеры и сеттеры

    @XmlElement(name = "type")
    public List<AnimalTypeXmlDto> getTypes() {
        return types;
    }

    public void setTypes(List<AnimalTypeXmlDto> types) {
        this.types = types;
    }
}

