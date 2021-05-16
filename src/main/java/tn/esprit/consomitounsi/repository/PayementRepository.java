package tn.esprit.consomitounsi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.modal.User;

@Repository
public interface PayementRepository extends CrudRepository<Payement , Long> {

	@Query("SELECT a FROM Payement a WHERE a.user= :l " )
	List<Payement> getUserPayements(@Param ("l") User user);
}
