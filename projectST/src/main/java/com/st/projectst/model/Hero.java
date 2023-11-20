package com.st.projectst.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends GameObject {
    private static final int screenHeight = 24;
    private double speedX;
    private double speedY;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;
    private int jumpCounter;

    public Hero(Position position) {
        super(position);
        speedX = 1; speedY = 0;
        remainingLives = 3;
        withKey = false;
        isJumping = false;
        jumpCounter = 0;
    }

    public void moveRight() {getPosition().setX((int) (getPosition().getX() + (1 * speedX)));}
    public void moveLeft() {getPosition().setX((int) (getPosition().getX() - (1 * speedX)));}

    public void update() {
        if (isJumping) {
            getPosition().setY(getPosition().getY()-1);
            jumpCounter++;

            if (jumpCounter >= 3) {
                isJumping = false;
                jumpCounter = 0;
            }
        } else if (getPosition().getY() < screenHeight - 1) {
            getPosition().setY(getPosition().getY()+1);
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
        }
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.ANSI.CYAN);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), " ");
    }
}