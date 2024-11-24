package app.managers;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import app.interfaces.ICallback;
import javafx.scene.input.KeyEvent;

public class InputManager {

	public static enum KeyCode {
		UP, DOWN, LEFT, RIGHT, ENTER, ESCAPE;
	}
	
	public static enum State {
        ACTIVE, INACTIVE;
    }
	
	private static Map<KeyCode, ICallback> keyCallbacks = new HashMap<>();
	
	public InputManager() {
		keyCallbacks.put(KeyCode.UP,  () -> System.out.println("press up"));
		keyCallbacks.put(KeyCode.DOWN,  () -> System.out.println("press down"));
		keyCallbacks.put(KeyCode.LEFT,  () -> System.out.println("press left"));
		keyCallbacks.put(KeyCode.RIGHT,  () -> System.out.println("press right!"));
		keyCallbacks.put(KeyCode.ENTER,  () -> System.out.println("press enter"));
	}
	
	static void handleInput(KeyEvent keyEvent, ICallback icallback, State state) {
		// Map the JavaFX KeyCode to the custom KeyCode enum
        KeyCode mappedKey = null;
        try {
            mappedKey = KeyCode.valueOf(keyEvent.getCode().toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Unrecognized key: " + keyEvent.getCode());
        }
        
     // If the key is mapped and has a callback, execute the callback
        if (mappedKey != null && keyCallbacks.containsKey(mappedKey)) {
            keyCallbacks.get(mappedKey).Call();
        } else {
            System.out.println("No action registered for key: " + keyEvent.getCode());
        }
	}
}
