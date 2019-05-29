package tvAddicts;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.UnknownSeasonException;
// FAZER JUNIT A VERIFICAR EPISODIOS FUNCIONAM, E SE OS PERSONAGENS ESTAO MESMO NOS EPISODIOS
// CRIAR EVENTOS E VERIFICAR SE ESTAO NO PERSONAGEM ENVOLVIDO E NO EPISODIO RELACIONADO
// NO FINAL FAZER ASSERT DO RESULTADO NO EPISODIO COM O RESULTADO NO PERSONAGEM
// TESTAR CASOS EXTREMOS (PERSONAGENS SEM PARTICIPACAO, PERSONAGENS COM PARTICIPACAO
// EM TODOS OS EPISODIOS)
public class EpisodeTest {

	private static Episode ep1;
	private static Show show;
	private static Season season;
	private static Actor a;
	private static RealCharacter ch1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		show = new ShowClass("Big bang theory");
		season = new SeasonClass(0);
		show.addSeason();
		a = new ActorClass("a");
		ch1 = new RealCharacterClass("Pluto", a, 0, show);
		
		try{
			show.addEpisode(1, "Pilot");
			show.addEpisode(1, "EP1");
		} catch (UnknownSeasonException e){
			System.out.println(e.getMessage());
		}
		ep1 = new EpisodeClass("abdfg", 0, show, season);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
