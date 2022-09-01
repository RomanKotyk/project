package testCommands;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class RensponseWrapper extends AbstractResponseWrapper{
    private Map<String, String> params = new HashMap<>();
    private HttpSession session = new SessionWrapper();

    public HttpSession getSession() {
        return session;
    }

    public void addParam(String key, String value){
        params.put(key, value);
    }

    public String getParameter(String s) {
        return params.get(s);
    }
}
