package usecases;

import editor.model.EditModel;
import general.Id;

public class DeleteObjectCase {
	public static void main(String[] args){
		editor.externalAPI.EditorModel editorModel = new EditModel();
		
		// A user selects an object in editorView, will use a default Id as selected object
		Id id = new Id();
		editorModel.request(id);
		editorModel.delete(id);
		// EditorView will then delete the id from its own collection of entities
	}
}
