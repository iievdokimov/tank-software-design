package ru.mipt.bit.platformer.visuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.logics.models.GameObject;
import ru.mipt.bit.platformer.logics.models.Livable;
import ru.mipt.bit.platformer.util.GdxGameUtils;
import ru.mipt.bit.platformer.util.TileMovement;

public class VisualObjectHealthDecorator implements VisualObject {

    private final VisualObject wrappee;
    private HealthBarSettings healthBarSettings;

    public VisualObjectHealthDecorator(VisualObject visualObject, HealthBarSettings healthBarSettings){
        wrappee = visualObject;
        this.healthBarSettings = healthBarSettings;
    }


    private void renderHealthbar(Batch batch) {
        if (wrappee.getLogicalEntity() instanceof Livable livable && healthBarSettings.isOn()) {
            var health = livable.getRelativeHealth();
            var healthbarTexture = getHealthbarTexture(health);
            var rectangle = createRectangle();
            GdxGameUtils.drawTextureRegionUnscaled(batch, healthbarTexture, rectangle, 0f);
        }
    }

    private TextureRegion getHealthbarTexture(float relativeHealth) {
        var pixmap = new Pixmap(90, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, 90, 20);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (90 * relativeHealth), 20);
        var texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    private Rectangle createRectangle() {
        var rectangle = new Rectangle(wrappee.getRectangle());
        rectangle.y += 90;
        return rectangle;
    }

    @Override
    public Rectangle getRectangle() {
        return wrappee.getRectangle();
    }

    @Override
    public void draw(Batch batch) {
        renderHealthbar(batch);
        wrappee.draw(batch);
    }

    @Override
    public void dispose() {
        wrappee.dispose();
    }

    @Override
    public void processMotion(TileMovement tileMovement) {
        wrappee.processMotion(tileMovement);
    }

    @Override
    public GameObject getLogicalEntity() {
        return wrappee.getLogicalEntity();
    }
}
