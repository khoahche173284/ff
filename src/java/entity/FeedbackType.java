/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Quang
 */
public class FeedbackType {
    private int id;
    private String feedbackName;

    public FeedbackType() {
    }

    public FeedbackType(int id, String feedbackName) {
        this.id = id;
        this.feedbackName = feedbackName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    @Override
    public String toString() {
        return "FeedbackType{" + "id=" + id + ", feedbackName=" + feedbackName + '}';
    }
}