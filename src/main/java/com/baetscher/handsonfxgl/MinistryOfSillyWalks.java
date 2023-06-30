package com.baetscher.handsonfxgl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class MinistryOfSillyWalks extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings settings) {
        // Using 16x10 on MacBook Pro
        settings.setWidth(1280);
        settings.setHeight(800);
        settings.setTitle("Basic Game App");
    }

    @Override
    protected void initGame() {

        player = FXGL.entityBuilder()
                .at(10, 10)
                .with(new AnimationComponent())
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveRight();
            }
        }, KeyCode.RIGHT);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveLeft();
            }
        }, KeyCode.LEFT);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class AnimationComponent extends Component {
        private int speed = 0;

        private AnimatedTexture texture;
        private AnimationChannel animIdle, animWalk;

        public AnimationComponent() {

//            animIdle = new AnimationChannel(FXGL.image("newdude.png"), 4, 32, 42, Duration.seconds(1), 1, 1);
//            animWalk = new AnimationChannel(FXGL.image("newdude.png"), 4, 32, 42, Duration.seconds(1), 0, 3);

            animIdle = new AnimationChannel(FXGL.image("bluebear.png"),
                    3, 230, 196, Duration.seconds(1), 4, 4);
            animWalk = new AnimationChannel(FXGL.image("bluebear.png"),
                    3, 230, 196, Duration.seconds(1), 0, 5);

            texture = new AnimatedTexture(animIdle);
        }

        @Override
        public void onAdded() {
            entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
            entity.getViewComponent().addChild(texture);
        }

        @Override
        public void onUpdate(double tpf) {
            entity.translateX(speed * tpf);

            if (speed != 0) {

                if (texture.getAnimationChannel() == animIdle) {
                    texture.loopAnimationChannel(animWalk);
                }

                speed = (int) (speed * 0.9);

                if (FXGLMath.abs(speed) < 1) {
                    speed = 0;
                    texture.loopAnimationChannel(animIdle);
                }
            }
        }

        public void moveRight() {
            speed = 150;

            getEntity().setScaleX(1);
        }

        public void moveLeft() {
            speed = -150;

            getEntity().setScaleX(-1);
        }
    }
}
