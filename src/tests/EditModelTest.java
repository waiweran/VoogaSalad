package tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import editor.model.EditModel;
import general.entities.GameEntity;
import general.storage.ObservableList;

public class EditModelTest {
	private File file;
	private EditModel editModel;
	
	@Before
	public void setUp() throws IOException {
		file = new File("src/games/TestGame.xml");
	}

//	@Test
//	public void SaveToNewFileTest() throws FileNotFoundException, Exception {
//		editModel = new EditModel();
//		editModel.createDefaultInstance();
//		editModel.saveGameState(file);
//		assertTrue("Saving game state properly", 0 < file.getUsableSpace());
//	}
//	
//	@Test
//	public void LoadFromTestFile(){
//		editModel = new EditModel(file);
//		ObservableList<GameEntity> temp = editModel.getEntities();
//		assertTrue("Number of instances", 0 < temp.size());
//	}

}
