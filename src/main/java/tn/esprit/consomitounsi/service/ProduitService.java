package tn.esprit.consomitounsi.service;

import java.util.List;

import tn.esprit.consomitounsi.modal.Category;
import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.modal.UploadFile;


public interface ProduitService {

	Produit addProduit(Produit produit);
	List<Produit> DeleteProduit(long id);
	Produit updateProduit(Produit produit);
	List<Produit> retrieveAllProduit();
	UploadFile saveImage(UploadFile p);
	Produit findById(long id);
    List<Produit> getBestProduct();
	List<Produit> retrieveProduitByNom(String l);
}