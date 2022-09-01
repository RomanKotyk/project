package testCommands;

import java.util.HashMap;
import java.util.Map;

public class SessionWrapper extends AbstractSessionWrapper{
    private Map<String, Object> values = new HashMap<>();

    @Override
    public Object getAttribute(String s) {
        return values.get(s);
    }

    @Override
    public void removeAttribute(String s) {
        values.remove(s);
    }

    @Override
    public void setAttribute(String s, Object o) {
        values.put(s, o);
    }
}
