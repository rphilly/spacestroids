package com.game.state;

import com.game.world.entity.Player;
import com.game.util.Mouse;
import com.game.util.Sprite;
import com.game.util.Vector2f;
import com.game.engine.view.Panel;

import java.awt.*;

public class Game extends State {

    Player player;

    public Game(Panel panel) {
        super(panel);

        player = new Player(new Sprite("entity/player/player4.png"), new Vector2f(300, 300));
    }

    @Override
    public void render(Graphics2D g) {
        player.render(g);
    }

    public void input(Mouse mouse) {
        player.input(mouse);
    }

    @Override
    public void update() {

    }
}
