package domain;

public class DirtyState extends FileState{

	public DirtyState(FileEditor fileEditor) {
		super(fileEditor);
	}

	@Override
	public boolean save() {
		System.out.println("new changes were saved");
		fileEditor.toState(new CleanState(fileEditor));
		return true;
	}

	@Override
	public boolean edit() {
		System.out.println("new changes where added to an already unsaved file");
		return false;
	}

}
