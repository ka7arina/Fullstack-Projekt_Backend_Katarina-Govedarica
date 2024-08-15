package noseryoung.ch.vinyl_store.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the model of the user roles table.
 */
@Getter
@Setter
@Entity
@Table(name = "user_roles")
public class UserRolesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_ID")
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "user_has_role")
    private List<UserEntity> role_assigned_to_users = new ArrayList<>();
}