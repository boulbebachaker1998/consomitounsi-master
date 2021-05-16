package tn.esprit.consomitounsi.service;

import java.util.List;

import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.Produit;


public interface PayementService {

	Payement addPayement(Payement payement);

	Payement updatePayement(Payement payement);

	Payement findPayementById(long id);
	String callStripeAPIBuy(Payement payement);
	List<Payement> retrieveAllPayement(long userid);

}