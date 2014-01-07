package web.entity;

import javax.persistence.*;


@Entity
public class UserRoleForBoard {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Boards board;
    private String role;
    @ManyToOne
    private Users user;

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
