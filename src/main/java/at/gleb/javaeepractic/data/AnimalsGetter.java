package at.gleb.javaeepractic.data;

import java.util.List;

public interface AnimalsGetter {
    List<AnimalDto> getAnimals(String name, Integer typeId);
    AnimalDto getAnimal(int id);

    void createAnimal(String name, int typeId);

    void editAnimal(int id, String name, int typeId);

    void removeAnimal(int id);
}
