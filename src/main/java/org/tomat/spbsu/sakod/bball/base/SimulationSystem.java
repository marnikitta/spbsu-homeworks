package org.tomat.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;

public abstract class SimulationSystem extends ArrayList<PObject> {
    public void operate(double dt) {
        init();
        solve();
        simulate(dt);
    }

    private void init() {
        for (PObject pObject : this) {
            pObject.initForce();
        }
    }

    protected abstract void solve();

    private void simulate(double dt) {
        for (PObject pObject : this) {
            pObject.simulate(dt);
        }
    }

    public void draw(GraphicsContext gc) {
        Paint p = gc.getStroke();
        double width = gc.getLineWidth();

        for (PObject pObject : this) {
            pObject.draw(gc);
        }

        gc.setStroke(p);
        gc.setLineWidth(width);
    }
}
