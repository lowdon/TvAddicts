package test;

import org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import tvAddicts.*;
import exceptions.NoShowSelectedException;
import exceptions.RepeatedRelationshipException;
import exceptions.SameCharacterException;
import exceptions.UnknownCharacterException;
import exceptions.UnknownSeasonException;

// FAZER JUNIT A VERIFICAR EPISODIOS FUNCIONAM, E SE OS PERSONAGENS ESTAO MESMO NOS EPISODIOS
// CRIAR EVENTOS E VERIFICAR SE ESTAO NO PERSONAGEM ENVOLVIDO E NO EPISODIO RELACIONADO
// NO FINAL FAZER ASSERT DO RESULTADO NO EPISODIO COM O RESULTADO NO PERSONAGEM
// TESTAR CASOS EXTREMOS (PERSONAGENS SEM PARTICIPACAO, PERSONAGENS COM PARTICIPACAO
// EM TODOS OS EPISODIOS)
public class EpisodeTest {

	private static TvAddicts addicts;
	private static ArrayList<String> involvedCharacters;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		addicts = new TvAddictsClass();
		Show bag = new ShowClass("big bag theory");
		addicts.addShow(bag.name());
		addicts.addSeason();
		addicts.addRealCharacter("a", "john johnson", 0);
		involvedCharacters = new ArrayList<String>();
		involvedCharacters.add("a");
		addicts.addEvent("a entra em cena", 1, 1, involvedCharacters.iterator());
		
	}

	@Test
	public void test() throws SameCharacterException, UnknownCharacterException, RepeatedRelationshipException {
		try {
			Show current = addicts.currentShow();
			int seasonNum = addicts.currentShow().seasonsNumber();
			//assert (current.seasonsNumber() == 1); // VERIFICA SE ADICIONOU SEASON NO SETUP
			try {
				addicts.addEpisode(seasonNum, "Pilot");
			} catch (UnknownSeasonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			involvedCharacters = new ArrayList<String>(0);
			involvedCharacters.add("b");
			involvedCharacters.add("c");
			assert (!current.isInRelationWith("b", "c")); // MUST BE FALSE
			current.addRelationship("b", "c");
			assert (current.isInRelationWith("b", "c")); // MUST BE TRUE
			assert (current.isInRelationWith("c", "b")); 
			
		} catch (NoShowSelectedException e) {
			e.getMessage();
		}		
		
	}

}

