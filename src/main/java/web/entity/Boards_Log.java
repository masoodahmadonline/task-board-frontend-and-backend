package web.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: syncsys
 * Date: 11/26/13
 * Time: 12:18 AM
 * To change this template use Attachment | Settings | Attachment Templates.
 */
@Entity
public class Boards_Log {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date log_time;
    private String description;
    private Long logBy;
    private Long boardLogId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLog_time() {
        return log_time;
    }

    public void setLog_time(Date log_time) {
        this.log_time = log_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLogBy() {
        return logBy;
    }

    public void setLogBy(Long logBy) {
        this.logBy = logBy;
    }

    public Long getBoardLogId() {
        return boardLogId;
    }

    public void setBoardLogId(Long boardLogId) {
        this.boardLogId = boardLogId;
    }
}
