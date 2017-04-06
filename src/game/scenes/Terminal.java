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
import game.filemanager.Directory;
import game.filemanager.FileManager;
import game.filemanager.TFile;

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
	private FileManager fileManager;
	
	public Terminal(GameContainer gc) {
		INPUT_MAX_Y = gc.getHeight() - 50 + 5;
		TERMINAL = new ScrollPanel(0, 5, gc.getWidth() - 10, gc.getHeight() - 50);
		input = new TextField(0, TERMINAL.getBottom() + Font.STANDARD.getHeight("@") - 5, terminal_dir, Font.STANDARD, new Rectangle(0, 0, gc.getWidth(), 0));
		printOut("type help to see a list of available commands");
		
		manager.addCommand(new Command("clear") {
			public void call(String[] args) {
				TERMINAL = new ScrollPanel(0, 5, gc.getWidth() - 10, gc.getHeight() - 50);
			}
		});
		manager.addCommand(new Command("cd") {
			public void call(String[] args) {
				if(args.length != 1) {
					printOut("CD: Invalid arguments".toUpperCase());
					return;
				}
				if(args[0].equals("..")) {
					fileManager.goBack();
					terminal_dir = "will@greatideas:" + fileManager.getPathName() + "$";
					input.setName("will@greatideas:" + fileManager.getPathName() + "$");
				} else {
					fileManager.goForward(args[0].toUpperCase());
					terminal_dir = "will@greatideas:" + fileManager.getPathName() + "$";
					input.setName("will@greatideas:" + fileManager.getPathName() + "$");
				}
			}
		});
		manager.addCommand(new Command("ls") {
			public void call(String[] args) {
				if(args.length > 0) {
					printOut("LS: Invalid arguments".toUpperCase());
					return;
				}
				fileManager.list();
			}
		});
		manager.addCommand(new Command("help") {
			public void call(String[] args) {
				if(args.length > 0) {
					printOut("Help: Invalid arguments".toUpperCase());
					return;
				}
				printOut("help                      : Lists available commands");
				printOut("cd       <directory>   : Changes current directory to <directory>");
				printOut("ls                         : Lists contents of current directory");
				printOut("clear                     : Clears current terminal session");
				printOut("cat      <file name>  : Prints contents of <file name>");
			}
		});
		
		//Initialize directories
		Directory mainDir = new Directory("home");
		Directory will = new Directory("will");
		Directory documents = new Directory("documents");
		Directory downloads = new Directory("downloads");
		Directory trash = new Directory("trash");
		Directory yttDir = new Directory("informationaboutmeasathinker");
		documents.addDir(yttDir);
		will.addDir(documents);
		will.addDir(downloads);
		mainDir.addDir(will);
		mainDir.addDir(trash);
		fileManager = new FileManager(mainDir);
		
		//Initialize files inside trash
		TFile trashReadme = new TFile("README.txt", new String[]{"i didn't have time to implement a good trash system", "but if i did, this stuff would go there"}, "cat readme.txt");
		trash.addFile(trashReadme);
		trash.addFile(new TFile("religion.txt", new String[]{""}, "cat religion.txt"));
		trash.addFile(new TFile("literary-analysis", new String[]{""}, ""));
		
		//Initialize files inside Downloads and Documents
		downloads.addFile(new TFile("elliptic-curve-cryptography.pdf", new String[]{""}, ""));
		downloads.addFile(new TFile("rudin-principles-of-mathematical-analysis.pdf", new String[]{""}, ""));
		downloads.addFile(new TFile("hp-y-la-piedra-filosofal.pdf", new String[]{""}, ""));
		documents.addFile(new TFile("classranking.txt", new String[]{"1. Number theory", "2. Multi", "3. Spanish", "4. whap", "5. great ideas", "6. english", "7. chemistry",
				"", "i prefer logic over emotion...", "although there is some workload bais", "how else would english be so high?"}, "cat classranking.txt"));
		
		//Initialize files inside IAMAAT
		yttDir.addFile(new TFile("generalstance.dat", new String[]{"religious status: atheist", "moral position: relativist", "right vs. left brain: left"}, "cat generalstance.dat"));
		yttDir.addFile(new TFile("hobbies.txt", new String[]{"doing math", "ctfs", "programming", "learning spanish", "driving around"}, "cat hobbies.txt"));
		yttDir.addFile(new TFile("somegoodmovies.txt", new String[]{"i don't watch movies, and i didn't make a rename command", "so here are some good tv shows",
				"-Dexter", "-walking dead", "-parks and rec", "-blue mountain state", "movie keywords: long, comedy, thriller"}, "cat somegoodmovies.txt"));
		yttDir.addFile(new TFile("personalitytype.dat", new String[]{"entp"}, "cat personalitytype.dat"));
	}
	
	@Override
	public void update(float timePassed) {
		TERMINAL.update();
		input.update();
		if(Input.isKeyPressed(KeyEvent.VK_ENTER)) {
			String command = input.getText();
			TERMINAL.addElement(new PlainText(Font.STANDARD, terminal_dir, Settings.TERMINAL_COLOR, " " + command, 0xffffffff, 0, 0));
			input.setText("");
			if(!command.equals(""))
				if(!manager.callCommand(command.toLowerCase()))
					if(!fileManager.callCommand(command.toUpperCase()))
						printOut("BASH: UNRECOGNIZED COMMAND");
			resetInput();
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
