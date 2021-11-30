package entities;

import java.util.StringJoiner;

public class Piece {
    private int code;
    private double width;
    private double height;
    private int count;

    public Piece() {
    }

    public Piece(int code) {
        this.code = code;
    }

    public Piece(double width, double height, int count) {
        this.width = width;
        this.height = height;
        this.count = count;
    }

    public Piece(int code, double width, double height, int count) {
        this.code = code;
        this.width = width;
        this.height = height;
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Piece.class.getSimpleName() + "[", "]")
                .add("id=" + code)
                .add("width=" + width)
                .add("height=" + height)
                .add("count=" + count)
                .toString();
    }
}