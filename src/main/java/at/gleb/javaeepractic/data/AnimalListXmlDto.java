package at.gleb.javaeepractic.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "animalList")
public class AnimalListXmlDto {
    private List<AnimaXmlDto> animals;

    // Конструктор, геттеры и сеттеры

    @XmlElement(name = "animal")
    public List<AnimaXmlDto> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimaXmlDto> animals) {
        this.animals = animals;
    }
}

