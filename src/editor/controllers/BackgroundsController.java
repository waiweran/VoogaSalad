package editor.controllers;

import java.io.File;
import java.util.ArrayList;

import editor.externalAPI.EditorModel;
import editor.view.ImageChooser;
import editor.view.background.BackgroundPopup;
import editor.view.background.BackgroundView;
import general.Vector;
import general.gameobjects.Background;
import general.resourceEngine.FileType;
import general.resourceEngine.MultiEngine;
import general.resourceEngine.ResourceEngine;

/**
 * Controls background selection.
 * @author Nathaniel Brooke
 * @version 04-25-2017
 */
public class BackgroundsController {
	
	private EditorModel model;
	private BackgroundPopup view;
	private Vector startingOffset;

	/**
	 * Initializes the Background controller.
	 * @param mainController the main editor controller.
	 */
	public BackgroundsController(EditorController mainController) {
		model = mainController.getModel();
		view = new BackgroundPopup(this);
	}
	
	/**
	 * @return the background editor pop-up this controls.
	 */
	public BackgroundPopup getPopup() {
		return view;
	}
	
	/**
	 * Creates a new background in the pop-up.
	 */
	public void newBackground() {
		BackgroundView newView = new BackgroundView(chooseBackgroundImage());
		prepBackgroundForView(newView);
		view.newBackground(newView);
	}
	
	public void saveBackgrounds() {
		model.getBackgrounds().clear();
		for(BackgroundView bv : view.getBackgrounds()) {
			Vector location = bv.getLocation().scalarMultiply(1.0/view.getZoom());
			Vector size = bv.getSize().scalarMultiply(1.0/view.getZoom());
			Background bkgd = new Background(bv.getImageURL(), location, size);
			model.getBackgrounds().add(bkgd);
		}
		System.out.println(model.getBackgrounds());
	}
	
	public void loadBackgrounds() {
		for(Background bkgd : model.getBackgrounds()) {
			BackgroundView bv = new BackgroundView(bkgd.getImageURL());
			bv.setPosition(bkgd.getPosition());
			bv.setSize(bkgd.getSize());
			prepBackgroundForView(bv);
			view.loadBackground(bv);
		}
	}

	/**
	 * Allows user to select a background image from the options in the backgrounds directory.
	 * @return the URL representation of the image.
	 */
	private String chooseBackgroundImage() {
		ArrayList<String> backgrounds = new ArrayList<>();
		ResourceEngine temp = new MultiEngine(FileType.IMAGES);
		temp.pullFiles("data/backgrounds/");
		for(File f : temp.get(FileType.IMAGES).values()) {
			backgrounds.add(f.toURI().toString());
		}
		return new ImageChooser().pickImage(backgrounds);
	}
	
	/**
	 * Loads the given BackgroundView into the popup window
	 * @param newView the background to add.
	 */
	private void prepBackgroundForView(BackgroundView newView) {
		newView.setOnDrag((view, press) -> {
			startingOffset = new Vector(view.getLocation().offset(-press.getX(), -press.getY()));
		}, (view, drag) -> {
			view.setPosition(startingOffset.add(new Vector(drag.getX(), drag.getY())));
		}, (bkgd, release) -> {});
	}

}
