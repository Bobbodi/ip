import java.util.Objects;

import resources.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for main GUI
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bobbodi bob;
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image bobImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaBobbodi.png")));

    /**
     * Initialise main window to print out initialise statement
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(DialogBox.getBobbodiDialog(Constants.INITIALISE, bobImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBobbodi(Bobbodi b) {
        bob = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        if (response.equals(Constants.BYE)) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.5),
                            e -> Platform.exit())
            );
            timeline.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobbodiDialog(response, bobImage)
        );
        userInput.clear();
    }



}
