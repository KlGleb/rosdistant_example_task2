package at.gleb.javaeepractic.data;

import java.util.List;

public interface AnimalsTypeGetter {
    List<AnimalTypeDto> getTypes(String name, Integer minCount);
    AnimalTypeDto getType(int id);

    void createType(String name, int approxCount);

    void editType(int id, String name, int approxCount);

    void removeType(int id);
}
