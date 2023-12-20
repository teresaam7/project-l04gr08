package com.st.projectst.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.Direction;
import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Platform extends Wall {
    private List<Wall> connectedPlatforms;
    private int moveCounter;
    private boolean movingUp;
    public Platform(Position position) {
        super(position);

        this.connectedPlatforms = new ArrayList<>();
        this.moveCounter = 0;
        this.movingUp = true;
    }
    public boolean isOnSameLevel(Wall otherPlatform) {
        return getPosition().getY() == otherPlatform.getPosition().getY();
    }
    public void addConnectedPlatform(Wall wall) {
        connectedPlatforms.add(wall);
    }
    public void moveAllPlatforms() {
        int speed = 16;

        if (movingUp) {
            setPosition(new Position(getPosition().getX(), getPosition().getY() - 1));
            if (moveCounter == speed) {
                movingUp = false;
                moveCounter = 0;
            }
        } else {
            setPosition(new Position(getPosition().getX(), getPosition().getY() + 1));
            if (moveCounter == speed) {
                movingUp = true;
                moveCounter = 0;
            }
        }
        moveCounter++;
    }


    public List<Wall> getConnectedPlatforms() {
        return connectedPlatforms;
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public int getMoveCounter() {
        return moveCounter;
    }

    public void setConnectedPlatforms(List<Wall> connectedPlatforms) {
        this.connectedPlatforms = connectedPlatforms;
    }
}
