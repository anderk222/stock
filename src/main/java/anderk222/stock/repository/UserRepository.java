package anderk222.stock.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByNameOrEmail(String name, String email);

    public Page<User> findAll(Pageable pageable);
}