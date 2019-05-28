import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import tvAddicts.*;
import tvAddicts.Character;
import exceptions.*;

public class Main {
	// Constantes que definem os comandos
	public static final String HELP = "help";
	public static final String CURRENT_SHOW = "currentshow";
	public static final String ADD_SHOW = "addshow";
	public static final String SWITCH_TO_SHOW = "switchtoshow";
	public static final String ADD_SEASON = "addseason";
	public static final String ADD_EPISODE = "addepisode";
	public static final String ADD_CHARACTER = "addcharacter";
	public static final String ADD_RELATIONSHIP = "addrelationship";
	public static final String ADD_ROMANCE = "addromance";
	public static final String ADD_EVENT = "addevent";
	public static final String ADD_QUOTE = "addquote";
	public static final String SEASONS_OUTLINE = "seasonsoutline";
	public static final String CHARACTER_RESUME = "characterresume";
	public static final String HOW_ARE_THESE_TWO_RELATED = "howarethesetworelated";
	public static final String FAMOUS_QUOTES = "famousquotes";
	public static final String ALSO_APPEARS_ON = "alsoappearson";
	public static final String MOST_ROMANTIC = "mostromantic";
	public static final String KING_OF_CGI = "kingofcgi";
	public static final String QUIT = "exit";

	// Constantes que definem as mensagens para o utilizador
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

