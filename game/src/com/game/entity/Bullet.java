package com.game.entity;

import com.game.state.Game;
import com.game.util.MouseHandler;
import com.game.util.Vector2f;

public class Bullet extends Entity {

    public Bullet(Vector2f position, Vector2f size, double rotation, Game instance) {
        super(position, new Vector2f(10, 10), size, rotation - Math.PI / 2, "entity/player/bullet_pink.png", instance);
    }

    @Override
    public void input(MouseHandler e) { }

    @Override
    public void update() {
        position.x += velocity.x * Math.cos(rotation);
        position.y += velocity.y * Math.sin(rotation);
    }

    @Override
    void remove() {
        super.remove();
        game.bulletList.remove(this);
    }
}
