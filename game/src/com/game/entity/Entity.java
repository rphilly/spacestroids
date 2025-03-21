package com.game.entity;

import com.game.engine.math.Vector2f;
import com.game.state.Game;
import com.game.util.SpriteLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Default abstract object class to build in-game objects.
 */
public abstract class Entity {

    /**
     * Variables to store all entity object information; accessed only within residing package.
     */
    protected Vector2f position, velocity, size;
    protected double rotation;
    protected SpriteLoader sprite;
    protected Game game;

    /**
     * Toggle to draw object bounds; used primarily for collision-detection.
     */
    private final boolean isDrawingBounds;

    public Entity(Vector2f position, Vector2f velocity, Vector2f size, double rotation, String path, Game instance) {
        this.position = position;
        this.velocity = velocity;
        this.size = size;
        this.rotation = rotation;
        this.sprite = new SpriteLoader(path);
        game = instance;

        if (game != null) game.entityList.add(this);

        isDrawingBounds = false;
    }

    public abstract void update();

    public void render(Graphics2D g2d) {
        BufferedImage image = sprite.getSprite();
        AffineTransform tx = AffineTransform.getScaleInstance(size.x / image.getWidth(), size.y / image.getHeight());
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        image = op.filter(image, null);
        double locationX = image.getWidth() / 2.0;
        double locationY = image.getHeight() / 2.0;
        AffineTransform tx2 = AffineTransform.getRotateInstance(rotation, locationX, locationY);
        AffineTransformOp op2 = new AffineTransformOp(tx2, AffineTransformOp.TYPE_BILINEAR);
        g2d.drawImage(op2.filter(image, null), (int) (position.x - locationX), (int) (position.y - locationY), null);

        if (isDrawingBounds) drawBounds(g2d);
    }

    /**
     * Detect collision based on current positions; uses Pythagorean theorem to calculate distance in-between objects.
     *
     * @param entity object to detect collision with.
     * @return the minimum distance between compared objects.
     */
    protected boolean collisionDetection(Entity entity) {
        double deltaX = position.x - entity.position.x;
        double deltaY = position.y - entity.position.y;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        return distance < (size.x + Math.min(entity.size.x, entity.size.y)) / 2;
    }

    private void drawBounds(Graphics2D g2d) {
        if (isDrawingBounds) {
            Rectangle bounds = this.getBounds();
            g2d.setColor(Color.RED);
            g2d.drawRect((int) position.x - (int) bounds.getWidth() / 2, (int) position.y - (int) bounds.getHeight() / 2,
                    (int) bounds.getWidth(), (int) bounds.getHeight());
        }
    }

    public void remove() {
        game.entityList.remove(this);
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getSize() {
        return size;
    }

    public Game getGame() {
        return game;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) position.x, (int) position.y, (int) size.x, (int) size.y);
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }
}
