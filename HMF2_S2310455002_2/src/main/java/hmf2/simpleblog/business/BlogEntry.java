package hmf2.simpleblog.business;

import java.util.Date;
import java.util.Objects;

/**
 * Domain class.
 *
 * <p>
 * HMF2 Hypermedia Frameworks.<br>
 * </p>
 *
 * @author Rimbert Rudisch-Sommer
 */
public class BlogEntry {

    private int id;
    private String contents;
    private Date timestamp;

    public BlogEntry() {
        this.id = -1;
        this.contents = "";
        this.timestamp = new Date();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogEntry blogEntry = (BlogEntry) o;
        return Objects.equals(contents, blogEntry.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}
