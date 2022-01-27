package com.example.proyectografos;

import android.graphics.Paint;
import android.graphics.Path;

public class nodoDraw {
    Path path;
    Paint paint;

    public nodoDraw(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
