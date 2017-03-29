package game.scenes;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.retrochicken.engine.GameContainer;
import com.retrochicken.engine.Input;
import com.retrochicken.engine.Renderer;
import com.retrochicken.engine.fx.Font;
import com.retrochicken.engine.scenes.Scene;
import com.retrochicken.engine.ui.PlainText;
import com.retrochicken.engine.ui.ScrollPanel;
import com.retrochicken.engine.ui.TextField;

public class Terminal implements Scene {
	
	private ScrollPanel terminal;
	private TextField input;
	
	public Terminal(GameContainer gc) {
		terminal = new ScrollPanel(0, 0, gc.getWidth(), gc.getHeight() - 50);
		input = new TextField(0, terminal.getBottom(), "will@greatideas:~$", Font.STANDARD, new Rectangle(0, 0, gc.getWidth(), 0));
	}
	
	@Override
	public void update(float timePassed) {
		terminal.update();
		if(Input.isKeyPressed(KeyEvent.VK_ENTER)) {
			String command = input.getText();
			String name = input.getName();
			terminal.addElement(new PlainText(Font.STANDARD, name + " " + command, 0xffffffff, 0, 0));
			input.setY(terminal.getBottom());
			input.setText("");
		}
	}

	@Override
	public void render(GameContainer gc, Renderer renderer) {
		renderer.flushMaps();
		terminal.render(renderer);
		input.render(renderer);
	}

	@Override
	public void reset(GameContainer gc) {
		
	}
}
