//package BusinessLayer;
//
//import java.awt.Color;
//import java.awt.Graphics;
//
//public class TextObject {
//
//    private String text;
//    private int x, y;
//    private Color textColor; // Add a color field
//
//    public TextObject(String text, int x, int y, Color color) {
//        this.text = text;
//        this.x = x;
//        this.y = y;
//        this.textColor = color; // Initialize the text color
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public void setPosition(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    // Method to draw the text on the canvas
////    public void draw(Graphics g) {
////        g.drawString(text, x, y);
////    }
//    public void draw(Graphics g) {
//        g.setColor(Color.BLACK); // Set the text color to black (or any color you prefer)
//        g.drawString(text, x, y); // Draw text at the specified position
//    }
//
//    // Check if the point is within the bounds of the text
//    public boolean containsPoint(int x, int y) {
//        // Assuming text width and height is approximately 100x20
//        return (x >= this.x && x <= this.x + 100) && (y >= this.y && y <= this.y + 20);
//    }
//
//    boolean contains(int x, int y) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
package BusinessLayer;

import java.awt.Color;
import java.awt.Graphics;

public class TextObject {
    private String text;
    private int x, y;
    private Color textColor;
    private int width = 200;  // Approximate width of the text
    private int height = 40;

    public TextObject(String text, int x, int y, Color color) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.textColor = color;
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(textColor);
        g.drawString(text, x, y);
    }

    public boolean contains(int x, int y) {
  // Approximate height of the text
        return (x >= this.x && x <= this.x + width) && (y >= this.y && y <= this.y + height);
    }

    public void setColor(Color color) {
        this.textColor = color;
    }

    public Color getColor() {
        return textColor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TextObject that = (TextObject) obj;
        return x == that.x && y == that.y && text.equals(that.text);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
