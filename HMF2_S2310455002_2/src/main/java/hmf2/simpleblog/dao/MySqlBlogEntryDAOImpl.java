package hmf2.simpleblog.dao;

import hmf2.simpleblog.business.BlogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Basic MySQL-based Singleton DAO Implementation.
 *
 * <p>
 * IM430 Hypermedia Frameworks.<br>
 * <a href="http://www.fh-ooe.at/im">Interactive Media</a>.
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
public class MySqlBlogEntryDAOImpl implements BlogEntryDAO {

    static Logger log = LogManager.getFormatterLogger(MySqlBlogEntryDAOImpl.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "admin";
    // private static final String PASS = ""; // XAMPP default
    private static final String PASS = "pass"; // MAMP default
    // private static final String URL = "jdbc:mysql://localhost/simpleblogdb"; // XAMPP
    private static final String URL = "jdbc:mysql://localhost:3306/my_db_name"; // MAMP

    // singleton pattern: exactly one instance of this class is accessible

    private static MySqlBlogEntryDAOImpl theInstance;

    private MySqlBlogEntryDAOImpl() {
        try {
            Class.forName(DRIVER); // load driver class
        } catch (ClassNotFoundException e) {
            log.error("Driver Class " + DRIVER + " not found!");
        }
        log.info("Driver Class " + DRIVER + " successfully loaded!");
    }

    public static MySqlBlogEntryDAOImpl getInstance() {
        if (theInstance == null) {
            theInstance = new MySqlBlogEntryDAOImpl();
        }
        return theInstance;
    }

    // end singleton pattern

    private BlogEntry mapRow(ResultSet rs) throws SQLException {

        BlogEntry blogEntry = new BlogEntry();

        blogEntry.setId(rs.getInt("id"));
        blogEntry.setContents(rs.getString("contents"));
        Timestamp t = rs.getTimestamp("timestamp");
        blogEntry.setTimestamp(new Date(t.getTime()));

        return blogEntry;
    }

    @Override
    public void addBlogEntry(BlogEntry blogEntry) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "insert into blogentries(contents, timestamp) values (?,?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, blogEntry.getContents());
            stmt.setTimestamp(2, new Timestamp(blogEntry.getTimestamp()
                    .getTime()));
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                blogEntry.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public BlogEntry getBlogEntry(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "select id, contents, timestamp from blogentries where id= ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();
            return mapRow(rs);
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public List<BlogEntry> getAllBlogEntries() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "select id, contents, timestamp from blogentries order by timestamp";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<BlogEntry> blogEntries = new ArrayList<>();
            while (rs.next()) {
                blogEntries.add(mapRow(rs));
            }
            return blogEntries;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public void updateBlogEntry(BlogEntry blogEntry) {
        log.error("Method updateBlogEntry is not implemented.");
    }

    @Override
    public void removeBlogEntry(BlogEntry blogEntry) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "delete from blogentries where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, blogEntry.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }

            blogEntry.setId(-1);
        }

    }
}

