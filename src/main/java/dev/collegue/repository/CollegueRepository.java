package dev.collegue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegue.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer> {

	Collegue findByPseudo(String pseudo);

	Boolean existsByPseudo(String pseudo);

}
