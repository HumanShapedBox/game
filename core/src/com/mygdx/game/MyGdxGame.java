package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.IWantToPlayAGame.unt.Game;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background, crossbow, warlock, monk, feeder, thug, archer, knight;
	Music music;
	Game game;
	
	@Override
	public void create () {
		game = new Game();
		game.start();
		batch = new SpriteBatch();
		background = new Texture("background/"+Background.values()[new Random().nextInt(Background.values().length)] + ".jpg");
		music = Gdx.audio.newMusic(Gdx.files.internal("meow.mp3"));
		music.setLooping(true);
		music.setVolume(0.125f);
		music.play();
		crossbow = new Texture("units/CrossBowMan.png");
		warlock = new Texture("units/Mage.png");
		monk = new Texture("units/Monk.png");
		feeder = new Texture("units/Peasant.png");
		thug = new Texture("units/Rouge.png");
		archer = new Texture("units/Sniper.png");
		knight = new Texture("units/SpearMan.png");
	}

	@Override
	public void render () {
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isTouched()){
			//game.unitSort();
			game.nextMove();
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		for (int i=game.teamSize-1; i>=0; i--){
			batch.setColor(1, 1, 1, 1);
			if (game.good.get(i).state.equals("Dead")) batch.setColor(Color.RED);
			int x = game.good.get(i).position.x * Gdx.graphics.getWidth()/12;
			int y = (game.good.get(i).position.y-1) * Gdx.graphics.getHeight()/12;
			switch (game.good.get(i).getInfo()){
				case "Лучник ":
					batch.draw(archer, x, y);
					break;
				case "Монах ":
					batch.draw(monk, x, y);
					break;
				case "Крестьянин ":
					batch.draw(feeder, x, y);
					break;
				case "Рыцарь ":
					batch.draw(knight, x, y);
			}
			batch.setColor(1, 1, 1, 1);
			if (game.bad.get(i).state.equals("Dead")) batch.setColor(Color.RED);
			x = game.bad.get(i).position.x * Gdx.graphics.getWidth()/12;
			y = (game.bad.get(i).position.y-1) * Gdx.graphics.getHeight()/12;
			switch (game.bad.get(i).getInfo()){
				case "Арбалетчик ":
					Sprite sprite = new Sprite(crossbow);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Чародей ":
					sprite = new Sprite(warlock);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Крестьянин ":
					sprite = new Sprite(feeder);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
				case "Бандит ":
					sprite = new Sprite(thug);
					sprite.setPosition(x, y);
					sprite.flip(true, false);
					sprite.draw(batch);
					break;
			}

		}
		batch.setColor(1, 1, 1, 1);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