		while (!comm.equals(QUIT)) {
			switch (comm) {
			case HELP:
				help();
				break;
			case CURRENT_SHOW:
				currentShow(addicts);
				break;
			case ADD_SHOW:
				addShow(in, addicts);
				break;
			case SWITCH_TO_SHOW:
				switchToShow(in, addicts);
				break;
			case ADD_SEASON:
				addSeason(addicts);
				break;
			case ADD_EPISODE:
				addEpisode(in, addicts);
				break;
			case ADD_CHARACTER:
				addCharacter(in, addicts);
				break;
			case ADD_RELATIONSHIP:
				addRelationship(in, addicts);
				break;
			case ADD_ROMANCE:
				addRomance(in, addicts);
				break;
			case ADD_EVENT:
				addEvent(in, addicts);
				break;
			case ADD_QUOTE:
				addQuote(in, addicts);
				break;
			case SEASONS_OUTLINE:
				seasonsOutline(in, addicts);
				break;
			case CHARACTER_RESUME:
				characterResume(in, addicts);
				break;
			case HOW_ARE_THESE_TWO_RELATED:
				howAreTheseTwoRelated(in, addicts);
				break;
			case FAMOUS_QUOTES:
				famousQuotes(in, addicts);
				break;
			case ALSO_APPEARS_ON:
				alsoAppearsOn(in, addicts);
				break;
			case MOST_ROMANTIC:
				mostRomantic(in, addicts);
				break;
			case KING_OF_CGI:
				kingOfCGI(in, addicts);
				break;
			default:
				System.out.println("Unknown command. Type help to see available commands.");
			}
			comm = getCommand(in);
		}
		System.out.println("Bye!");
		in.close();
	}

	private static void prompt() {
		System.out.print("> ");
	}

	private static String getCommand(Scanner in) {
		String input;
		prompt();
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
			System.out.println(currentShow.name() + ". Seasons: " + currentShow.seasonsNumber() + " Episodes: "
					+ currentShow.episodesNumber());
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		}
	}

	private static void addShow(Scanner in, TvAddicts addicts) {
		String showName = in.nextLine().trim();
		try {
			addicts.addShow(showName);
			System.out.println(showName + " created.");
		} catch (ShowAlreadyExistsException exception) {
			System.out.println("Show already exists!");
		}
	}

	private static void switchToShow(Scanner in, TvAddicts addicts) {
		String showName = in.nextLine().trim();

		try {
			addicts.switchToShow(showName);
			currentShow(addicts);
		} catch (UnknownShowException exception) {
			System.out.println("Unknown show!");
		}
	}

	private static void addSeason(TvAddicts addicts) {
		try {
			addicts.addSeason();
			currentShow(addicts);
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		}
	}

	private static void addEpisode(Scanner in, TvAddicts addicts) {
		int seasonNum = in.nextInt();
		String espisodeName = in.nextLine().trim();

		try {
			Episode episode = addicts.addEpisode(seasonNum, espisodeName);
			System.out.println(episode.show().name() + " S" + episode.season().number() + ", Ep" + episode.number()
					+ ": " + episode.name() + ".");
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownSeasonException exception) {
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
				System.out.println(character.name() + " is now part of " + character.show().name() + ". This is "
						+ ((RealCharacter) character).actor().name() + " role "
						+ ((RealCharacter) character).actor().rolesNumber() + ".");
			} else if (kind.equals("virtual")) {
				Character character = addicts.addVirtualCharacter(characterName, actorOrCompanyName, feeOrCost);
				System.out.println(character.name() + " is now part of " + character.show().name()
						+ ". This is a virtual actor.");
			} else {
				System.out.println("Unknown actor category!");
			}
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (DuplicateCharacterException exception) {
			System.out.println("Duplicate character names are not allowed!");
		} catch (NegativeFeeException exception) {
			System.out.println("Slavery is long gone and this is outrageous!");
		}
	}

	private static void addRelationship(Scanner in, TvAddicts addicts) {
		String character1Name = in.nextLine().trim();
		String character2Name = in.nextLine().trim();
		try {
			addicts.addRelationship(character1Name, character2Name);
			System.out.println(addicts.character(character1Name).name() + " has now "
					+ addicts.character(character1Name).kidsNumber() + " kids. "
					+ addicts.character(character2Name).name() + " has now "
					+ addicts.character(character2Name).parentsNumber() + " parent(s).");
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (SameCharacterException exception) {
			System.out.println(character1Name + " cannot be parent and child at the same time!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		} catch (RepeatedRelationshipException exception) {
			System.out.println("What else is new? We already know about those two...");
		}
	}

	private static void addRomance(Scanner in, TvAddicts addicts) {
		String character1Name = in.nextLine().trim();
		String character2Name = in.nextLine().trim();
		try {
			addicts.addRomance(character1Name, character2Name);
			System.out.println(character1Name + " and " + character2Name + " are now a couple.");
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (SameCharacterException exception) {
			System.out.println(character1Name + " cannot be in a single person romantic relationship!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		} catch (RepeatedRomanceException exception) {
			System.out.println("What else is new? We already know about those two...");
		}
	}

	private static void addEvent(Scanner in, TvAddicts addicts) {
		
		String descriptionOfTheEvent = in.nextLine().trim();
		int seasonNum = in.nextInt();
		int episodeNum = in.nextInt();
		int involvedCharactersNum = in.nextInt();
		in.nextLine().trim();
		ArrayList<String> involvedCharacters = new ArrayList<String>(involvedCharactersNum);
		for (int i = 0; i < involvedCharactersNum; i++) {
			String caracter = in.nextLine().trim();
			involvedCharacters.add(caracter);
		}
			
		try {
			addicts.addEvent(descriptionOfTheEvent, seasonNum, episodeNum, involvedCharacters.iterator());
			System.out.println("Event added.");
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownSeasonException exception) {
			System.out.println(exception.getMessage() + " does not have season " + seasonNum + "!");
		} catch (UnknownEpisodeException exception) {
			System.out
					.println(exception.getMessage() + " S" + seasonNum + " does not have episode " + episodeNum + "!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		} catch (SameCharacterException exception) {
			System.out.println("Duplicate character names are not allowed!");
		}

	}

	private static void addQuote(Scanner in, TvAddicts addicts) {
		int seasonNum = in.nextInt();
		int episodeNum = in.nextInt();
		in.nextLine().trim();
		String characterName = in.nextLine().trim();
		String quote = in.nextLine().trim();
		try {
			addicts.addQuote(seasonNum, episodeNum, characterName, quote);
			System.out.println("Quote added.");
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownSeasonException exception) {
			System.out.println(exception.getMessage() + " does not have season " + seasonNum + "!");
		} catch (UnknownEpisodeException exception) {
			System.out
					.println(exception.getMessage() + " S" + seasonNum + " does not have episode " + episodeNum + "!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		}
	}

	private static void seasonsOutline(Scanner in, TvAddicts addicts) {
		int startingSeason = in.nextInt();
		int endingSeason = in.nextInt();
		in.nextLine().trim();
		try {
			Iterator<Episode> seasonsOutline = addicts.seasonsOutline(startingSeason, endingSeason);
			if (!seasonsOutline.hasNext()) {
				System.out.println(addicts.currentShow().name());
			}
			else {
				int aux = 0;
				while (seasonsOutline.hasNext()) {
					Episode episode = seasonsOutline.next();
					if (aux == 0)
						System.out.println(episode.show().name());
					aux++;
					System.out.println("S" + episode.season().number() + " EP" + episode.number() + ": " + episode.name());
					Iterator<Event> eventsList = episode.eventsList();
					while (eventsList.hasNext())
						System.out.println(eventsList.next().descriptionOfTheEvent());
				}
			}
		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (InvalidSeasonsIntervalException exception) {
			System.out.println("Invalid seasons interval!");
		}
	}

	private static void characterResume(Scanner in, TvAddicts addicts) {
		String characterName = in.nextLine().trim();
		try {
			Character character = addicts.character(characterName);
			System.out.print("Parents: ");
			Iterator<Character> parentsIterator = character.parentsIterator();
			if (!parentsIterator.hasNext())
				System.out.println("None.");
			else {
				System.out.print(parentsIterator.next().name());
				while (parentsIterator.hasNext())
					System.out.print(", " + parentsIterator.next().name());
				System.out.println("");
			}

			System.out.print("Kids: ");
			Iterator<Character> kidsIterator = character.kidsIterator();
			if (!kidsIterator.hasNext())
				System.out.println("None.");
			else {
				System.out.print(kidsIterator.next().name());
				while (kidsIterator.hasNext())
					System.out.print(", " + kidsIterator.next().name());
				System.out.println("");
			}

			System.out.print("Siblings: ");

			Iterator<Character> SiblingsIterator = character.siblingsIterator();
			if (!SiblingsIterator.hasNext())
				System.out.println("None.");
			else {
				System.out.print(SiblingsIterator.next().name());
				while (SiblingsIterator.hasNext())
					System.out.print(", " + SiblingsIterator.next().name());
				System.out.println("");
			}

			System.out.print("Romantic relationships: ");
			Iterator<Character> RomanticIterator = character.romanticIterator();
			if (!RomanticIterator.hasNext())
				System.out.println("None.");
			else {
				System.out.print(RomanticIterator.next().name());
				while (RomanticIterator.hasNext())
					System.out.print(", " + RomanticIterator.next().name());
				System.out.println("");
			}
			Iterator<Episode> characterOutline = addicts.characterOutline(character);
			while (characterOutline.hasNext()) {
				Episode episode = characterOutline.next();
				System.out.println("S" + episode.season().number() + " EP" + episode.number() + ": " + episode.name());
				Iterator<Event> eventsList = episode.eventsList();
				while (eventsList.hasNext()) {
					Event event = eventsList.next();
					Iterator<Character> involvedCharacters = event.involvedCharacters();
					while (involvedCharacters.hasNext())
						if (involvedCharacters.next().equals(character))
							System.out.println(event.descriptionOfTheEvent());
				}
			}

		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		}

	}

	private static void howAreTheseTwoRelated(Scanner in, TvAddicts addicts) {
		String character1Name = in.nextLine().trim();
		String character2Name = in.nextLine().trim();

		try {

			Iterator<Character> howAreTheseTwoRelated = addicts.howAreTheseTwoRelated(character1Name, character2Name);
			if (howAreTheseTwoRelated.hasNext()) {
				System.out.print(howAreTheseTwoRelated.next().name());
				while (howAreTheseTwoRelated.hasNext())
					System.out.print("; " + howAreTheseTwoRelated.next().name());
				System.out.println();
			}

		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		} catch (SameCharacterException exception) {
			System.out.println("Like... you know, they are THE SAME character! duuuuh...");
		} catch (NoRealatedCharactersException exception) {
			System.out.println("These characters are not related!");
		}

	}

	private static void famousQuotes(Scanner in, TvAddicts addicts) {
		String quote = in.nextLine().trim();
		try {
			Iterator<Character> famousQuotes = addicts.famousQuotes(quote);
			if (famousQuotes.hasNext()) {
				System.out.print(famousQuotes.next().name());
				while (famousQuotes.hasNext())
					System.out.print(", " + famousQuotes.next().name());
			}
			System.out.println("");
			

		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownQuoteException exception) {
			System.out.println("First time I hear that!");
		}
	}

	private static void alsoAppearsOn(Scanner in, TvAddicts addicts) {
		String characterName = in.nextLine().trim();
		try {
			Iterator<Show> alsoAppearsOn = addicts.alsoAppearsOn(characterName);
			while (alsoAppearsOn.hasNext())
				System.out.println(alsoAppearsOn.next().name());

		} catch (NoShowSelectedException exception) {
			System.out.println("No show is selected!");
		} catch (UnknownCharacterException exception) {
			System.out.println("Who is " + exception.getMessage() + "?");
		} catch (VirtualActorException exception) {
			System.out.println(characterName + " is played by a virtual actor!");
		}
	}

	private static void mostRomantic(Scanner in, TvAddicts addicts) {
		String actorName = in.nextLine().trim();
		try {
			Iterator<Actor> mostRomantic = addicts.mostRomantic(actorName);
			while (mostRomantic.hasNext()) {
				Actor actor = mostRomantic.next();
				System.out.println(actor.name() + " " + actor.romanticNumber());
			}

		} catch (UnknownActorException exception) {
			System.out.println("Who is " + actorName + "?");
		} catch (NoRomanticCharactersException exception) {
			System.out.println("Love is not in the air. :-(");
		}
	}

	private static void kingOfCGI(Scanner in, TvAddicts addicts) {

		try {
			CGI kingOfGDI = addicts.kingOfGDI();
			System.out.println(kingOfGDI.name() + " " + kingOfGDI.feesCollected());
		} catch (NoVirtualCharactersException exception) {
			System.out.println("This is the real thing, this is art!");
		}
	}

}