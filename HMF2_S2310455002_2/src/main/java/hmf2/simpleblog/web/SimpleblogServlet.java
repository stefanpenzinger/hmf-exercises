package hmf2.simpleblog.web;

import hmf2.simpleblog.business.BlogEntry;
import hmf2.simpleblog.dao.BlogEntryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Controller Servlet.*
 *
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
@WebServlet("/cmd/*")
public class SimpleblogServlet extends HttpServlet {

    private final Logger log = LogManager.getFormatterLogger(SimpleblogServlet.class);
    private final String VIEWS_DIR = "/WEB-INF/views/";

    BlogEntryDAO blogEntryDAO;

    private void forward(HttpServletRequest req, HttpServletResponse res,
                         String page) throws ServletException, IOException {

        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                VIEWS_DIR + page);
        rd.forward(req, res);
    }

    @Override
    public void init() {
        //blogEntryDAO = MemoryBlogEntryDAOImpl.getInstance();
        //blogEntryDAO = MySqlBlogEntryDAOImpl.getInstance();

        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        blogEntryDAO = (BlogEntryDAO) context.getBean("blogEntryDAO");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String module = request.getPathInfo();
        if (null == module) {
            module = "list";
        } else {
            module = module.substring(1);
        }

        if (module.equals("admin")) {
            doAdmin(request, response);
        } else if (module.equals("list")) {
            doList(request, response);
        } else {
            doList(request, response);
        }

    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("blogEntries", blogEntryDAO.getAllBlogEntries());
        forward(request, response, "list.jsp");

    }

    private void showAdminMenu(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("blogEntries", blogEntryDAO.getAllBlogEntries());
        forward(request, response, "adminMenu.jsp");
    }

    private void doAdmin(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String cmd = request.getParameter("cmd");

        if (null == cmd) {
            cmd = "menu";
        }

        switch (cmd) {
            case "add":
                SimpleToken.set(request);
                forward(request, response, "addBlogEntry.jsp");
                break;
            case "do-add":
                if (SimpleToken.isValid(request)) {
                    SimpleToken.remove(request);
                    BlogEntry logEntry = new BlogEntry();
                    logEntry.setContents(request.getParameter("contents"));
                    blogEntryDAO.addBlogEntry(logEntry);
                }

                showAdminMenu(request, response);
                break;
            case "update": {

                int id = -1;
                boolean error = false;
                try {
                    id = Integer.parseInt(request.getParameter("entryId"));
                    request.setAttribute("blogEntry", blogEntryDAO.getBlogEntry(id));
                    SimpleToken.set(request);
                } catch (NumberFormatException e) {
                    log.error("Non numeric id");
                    error = true;
                } catch (RuntimeException e) {
                    log.error("DAO Runtime Exception: " + e);
                    error = true;
                } finally {
                    if (error) {
                        showAdminMenu(request, response);
                    } else {
                        forward(request, response, "updateBlogEntry.jsp");
                    }
                }
                break;
            }
            case "do-update":
                if (SimpleToken.isValid(request)) {
                    SimpleToken.remove(request);

                    var blogEntryToUpdate = blogEntryDAO.getBlogEntry(Integer.parseInt(request.getParameter("id")));
                    blogEntryToUpdate.setContents(request.getParameter("contents"));
                    blogEntryToUpdate.setTimestamp(new Date());

                    blogEntryDAO.updateBlogEntry(blogEntryToUpdate);
                }

                showAdminMenu(request, response);
                break;
            case "delete": {

                int id = -1;
                boolean error = false;
                try {
                    id = Integer.parseInt(request.getParameter("entryId"));
                    request.setAttribute("blogEntry", blogEntryDAO.getBlogEntry(id));
                    SimpleToken.set(request);
                } catch (NumberFormatException e) {
                    log.error("Non numeric id");
                    error = true;
                } catch (RuntimeException e) {
                    log.error("DAO Runtime Exception: " + e);
                    error = true;
                } finally {
                    if (error) {
                        showAdminMenu(request, response);
                    } else {
                        forward(request, response, "deleteBlogEntry.jsp");
                    }
                }
                break;
            }
            case "do-delete": {

                int id = -1;
                try {
                    if (SimpleToken.isValid(request)) {
                        SimpleToken.remove(request);
                        id = Integer.parseInt(request.getParameter("entryId"));
                        BlogEntry blogEntry = blogEntryDAO.getBlogEntry(id);
                        if (blogEntry != null) {
                            blogEntryDAO.removeBlogEntry(blogEntry);
                        }
                    }
                } catch (NumberFormatException e) {
                    log.error("Non numeric id");
                } catch (RuntimeException e) {
                    log.error("DAO Runtime Exception: " + e);
                } finally {
                    showAdminMenu(request, response);
                }
                break;
            }
            default:  // includes cmd "menu"

                showAdminMenu(request, response);
                break;
        }

    }

}
