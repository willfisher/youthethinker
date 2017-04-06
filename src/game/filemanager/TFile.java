package game.filemanager;

import game.scenes.Terminal;

public class TFile {
	private String name;
	
	private String[] contents;
	
	private String activeCommand;
	
	public TFile(String name, String[] contents, String activeCommand) {
		this.name = name.toUpperCase();
		this.contents = contents;
		for(int i = 0; i < this.contents.length; i++)
			this.contents[i] = this.contents[i].toUpperCase();
		this.activeCommand = activeCommand.toUpperCase();
	}
	
	public String getName() {
		return name;
	}
	
	public void call() {
		for(String str : contents)
			Terminal.printOut(str);
	}
	
	public String getActiveCommand() {
		return activeCommand;
	}
}
