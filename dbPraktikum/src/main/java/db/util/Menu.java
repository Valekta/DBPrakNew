package db.util;

import java.util.Scanner;

public class Menu {
	private boolean exit = false;
	private PersonRelatedImpl personInfo = new PersonRelatedImpl();
	private StatisticImpl statistic = new StatisticImpl();
	
	public void show() {
		while(!exit) {
			printMenu();
			int selection = readSelection();
			performAction(selection);
		}
	}
	
	private void printMenu() {
		System.out.println("[0]Exit\n [1]Person related API\n [2]Statistics API");
	}
	
	private void printStatisticMenu() {
		System.out.println("[0]Exit\n [1]Person related API\n [2]Statistics API");
	}
	
	private int readSelection() {
		Scanner sc = new Scanner(System.in);
		int eingabe = sc.nextInt();
		return eingabe;
	}
	
	private Long readPersonID() {
		System.out.println("Input ID for person");
		Scanner sc = new Scanner(System.in);
		Long eingabe = Long.parseLong(sc.nextLine());
		return eingabe;
	}
	
	private void performPersonRelatedContent() {
		boolean exitP = false;
		Long personID;
		personID = readPersonID();
		while(!exitP) {
			System.out.println("[0]Exit person related menu\n [1]Profile\n [2]Common interests of my friends\n [3]Common friends\n [4]Persons with most common interests\n [5]Job recommendation\n [6]Shorthest friendship path");
			int selection = readSelection();
			switch(selection) {
			case 0 : exitP = true; break;
			case 1 : personInfo.getProfile(personID); break;
			case 2 : personInfo.getCommonInterestsOfMyFriends(personID); break;
			case 3 : personInfo.getCommonFriends(personID, readPersonID()); break;
			case 4 : personInfo.getPersonsWithMostCommonInterests(personID); break;
			case 5 : personInfo.getJobRecommendation(personID); break;
			case 6 : personInfo.getShortestFriendshipPath(personID, readPersonID()); break;
			}
		}
	}
	
	private void performStatisticContent() {
		boolean exitS = false;
		while(!exitS) {
			System.out.println("[0]Exit statistic menu\n [1]Tag class hierarchy\n [2]Popular comments\n [3]Most posting country");
			int selection = readSelection();
			switch(selection) {
			case 0 : exitS = true; break;
			case 1 : statistic.getTagClassHierarchy(); break;
			case 2 : System.out.println("Input minimum count of likes");
					 statistic.getPopularComments(readSelection()); break;
			case 3 : statistic.getMostPostingCountry(); break;
			}
		}
	}
	
	private void performAction(int selection) {
		switch(selection) {
		case 0 : exit = true; break;
		case 1 : performPersonRelatedContent(); break;
		case 2 : performStatisticContent(); break;
		}
	}
}
