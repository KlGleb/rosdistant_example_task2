package at.gleb.javaeepractic.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "type")
public class AnimalTypeXmlDto {
    private int id;
    private String name;
    private int approximateCount;


    public void setApproximateCount(int approximateCount) {
        this.approximateCount = approximateCount;
    }


    @XmlElement
    public int getId() {
        return id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public int getApproximateCount() {
        return approximateCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
