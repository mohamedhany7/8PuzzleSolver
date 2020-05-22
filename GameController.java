/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class GameController implements Initializable {

    @FXML
    private Button num0;
    @FXML
    private Button num1;
    @FXML
    private Button num2;
    @FXML
    private Button num3;
    @FXML
    private Button num4;
    @FXML
    private Button num5;
    @FXML
    private Button num6;
    @FXML
    private Button num7;
    @FXML
    private Button num8;
    @FXML
    private Button block0;
    @FXML
    private Button block1;
    @FXML
    private Button block2;
    @FXML
    private Button block3;
    @FXML
    private Button block4;
    @FXML
    private Button block5;
    @FXML
    private Button block6;
    @FXML
    private Button block7;
    @FXML
    private Button block8;
    @FXML
    private Button solve;
    @FXML
    private ToggleGroup mode;
    @FXML
    private RadioButton bfsRadio= new RadioButton();
    @FXML
    private RadioButton dfsRadio = new RadioButton();
    @FXML
    private RadioButton Manhattan = new RadioButton();
    @FXML
    private RadioButton Euclidean = new RadioButton();
    @FXML
    private Button solve2;
    @FXML
    private TextArea txt;
    @FXML
    private Text txt0;
    @FXML
    private Button cost;
    @FXML
    private Button nodes;
    @FXML
    private Button depth;
    @FXML
    private Button time;
    @FXML
    private Text txt2;
    @FXML
    private Text txt1;
    @FXML
    private Text txt3;
    @FXML
    private Text txt4;
    @FXML
    private Text txt5;
    @FXML
    private Text txt6;
    @FXML
    private Text txt7;
    
    
    
    int num = 0;
    private static int gameMode = 0;
    private static String stage = "";
    private int [][] goal = {{0,1,2},{3,4,5},{6,7,8}};
    int step=1;
    String str = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void action0(ActionEvent event) {
        num0.setDisable(true);
        do_logic(0);
        stage+=0;
    }

    @FXML
    private void action1(ActionEvent event) {
        num1.setDisable(true);
        do_logic(1);
        stage+=1;
    }

    @FXML
    private void action2(ActionEvent event) {
        num2.setDisable(true);
        do_logic(2);
        stage+=2;
    }

    @FXML
    private void action3(ActionEvent event) {
        num3.setDisable(true);
        do_logic(3);
        stage+=3;
    }

    @FXML
    private void action4(ActionEvent event) {
        num4.setDisable(true);
        do_logic(4);
        stage+=4;
    }

    @FXML
    private void action5(ActionEvent event) {
        num5.setDisable(true);
        do_logic(5);
        stage+=5;
    }

    @FXML
    private void action6(ActionEvent event) {
        num6.setDisable(true);
        do_logic(6);
        stage+=6;
    }

    @FXML
    private void action7(ActionEvent event) {
        num7.setDisable(true);
        do_logic(7);
        stage+=7;
    }

    @FXML
    private void action8(ActionEvent event) {
        num8.setDisable(true);
        do_logic(8);
        stage+=8;
    }
    
    @FXML
    private void next(ActionEvent event) throws IOException{
        num0.setVisible(false);
        num1.setVisible(false);
        num2.setVisible(false);
        num3.setVisible(false);
        num4.setVisible(false);
        num5.setVisible(false);
        num6.setVisible(false);
        num7.setVisible(false);
        num8.setVisible(false);
        solve.setVisible(false);
        txt0.setVisible(false);
        bfsRadio.setVisible(true);
        dfsRadio.setVisible(true);
        Manhattan.setVisible(true);
        Euclidean.setVisible(true);
        txt.setVisible(true);
        time.setVisible(true);
        depth.setVisible(true);
        nodes.setVisible(true);
        txt1.setVisible(true);
        txt2.setVisible(true);
        txt3.setVisible(true);
        txt4.setVisible(true);
        txt5.setVisible(true);
        txt6.setVisible(true);
        txt7.setVisible(true);
        cost.setVisible(true);
        solve2.setVisible(true);
        int c=0;
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++){
            Puzzle.state[x][y] = stage.charAt(c)-48;
            c++;
            }
            
        }
    }
    
    @FXML
    private void setMode(){
        if(bfsRadio.isSelected())
            gameMode=0;
        else if (dfsRadio.isSelected())
            gameMode=1;
        else if (Manhattan.isSelected())
            gameMode=2;
        else if (Euclidean.isSelected())
            gameMode=3;
    }
    
    @FXML
    private void solve(){
        Logic l = new Logic();
        txt.setText("");
        boolean found;
        switch (gameMode){
            case 0:
                txt2.setText("BFS");
                found = l.bfs();
                if (found)
                    while(!Puzzle.puzzle.isEmpty()){
                        refresh(Puzzle.puzzle.remove());
                    }
                else
                    txt.setText("There is no solution");
                time.setText(Long.toString(Puzzle.elapsedTime));
                break;
            case 1:
                txt2.setText("DFS");
                found = l.dfs();
                if (found)
                    while(!Puzzle.puzzle.isEmpty())
                        refresh(Puzzle.puzzle.remove());
                else
                    txt.setText("There is no solution");
                time.setText(Long.toString(Puzzle.elapsedTime));
                break;
            case 2:
                txt2.setText("A*\nManhattan");
                found = l.aStar(1);
                if (found)
                    while(!Puzzle.puzzle.isEmpty())
                        refresh(Puzzle.puzzle.remove());
                else
                    txt.setText("There is no solution");
                time.setText(Long.toString(Puzzle.elapsedTime));
                break;
            case 3:
                txt2.setText("A*\nEuclidean");
                found = l.aStar(2);
                if (found)
                    while(!Puzzle.puzzle.isEmpty())
                        refresh(Puzzle.puzzle.remove());
                else
                    txt.setText("There is no solution");
                time.setText(Long.toString(Puzzle.elapsedTime));
                break;
        }
    }
    
    private void do_logic(int v){
        String s = Integer.toString(v);
        switch(num){
            case 0:
                    block0.setText(s);
            break;
            case 1:
                    block1.setText(s);
            break;
            case 2:
                    block2.setText(s);
            break;
            case 3:
                    block3.setText(s);
            break;
            case 4:
                    block4.setText(s);
            break;
            case 5:
                    block5.setText(s);
            break;
            case 6:
                    block6.setText(s);
            break;
            case 7:
                    block7.setText(s);
            break;
            case 8:
                    block8.setText(s);
                    solve.setDisable(false);
            break;
        }
        num++;
    }
    
    private void refresh(int [][] puzzle){
        SequentialTransition seq = new SequentialTransition();
        //while (!Puzzle.output.isEmpty()){
            print ();
            cost.setText(String.valueOf(Puzzle.cost));
            depth.setText(String.valueOf(Puzzle.cost));
            nodes.setText(String.valueOf(Puzzle.step));
            if (puzzle != null)
                for(int i=0;i<3;i++)
                    for(int j=0;j<3;j++){
                        int sw = (i)*3+(j);
                        switch(sw){
                            case 0:
                                if (!block0.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition = new FadeTransition(Duration.millis(1500), block0);
                                    fadeInTransition.setFromValue(1.0);
                                    fadeInTransition.setToValue(0.0);
                                    block0.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(1500), block0);
                                    fadeOutTransition.setFromValue(0.0);
                                    fadeOutTransition.setToValue(1.0);
                                    //fadeInTransition.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition,fadeOutTransition));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 1:
                                if (!block1.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition1 = new FadeTransition(Duration.millis(1500), block1);
                                    fadeInTransition1.setFromValue(1.0);
                                    fadeInTransition1.setToValue(0.0);
                                    //fadeInTransition1.play();
                                    block1.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition1 = new FadeTransition(Duration.millis(1500), block1);
                                    fadeOutTransition1.setFromValue(0.0);
                                    fadeOutTransition1.setToValue(1.0);
                                    //fadeInTransition1.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition1,fadeOutTransition1));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 2:
                                if (!block2.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition2 = new FadeTransition(Duration.millis(1500), block2);
                                    fadeInTransition2.setFromValue(1.0);
                                    fadeInTransition2.setToValue(0.0);
                                    //fadeInTransition2.play();
                                    block2.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition2 = new FadeTransition(Duration.millis(1500), block2);
                                    fadeOutTransition2.setFromValue(0.0);
                                    fadeOutTransition2.setToValue(1.0);
                                    //fadeInTransition2.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition2,fadeOutTransition2));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 3:
                                if (!block3.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition3 = new FadeTransition(Duration.millis(1500), block3);
                                    fadeInTransition3.setFromValue(1.0);
                                    fadeInTransition3.setToValue(0.0);
                                    //fadeInTransition3.play();
                                    block3.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition3 = new FadeTransition(Duration.millis(1500), block3);
                                    fadeOutTransition3.setFromValue(0.0);
                                    fadeOutTransition3.setToValue(1.0);
                                    //fadeInTransition3.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition3,fadeOutTransition3));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 4:
                                if (!block4.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition4 = new FadeTransition(Duration.millis(1500), block4);
                                    fadeInTransition4.setFromValue(1.0);
                                    fadeInTransition4.setToValue(0.0);
                                    //fadeInTransition4.play();
                                    block4.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition4 = new FadeTransition(Duration.millis(1500), block4);
                                    fadeOutTransition4.setFromValue(0.0);
                                    fadeOutTransition4.setToValue(1.0);
                                    //fadeInTransition4.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition4,fadeOutTransition4));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 5:
                                if (!block5.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition5 = new FadeTransition(Duration.millis(1500), block5);
                                    fadeInTransition5.setFromValue(1.0);
                                    fadeInTransition5.setToValue(0.0);
                                    //fadeInTransition5.play();
                                    block5.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition5 = new FadeTransition(Duration.millis(1500), block5);
                                    fadeOutTransition5.setFromValue(0.0);
                                    fadeOutTransition5.setToValue(1.0);
                                    //fadeInTransition5.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition5,fadeOutTransition5));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 6:
                                if (!block6.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition6 = new FadeTransition(Duration.millis(1500), block6);
                                    fadeInTransition6.setFromValue(1.0);
                                    fadeInTransition6.setToValue(0.0);
                                    //fadeInTransition6.play();
                                    block6.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition6 = new FadeTransition(Duration.millis(1500), block6);
                                    fadeOutTransition6.setFromValue(0.0);
                                    fadeOutTransition6.setToValue(1.0);
                                    //fadeInTransition6.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition6,fadeOutTransition6));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 7:
                                if (!block7.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition7 = new FadeTransition(Duration.millis(1500), block7);
                                    fadeInTransition7.setFromValue(1.0);
                                    fadeInTransition7.setToValue(0.0);
                                    //fadeInTransition7.play();
                                    block7.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition7 = new FadeTransition(Duration.millis(1500), block7);
                                    fadeOutTransition7.setFromValue(0.0);
                                    fadeOutTransition7.setToValue(1.0);
                                    //fadeInTransition7.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition7,fadeOutTransition7));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                            case 8:
                                if (!block8.getText().contentEquals(Integer.toString(puzzle[i][j]))){
                                    FadeTransition fadeInTransition8 = new FadeTransition(Duration.millis(1500), block8);
                                    fadeInTransition8.setFromValue(1.0);
                                    fadeInTransition8.setToValue(0.0);
                                    //fadeInTransition8.play();
                                    block8.setText(Integer.toString(puzzle[i][j]));
                                    FadeTransition fadeOutTransition8 = new FadeTransition(Duration.millis(1500), block8);
                                    fadeOutTransition8.setFromValue(0.0);
                                    fadeOutTransition8.setToValue(1.0);
                                    //fadeInTransition8.play();
                                    seq.getChildren().add(new ParallelTransition(fadeInTransition8,fadeOutTransition8));
                                    seq.getChildren().add(new PauseTransition(Duration.seconds(1.0)));
                                }
                            break;
                        }
                    }
        //}
        seq.play();
    }
    
    private void print (){
        String okay;
        okay = Puzzle.output.remove();
        txt.setText(okay + txt.getText());
    }
}
