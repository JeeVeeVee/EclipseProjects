package domain;

public class CleanState extends FileState {
	
	public CleanState(FileEditor fileEditor) {
		super(fileEditor);
		// TODO Auto-generated constructor stub
	}

	public boolean save() {
		System.out.println("file was already up to date");
		return false;
	}

	public boolean edit() {
		System.out.println("a clean file was editted, changes were not saved (yet)");
		fileEditor.toState(new DirtyState(fileEditor));
		return true;
	}
	
	
}
