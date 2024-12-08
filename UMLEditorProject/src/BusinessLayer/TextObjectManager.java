//package BusinessLayer;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TextObjectManager {
//
//    private List<TextObject> textObjects = new ArrayList<>();
//    private static TextObjectManager instance;
//    private TextObject selectedText;
//
//    private TextObjectManager() {
//        textObjects = new ArrayList<>();
//    }
//
//    public static TextObjectManager getInstance() {
//        if (instance == null) {
//            instance = new TextObjectManager();
//        }
//        return instance;
//    }
//
//    // Add a new TextObject
//    public void addTextObject(String text, int x, int y) {
//        TextObject newText = new TextObject(text, x, y, Color.BLACK);
//        textObjects.add(newText);
//    }
//
//    // Render all text objects on the panel (called in the paint method)
//    public void renderTextObjects(Graphics g) {
//        for (TextObject textObject : textObjects) {
//            textObject.draw(g);
//        }
//    }
//
//    public void updateTextObject(TextObject textObject, String newText) {
//        // Update the text of the selected text object
//
//        textObject.setText(newText);
//    }
//
//    // Get all text objects
//    public List<TextObject> getTexts() {
//        return textObjects;
//    }
//
//    public void removeTextObject(TextObject textObject) {
//        textObjects.remove(textObject);
//    }
//    // Find text object by coordinates (if it contains the point)
//
//    public TextObject getTextAt(int x, int y) {
//        for (TextObject text : textObjects) {
//            // Assuming TextObject has a method containsPoint() that checks if the point is inside the object
//            if (text.containsPoint(x, y)) {
//                return text;
//            }
//        }
//        return null;
//    }
//
//    public TextObject textObjectIncludeAt(int x, int y) {
//        for (TextObject text : textObjects) {
//            if (text.containsPoint(x, y)) {
//                selectedText = text;
//                return text;
//            }
//        }
//        return null;
//    }
//
//    public void selectActorAt(int x, int y) {
//        selectedText = null;
//        for (TextObject text : textObjects) {
//            if (text.contains(x, y)) {
//                selectedText = text;
//                break;
//            }
//        }
//    }
//
//}


package BusinessLayer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class TextObjectManager {
    private List<TextObject> textObjects = new ArrayList<>();
    private static TextObjectManager instance;
    private TextObject selectedText;

    private TextObjectManager() {}

    public static TextObjectManager getInstance() {
        if (instance == null) {
            instance = new TextObjectManager();
        }
        return instance;
    }

    public void addTextObject(String text, int x, int y) {
        textObjects.add(new TextObject(text, x, y, Color.BLACK));
    }

    
    public void renderTextObjects(Graphics g) {
        for (TextObject textObject : textObjects) {
            textObject.draw(g);
        }
    }

    public void updateText(String newText) {
        if (selectedText != null) {
            selectedText.setText(newText);
        }
    }

    public boolean selectTextAt(int x, int y) {
        selectedText = null;
        for (TextObject text : textObjects) {
            if (text.contains(x, y)) {
                selectedText = text;
                return true;  // Successfully selected
            }
        }
        return false;  // No text object found at the point
    }

    public TextObject getSelectedText() {
        return selectedText;
    }

    public void moveSelectedText(int newX, int newY) {
        if (selectedText != null) {
            selectedText.setPosition(newX, newY);
        }
    }

    public void removeSelectedText() {
        if (selectedText != null) {
            textObjects.remove(selectedText);
            selectedText = null;
        }
    }

    public void changeTextColor(Color newColor) {
        if (selectedText != null) {
            selectedText.setColor(newColor);
        }
    }

    public List<TextObject> getTexts() {
        return new ArrayList<>(textObjects);
    }
}
