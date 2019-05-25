import java.util.Scanner;
import tvAddicts.*;
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
				 currentShow(in,addicts);
				 break;
			 case ADD_SHOW: 
				 addShow(in,addicts);
				 break;
			 case SWITCH_TO_SHOW: 
				 switchToShow(in,addicts);
				 break;
			 case ADD_SEASON: 
				 addSeason(in,addicts);
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
	
	private static void currentShow(Scanner in, TvAddicts addicts) {
		
	}
	private static void addShow(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void switchToShow(Scanner in, TvAddicts addicts) {
		
	}
	private static void addSeason(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void addEpisode(Scanner in, TvAddicts addicts) {
		
	}
	private static void addCharacter(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void addRelationship(Scanner in, TvAddicts addicts) {
		
	}
	private static void addRomance(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void addEvent(Scanner in, TvAddicts addicts) {
		
	}
	private static void addQuote(Scanner in, TvAddicts addicts) {
		
	}
	
	private static void seasonsOutline(Scanner in, TvAddicts addicts) {
		
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