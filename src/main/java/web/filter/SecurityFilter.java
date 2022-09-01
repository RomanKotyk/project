package web.filter;

import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Security filter
 *
 * @author R.Kotyk
 *
 * */
public class SecurityFilter implements Filter {
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    // commands access
    private static Map<Boolean, List<String>> accessMap = new HashMap<>();
    private static List<String> commons = new ArrayList<>();

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        log.debug("Filter initialization starts");

        // roles
        accessMap.put(Boolean.TRUE, asList(fConfig.getInitParameter("admin")));
        accessMap.put(Boolean.FALSE, asList(fConfig.getInitParameter("user")));
        log.trace("Access map --> " + accessMap);

        // commons
        commons = asList(fConfig.getInitParameter("common"));
        log.trace("Common commands --> " + commons);

        log.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
        log.debug("Filter starts");
        if (accessAllowed(request)) {
            log.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessages = "You do not have permission to access the requested resource or invalid command";
            request.setAttribute("errorMessage", errorMessages);

            log.trace("Set the request attribute: errorMessage --> " + errorMessages);

            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String commandName = request.getParameter("action");
        if (commandName == null || commandName.isEmpty())
            return false;

        HttpSession session = httpRequest.getSession();
        if (session == null)
            return false;

        User user = (User) session.getAttribute("user");
        if (user == null){
            return commons.contains(commandName);
        }

        return accessMap.get(user.isAdmin()).contains(commandName) || commons.contains(commandName);
    }

    @Override
    public void destroy() {
        log.debug("Filter destruction starts");
        // do nothing
        log.debug("Filter destruction finished");
    }

    /**
     * Extracts parameter values from string.
     * @param param parameter values string.
     * @return list of parameter values.
     */
    private List<String> asList(String param) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(param);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}
