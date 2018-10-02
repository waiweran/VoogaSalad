package play.view.popupMenuTypes;
import general.storage.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import play.externalAPI.PlayGoal;

public class GoalsMenu extends PopupMenuItem{

	private ObservableList<PlayGoal> myGoals;
	private ScrollPane view;
	private VBox options;

	public GoalsMenu(ObservableList<PlayGoal> goals) {
		super("Goals");
		myGoals = goals;	
		createGoalsPane();
		goals.addObserver((o, arg) -> updateGoals());
		updateGoals();
	}

	@Override
	public ScrollPane getView() {
		return view;
	}
	
	private void createGoalsPane(){
		options = new VBox(8);
		view = new ScrollPane();
		view.setContent(options);
	}
	
	private void updateGoals() {
		options.getChildren().clear();
		for(PlayGoal goal : myGoals) {
			VBox goalView = new VBox(5);
			Text name = new Text(goal.getName());
			name.setFont(Font.font(18));
			goalView.getChildren().add(name);
			Text descrip = new Text(goal.getDescription());
			descrip.setFont(Font.font(10));
			goalView.getChildren().add(descrip);
			options.getChildren().add(goalView);
		}
	}
}


