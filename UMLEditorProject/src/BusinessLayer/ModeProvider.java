package BusinessLayer;

public class ModeProvider {

    private String currentMode = "Neutral";

    public String getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(String mode) {
        currentMode = mode;
    }

    public void resetMode() {
        currentMode = "Neutral";
    }
}
