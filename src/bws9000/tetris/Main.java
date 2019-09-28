package bws9000.tetris;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Tetris Test with JavaFX
 *
 * @author Burt W Snyder
 * @version 0.1
 * @since 2019-09-21
 */
public class Main extends Application {

    private Group root;
    private int GRID_HEIGHT = 800;
    private int GRID_WIDTH = 400;
    private Tetromino tetromino;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = new Group();
        primaryStage = new Stage();
        primaryStage.setTitle("Tetris Test");
        primaryStage.setScene(new Scene(root, 400, 800));
        primaryStage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        System.out.println("UP");
                        break;
                    case LEFT:
                        System.out.println("LEFT");
                        tetromino.moveLeft();
                        break;
                    case RIGHT:
                        System.out.println("RIGHT");
                        tetromino.moveRight();
                        break;
                }
            }
        });

        addTetronimo("L");

        primaryStage.show();

        new AnimationTimer() {

            private long update_timer = 0;
            @Override
            public void handle(long now) {
                if (now - update_timer >= 600_000_000) {
                    tetromino.descend();
                    update_timer = now ;
                }
            }

        }.start();
    }

    private void addTetronimo(String shape) {
        this.tetromino = new Tetromino(shape);
        root.getChildren().addAll(tetromino.getShape());
    }

    public int getGridWidth(){
        return this.GRID_WIDTH;
    }
    public int getGridHEIGHT(){
        return this.GRID_HEIGHT;
    }

    public static void main(String[] args) {
        launch(args);
    }

}