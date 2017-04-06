package game.commands;

import java.util.ArrayList;


public class CommandManager {
	
	private ArrayList<Command> commands = new ArrayList<>();
	
	public void addCommand(Command command) {
		commands.add(command);
	}
	
	public boolean callCommand(String input) {
		String[] data = input.split(" ");
		String[] args = new String[data.length - 1];
		boolean called = false;
		if(data.length > 1)
			for(int i = 1; i < data.length; i++)
				args[i - 1] = data[i];
		for(Command command : commands)
			if(command.getCommandName().equals(data[0])) {
				command.call(args);
				called = true;
			}
		
		return called;
	}
}
