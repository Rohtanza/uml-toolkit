package BusinessLayer;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class IncludeManager {
    private static IncludeManager instance;
    private List<Include> includes;
    private Include selectedInclude;

    private IncludeManager() {
        includes = new ArrayList<>();
    }

    public static IncludeManager getInstance() {
        if (instance == null) {
            instance = new IncludeManager();
        }
        return instance;
    }

    // Add Include
    public void addInclude(Point start, Point end) {
        includes.add(new Include(start, end));
    }

    // Draw all Includes (dotted lines)
    public void drawIncludes(Graphics g) {
        for (Include include : includes) {
            include.draw(g);
        }
    }

    // Select Include at a specific point (for moving it)
    public Include selectIncludeAt(int x, int y) {
        for (Include include : includes) {
            if (include.contains(x, y)) {
                selectedInclude = include;
                return include;
            }
        }
        return null;
    }

    // Get the selected Include
    public Include getSelectedInclude() {
        return selectedInclude;
    }

    // Move the selected Include
    public void moveSelectedInclude(Include include, int x, int y) {
        // Move only the end point of the line (head)
        if (Math.abs(x - include.getStartPoint().x) > Math.abs(x - include.getEndPoint().x)) {
            // Move head (endPoint)
            include.setEndPoint(new Point(x, y));
        } else {
            // Move tail (startPoint)
            include.setStartPoint(new Point(x, y));
        }
    }

    // Other methods like removing, editing can also be added
}
