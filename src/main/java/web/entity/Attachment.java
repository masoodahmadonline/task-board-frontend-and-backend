package web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: syncsys
 * Date: 11/26/13
 * Time: 12:18 AM
 * To change this template use Attachment | Settings | Attachment Templates.
 */
@Entity
public class Attachment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Tasks parentTask;
    private String name;
    private String description;
    private long size;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tasks getParentTask() {
        return parentTask;
    }

    public void setParentTask(Tasks parentTask) {
        this.parentTask = parentTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
