package tn.esprit.consomitounsi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.esprit.consomitounsi.modal.Produit;
import tn.esprit.consomitounsi.modal.User;
import tn.esprit.consomitounsi.modal.Vote;

@Service
public interface VoteService {
	
	    List<Vote> retrieveAllVote();
	    Vote findVoteById(int r);
	    Vote findLikeByProduitAndUser(Produit r, User user);
	    Vote addVoteLike(int idp , int idu, int note);
	  
	     
	

}
