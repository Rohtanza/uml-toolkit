//package BusinessLayer;
//
//import java.awt.*;
//import java.awt.geom.Line2D;
//
//public class DottedLineRelationship extends Relationship {
//    public DottedLineRelationship(int startX, int startY, int endX, int endY) {
//        super(startX, startY, endX, endY);
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.BLACK);
//        g2d.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0f, new float[]{9f, 6f}, 0f));  // Dotted line style
//        g2d.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
//        
//        // Draw arrowhead at the end of the line
//        drawArrowHead(g2d, getEndX(), getEndY(), getStartX(), getStartY());
//    }
//
//    // Draw an arrowhead at the end of the line
//    private void drawArrowHead(Graphics2D g2d, int x1, int y1, int x2, int y2) {
//        int arrowSize = 10;
//        double angle = Math.atan2(y1 - y2, x1 - x2);
//        int x1a = (int) (x1 - arrowSize * Math.cos(angle - Math.PI / 6));
//        int y1a = (int) (y1 - arrowSize * Math.sin(angle - Math.PI / 6));
//        int x2a = (int) (x1 - arrowSize * Math.cos(angle + Math.PI / 6));
//        int y2a = (int) (y1 - arrowSize * Math.sin(angle + Math.PI / 6));
//        
//        // Draw the arrowhead (triangle)
//        g2d.fillPolygon(new int[] {x1, x1a, x2a}, new int[] {y1, y1a, y2a}, 3);
//    }
//}
