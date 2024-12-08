package BusinessLayer;

import java.awt.*;

public class UseCase {

    private int x, y, width, height;
    private String name;

    public UseCase(int x, int y, int width, int height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(x - width / 2, y - height / 2, width, height); // Oval with dynamic width

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics();
        int textWidth = metrics.stringWidth(name);
        int textHeight = metrics.getHeight();
        g.drawString(name, x - textWidth / 2, y + textHeight / 4);
    }

    public String getName() {
        return name;
    }

    public boolean contains(int px, int py) {
        // Check if the point is inside the oval
        double dx = (px - x) / (double) (width / 2);
        double dy = (py - y) / (double) (height / 2);
        return dx * dx + dy * dy <= 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
