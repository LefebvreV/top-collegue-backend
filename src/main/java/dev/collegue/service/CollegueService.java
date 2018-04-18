package dev.collegue.service;

import dev.collegue.entite.Avis;
import dev.collegue.entite.Collegue;

public class CollegueService {

	public static Collegue voteControl(Avis avis, Collegue collegue) {
		if (avis.equals(Avis.AIMER)) {
			collegue.setScore(collegue.getScore() + 10);
		}
		if (avis.equals(Avis.DETESTER)) {
			collegue.setScore(collegue.getScore() - 5);
		}

		return collegue;
	}
}
