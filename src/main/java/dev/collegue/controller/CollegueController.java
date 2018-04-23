package dev.collegue.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dev.collegue.controller.vm.ActionIhm;
import dev.collegue.controller.vm.CollegueJson;
import dev.collegue.controller.vm.CollegueJsonApi;
import dev.collegue.entite.Collegue;
import dev.collegue.entite.Vote;
import dev.collegue.repository.CollegueRepository;
import dev.collegue.repository.VoteRepository;
import dev.collegue.service.CollegueService;

@RestController
@CrossOrigin
@RequestMapping("/collegue")
public class CollegueController {

	@Autowired
	private CollegueRepository colleguetRepo;
	@Autowired
	private VoteRepository voteRepo;

	@GetMapping
	public List<Collegue> listerexemples() {
		return this.colleguetRepo.findAll();
	}
	
	@RequestMapping(value = "/{pseudo}", method = RequestMethod.GET)
	public Collegue afficheCollegue(@PathVariable String pseudo) {
		return this.colleguetRepo.findByPseudo(pseudo);
	}

	@RequestMapping(value="/{pseudo}" , method = RequestMethod.PATCH)
	public Collegue scorer(@PathVariable String pseudo, @RequestBody ActionIhm avis) {
		Collegue update = new Collegue();
		
		if(colleguetRepo.existsByPseudo(pseudo)){
			update = colleguetRepo.findByPseudo(pseudo);
			update = CollegueService.voteControl(avis.getAction(), update);
		}
		Vote vote = new Vote(update, avis.getAction(), update.getScore());
		voteRepo.save(vote);
		colleguetRepo.save(update);
		return update;
	}


	@RequestMapping(value = "/nouveau", method = RequestMethod.POST)
	public void ajouter(@RequestBody CollegueJson collegue) {

		final String uri = "http://collegues-api.cleverapps.io/collegues";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CollegueJsonApi[]> response = restTemplate.getForEntity(uri, CollegueJsonApi[].class);
		List<CollegueJsonApi> list = Arrays.asList(response.getBody());
		
		Optional<CollegueJsonApi> collegueTrouve = list.stream().filter((CollegueJsonApi col) -> {
			return col.getMatricule().equals(collegue.getMatricule());
		}).findFirst();

		collegueTrouve.ifPresent(c -> {
			Collegue nouveau = new Collegue();
			nouveau.setPseudo(collegue.getPseudo());
			nouveau.setScore(0);
			nouveau.setAdresse(c.getAdresse());
			nouveau.setEmail(c.getEmail());
			nouveau.setNom(c.getNom());
			nouveau.setPrenom(c.getPrenom());
			nouveau.setUrl(c.getPhoto());
			colleguetRepo.save(nouveau);
		});

	}

}
