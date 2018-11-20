import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PlayoffPointsMainProg extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("PlayoffPointsUi.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("2018 GESL Playoff Race");
		Platform.setImplicitExit(true);
		stage.setOnCloseRequest((ae) -> {
			Platform.exit();
			System.exit(0);
		});
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);

	}


}
