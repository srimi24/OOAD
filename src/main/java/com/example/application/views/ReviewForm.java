package com.example.application.views;


import com.example.application.manager.ReviewManager;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;


public class ReviewForm extends FormLayout {

    private final Dialog dialog;
    private final TextArea stayOrTravelExperience;
    private final TextArea otherFeedback;

    public ReviewForm(Dialog dialog) {
        this.dialog = dialog;

        stayOrTravelExperience = new TextArea("How was the Stay/Travel Experience?");
        otherFeedback = new TextArea("Any other Feedback?");

        Button reviewButton = new Button("Add Review");
        reviewButton.addClickListener(event -> {
            review();
        });

        add(stayOrTravelExperience, otherFeedback, reviewButton);
    }

    private void review() {
        ReviewManager reviewManager = new ReviewManager();
        reviewManager.review(stayOrTravelExperience.getValue(), otherFeedback.getValue());
        dialog.close();
    }
}
