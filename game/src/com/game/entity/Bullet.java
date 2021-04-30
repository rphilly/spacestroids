package com.game.entity;

import com.game.state.Game;
import com.game.util.Vector2f;

public class Bullet extends Entity {

    public Bullet(Vector2f position, double rotation, Game instance) {
        super(position, new Vector2f(10, 10), new Vector2f(20, 10),
                rotation - Math.PI / 2, "entity/player/bullet_pink.png", instance);
    }

    @Override
    public void update() {
        position.x += velocity.x * Math.cos(rotation);
        position.y += velocity.y * Math.sin(rotation);
    }

    @Override
    public void remove() {
        super.remove();
        game.bulletList.remove(this);
    }
}
