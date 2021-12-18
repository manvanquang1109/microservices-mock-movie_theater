package fsoft.mock_project.movie_theater.security.service;

import fsoft.mock_project.movie_theater.security.model.Role;
import fsoft.mock_project.movie_theater.security.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find role with id = " + id));
    }
}
