---

# **UML Diagram Editor**

The UML Diagram Editor is an advanced JavaFX-based desktop application designed to assist software developers, designers, and students in creating UML diagrams with ease. The application allows users to add components such as class boxes and connections, customize them, and export the diagrams as images. It offers a rich set of tools for managing diagram elements, including various arrowheads, connection types, and undo/redo functionality.

---

## **Features**
- **User-Friendly Interface**: Simplified navigation and tool usage for seamless diagram creation.
- **Customizable Components**: Add single-section, two-section, or three-section class boxes.
- **Connection Types**: Draw connections with multiple styles, including:
  - Dependency
  - Association
  - Aggregation
  - Composition
  - Inheritance
  - Implementation
- **Dynamic Connection Heads**: Includes open arrows, closed arrows, and diamond-shaped heads.
- **Real-Time Diagram Updates**: Redraw components and connections instantly.
- **Save & Load**: Save diagrams to a file and reload them anytime.
- **Export to Image**: Export diagrams as high-quality PNG images.
- **Undo/Redo**: Maintain flexibility with action history management.

---

## **Technologies Used**
The project incorporates the following technologies and libraries:

### **Programming Language**
- **Java 13+**: Core programming language used for application logic.

### **Frameworks & Libraries**
- **JavaFX**: For building the user interface, including components like `Canvas`, `ScrollPane`, and `Button`.
- **Lombok**: Simplifies code by reducing boilerplate for getters, setters, and constructors.
- **JavaTuples**: Utilized for managing coordinate pairs and component properties.
- **FXML**: For defining the user interface layout and controls.

### **Build Tools**
- **Maven**: Dependency and build management.

### **Version Control**
- **Git**: Used for version control and project collaboration.

### **Styling**
- **CSS**: Customized JavaFX styles for buttons, labels, and other UI components.

---

## **How It Works**
1. **Setup Components**:
   - Add class boxes to the canvas.
   - Draw connections between the boxes with appropriate relationships.

2. **Customize Connections**:
   - Use the toolbox to select different connection types.
   - Customize connection heads dynamically.

3. **Manage Canvas**:
   - Save diagrams locally and reload them when needed.
   - Export completed diagrams as PNG images.

4. **Interactive Tools**:
   - Preview connections before finalizing.
   - Use undo/redo to correct mistakes.

---

## **How to Run**
### **Prerequisites**
- Java Development Kit (JDK) 13 or above.
- Maven installed.
- JavaFX runtime.

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/uml-diagram-editor.git
   ```
2. Navigate to the project directory:
   ```bash
   cd uml-diagram-editor
   ```
3. Build the project with Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar target/uml-diagram-editor.jar
   ```

---

## **Future Enhancements**
- Add support for sequence and use-case diagrams.
- Provide more styling options for UML elements.
- Improve canvas management for large-scale diagrams.
- Integration with popular IDEs like IntelliJ IDEA or Eclipse.

---

## **Contributors**
- **Muhammad Rehan** - 22P-9106
- **Zia ul Haq** - 20L-1077
- **Abdul Hanan** - 20L-1338

---

## **License**
This project is licensed under the [MIT License](./LICENSE).

---
