package at.gleb.javaeepractic.data;

import java.util.Objects;

public class AnimalDto {
    private final int id;
    private final String name;
    private final int typeId;

    public AnimalDto(int id, String name, int typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTypeId() {
        return typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalDto animalDto = (AnimalDto) o;
        return id == animalDto.id && typeId == animalDto.typeId && Objects.equals(name, animalDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, typeId);
    }
}
