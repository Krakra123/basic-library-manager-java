package app.managers;

public class StateManager {
    
    public static enum State {
        NONE,
        LOGIN,
        REGISTER,
        DIALOG,
        MENU,
        LIBRARY
    }

    private static State state;
    public static State getState() {
        return state;
    }

    public static void setState(State state) {
        StateManager.state = state;
    }
}
