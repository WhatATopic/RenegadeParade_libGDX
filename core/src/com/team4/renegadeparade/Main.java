package com.team4.renegadeparade;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.team4.renegadeparade.screens.InGameScreen;
import com.team4.renegadeparade.screens.MenuScreen;
import com.team4.renegadeparade.screens.SettingsScreen;
import com.team4.renegadeparade.screens.StatisticsScreen;

public class Main extends Game
{
	private static Main instance;
	public Screen menuScreen;
	public Screen inGameScreen;
	public Screen statsScreen;
	public Screen settingsScreen;
	public TextButton.TextButtonStyle mainButtonStyle;
	public TextButton.TextButtonStyle inGameButtonStyle;
	BitmapFont blackFont;
	@Override
	public void create ()
	{
		instance = this;
		menuScreen = new MenuScreen();
		inGameScreen = new InGameScreen();
		statsScreen = new StatisticsScreen();
		settingsScreen = new SettingsScreen();

		blackFont = new BitmapFont(Gdx.files.internal("fonts/blackFont.fnt"), false);
		mainButtonStyle = new TextButton.TextButtonStyle();
		mainButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/button_up.png"))));
		mainButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/button_down.png"))));
		mainButtonStyle.pressedOffsetX = 1;
		mainButtonStyle.pressedOffsetY = -1;
		mainButtonStyle.font = blackFont;


		inGameButtonStyle = new TextButton.TextButtonStyle();
		inGameButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("InGameScreen/button_up.png"))));
		inGameButtonStyle.down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("InGameScreen/button_down.png"))));
		inGameButtonStyle.pressedOffsetX = 1;
		inGameButtonStyle.pressedOffsetY = -1;
		inGameButtonStyle.font = blackFont;


		this.setScreen(menuScreen);
	}

	@Override
	public void render ()
	{
		super.render();
	}
	
	@Override
	public void dispose ()
	{
		super.dispose();
	}

	public static Main getInstance()
	{
		return instance;
	}
}
