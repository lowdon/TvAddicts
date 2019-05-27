import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import tvAddicts.*;
import tvAddicts.Character;
import exceptions.*;

public class Main {
	//Constantes que definem os comandos
	 public static final String HELP    = "helpC";
	 public static final String CURRENT_SHOW = "currentshow";
	 public static final String ADD_SHOW      = "addshow";
	 public static final String SWITCH_TO_SHOW      = "switchtoshow";
	 public static final String ADD_SEASON      = "addseason";
	 public static final String ADD_EPISODE      = "addepisode";
	 public static final String ADD_CHARACTER  = "addcharacter";
	 public static final String ADD_RELATIONSHIP  = "addrelationship";
	 public static final String ADD_ROMANCE  = "addromance";
	 public static final String ADD_EVENT  = "addevent";
	 public static final String ADD_QUOTE  = "addquote";
	 public static final String SEASONS_OUTLINE  = "seasonsoutline";
	 public static final String CHARACTER_RESUME  = "characterresume";
	 public static final String HOW_ARE_THESE_TWO_RELATED  = "howarethesetworelated";
	 public static final String FAMOUS_QUOTES  = "famousquotes";
	 public static final String ALSO_APPEARS_ON  = "alsoappearson";
	 public static final String MOST_ROMANTIC  = "mostromantic";
	 public static final String KING_OF_CGI  = "kingofcgi";
	 public static final String QUIT           = "exit";
	 
	 //Constantes que definem as mensagens para o utilizador
	 public static final String CONTACT_EXISTS = "Contact already exists.";
	 public static final String NAME_NOT_EXIST = "Contact does not exist.";
	 public static final String CONTACT_ADDED = "Contact added.";
	 public static final String CONTACT_REMOVED = "Contact removed.";
	 public static final String CONTACT_UPDATED = "Contact updated.";
	 public static final String BOOK_EMPTY = "Contact book empty.";
	
	 public static void main(String[] args) {   
		 Scanner in = new Scanner(System.in);
		 TvAddicts addicts = new TvAddictsClass();
		 String comm = getCommand(in);
	  
		 while (!comm.equals(QUIT)){
			 switch (comm) {
			 case HELP: 
				 help();
				 break;
			 case CURRENT_SHOW: 
				 currentShow(addicts);
				 break;
			 case ADD_SHOW: 
				 addShow(in,addicts);
				 break;
			 case SWITCH_TO_SHOW: 
				 switchToShow(in,addicts);
				 break;
			 case ADD_SEASON: 
				 addSeason(addicts);
				 break;
			 case ADD_EPISODE: 
				 addEpisode(in,addicts);
				 break;
			 case ADD_CHARACTER: 
				 addCharacter(in,addicts);
				 break;
			 case ADD_RELATIONSHIP: 
				 addRelationship(in,addicts);
				 break;
			 case ADD_ROMANCE: 
				 addRomance(in,addicts);
				 break;
			 case ADD_EVENT: 
				 addEvent(in,addicts);
				 break;
			 case ADD_QUOTE: 
				 addQuote(in,addicts);
				 break;
			 case SEASONS_OUTLINE: 
				 seasonsOutline(in,addicts);
				 break;
			 case CHARACTER_RESUME: 
				 characterResume(in,addicts);
				 break;
			 case HOW_ARE_THESE_TWO_RELATED: 
				 howAreTheseTwoRelated(in,addicts);
				 break;
			 case FAMOUS_QUOTES: 
				 famousQuotes(in,addicts);
				 break;
			 case ALSO_APPEARS_ON: 
				 alsoAppearsOn(in,addicts);
				 break;
			 case MOST_ROMANTIC: 
				 mostRomantic(in,addicts);
				 break;
			 case KING_OF_CGI: 
				 kingOfCGI(in,addicts);
				 break;
			 default:
				 System.out.println("Unknown command. Type help to see available commands.");
			 }
			 System.out.println();
			 comm = getCommand(in);
		 }
		 System.out.println("Bye!");
		 in.close();
	 }
	
	private static String getCommand(Scanner in) {
		String input;
		input = in.nextLine().toLowerCase();
		return input;
	}

