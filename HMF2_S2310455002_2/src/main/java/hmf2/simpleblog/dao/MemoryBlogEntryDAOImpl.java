package hmf2.simpleblog.dao;

import hmf2.simpleblog.business.BlogEntry;

import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Vector-based Singleton DAO Implementation.
 *
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
public class MemoryBlogEntryDAOImpl implements BlogEntryDAO {

    private Vector<BlogEntry> blogEntries; // Vector is syncronized
    private int nextId;

    // singleton pattern: exactly one instance of this class is accessible

    private static MemoryBlogEntryDAOImpl theInstance;

    private MemoryBlogEntryDAOImpl() {
        this.blogEntries = new Vector<BlogEntry>();
        this.nextId = 1;
    }

    public static MemoryBlogEntryDAOImpl getInstance() {
        if (theInstance == null) {
            theInstance = new MemoryBlogEntryDAOImpl();
        }
        return theInstance;
    }

    // end singleton pattern

    private int searchBlogEntry(int id) {
        ListIterator<BlogEntry> it = blogEntries.listIterator();
        int index = 0;
        boolean found = false;

        while (!found && it.hasNext()) {
            index = it.nextIndex();
            found = (it.next().getId() == id);
        }

        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    @Override
    public void addBlogEntry(BlogEntry blogEntry) {
        blogEntry.setId(this.nextId++);
        blogEntries.add(blogEntry);
    }

    @Override
    public BlogEntry getBlogEntry(int id) {
        return blogEntries.elementAt(searchBlogEntry(id));
    }

    @Override
    public List<BlogEntry> getAllBlogEntries() {
        return blogEntries;
    }

    @Override
    public void updateBlogEntry(BlogEntry blogEntry) {
        blogEntries.setElementAt(blogEntry, searchBlogEntry(blogEntry.getId()));
    }

    @Override
    public void removeBlogEntry(BlogEntry blogEntry) {
        blogEntries.removeElementAt(searchBlogEntry(blogEntry.getId()));
        blogEntry.setId(-1);
    }
}
