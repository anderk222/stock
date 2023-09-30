package anderk222.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    
}
