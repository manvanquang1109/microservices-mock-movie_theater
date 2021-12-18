package fsoft.mock_project.movie_theater.security.repository;

import fsoft.mock_project.movie_theater.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
