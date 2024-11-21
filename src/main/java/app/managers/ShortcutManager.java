package app.managers;

import app.interfaces.ICallback;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

@SuppressWarnings("exports")
public class ShortcutManager {
    public static Scene scene;

    public static void updateScene(Scene s) {
        scene = s;
    }

    public static void handleKeyShortcut(KeyCode key, ICallback callback) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent ke) -> {
            if (ke.getCode() == key) {
                callback.Call();
                ke.consume();
            }
        });
    }

    public static void handleCombinationShortcut(KeyCombination.Modifier comb, KeyCode key, ICallback callback) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCodeCombination(key, comb);
            @Override
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                    callback.Call();
                    ke.consume();
                }
            }
        });
    }
}
