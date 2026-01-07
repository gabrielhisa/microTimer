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

    private int milliseconds = 0;
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
    private Label milliLabel;

    @FXML
    private Button startPauseButton;

    @FXML
    private Button resetButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private void initialize(){
        KeyFrame timeFrame = new KeyFrame(Duration.millis(10), event -> {
            timePasser();
            updateLabels();

        });
        timer = new Timeline(timeFrame);
        timer.setCycleCount(INDEFINITE);
    }

    @FXML
    private void startTimer(){
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

    @FXML
    private void resetTimer(){
        timer.stop();
        startPauseButton.setText("▶");
        milliseconds = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        isRunning = false;
        updateLabels();
    }

    private void timePasser(){
        milliseconds++;

        if (milliseconds >= 100){
            milliseconds=0;
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
    }

    private String formatTime(int n){
        if (n < 10){
            return "0" + n;
        } else {
            return String.valueOf(n);
        }
    }

    private void updateLabels(){
        milliLabel.setText(formatTime(milliseconds));
        secondLabel.setText(formatTime(seconds));
        minuteLabel.setText(formatTime(minutes));
        hourLabel.setText(formatTime(hours));
    }

    // Moving the panel around
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
