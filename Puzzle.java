package puzzle;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Puzzle extends Application {
    
    public static int [][] state = new int [3][3];
    public static Queue<String> output = new ArrayDeque<>();
    public static Queue<int [][]> puzzle = new ArrayDeque<>();
    public static int cost=0;
    public static int step=0;
    public static long elapsedTime;

    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
