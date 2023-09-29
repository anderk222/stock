package anderk222.stock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import anderk222.stock.model.Role;

public interface RolRepository extends JpaRepository<Role, Long> {

    public Page<Role> findAll(Pageable pageable);
   
}