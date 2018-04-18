package dev.collegue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.collegue.controller.vm.ActionIhm;
import dev.collegue.entite.Collegue;
import dev.collegue.repository.CollegueRepository;
import dev.collegue.service.CollegueService;

@RestController
@CrossOrigin
@RequestMapping("/collegue")
public class CollegueController {

	@Autowired
	private CollegueRepository colleguetRepo;

	@GetMapping
	public List<Collegue> listerexemples() {
		return this.colleguetRepo.findAll();
	}
	
	@RequestMapping(value="/{pseudo}" , method = RequestMethod.PATCH)
	public Collegue scorer(@PathVariable String pseudo, @RequestBody ActionIhm avis) {
		Collegue update = new Collegue();
		
		if(colleguetRepo.existsByPseudo(pseudo)){
			update = colleguetRepo.findByPseudo(pseudo);
			update = CollegueService.voteControl(avis.getAction(), update);
		}
		colleguetRepo.save(update);
		return update;
	}

}
