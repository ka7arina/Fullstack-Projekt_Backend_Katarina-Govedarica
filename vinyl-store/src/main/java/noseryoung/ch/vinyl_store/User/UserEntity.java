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

    public String getUsername() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserRolesEntity getUser_has_role() {
        return user_has_role;
    }

    public void setUser_has_role(UserRolesEntity user_has_role) {
        this.user_has_role = user_has_role;
    }
}