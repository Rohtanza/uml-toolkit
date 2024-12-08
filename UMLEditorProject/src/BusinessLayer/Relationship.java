package BusinessLayer;

import java.awt.*;

public class Relationship {
    private int startX, startY, endX, endY;

    public Relationship(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(startX, startY, endX, endY);
    }

    public boolean isNearPoint(int x, int y) {
        int tolerance = 5; // Adjust as needed
        double distance = pointToLineDistance(x, y, startX, startY, endX, endY);
        return distance <= tolerance;
    }

    private double pointToLineDistance(int px, int py, int x1, int y1, int x2, int y2) {
        double A = px - x1;
        double B = py - y1;
        double C = x2 - x1;
        double D = y2 - y1;

        double dot = A * C + B * D;
        double len_sq = C * C + D * D;
        double param = len_sq != 0 ? dot / len_sq : -1;

        double xx, yy;
        if (param < 0) {
            xx = x1;
            yy = y1;
        } else if (param > 1) {
            xx = x2;
            yy = y2;
        } else {
            xx = x1 + param * C;
            yy = y1 + param * D;
        }

        double dx = px - xx;
        double dy = py - yy;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean isNearStartPoint(int x, int y) {
        int tolerance = 5; // Adjust this tolerance as needed
        return Math.abs(x - startX) <= tolerance && Math.abs(y - startY) <= tolerance;
    }

    public boolean isNearEndPoint(int x, int y) {
        int tolerance = 5; // Adjust this tolerance as needed
        return Math.abs(x - endX) <= tolerance && Math.abs(y - endY) <= tolerance;
    }

    public void setStartPoint(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void setEndPoint(int x, int y) {
        this.endX = x;
        this.endY = y;
    }
}