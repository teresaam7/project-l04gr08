package com.st.projectst;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Position p = (Position) other;
        return x == p.getX() && y == p.getY();
    }
}
