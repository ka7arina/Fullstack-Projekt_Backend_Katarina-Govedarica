package noseryoung.ch.vinyl_store.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is the model of the user table.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String  password;

    @Column(name = "address")
    private String  address;

    @Column(name = "postcode")
    private int  postcode;

    @Column(name = "city")
    private String  city;

    @Column(name = "country")
    private String  country;


    @ManyToOne
    @JoinColumn(name = "ID_role",referencedColumnName = "role_ID")
    private UserRolesEntity user_has_role;
}