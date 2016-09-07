package org.marnikitta.spbsu.sakod.bball.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public abstract class SimulationSystem extends ArrayList<PObject> {
    public synchronized void operate(double dt) {
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
        Paint stroke = gc.getStroke();
        Paint fill = gc.getFill();
        double width = gc.getLineWidth();

        for (PObject pObject : this) {
            pObject.draw(gc);
        }

        gc.setStroke(stroke);
        gc.setFill(fill);
        gc.setLineWidth(width);
    }
}
