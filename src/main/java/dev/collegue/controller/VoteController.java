package dev.collegue.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.entite.Vote;
import dev.collegue.repository.VoteRepository;

@RestController
@CrossOrigin
@RequestMapping("/votes")
public class VoteController {

	@Autowired
	private VoteRepository voteRepo;

	@GetMapping
	public List<Vote> listerVote() {
		return this.voteRepo.findTop3ByOrderByIdDesc();
	}

	@RequestMapping(method = RequestMethod.GET, params = ("since"))
	public List<Vote> afficheVoteSinceId(@RequestParam(value = "since") int id) {
		List<Vote> listeReverse = this.voteRepo.findByIdGreaterThan(id);
		Collections.reverse(listeReverse);
		return listeReverse;
	}

}
