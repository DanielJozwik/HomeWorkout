package com.example.homeworkout;

import java.io.Serializable;
class Exercise implements Serializable {
    private String exerciseName;
    private float predictor;
    private String videoPath;
    public Exercise(String exerciseName,float predictor,String videoPath) {
        this.exerciseName = exerciseName;
        this.predictor = predictor;
        this.videoPath = videoPath;
    }
    public String getExerciseName() {

        return exerciseName;
    }
    public void setExerciseName(String exerciseName) {

        this.exerciseName = exerciseName;
    }
    public float getPredictor() {

        return predictor;
    }
    public void setPredictor(float predictor) {

        this.predictor = predictor;
    }
    public float calculateBurnedCalories(float numberOfRepetitions){
        return predictor*numberOfRepetitions;
    }
    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
