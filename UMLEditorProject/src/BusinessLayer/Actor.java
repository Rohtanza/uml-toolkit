package BusinessLayer;

import java.awt.*;

public class Actor {

    private int x, y;
    private String name;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    public Actor(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public boolean contains(int px, int py) {
        return px >= x && px <= x + WIDTH && py >= y && py <= y + HEIGHT;
    }

    public void draw(Graphics g) {
        // Draw stick figure
        g.setColor(Color.BLACK);
        g.drawOval(x, y, WIDTH, WIDTH); // Head
        g.drawLine(x + WIDTH / 2, y + WIDTH, x + WIDTH / 2, y + 2 * WIDTH); // Body
        g.drawLine(x + WIDTH / 2, y + WIDTH + 10, x + 10, y + WIDTH + 30); // Left arm
        g.drawLine(x + WIDTH / 2, y + WIDTH + 10, x + WIDTH - 10, y + WIDTH + 30); // Right arm
        g.drawLine(x + WIDTH / 2, y + 2 * WIDTH, x + 10, y + 2 * WIDTH + 20); // Left leg
        g.drawLine(x + WIDTH / 2, y + 2 * WIDTH, x + WIDTH - 10, y + 2 * WIDTH + 20); // Right leg
        // Draw name
        g.drawString(name, x, y - 5);
    }

    public boolean isInside(int x, int y) {
        int centerX = this.x + WIDTH / 2;
        int centerY = this.y + WIDTH / 2;
        int radius = WIDTH / 2;
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(radius, 2); // Check if within circle
    }

    public void setPosition(int x, int y) {
        this.x = x - WIDTH / 2; // Adjust position to center the actor
        this.y = y - WIDTH / 2;
    }

    public String getName() {
        return name; // Getter for actor's name
    }
    
       public void setName(String name) {
        this.name = name; // Update the actor's name
    }

}
