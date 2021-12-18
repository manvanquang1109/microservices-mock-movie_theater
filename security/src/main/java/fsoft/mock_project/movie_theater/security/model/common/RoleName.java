package fsoft.mock_project.movie_theater.security.model.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleName {
    CUSTOMER("CUSTOMER"), MEMBER("MEMBER"), EMPLOYEE("EMPLOYEE"), ADMIN("ADMIN");
    public String roleName;
}
