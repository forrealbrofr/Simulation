package org.example;

/**
 * @param x vertical coordinate
 * @param y horizontal coordinate
 */
public record Coordinates(Integer x, Integer y) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!x.equals(that.x)) return false;
        return y.equals(that.y);
    }


}
