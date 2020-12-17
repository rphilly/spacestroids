package com.game.state;

import com.game.entity.player.Player;
import com.game.util.Mouse;
import com.game.view.Panel;

import java.awt.Graphics2D;

public class Game extends State {

    Player player;

    int x = 50;
    int y = 50;

    public Game(Panel panel) {
        super(panel);

        //player = new Player(new Sprite("entity/player/player.png"), new Vector2f(10, 10), 50);
        player = new Player();
    }

    @Override
    public void render(Graphics2D g) {
        player.draw(g);
    }

    public void input(Mouse mouse) {

    }

    @Override
    public void update() {

    }
}
