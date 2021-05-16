package tn.esprit.consomitounsi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.consomitounsi.modal.UploadFile;

@Repository
public interface UploadFileRepository extends CrudRepository<UploadFile, Long> {

}
