package domain;

import java.io.File;

public class FileEditor {

    private final File file;
    private FileState state = new CleanState(this);

    public FileEditor(File file) {
        this.file = file;
    }
    
    public boolean edit() {
    	return state.edit();
    }
    
    public boolean save() {
    	return state.save();
    }
    
    public void toState(FileState newState) {
    	this.state = newState;
    }

}
