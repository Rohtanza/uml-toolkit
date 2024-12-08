package BusinessLayer;

import java.awt.*;
import java.util.ArrayList;

public class RelationshipManager {

    private static RelationshipManager instance;
    private ArrayList<Relationship> relationships;
    private Relationship selectedRelationship;
    private boolean selectingStart = true; // Determines whether we are selecting start or end of the relationship
    private int startX, startY;

    private RelationshipManager() {
        relationships = new ArrayList<>();
    }

    public static RelationshipManager getInstance() {
        if (instance == null) {
            instance = new RelationshipManager();
        }
        return instance;
    }

    public boolean isSelectingStart() {
        return selectingStart;
    }

    public void startRelationship(int x, int y) {
        selectingStart = false;
        startX = x;
        startY = y;
    }

    public void endRelationship(int x, int y) {
        selectingStart = true;
        Relationship relationship = new Relationship(startX, startY, x, y);
        relationships.add(relationship);
    }

    public void drawRelationships(Graphics g) {
        for (Relationship relationship : relationships) {
            relationship.draw(g);
        }
    }

    public void selectRelationshipAt(int x, int y) {
        selectedRelationship = null;
        for (Relationship relationship : relationships) {
            if (relationship.isNearPoint(x, y)) {
                selectedRelationship = relationship;
                break;
            }
        }
    }

    public Relationship getSelectedRelationship() {
        return selectedRelationship;
    }

    public void removeSelectedRelationship() {
        if (selectedRelationship != null) {
            relationships.remove(selectedRelationship);
            selectedRelationship = null;
        }
    }

    public void moveEndpoint(int x, int y) {
        if (selectedRelationship != null) {
            // Move the start point or end point depending on the proximity
            if (selectedRelationship.isNearStartPoint(x, y)) {
                selectedRelationship.setStartPoint(x, y);  // Move start point
            } else if (selectedRelationship.isNearEndPoint(x, y)) {
                selectedRelationship.setEndPoint(x, y);    // Move end point
            }
        }
    }

}
