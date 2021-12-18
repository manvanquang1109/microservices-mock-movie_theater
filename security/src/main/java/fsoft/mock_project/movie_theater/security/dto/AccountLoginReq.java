package fsoft.mock_project.movie_theater.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountLoginReq {
    private String username;
    private String password;
}
