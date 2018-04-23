package dev.collegue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.entite.Vote;
import dev.collegue.repository.VoteRepository;

@RestController
@CrossOrigin
@RequestMapping("/vote")
public class VoteController {

	@Autowired
	private VoteRepository voteRepo;

	@GetMapping
	public List<Vote> listerexemples() {
		return this.voteRepo.findTop10ByOrderByIdDesc();
	}

}
