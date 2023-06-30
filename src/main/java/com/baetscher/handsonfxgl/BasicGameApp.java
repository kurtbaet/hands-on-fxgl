package com.baetscher.handsonfxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BasicGameApp extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        // Using 16x10 on MacBook Pro
        settings.setWidth(1280);
        settings.setHeight(800);
        settings.setTitle("Basic Game App");
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new SimpleFactory());
        FXGL.spawn("enemy");

        FXGL.entityBuilder()
                .at(150, 150)
                .view(new Rectangle(40, 40, Color.BLUE))
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
