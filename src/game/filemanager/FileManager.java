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
			result = result.getSubFolders().get(i);
		return result;
	}
	
	public String getPathName() {
		String result = "/" + mainDir.getName().toUpperCase();
		Directory currDir = mainDir;
		for(int i = 0; i < indexRoute.size(); i++) {
			currDir = currDir.getSubFolders().get(i);
			result += "/" + currDir.getName().toUpperCase();
		}
		return result;
	}
	
	public void goForward(String newDir) {
		ArrayList<Directory> dir = getCurrentDir().getSubFolders();
		for(int i = 0; i < dir.size(); i++) {
			if(dir.get(i).getName().equals(newDir)) {
				indexRoute.add(i);
				return;
			}
		}
		Terminal.printOut("CD: DIRECTORY " + newDir.toUpperCase() + " DOES NOT EXIST");
	}
}
