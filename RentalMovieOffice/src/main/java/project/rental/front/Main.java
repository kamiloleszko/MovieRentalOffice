package project.rental.front;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.TableView;
        import javafx.stage.Stage;
        import project.rental.entity.Users;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        java.net.URL url = getClass().getResource("/sample.fxml");
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Rental Office");
        primaryStage.setScene(new Scene(root, 1003, 950));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
