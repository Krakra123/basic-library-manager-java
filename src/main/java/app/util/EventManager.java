package app.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.interfaces.ICallback;

public class EventManager {
    
    private HashMap<String, List<ICallback>> eventMap;

    public EventManager() {
        eventMap = new HashMap<>();
    }

    public void RegisterEvent(String event, ICallback callback) {
        eventMap.put(event, new ArrayList<>());
        eventMap.get(event).add(callback);
    }

    public void RaiseEvent(String event) {
        List<ICallback> callbacks = eventMap.get(event);
        if (callbacks == null) {
            System.out.printf("Event \'%s\' is not available\n", event);
            return;
        }

        for (ICallback callback : callbacks) {
            callback.Call();
        }
    }
}
