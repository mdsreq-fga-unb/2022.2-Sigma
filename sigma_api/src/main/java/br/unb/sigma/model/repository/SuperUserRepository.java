package br.unb.sigma.model.repository;

import br.unb.sigma.model.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserRepository extends JpaRepository<SuperUser, Integer> {

}
