/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.util;

import java.util.List;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Control;

/**
 *
 * @author Rodolfo
 */
public class AnimationValidationField {
    
    public static void starRotationAnimation(List<Control> controls) {
        controls.forEach( control -> {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(60), control);
        rotateTransition.setFromAngle(-4);
        rotateTransition.setToAngle(4);
        rotateTransition.setCycleCount(8);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setOnFinished((ActionEvent event1) -> {
          control.setRotate(0);
        });
           rotateTransition.play();     
        });
        
        if(!controls.isEmpty()) {
            controls.get(0).requestFocus();
        }
        
    }
    
    
}
