package tn.esprit.consomitounsi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.Cagnotte;

@Repository
public interface CagnotteRepository extends JpaRepository<Cagnotte, Long> {

}
