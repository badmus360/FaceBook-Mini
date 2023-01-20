package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String userId;
    private String firstname;
    private String lastname;
    private String contact;
    private String password;
    private String dob;
    private String gender;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PostEntity> posts;
}
