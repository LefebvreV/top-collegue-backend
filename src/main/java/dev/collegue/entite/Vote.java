package dev.collegue.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@ManyToOne
	private Collegue collegue;
	@Column(name = "AVIS")
	@Enumerated(EnumType.STRING)
	private Avis avis;
	@Column(name = "SCORE")
	private int score;

	public Vote() {

	}

	public Vote(Collegue collegue, Avis avis, int score) {
		this.collegue = collegue;
		this.avis = avis;
		this.score = score;
	}

	/**
	 * @return the collegue
	 */
	public Collegue getCollegue() {
		return collegue;
	}

	/**
	 * @param collegue
	 *            the collegue to set
	 */
	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	/**
	 * @return the avis
	 */
	public Avis getAvis() {
		return avis;
	}

	/**
	 * @param avis
	 *            the avis to set
	 */
	public void setAvis(Avis avis) {
		this.avis = avis;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
