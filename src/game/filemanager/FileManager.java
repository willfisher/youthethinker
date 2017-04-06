package game.filemanager;

import java.util.ArrayList;

import game.scenes.Terminal;

public class FileManager {
	private Directory mainDir;
	
	private ArrayList<Integer> indexRoute = new ArrayList<>();
	
	public FileManager(Directory mainDir) {
		this.mainDir = mainDir;
	}
	
	public void goBack() {
		if(indexRoute.size() > 0)
			indexRoute.remove(indexRoute.size() - 1);
	}
	
	public Directory getCurrentDir() {
		Directory result = mainDir;
		for(int i = 0; i < indexRoute.size(); i++)
			result = result.getSubFolders().get(indexRoute.get(i));
		return result;
	}
	
	public String getPathName() {
		String result = "/" + mainDir.getName().toUpperCase();
		Directory currDir = mainDir;
		for(int i = 0; i < indexRoute.size(); i++) {
			currDir = currDir.getSubFolders().get(indexRoute.get(i));
			result += "/" + currDir.getName().toUpperCase();
		}
		return result;
	}
	
	public void goForward(String newDir) {
		String[] moves = newDir.split("/");
		boolean worked = true;
		int count = 0;
		for(String move : moves) {
			boolean found = false;
			ArrayList<Directory> dir = getCurrentDir().getSubFolders();
			for(int i = 0; i < dir.size(); i++) {
				if(dir.get(i).getName().equals(move)) {
					indexRoute.add(i);
					found = true;
					count++;
					break;
				}
			}
			if(!found) {
				worked = false;
				break;
			}
		}
		if(!worked) {
			for(int i = 0; i < count; i++)
				indexRoute.remove(indexRoute.size() - 1);
			Terminal.printOut("CD: DIRECTORY " + newDir.toUpperCase() + " DOES NOT EXIST");
		}
	}
	
	public void list() {
		String resultDir = "";
		if(getCurrentDir().getSubFolders().size() > 0) {
			for(Directory dir : getCurrentDir().getSubFolders())
				resultDir += dir.getName() + " ";
			Terminal.printOut(resultDir);
		}
		String resultFile = "";
		if(getCurrentDir().getFiles().size() > 0) {
			for(TFile file : getCurrentDir().getFiles())
				resultFile += file.getName() + " ";
			Terminal.printOut(resultFile);
		}
	}
	
	public boolean callCommand(String command) {
		for(TFile file : getCurrentDir().getFiles()) {
			if(file.getActiveCommand().equals(command)) {
				file.call();
				return true;
			}
		}
		return false;
	}
}
