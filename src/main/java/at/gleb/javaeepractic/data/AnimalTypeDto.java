package at.gleb.javaeepractic.data;

import java.util.Objects;

public class AnimalTypeDto {
    private final int id;
    private final String name;

    private final int approxCount;

    public AnimalTypeDto(int id, String name, int approxCount) {
        this.id = id;
        this.name = name;
        this.approxCount = approxCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getApproxCount() {
        return approxCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalTypeDto that = (AnimalTypeDto) o;
        return id == that.id && approxCount == that.approxCount && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, approxCount);
    }
}
