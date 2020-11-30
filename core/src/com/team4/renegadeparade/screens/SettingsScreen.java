package com.team4.renegadeparade.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team4.renegadeparade.Main;

public class SettingsScreen implements Screen
{
    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private TextButton backButton;

    @Override
    public void show()
    {
        batch = new SpriteBatch();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport, batch);
        Gdx.input.setInputProcessor(stage);

        backButton = new TextButton("Back", Main.getInstance().mainButtonStyle);
        backButton.setBounds(0, Gdx.graphics.getHeight() * 0.9f, Gdx.graphics.getWidth()/5, Gdx.graphics.getHeight()/8);
        backButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Main.getInstance().setScreen(Main.getInstance().menuScreen);
            }
        });
        stage.addActor(backButton);
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
        stage.dispose();
        batch.dispose();
    }
}
