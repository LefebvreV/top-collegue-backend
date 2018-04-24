package dev.collegue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegue.entite.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

	List<Vote> findTop3ByOrderByIdDesc();

	List<Vote> findByIdGreaterThan(Integer id);

}
