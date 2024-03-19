package hmf2.simpleblog.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Token to ensure Form workflow.
 *
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
public class SimpleToken {

    public static void set(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        long systime = System.currentTimeMillis();
        String token = String.valueOf(systime);
        token += session.getId();
        req.setAttribute("token", token);
        session.setAttribute("token", token);
    }

    public static boolean isValid(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        String requestToken = req.getParameter("token");
        String sessionToken = (String) session.getAttribute("token");
        if (requestToken == null || sessionToken == null)
            return false;
        else {
            requestToken = requestToken.trim();
            sessionToken = sessionToken.trim();
            return requestToken.equals(sessionToken);
        }
    }

    public static void remove(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.removeAttribute("token");
    }

}

