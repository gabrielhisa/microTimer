package org.microtimer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.MouseEvent;

import static javafx.animation.Animation.INDEFINITE;

public class MicroTimerController {

    private Timeline timer;
    private Boolean isRunning = false;
    private double dragOffsetX, dragOffsetY;

    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    @FXML
    private Label hourLabel;

    @FXML
    private Label minuteLabel;

    @FXML
    private Label secondLabel;

    @FXML
    private Button startPauseButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private void initialize(){
        KeyFrame timeFrame = new KeyFrame(Duration.seconds(1), event -> {
            timePasser();
            updateLabels();

        });
        timer = new Timeline(timeFrame);
        timer.setCycleCount(INDEFINITE);
    }

    @FXML
    private void startButton(){
        if (isRunning == false){
            timer.play();
            startPauseButton.setText("⏸");
            isRunning = true;
        } else {
            timer.pause();
            startPauseButton.setText("▶");
            isRunning = false;
        }
    }





    private void timePasser(){
        seconds++;

        if (seconds >= 60){
            seconds = 0;
            minutes++;

            if (minutes >= 60){
                minutes = 0;
                hours++;
            }
        }
    }

    private String formatTime(int n){
        if (n < 10){
            return "0" + n;
        } else {
            return String.valueOf(n);
        }
    }

    private void updateLabels(){
        secondLabel.setText(formatTime(seconds));
        minuteLabel.setText(formatTime(minutes));
        hourLabel.setText(formatTime(hours));
    }

    @FXML
    private void handleMousePressed(MouseEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        dragOffsetX = event.getScreenX() - stage.getX();
        dragOffsetY = event.getScreenY() - stage.getY();
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setX(event.getScreenX() - dragOffsetX);
        stage.setY(event.getScreenY() - dragOffsetY);
    }






}
