package BusinessLayer;

// In BusinessLayer.ActorManager
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ActorManager {

    private static ActorManager instance = new ActorManager();
    private List<Actor> actors = new ArrayList<>();
    private Actor selectedActor;

    private ActorManager() {
    }

    public static ActorManager getInstance() {
        return instance;
    }

    public void addActor(int x, int y, String name) {
        actors.add(new Actor(x, y, name));
    }

    public void drawActors(Graphics g) {
        for (Actor actor : actors) {
            actor.draw(g);
        }
    }

    public void selectActorAt(int x, int y) {
        selectedActor = null;
        for (Actor actor : actors) {
            if (actor.contains(x, y)) {
                selectedActor = actor;
                break;
            }
        }
    }

    public void moveSelectedActor(int x, int y) {
        if (selectedActor != null) {
            selectedActor.setPosition(x, y);
        }
    }

    public Actor getSelectedActor() {
        return selectedActor;
    }

    public void editSelectedActor(String newName) {
        if (selectedActor != null) {
            selectedActor.setName(newName);
        }
    }

    public String editActorNameAt(int x, int y) {
        for (Actor actor : actors) {
            if (actor.contains(x, y)) {
                String newName = JOptionPane.showInputDialog(null, "Enter new name for the actor:");
                if (newName != null && !newName.trim().isEmpty()) {
                    actor.setName(newName);
                    return newName; // Return the updated name
                }
                break;
            }
        }
        return null; // No changes made
    }

    public void clearActors() {
        actors.clear();
    }
}
