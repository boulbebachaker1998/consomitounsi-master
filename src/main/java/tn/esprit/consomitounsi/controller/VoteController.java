package tn.esprit.consomitounsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.consomitounsi.modal.Vote;
import tn.esprit.consomitounsi.service.ProduitServiceImpl;
import tn.esprit.consomitounsi.service.UserService;
import tn.esprit.consomitounsi.service.VoteService;
@CrossOrigin("*")
@RestController
public class VoteController {


	@Autowired
	VoteService voteService ;
	@Autowired
	ProduitServiceImpl produitservice;
	@Autowired
	UserService userService ;

	@GetMapping("/retrieve-all-Vote")
	@ResponseBody
	public List<Vote> getComment() {
		List<Vote> list = voteService.retrieveAllVote();
		return list;
	}

	@GetMapping("/retrieve-vote/{vote-id}")
	@ResponseBody
	public Vote retrieveVote(@PathVariable("vote-id") int voteId) {
		return voteService.findVoteById(voteId);
	}


	@GetMapping("/retrieve-voteByUserandPost/{vote-id}/{voteuser}")
	@ResponseBody
	public Vote retrieveVoteByPostandUser(@PathVariable("vote-id") int voteId, @PathVariable("voteuser") int voteuser) {
		return voteService.findLikeByProduitAndUser(produitservice.findById(voteId),userService.findUserById(voteuser));
	}



	@PostMapping("/add-votelike/{idp}/{idu}/{note}")
	@ResponseBody
	public Vote addVoteLike(@PathVariable("idp") int idp,@PathVariable("idu") int idu,@PathVariable("note") int note) {
		Vote vote = voteService.addVoteLike(idp,idu,note);
		return vote ;
	}

}