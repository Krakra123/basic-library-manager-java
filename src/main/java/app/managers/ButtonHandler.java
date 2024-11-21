package app.managers;

import app.interfaces.ICallback;

public class ButtonHandler {
    
    private ICallback callback;

    public void Call() {
        if (callback != null) {
            callback.Call();
        } else {
            throw new NullPointerException("Callback null.");
        }
    }

    public ButtonHandler() {

    }

    public ButtonHandler(ICallback callback) {
        this.callback = callback;
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }
}
