package com.baetscher.handsonfxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;


public class SimplePhysics extends GameApplication {

    private enum Type {
        PLAYER, ENEMY
    }

    protected void initSettings(GameSettings settings) {
        // Using 16x10 on MacBook Pro
        settings.setWidth(1280);
        settings.setHeight(800);
        settings.setTitle("Basic Game App");
    }

    @Override
    protected void initGame() {
        FXGL.entityBuilder()
                .type(Type.PLAYER)
                .at(100, 100)
                // 1. define hit boxes manually
                .bbox(new HitBox(BoundingShape.box(40, 40)))
                .view(new Rectangle(40, 40, Color.BLUE))
                // 2. make it collidable
                .collidable()
                // Note: in case you are copy-pasting, this class is in dev.DeveloperWASDControl
                // and enables WASD movement for testing
                // .with(new DeveloperWASDControl())
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(Type.ENEMY)
                .at(200, 100)
                // 1. OR let the view generate it from view data
                .viewWithBBox(new Rectangle(40, 40, Color.RED))
                // 2. make it collidable
                .collidable()
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(Type.ENEMY)
                .at(400, 140)
                // 1. OR let the view generate it from view data
                .viewWithBBox(new Circle(20,  Color.DARKMAGENTA))
                // 2. make it collidable
                .collidable()
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(Type.ENEMY)
                .at(600, 240)
                // 1. OR let the view generate it from view data
                .viewWithBBox(new Sphere(20))
                // 2. make it collidable
                .collidable()
                .buildAndAttach();
    }

    @Override
    protected void initPhysics() {

        // the order of entities is determined by
        // the order of their types passed into this method
        FXGL.onCollision(Type.PLAYER, Type.ENEMY, (player, enemy) -> {
            System.out.println("On Collision");
            enemy.removeFromWorld();
        });

    }

    @Override
    protected void initInput() {

        FXGL.onKey(KeyCode.UP, "UP", () ->
                FXGL.getGameWorld().getSingleton(Type.PLAYER)
                        .translateY(-2));

        FXGL.onKey(KeyCode.DOWN, "DOWN", () ->
                FXGL.getGameWorld().getSingleton(Type.PLAYER)
                        .translateY(2));

        FXGL.onKey(KeyCode.LEFT, "LEFT", () ->
                FXGL.getGameWorld().getSingleton(Type.PLAYER)
                        .translateX(-2));

        FXGL.onKey(KeyCode.RIGHT, "RIGHT", () ->
                FXGL.getGameWorld().getSingleton(Type.PLAYER).
                        translateX(2));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