	private static void help() {
		System.out.println("currentShow - show the current show");
		System.out.println("addShow - add a new show");
		System.out.println("switchToShow - change the context to a particular show");
		System.out.println("addSeason - add a new season to the current show");
		System.out.println("addEpisode - add a new episode to a particular season of the current show");
		System.out.println("addCharacter - add a new character to a show");
		System.out.println("addRelationship - add a family relationship between characters");
		System.out.println("addRomance - add a romantic relationship between characters");
		System.out.println("addEvent - add a significant event involving at least one character");
		System.out.println("addQuote - add a new quote to a character");
		System.out.println("seasonsOutline - outline the contents of the selected seasons for the selected show");
		System.out.println("characterResume - outline the main information on a specific character");
		System.out.println("howAreTheseTwoRelated - find out if and how two characters may be related");
		System.out.println("famousQuotes - find out which character(s) said a particular quote");
		System.out.println("alsoAppearsOn - which other shows and roles is the same actor on?");
		System.out.println("mostRomantic - find out who is at least as romantic as X");
		System.out.println("kingOfCGI - find out which company has earned more revenue with their CGI virtual actors");
		System.out.println("help - shows the available commands");
		System.out.println("exit - terminates the execution of the program");

	}
	
	private static void currentShow(TvAddicts addicts) {
		try {
			Show currentShow = addicts.currentShow();
			System.out.println(currentShow.name() + ". Seasons: " + currentShow.seasonsNumber() + " Episodes: " + currentShow.episodesNumber());
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
	}
	
	private static void addShow(Scanner in, TvAddicts addicts) {
		String showName = in.nextLine().trim();
		try {
			addicts.addShow(showName);
			System.out.println(showName + " created.");
		}
		catch (ShowAlreadyExistsException exception) {
			 System.out.println("Show already exists!");
		}
	}
	
	private static void switchToShow(Scanner in, TvAddicts addicts) {
		String showName = in.nextLine().trim();
		
		try {
			addicts.switchToShow(showName);
			currentShow(addicts);
		}
		catch (UnknownShowException exception) {
			 System.out.println("Unknown show!");
		}
	}
	
	private static void addSeason(TvAddicts addicts) {
		try {
			addicts.addSeason();
			currentShow(addicts);
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
	}
	
	private static void addEpisode(Scanner in, TvAddicts addicts) {
		int seasonNum = in.nextInt();
		String espisodeName = in.nextLine().trim();
		
		try {
			Episode episode = addicts.addEpisode(seasonNum, espisodeName);
			System.out.println(episode.show().name() + " S" + episode.season().number() + ", Ep" + episode.number() + ": " + episode.name() + ".");
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (UnknownSeasonException exception) {
			 System.out.println("Unknown season!");
		}
	}
	
	private static void addCharacter(Scanner in, TvAddicts addicts) {
		String kind = in.nextLine().trim();
		String characterName = in.nextLine().trim();
		String actorOrCompanyName = in.nextLine().trim();
		int feeOrCost = in.nextInt();
		in.nextLine();
		try {
			if (kind.equals("real")) {
				Character character = addicts.addRealCharacter(characterName, actorOrCompanyName, feeOrCost);
				System.out.println(character.characterName() + " is now parte of " + character.show() + ". This is " + ((RealCharacter)character).actor().name() + " role " + ((RealCharacter)character).actor().rolesNumber() + ".");
			}
			else if (kind.equals("real")) {
				Character character = addicts.addVirtualCharacter(characterName, actorOrCompanyName, feeOrCost);
				System.out.println(character.characterName() + " is now parte of " + character.show() + ". This is  a virtual actor.");
			}
			else {
				System.out.println("Unknown actor category!");
			}
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (DuplicateCharacterException exception) {
			 System.out.println("Duplicate character names are not allowed!");
		}
		catch (NegativeFeeException exception) {
			 System.out.println("Slavery is long gone and this is outrageous!");
		}
	}
	
	private static void addRelationship(Scanner in, TvAddicts addicts) {
		String character1Name = in.nextLine().trim();
		String character2Name = in.nextLine().trim();
		try {
			addicts.addRelationship(character1Name, character2Name);
			System.out.println(addicts.character(character1Name).characterName() + "has now " + addicts.character(character1Name).kidsNumber() + " kids. " + addicts.character(character2Name).characterName() + " has now " + addicts.character(character2Name).parentsNumber() + " parent(s).");
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (SameCharacterException exception) {
			 System.out.println(character1Name + " cannot be parent and child at the same time!");
		}
		catch (UnknownCharacterException exception) {
			 System.out.println("Who is " + exception.getMessage());
		}
		catch (RepeatedRelationshipException exception) {
			 System.out.println("What else is new? We already know about those two...");
		}
	}
	
	private static void addRomance(Scanner in, TvAddicts addicts) {
		String character1Name = in.nextLine().trim();
		String character2Name = in.nextLine().trim();
		try {
			addicts.addRomance(character1Name, character2Name);
			System.out.println(character1Name + "and" +  character2Name + "are now a couple.");
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (SameCharacterException exception) {
			 System.out.println(character1Name + " cannot be in a single person romantic relationship!");
		}
		catch (UnknownCharacterException exception) {
			 System.out.println("Who is " + exception.getMessage() + "!");
		}
		catch (RepeatedRomanceException exception) {
			 System.out.println("What else is new? We already know about those two...");
		}
	}
	
	private static void addEvent(Scanner in, TvAddicts addicts) {
		String descriptionOfTheEvent = in.nextLine().trim();
		int seasonNum = in.nextInt();
		int episodeNum = in.nextInt();
		int involvedCharactersNum = in.nextInt();
		ArrayList<String> involvedCharacters = new ArrayList<String>(involvedCharactersNum);
		for (int i = 0; i < involvedCharactersNum; i++)
			involvedCharacters.add(in.nextLine().trim());
		try {
			addicts.addEvent(descriptionOfTheEvent, seasonNum, episodeNum, involvedCharacters.iterator());
			 System.out.println("Event added.");
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (UnknownSeasonException exception) {
			 System.out.println(exception.getMessage() + " does not have season " + seasonNum + "!");
		}
		catch (UnknownEpisodeException exception) {
			 System.out.println(exception.getMessage() + " S" + seasonNum + " does not have episode " + episodeNum + "!");
		}
		catch (UnknownCharacterException exception) {
			 System.out.println("Who is " + exception.getMessage() + "!");
		}
		catch (SameCharacterException exception) {
			 System.out.println("Duplicate character names are not allowed!");
		}
		
	}
	
	private static void addQuote(Scanner in, TvAddicts addicts) {
		int seasonNum = in.nextInt();
		int episodeNum = in.nextInt();
		String characterName = in.nextLine().trim();
		String quote = in.nextLine().trim();
		try {
			addicts.addQuote(seasonNum, episodeNum, characterName, quote);
			System.out.println("Quote added!");
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (UnknownSeasonException exception) {
			 System.out.println(exception.getMessage() + " does not have season " + seasonNum + "!");
		}
		catch (UnknownEpisodeException exception) {
			 System.out.println(exception.getMessage() + " S" + seasonNum + " does not have episode " + episodeNum + "!");
		}
		catch (UnknownCharacterException exception) {
			 System.out.println("Who is " + exception.getMessage() + "!");
		}
	}
	
	private static void seasonsOutline(Scanner in, TvAddicts addicts) {
		int startingSeason = in.nextInt();
		int endingSeason = in.nextInt();
		try {
			Iterator<Episode> seasonsOutline = addicts.seasonsOutline(startingSeason, endingSeason);
			int aux = 0;
			while(seasonsOutline.hasNext()) {
				Episode episode = seasonsOutline.next();
				if (aux == 0)
					System.out.println(episode.show().name());
				aux++;
				System.out.println("S" + episode.season().number() + " Ep" + episode.number() + ": " + episode.name());
				Iterator<Event> eventsList = episode.eventsList();
				while(eventsList.hasNext())
					System.out.println(eventsList.next().descriptionOfTheEvent());
			}
		}
		catch (NoShowSelectedException exception) {
			 System.out.println("No show is selected!");
		}
		catch (InvalidSeasonsIntervalException exception) {
			 System.out.println("Who is \" + exception.getMessage() + \"!");
		}
	}
	
	private static void characterResume(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void howAreTheseTwoRelated(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void famousQuotes(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void alsoAppearsOn(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void mostRomantic(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void kingOfCGI(Scanner in, TvAddicts addicts) {
		
	}


	
	}