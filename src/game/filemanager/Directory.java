package game.filemanager;

import java.util.ArrayList;

public class Directory {
	private String name;
	
	private ArrayList<Directory> directories = new ArrayList<>();
	private ArrayList<TFile> files = new ArrayList<>();
	
	public Directory(String name) {
		this.name = name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Directory> getSubFolders() {
		return directories;
	}
	
	public void addDir(Directory directory) {
		directories.add(directory);
	}
	
	public void addFile(TFile file) {
		files.add(file);
	}
	
	public ArrayList<TFile> getFiles() {
		return files;
	}
}
