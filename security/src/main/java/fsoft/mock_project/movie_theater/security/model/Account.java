package fsoft.mock_project.movie_theater.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generator = "account-generator")
    @GenericGenerator(
            name = "account-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "ACC"),
            strategy = "fsoft.mock_project.movie_theater.security.generator.StringGenerator"
    )
    private String accountId;

    //    @Column(unique = true)
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
//
//    @Column(length = 1)
//    private Integer status;
}
