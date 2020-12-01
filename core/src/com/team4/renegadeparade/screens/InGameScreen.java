package com.team4.renegadeparade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team4.renegadeparade.Main;

public class InGameScreen implements Screen
{
    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera gameCamera;
    private OrthographicCamera interfaceCamera;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private Texture blockTexture;
    private Texture backgroundTexture;
    private Sprite blockSprite;
    private Sprite backgroundSprite;
    private TextButton disconnectButton,shootButton,useButton,reloadButton;
    private float speed;
    private float centerX;
    private float centerY;

    @Override
    public void show()
    {
        centerX = Gdx.graphics.getWidth()/2;
        centerY = Gdx.graphics.getHeight()/2;

        batch = new SpriteBatch();
        gameCamera = new OrthographicCamera();
        interfaceCamera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), interfaceCamera);
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);
        //Create a touchpad skin
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("InGameScreen/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("InGameScreen/touchKnob.png"));
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Scale the knob
        touchpadStyle.knob.setMinHeight(Gdx.graphics.getHeight()/11);
        touchpadStyle.knob.setMinWidth(Gdx.graphics.getWidth()/17.5f);
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(Gdx.graphics.getWidth()/12, Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/6.7f, Gdx.graphics.getHeight()/4);

        disconnectButton = new TextButton("Disconnect", Main.getInstance().mainButtonStyle);
        disconnectButton.setBounds(0, Gdx.graphics.getHeight() * 0.9f, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/8);
        disconnectButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(Main.getInstance().menuScreen);
            }
        });

        shootButton = new TextButton("Shoot", Main.getInstance().inGameButtonStyle);
        shootButton.setBounds(Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/5.5f);
        shootButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                shoot();
            }
        });

        useButton = new TextButton("Use", Main.getInstance().inGameButtonStyle);
        useButton.setBounds(Gdx.graphics.getWidth() * 0.85f, Gdx.graphics.getHeight()/3.3f, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/5.5f);
        useButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                use();
            }
        });

        reloadButton = new TextButton("Reload", Main.getInstance().inGameButtonStyle);
        reloadButton.setBounds(Gdx.graphics.getWidth() * 0.71f, Gdx.graphics.getHeight()/10, Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/5.5f);
        reloadButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                reload();
            }
        });

        //Add actors to stage
        stage.addActor(touchpad);
        stage.addActor(disconnectButton);
        stage.addActor(shootButton);
        stage.addActor(useButton);
        stage.addActor(reloadButton);

        //Create block sprite
        blockTexture = new Texture(Gdx.files.internal("InGameScreen/block.png"));
        blockSprite = new Sprite(blockTexture);

        //Create background sprite
        backgroundTexture = new Texture(Gdx.files.internal("InGameScreen/background.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.scale(10);
        backgroundSprite.setOrigin(0,0);
        //Set position to center of the screen
        blockSprite.setPosition(centerX-blockSprite.getWidth()/2, centerY-blockSprite.getHeight()/2);
        gameCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        speed = 4f;
    }

    @Override
    public void render(float delta)
    {
        //Clear the background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Touchpad delta values
        float deltaX = touchpad.getKnobPercentX() * speed;
        float deltaY = touchpad.getKnobPercentY() * speed;
        float originX = backgroundSprite.getOriginX();
        float originY = backgroundSprite.getOriginY();
        //Move game cameras with TouchPad
        gameCamera.translate(deltaX,deltaY);
        gameCamera.update();
        System.out.println("Current position X:" + gameCamera.position.x + " Y:" + gameCamera.position.y + " Z:" + gameCamera.position.z);

        //Draw
        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        backgroundSprite.draw(batch);
        batch.end();
        batch.setProjectionMatrix(interfaceCamera.combined);
        batch.begin();
        blockSprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
        stage.dispose();
    }

    private void shoot() {}
    private void use() {}
    private void reload() {}
}
