package web.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class UserRoleForBoard {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Boards board;
    private String role;

    //users
    @OneToMany (mappedBy = "userRoleForBoard")
    private Collection<Boards_Users> boardUsersList = new ArrayList<Boards_Users>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boards getBoard() {
        return board;
    }

    public void setBoard(Boards board) {
        this.board = board;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Collection<Boards_Users> getBoardUsersList() {
        return boardUsersList;
    }

    public void setBoardUsersList(Collection<Boards_Users> boardUsersList) {
        this.boardUsersList = boardUsersList;
    }
}
