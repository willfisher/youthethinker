package game.scenes;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.retrochicken.engine.GameContainer;
import com.retrochicken.engine.Input;
import com.retrochicken.engine.Renderer;
import com.retrochicken.engine.fx.Font;
import com.retrochicken.engine.fx.Settings;
import com.retrochicken.engine.scenes.Scene;
import com.retrochicken.engine.ui.PlainText;
import com.retrochicken.engine.ui.ScrollPanel;
import com.retrochicken.engine.ui.TextField;

import game.commands.Command;
import game.commands.CommandManager;

public class Terminal implements Scene {
	
	private String terminal_dir = "will@greatideas:/home$".toUpperCase();
	
	public static ScrollPanel TERMINAL;
	public static void printOut(String text) {
		TERMINAL.addElement(new PlainText(Font.STANDARD, "", Settings.TERMINAL_COLOR, text, 0xffffffff, 0, 0));
		resetInput();
	}
	private static void resetInput() {
		input.setY(Math.min(TERMINAL.getBottom(), INPUT_MAX_Y));
	}
	public static float INPUT_MAX_Y;
	public static TextField input;
	
	private CommandManager manager = new CommandManager();
	
	public Terminal(GameContainer gc) {
		INPUT_MAX_Y = gc.getHeight() - 50 + 5;
		TERMINAL = new ScrollPanel(0, 5, gc.getWidth() - 7, gc.getHeight() - 50);
		input = new TextField(0, TERMINAL.getBottom() + Font.STANDARD.getHeight("@") - 5, terminal_dir, Font.STANDARD, new Rectangle(0, 0, gc.getWidth(), 0));
		
		manager.addCommand(new Command("clear") {
			public void call(String[] args) {
				TERMINAL = new ScrollPanel(0, 0, gc.getWidth(), gc.getHeight() - 50);
				resetInput();
			}
		});
	}
	
	@Override
	public void update(float timePassed) {
		TERMINAL.update();
		input.update();
		if(Input.isKeyPressed(KeyEvent.VK_ENTER)) {
			String command = input.getText();
			TERMINAL.addElement(new PlainText(Font.STANDARD, terminal_dir, Settings.TERMINAL_COLOR, " " + command, 0xffffffff, 0, 0));
			resetInput();
			input.setText("");
			manager.callCommand(command.toLowerCase());
		}
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.flushMaps();
		TERMINAL.render(renderer);
		input.render(renderer);
	}

	@Override
	public void reset(GameContainer gc) {
		
	}
}
