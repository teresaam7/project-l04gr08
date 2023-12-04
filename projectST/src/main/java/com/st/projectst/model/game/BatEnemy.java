package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Enemy;

public class BatEnemy extends Enemy {
    public BatEnemy(Position position) {
        super(position);
    }
    @Override
    public void move() {
    }
    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.ANSI.GREEN_BRIGHT);
        graphics.putString(new TerminalPosition((int) getPosition().getX(), (int) getPosition().getY()), "B");
    }
}