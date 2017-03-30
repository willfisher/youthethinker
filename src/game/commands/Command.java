package game.commands;

public class Command {
	private String commandName;
	
	public String getCommandName() {
		return commandName;
	}
	
	public Command(String commandName) {
		this.commandName = commandName;
	}
	
	public void call(String[] args) {
		
	}
}
