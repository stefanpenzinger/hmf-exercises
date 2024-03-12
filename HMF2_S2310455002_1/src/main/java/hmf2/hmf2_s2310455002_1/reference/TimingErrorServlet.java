package hmf2.hmf2_s2310455002_1.reference;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "TimingErrorServlet", value = "/TimingErrorExample")
public class TimingErrorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final long DEFAULT_TIME = 10000;
    private String instanceParameter;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String localParameter;

        String timeStr = request.getParameter("time");
        long time;
        try {
            time = Long.parseLong(timeStr) * 1000;
        } catch (Exception e) {
            time = DEFAULT_TIME;
        }

        instanceParameter = request.getParameter("param");
        localParameter = instanceParameter;

        // "wait <time> milliseconds:"

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }

        response.setContentType("text/html");
        response.getWriter().print("instanceParam: " + instanceParameter + "<br>" +
                "localParam: " + localParameter);
    }
}