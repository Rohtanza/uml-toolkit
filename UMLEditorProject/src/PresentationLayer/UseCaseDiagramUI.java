package PresentationLayer;

import BusinessLayer.Actor;
import BusinessLayer.ActorManager;
import BusinessLayer.ModeProvider;
import BusinessLayer.RelationshipManager;
import BusinessLayer.UseCaseManager;
import BusinessLayer.IncludeManager;
import BusinessLayer.Include;
import BusinessLayer.TextObject;
import BusinessLayer.TextObjectManager;
import BusinessLayer.UseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UseCaseDiagramUI extends JFrame {

    private JPanel canvasPanel;
    private JPanel filePanel;
    private JPanel editPanel;
    private JPanel viewPanel;
    private JPanel toolsPanel;
    private JPanel helpPanel;

    private JPanel sidebarPanel;

    private ModeProvider modeProvider;
    private String currentName = null;
    private Point firstClickPoint = null;
    private Point secondClickPoint = null;

    public UseCaseDiagramUI() {
        setTitle("Use Case Diagram");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setLocationRelativeTo(null);

        // Initialize panels
        filePanel = createFilePanel();
        editPanel = createEditPanel();
        viewPanel = createViewPanel();
        toolsPanel = createToolsPanel();
        helpPanel = createHelpPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("File", filePanel);
        tabbedPane.addTab("Edit", editPanel);
        tabbedPane.addTab("View", viewPanel);
        tabbedPane.addTab("Tools", toolsPanel);
        tabbedPane.addTab("Help", helpPanel);
        tabbedPane.setSelectedIndex(0);

        // Style the tabbed pane
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 15));
        tabbedPane.setForeground(Color.black);
        tabbedPane.setBackground(Color.lightGray);

        setLayout(new BorderLayout());
        // Add components to the layout
        add(tabbedPane, BorderLayout.NORTH);

        //canvasPanel = createCanvasPanel();
        JScrollPane scrollPane = new JScrollPane(canvasPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Create the sidebar with styled layout
        //sidebarPanel = createSidebarPanel();
        //add(sidebarPanel, BorderLayout.WEST);
        modeProvider = new ModeProvider();
        canvasPanel = createCanvasPanel(modeProvider);

        add(createSidebarPanel(), BorderLayout.WEST);
        add(new JScrollPane(canvasPanel), BorderLayout.CENTER);
    }

    private JPanel createCanvasPanel(ModeProvider modeProvider) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                for (int i = 0; i < getWidth(); i += 20) {
                    for (int j = 0; j < getHeight(); j += 20) {
                        g.drawLine(i, j, i, j);
                    }
                }
                ActorManager.getInstance().drawActors(g);
                UseCaseManager.getInstance().drawUseCases(g);
                RelationshipManager.getInstance().drawRelationships(g);
                IncludeManager.getInstance().drawIncludes(g);
                TextObjectManager.getInstance().renderTextObjects(g);
            }
        };

        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(2000, 2000));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (modeProvider.getCurrentMode().equals("Add Actor")) {
                    ActorManager.getInstance().addActor(e.getX(), e.getY(), currentName);
                    panel.repaint();
                    modeProvider.resetMode();
                } else if (modeProvider.getCurrentMode().equals("Add Use Case")) {
                    UseCaseManager.getInstance().addUseCase(e.getX(), e.getY(), currentName);
                    panel.repaint();
                    modeProvider.resetMode();
                } else if (modeProvider.getCurrentMode().equals("Add Relationship")) {
                    if (RelationshipManager.getInstance().isSelectingStart()) {
                        RelationshipManager.getInstance().startRelationship(e.getX(), e.getY());
                        panel.repaint();
                    } else {
                        RelationshipManager.getInstance().endRelationship(e.getX(), e.getY());
                        panel.repaint();
                        modeProvider.resetMode(); // Reset mode after adding the relationship
                    }
                } else if (modeProvider.getCurrentMode().equals("Add Include")) {
                    if (firstClickPoint == null) {
                        firstClickPoint = e.getPoint();
                    } else {
                        secondClickPoint = e.getPoint();
                        IncludeManager.getInstance().addInclude(firstClickPoint, secondClickPoint);
                        panel.repaint();
                        modeProvider.resetMode();
                        firstClickPoint = null;
                        secondClickPoint = null;
                    }
                } else if (modeProvider.getCurrentMode().equals("Edit")) {
                    // Check if an Actor is selected
                    ActorManager.getInstance().selectActorAt(e.getX(), e.getY());
                    Actor selectedActor = ActorManager.getInstance().getSelectedActor();

                    if (selectedActor != null) { // If an Actor is selected
                        String newName = JOptionPane.showInputDialog(panel,
                                "Enter a new name for the Actor:",
                                selectedActor.getName());

                        if (newName != null && !newName.trim().isEmpty()) { // If a valid name is provided
                            selectedActor.setName(newName.trim()); // Update the actor's name
                            panel.repaint(); // Redraw the panel
                        }
                    } else {
                        // If no Actor is selected, check for Use Cases
                        UseCaseManager.getInstance().selectUseCaseAt(e.getX(), e.getY());
                        UseCase selectedUseCase = UseCaseManager.getInstance().getSelectedUseCase();

                        if (selectedUseCase != null) { // If a Use Case is selected
                            String newName = JOptionPane.showInputDialog(panel,
                                    "Enter a new name for the Use Case:",
                                    selectedUseCase.getName());

                            if (newName != null && !newName.trim().isEmpty()) {
                                selectedUseCase.setName(newName.trim());
                                selectedUseCase.setWidth(UseCaseManager.getInstance().calculateWidth(newName.trim())); // Adjust width
                                panel.repaint();
                            }
                        } else {
                            // Check if a TextObject is selected
                            TextObjectManager.getInstance().selectTextAt(e.getX(), e.getY());
                            TextObject selectedText = TextObjectManager.getInstance().getSelectedText();

                            if (selectedText != null) {
                                String newText = JOptionPane.showInputDialog(panel,
                                        "Enter a new value for the Text Object:",
                                        selectedText.getText());

                                if (newText != null && !newText.trim().isEmpty()) {
                                    selectedText.setText(newText.trim());
                                    panel.repaint();
                                }
                            }
                        }
                    }
                } else if (modeProvider.getCurrentMode().equals("Add Text")) {
                    // Add text at the clicked position
                    TextObjectManager.getInstance().addTextObject(currentName, e.getX(), e.getY());
                    panel.repaint();
                    modeProvider.resetMode();
                } else if (modeProvider.getCurrentMode().equals("Move")) {
                    TextObjectManager.getInstance().selectTextAt(e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (modeProvider.getCurrentMode().equals("Move")) {
                    RelationshipManager.getInstance().selectRelationshipAt(e.getX(), e.getY());
                    if (RelationshipManager.getInstance().getSelectedRelationship() == null) {
                        UseCaseManager.getInstance().selectUseCaseAt(e.getX(), e.getY());
                        if (UseCaseManager.getInstance().getSelectedUseCase() == null) {
                            ActorManager.getInstance().selectActorAt(e.getX(), e.getY());
                            if (ActorManager.getInstance().getSelectedActor() == null) {
                                if (TextObjectManager.getInstance().selectTextAt(e.getX(), e.getY())) {
                                    System.out.println("Selected TextObject: " + TextObjectManager.getInstance().getSelectedText().getText());
                                }
                            }
                        }
                    }

                    Include selectedInclude = IncludeManager.getInstance().selectIncludeAt(e.getX(), e.getY());
                    if (selectedInclude != null) {
                        // Handle the movement logic for Include if needed
                    }
                }
            }

        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (modeProvider.getCurrentMode().equals("Move")) {
                    TextObject selectedText = TextObjectManager.getInstance().getSelectedText();
                    if (selectedText != null) {
                        selectedText.setPosition(e.getX(), e.getY());
                        panel.repaint(); // Redraw the panel to reflect the move
                    } else if (RelationshipManager.getInstance().getSelectedRelationship() != null) {
                        RelationshipManager.getInstance().moveEndpoint(e.getX(), e.getY());
                        panel.repaint();
                    } else if (UseCaseManager.getInstance().getSelectedUseCase() != null) {
                        UseCaseManager.getInstance().moveSelectedUseCase(e.getX(), e.getY());
                        panel.repaint();
                    } else if (ActorManager.getInstance().getSelectedActor() != null) {
                        ActorManager.getInstance().moveSelectedActor(e.getX(), e.getY());
                        panel.repaint();
                    } else if (IncludeManager.getInstance().getSelectedInclude() != null) {
                        IncludeManager.getInstance().moveSelectedInclude(IncludeManager.getInstance().getSelectedInclude(), e.getX(), e.getY());
                        panel.repaint();
                    }
                }
            }

        });

        return panel;
    }

    private JPanel createSidebarPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(180, getHeight())); // Adjust sidebar width dynamically

    // Define common button size
    Dimension buttonSize = new Dimension(140, 30);

    // Add buttons with appropriate actions and listeners
    JButton addActorButton = createStyledButton("Add Actor", buttonSize);
    addActorButton.addActionListener(e -> setModeAndPrompt("Add Actor", "Enter Actor Name:"));
    panel.add(addActorButton);

    JButton addUseCaseButton = createStyledButton("Add Use Case", buttonSize);
    addUseCaseButton.addActionListener(e -> setModeAndPrompt("Add Use Case", "Enter Use Case Name:"));
    panel.add(addUseCaseButton);

    JButton addRelationshipButton = createStyledButton("Add Relationship", buttonSize);
    addRelationshipButton.addActionListener(e -> modeProvider.setCurrentMode("Add Relationship"));
    panel.add(addRelationshipButton);

    JButton addIncludeButton = createStyledButton("Add Include", buttonSize);
    addIncludeButton.addActionListener(e -> modeProvider.setCurrentMode("Add Include"));
    panel.add(addIncludeButton);

    JButton moveButton = createStyledButton("Move", buttonSize);
    moveButton.addActionListener(e -> modeProvider.setCurrentMode("Move"));
    panel.add(moveButton);

    JButton editButton = createStyledButton("Edit", buttonSize);
    editButton.addActionListener(e -> modeProvider.setCurrentMode("Edit"));
    panel.add(editButton);

    JButton addTextButton = createStyledButton("Add Text", buttonSize);
    addTextButton.addActionListener(e -> setModeAndPrompt("Add Text", "Enter Text:"));
    panel.add(addTextButton);

    panel.add(Box.createVerticalGlue()); // Ensure buttons are at the top

    return panel;
}

