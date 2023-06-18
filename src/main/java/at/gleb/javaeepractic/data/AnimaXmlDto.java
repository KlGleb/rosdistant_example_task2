package at.gleb.javaeepractic.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "animal")
public class AnimaXmlDto {
    private int id;
    private String name;
    private int typeId;

    // Конструктор, геттеры и сеттеры

    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public int getTypeId() {
        return typeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
