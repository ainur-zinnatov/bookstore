package ru.kpfu.bookstore.entities;


        import org.hibernate.validator.constraints.Email;
        import org.hibernate.validator.constraints.NotEmpty;
        import org.springframework.stereotype.Component;

        import javax.persistence.*;
        import javax.validation.constraints.Size;
        import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 128)
    @NotEmpty
    @Column(nullable = false, name="name")
    private String name;

    @Email
    @Size(min = 4, max = 128)
    @Column(unique = true, nullable = false,  name="email")
    private String email;


    @Size(min = 6, max = 64)
    @Column(nullable = false ,  name="password")
    private String password;


    public User(Integer id, String name, String email, String password, String salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User( String email, String password) {

        this.email = email;
        this.password = password;
    }
    public User() {}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}