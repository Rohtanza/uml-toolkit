package BusinessLayer;

import java.awt.*;
import java.util.ArrayList;

public class UseCaseManager {
    private static UseCaseManager instance;
    private ArrayList<UseCase> useCases = new ArrayList<>();
    private UseCase selectedUseCase;

    private UseCaseManager() {}

    public static UseCaseManager getInstance() {
        if (instance == null) {
            instance = new UseCaseManager();
        }
        return instance;
    }

    public void addUseCase(int x, int y, String name) {
        int width = calculateWidth(name);
        useCases.add(new UseCase(x, y, width, 50, name)); // Fixed height of 50
    }

    public void drawUseCases(Graphics g) {
        for (UseCase useCase : useCases) {
            useCase.draw(g);
        }
    }

    public void selectUseCaseAt(int x, int y) {
        selectedUseCase = null;
        for (UseCase useCase : useCases) {
            if (useCase.contains(x, y)) {
                selectedUseCase = useCase;
                break;
            }
        }
    }
    
    

    public UseCase getSelectedUseCase() {
        return selectedUseCase;
    }

    public void editSelectedUseCase(String newName) {
        if (selectedUseCase != null) {
            selectedUseCase.setName(newName);
            selectedUseCase.setWidth(calculateWidth(newName));
        }
    }

    public void moveSelectedUseCase(int x, int y) {
        if (selectedUseCase != null) {
            selectedUseCase.setX(x);
            selectedUseCase.setY(y);
        }
    }

    public int calculateWidth(String name) {
        // Basic logic to calculate width based on text length
        return Math.max(100, name.length() * 12); // Minimum width of 100
    }
}