private void setModeAndPrompt(String mode, String prompt) {
    modeProvider.setCurrentMode(mode);
    currentName = JOptionPane.showInputDialog(getContentPane(), prompt);
}


    private JPanel createFilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.add(createIconButton("New", "path_to_save_icon.png"));
        panel.add(createIconButton("Open", "path_to_open_icon.png"));
        panel.add(createIconButton("Save", "path_to_save_icon.png"));
        panel.add(createIconButton("Export", "path_to_export_icon.png"));
        panel.add(createIconButton("Exit", "path_to_exit_icon.png"));
        return panel;
    }

    private JPanel createEditPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.add(createIconButton("Undo", "path_to_undo_icon.png"));
        panel.add(createIconButton("Redo", "path_to_redo_icon.png"));
        panel.add(createIconButton("Copy", "path_to_copy_icon.png"));
        panel.add(createIconButton("Paste", "path_to_paste_icon.png"));
        return panel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.add(createIconButton("ZoomIn", "path_to_zoom_in_icon.png"));
        panel.add(createIconButton("ZoomOut", "path_to_zoom_out_icon.png"));
        return panel;
    }

    private JPanel createToolsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.add(createIconButton("Tool 1", "path_to_tool_1_icon.png"));
        panel.add(createIconButton("Tool 2", "path_to_tool_2_icon.png"));
        return panel;
    }

    private JPanel createHelpPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.add(createIconButton("Documentation", "path_to_docs_icon.png"));
        panel.add(createIconButton("About", "path_to_about_icon.png"));
        return panel;
    }

    private JPanel createIconButton(String label, String iconPath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));  // Light gray background

        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH); // Reduced icon size
        icon = new ImageIcon(resizedImg);

        JButton button = new JButton(label);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(100, 30)); // Adjusted button size for better layout
        button.setBackground(new Color(230, 230, 230)); // Light background for the button
        button.setForeground(new Color(50, 50, 50));  // Dark gray text
        panel.add(button, BorderLayout.CENTER);

        return panel;
    }

    private JButton createStyledButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setMaximumSize(size); // Set maximum size
        button.setMinimumSize(size); // Set minimum size
        button.setPreferredSize(size); // Set preferred size
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UseCaseDiagramUI().setVisible(true));
    }
}
