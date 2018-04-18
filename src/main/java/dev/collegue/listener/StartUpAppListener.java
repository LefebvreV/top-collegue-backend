package dev.collegue.listener;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.collegue.entite.Collegue;

@Component
public class StartUpAppListener {

	@PersistenceContext
	private EntityManager em;

	@EventListener(ContextRefreshedEvent.class)
	@Transactional
	public void contextRefreshedEvent() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("configWeb.xml");

		Stream.of(Collegue.class).flatMap(classe -> context.getBeansOfType(classe).values().stream())
				.forEach(em::persist);
		context.close();
	}
}