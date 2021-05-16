package tn.esprit.consomitounsi.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.esprit.consomitounsi.modal.Category;
import tn.esprit.consomitounsi.modal.OrderStatus;
import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.PayementType;
import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.modal.UploadFile;
import tn.esprit.consomitounsi.modal.User;
import tn.esprit.consomitounsi.service.CategoryService;
import tn.esprit.consomitounsi.service.PayementService;
import tn.esprit.consomitounsi.service.ProduitService;
import tn.esprit.consomitounsi.service.UserService;
@CrossOrigin("*")
@RestController

public class ProduitController {
    
	@Autowired 
	ProduitService produitService;
	@Autowired 
	UserService userService;
	@Autowired 
	PayementService payementService;

	@Autowired 
	CategoryService categoryService;
	
	@PostMapping("/buy") 
	@ResponseBody 
	public Object createOrder(@RequestParam("productId") Long id,@RequestParam("quantity") int quantity,@RequestParam("payementType") String payementType,@RequestParam("userID") Long iduser) { 
		Produit produit =produitService.findById(id);
		User user=userService.findUserById(iduser);
		if(quantity > 0 && produit.getQuantity() >= quantity) {
			Payement payement = new Payement();
			payement.setQuantity(quantity);
			try {
				payement.setPayementType(PayementType.valueOf(payementType));		
			}catch (Exception e) {
				Map<String, String> errorDetails= new HashMap<>();
				errorDetails.put("message", "Type de payement non definit");
				return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); 
				}
			payement.setAmount(produit.getPrixProduit());
		    payement.setStatus(OrderStatus.Created);
			payement.setProduct(produit);
			payement.setUser(user);
			return payementService.addPayement(payement);
		}

		return null ; 
	}
	
	

	public int getAvailableQuantity(Produit produit) {
		// appel api quatite restante du produit (firas)
		return produit.getQuantity();
	}


	@GetMapping("/retrieve-all-Produit") 
	@ResponseBody 
	public List<Produit> getProduit() { 
		return produitService.retrieveAllProduit(); 
	}
	
	@GetMapping("/retrieve-best-Produit") 
	@ResponseBody 
	public List<Produit> getBestProduct() { 
		return produitService.getBestProduct(); 
	}
	
	
	
	@PostMapping("/add-Produit")
	@ResponseBody
	@Transactional
	public Object addProduit(@RequestBody Produit a) {
		Category category =categoryService.findCategoryById(a.getCategoryProduit().getIdCategory());
		a.setDateCreation(null);
		if(category == null)
		{
			Map<String, String> errorDetails= new HashMap<>();
			errorDetails.put("message", "category non definit");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}
			Produit produit = produitService.addProduit(a);
		return produit ; 
	}
	
	
	
	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public List<Produit> removeProduit(@PathVariable("produit-id")  long produitId) {
		return  produitService.DeleteProduit(produitId);

	}

	@PostMapping("/modify-Produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody Produit produit) {
		produit.setDateCreation(null);
		return produitService.updateProduit(produit);
	}

	@PostMapping("/file")
	@ResponseBody
	@Transactional
	public Object  uploadImg (@RequestParam("file") @Nullable  MultipartFile file,@RequestParam("identifier") Long id) {
			Produit produit =produitService.findById(id);
		if(produit==null)
		{
			Map<String, String> errorDetails= new HashMap<>();
			errorDetails.put("message", "produit non definit");
			return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		}
		if(file!=null) {
			UploadFile image =produit.getImage();
			
			if(image==null)
			{
				image= new UploadFile();
				image.setProduit(produit);
				produit.setImage(image);
			}

			try {
				image.setData(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			image.setName(file.getOriginalFilename());
			
			produitService.saveImage(image);

			produitService.updateProduit(produit);
		}


		return produit;
	}

	@GetMapping("/retrieve-Produit/{category-id}")
	@ResponseBody
	public Produit retrieveProduit(@PathVariable("produit-id") int produitId) {
		return produitService.findById(produitId);  
	}


	@GetMapping("/search/{nom}")
	@ResponseBody

	public List<Produit> getAdsbyLocation(@PathVariable("nom") String nom)
	{
		List<Produit> list = produitService.retrieveProduitByNom(nom);
		return list; 
	}


}