package fsoft.mock_project.movie_theater.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRegisterReq {
    private String username;
    private String password;
    private String roleName;
}
