package dev.vivek.springapp.repositories;

import dev.vivek.springapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    User save(User entity);

    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findByPhone(String phone);

    List<User> findAll();
}
