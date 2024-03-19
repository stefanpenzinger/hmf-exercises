package hmf2.simpleblog.test;


import hmf2.simpleblog.business.BlogEntry;
import hmf2.simpleblog.dao.BlogEntryDAO;
import hmf2.simpleblog.dao.MemoryBlogEntryDAOImpl;
import hmf2.simpleblog.dao.MySqlBlogEntryDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BlogEntryDAOTest {

    static private BlogEntryDAO blogEntryDAO;

    @BeforeAll
    static public void setup() {
        //blogEntryDAO = MemoryBlogEntryDAOImpl.getInstance();
        blogEntryDAO = MySqlBlogEntryDAOImpl.getInstance();
    }

    @Test
    public void testBlogEntryDAO() {

        BlogEntry e1 = new BlogEntry();
        e1.setContents("Test Entry "+ new Date());

        int len1 = blogEntryDAO.getAllBlogEntries().size();
        blogEntryDAO.addBlogEntry(e1);
        int len2 = blogEntryDAO.getAllBlogEntries().size();

        assertEquals(len1+1, len2);

        BlogEntry e2 = blogEntryDAO.getBlogEntry(e1.getId());
        assertEquals(e1.getContents(), e2.getContents());
        assertEquals(e1, e2);

        blogEntryDAO.removeBlogEntry(e1);
        assertEquals(len1, blogEntryDAO.getAllBlogEntries().size());
    }

}

