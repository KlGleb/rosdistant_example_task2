package at.gleb.javaeepractic.data;

import java.util.List;

public interface AnimalsTypeGetter {
    List<AnimalTypeDto> getTypes(String name, Integer minCount);
    AnimalTypeDto getType(int id);

    void createType(String name, int typeId, int approxCount);

    void editType(int id, String name, int typeId);

    void removeType(int id);
}