package BusinessLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class Include {
    private Point startPoint;
    private Point endPoint;

    public Include(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }

    // Getters and setters
    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    // Draw method for Include (dotted line with an arrow at the end)
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, new float[]{3}, 0)); // Dotted line

        // Draw the dotted line
        g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        // Draw arrowhead (for head representation)
        drawArrowHead(g2d, endPoint.x, endPoint.y, startPoint.x, startPoint.y);
    }

    // Method to draw the arrowhead at the end point (head)
    private void drawArrowHead(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        int arrowSize = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);

        int xArrow1 = (int) (x1 + arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow1 = (int) (y1 + arrowSize * Math.sin(angle + Math.PI / 6));

        int xArrow2 = (int) (x1 + arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow2 = (int) (y1 + arrowSize * Math.sin(angle - Math.PI / 6));

        g2d.drawLine(x1, y1, xArrow1, yArrow1);
        g2d.drawLine(x1, y1, xArrow2, yArrow2);
    }

    // Check if a point is near the Include for selection (tail or head)
    public boolean contains(int x, int y) {
        int buffer = 10; // Selectable area around the line
        double distanceToStart = Math.sqrt(Math.pow(x - startPoint.x, 2) + Math.pow(y - startPoint.y, 2));
        double distanceToEnd = Math.sqrt(Math.pow(x - endPoint.x, 2) + Math.pow(y - endPoint.y, 2));

        return (distanceToStart <= buffer || distanceToEnd <= buffer);
    }
}
