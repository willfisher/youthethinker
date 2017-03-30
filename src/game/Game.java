package game;

import com.retrochicken.engine.AbstractGame;
import com.retrochicken.engine.GameContainer;
import com.retrochicken.engine.Renderer;
import com.retrochicken.engine.scenes.SceneManager;

import game.scenes.Terminal;

public class Game extends AbstractGame {
	
	private SceneManager manager;
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer();
		gc.setClearScreen(true);
		gc.setDynamicLights(true);
		gc.setWidth((int)(340 * 1.5));
		gc.setHeight((int)(220 * 1.25));
		gc.setGame(new Game(gc));
		gc.start();
	}
	
	public Game(GameContainer gc) {
		manager = new SceneManager(gc);
		manager.addScene(new Terminal(gc));
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		manager.update(dt);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		manager.render(gc, r);
	}
}
