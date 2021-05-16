package tn.esprit.consomitounsi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.modal.OrderStatus;
import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.PayementType;
import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.service.PayementService;
import tn.esprit.consomitounsi.service.ProduitService;
@CrossOrigin("*")
@RestController
public class PayementController {
	@Autowired 
	PayementService payementService;
	@Autowired 
	ProduitService produitService;

	@PostMapping("/add-Payement")
	@ResponseBody
	public Payement addPayement(@RequestBody Payement a) {
		Payement payement = payementService.addPayement(a);
		return payement ; }
	@PutMapping("/modify-Payement")
	@ResponseBody
	public Payement modifyPayement(@RequestBody Payement pay) {
		return payementService.updatePayement(pay);
	}
	@GetMapping("/retrieve-all-Payements") 
	@ResponseBody 
	public List<Payement> getProduit(@RequestParam("userId") Long userid) { 
		return payementService.retrieveAllPayement(userid); 
	}

	@PostMapping("/purchase") 
	@ResponseBody 
	@Transactional
	public Object buyProduit(@RequestParam("payementId") Long id) { 
		Payement payement =payementService.findPayementById(id);

		Map<String, Object> responseDetails = new HashMap<>();
		if(payement==null)
			responseDetails.put("message", "payement does not exists");
		else{	
			Produit produit = payement.getProduct();
			if(payement.getPayementType().equals(PayementType.Online) 
					&& payement.getQuantity() > 0 && produit.getQuantity() >= payement.getQuantity() 
					&& !payement.getStatus().equals(OrderStatus.Executed)) {
				String referenceExterne = payementService.callStripeAPIBuy(payement);
				if(referenceExterne != null)
				{
					produit.setQuantity(produit.getQuantity()-payement.getQuantity());
					payement.setStatus(OrderStatus.Executed);
					payement.setReferenceExterne(referenceExterne);
					payementService.updatePayement(payement);
					produitService.updateProduit(produit);
					responseDetails.put("message", "payement effectué");
					responseDetails.put("payement", payement);
				}
				else
					responseDetails.put("message", "payement non abouti");

			}
			else
				responseDetails.put("message", "payement could not be executed");
		}

		return new ResponseEntity<>(responseDetails, HttpStatus.OK); 
	}

}