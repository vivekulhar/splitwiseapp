package dev.vivek.springapp.repositories;

import dev.vivek.springapp.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long aLong);
}
