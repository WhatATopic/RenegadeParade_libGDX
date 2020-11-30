package com.team4.renegadeparade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team4.renegadeparade.Main;

public class MenuScreen implements Screen
{
    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Table table;
    private TextButton playButton,statsButton,settingsButton;

    public Music menuMusic;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/mainbackground.jpg")))));

        Image imageLogo = new Image();
        imageLogo.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/renegadeparade.png")))));
        imageLogo.setScaling(Scaling.fit);
        table.add(imageLogo).center().colspan(4);
        table.row();

        playButton = new TextButton("Play Match", Main.getInstance().mainButtonStyle);
        playButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                menuMusic.stop();
                Main.getInstance().setScreen(Main.getInstance().inGameScreen);
            }
        });
        statsButton = new TextButton("Statistics", Main.getInstance().mainButtonStyle);
        statsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(Main.getInstance().statsScreen);
            }
        });
        settingsButton = new TextButton("Settings", Main.getInstance().mainButtonStyle);
        settingsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(Main.getInstance().settingsScreen);
            }
        });

        table.add(statsButton);
        table.add(playButton);
        table.add(settingsButton);
        //table.debug();
        stage.addActor(table);

        if (menuMusic == null || !menuMusic.isPlaying())
        {
            menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/lounge.ogg"));
            menuMusic.setLooping(true);
            menuMusic.play();
        }
    }

    @Override
    public void render(float delta)
    {
        //Clear the background
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Draw
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
        menuMusic.pause();
    }

    @Override
    public void resume()
    {
        menuMusic.play();
    }

    @Override
    public void hide() {}

    @Override
    public void dispose()
    {
        stage.dispose();
        batch.dispose();
        menuMusic.stop();
    }
}
