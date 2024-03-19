package hmf2.simpleblog.dao;

import hmf2.simpleblog.business.BlogEntry;

import java.util.List;

/**
 * Data Access Object Interface.
 *
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
public interface BlogEntryDAO {

    void addBlogEntry(BlogEntry blogEntry);

    BlogEntry getBlogEntry(int id);

    List<BlogEntry> getAllBlogEntries();

    void updateBlogEntry(BlogEntry blogEntry);

    void removeBlogEntry(BlogEntry blogEntry);
}
