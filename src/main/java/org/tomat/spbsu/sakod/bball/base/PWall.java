package org.tomat.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PWall extends PObject {
    private Vector2D position;
    private Vector2D direction;

    public PWall() {
        fixed = true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        
    }
}
