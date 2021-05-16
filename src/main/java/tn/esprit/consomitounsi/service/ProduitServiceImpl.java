package tn.esprit.consomitounsi.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.consomitounsi.modal.Category;
import tn.esprit.consomitounsi.modal.Payement;
import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.modal.UploadFile;
import tn.esprit.consomitounsi.repository.CategoryRepository;
import tn.esprit.consomitounsi.repository.ProduitRepository;
import tn.esprit.consomitounsi.repository.UploadFileRepository;


@Service("produitService")
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	ProduitRepository produitRepository;

	@Autowired
	UploadFileRepository  uploadFileRepository;
	
	@Override
	public Produit addProduit(Produit produit) {
		produitRepository.save(produit) ;
		return produit;
	}
	@Override
	public List<Produit> DeleteProduit(long id) {
		Produit produit = produitRepository.findById((long) id).get();
		UploadFile image =produit.getImage();
		produit.setImage(null);
		if(image!=null)
			uploadFileRepository.delete(image);
		produitRepository.delete(produit);
		return retrieveAllProduit(); 

	}

	@Override
	public Produit updateProduit (Produit produit) {
		return produitRepository.save(produit);
	}
	
	@Override
	public List<Produit> retrieveAllProduit() {
		List<Produit> produits=(List<Produit>)produitRepository.findAll();
		return produits;
	}
	
	@Override
	public List<Produit> getBestProduct() {
		List<Produit> produits=(List<Produit>)produitRepository.getBestProduct();
		return produits;
	}
	
	

	@Override
	public UploadFile saveImage(UploadFile p) {
		return uploadFileRepository.save(p);

	}

	@Override
	public Produit findById(long id) {
		return produitRepository.findById(id).orElse(null);
	}


	@Override
	public List<Produit> retrieveProduitByNom(String l) {
		return this.produitRepository.Search(l);
	}





}