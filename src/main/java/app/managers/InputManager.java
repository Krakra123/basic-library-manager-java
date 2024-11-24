package app.managers;

import app.interfaces.ICallback;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

@SuppressWarnings("exports")
public class InputManager {
    public static Scene scene;

    public static void updateScene(Scene s) {
        scene = s;
    }

    public static void handleKeyShortcut(KeyCode key, StateManager.State state, ICallback callback) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode() == key) {
                if (StateManager.getState() == state) {
                    callback.Call();
                }
                ke.consume();
            }
        });
    }

    public static void handleCombinationShortcut(KeyCombination.Modifier comb, KeyCode key, StateManager.State state, ICallback callback) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(key, comb);
            @Override
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    if (StateManager.getState() == state) {
                        callback.Call();
                    }
                    ke.consume();
                }
            }
        });
    }
}
