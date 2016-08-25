/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage1.step42.app;

import java.util.Collection;
import java.util.Scanner;

import javastory.club.stage1.step42.domain.TravelClub;
import javastory.club.stage1.step42.domain.TravelClubManager;
import javastory.club.stage1.step42.domain.TravelClubRepository;
import javastory.club.stage1.step42.persist.file.TravelClubFile;

public class TravelClubConsole {
	//
	private Scanner scanner;
	private TravelClubManager clubCoordinator; 

	public TravelClubConsole() {
		//
		this.scanner = new Scanner(System.in);
//		TravelClubFile clubStore = new TravelClubFile(); 
//		TravelClubRepository clubRepo = new TravelClubRepository(clubStore); 
//		this.clubCoordinator = new TravelClubManager(clubRepo);  
	}

	public void showMenu() {
		//
		int inputNumber = 0;

		while (true) {
			displayMainMenu();
			inputNumber = selectMainMenu();

			switch (inputNumber) {
			//
			case 1:
				register();
				break;
			case 2:
				find();
				break;
			case 3:
				findAll();
				break;
			case 0:
				exitProgram();
				return;

			default:
				System.out.println("Choose again!");
			}
		}
	}

	private void displayMainMenu() {
		//
		System.out.println("");
		System.out.println("..............................");
		System.out.println(" Travel club menu ");
		System.out.println("..............................");
		System.out.println(" 1. Register");
		System.out.println(" 2. Find");
		System.out.println(" 3. Find all");
		System.out.println(" 0. Program exit");
		System.out.println("..............................");
	}

	private int selectMainMenu() {
		//
		System.out.print("Select number: ");
		String menuNumber = scanner.nextLine();

		if (menuNumber.length() != 1) {
			System.out.println("Allow only one digit, but it's --> " + menuNumber);
			return -1; 
		}
		
		if (menuNumber.equals("0") || menuNumber.equals("1") || menuNumber.equals("2") || menuNumber.equals("3")) {
			return Integer.valueOf(menuNumber);  
		} else {
			System.out.println("Input a valid digit.");
			return -1; 
		}
	}
 
	private void exitProgram() {
		//
		System.out.println("Program exit. Bye...");
		System.exit(0);
	}

	private void register() {
		//
		System.out.print("Input club name: ");
		String clubName = scanner.nextLine();

		if (clubName == null || clubName.equals("")) {
			System.out.println("Club name should not be null.");
			return; 
		}
		clubName = clubName.trim(); 
		if (clubCoordinator.existByName(clubName)) {
			System.out.println("Club name already exists. --> " + clubName);
			return; 
		}
		
		System.out.print("Input club intro: ");
		String intro = scanner.nextLine();
		intro = intro.trim(); 
		
		TravelClub newClub = new TravelClub(clubName, intro); 
		boolean isRegistered = clubCoordinator.register(newClub);
		
		if (isRegistered) {
			System.out.println("\t > Registered club:" + newClub.toString());
		} else {
			System.out.println("\t > Sorry, fail to register the club.");
		}
	}

	private void find() {
		//
		String clubName = ""; 
		
		while(true) {
			clubName = displayFindMenuAndGetKey(); 

			if (clubName.equals("0")) {
        		break; 
	        }
	        
			TravelClub clubFound = clubCoordinator.findByName(clubName); 
			if (clubFound != null) {
				System.out.print("\t >> Found club : " + clubFound.toString());
			} else {
				System.out.print("\t >> No such a club: " + clubName);
			}
		}
	}
	
	private String displayFindMenuAndGetKey() {
		// 
		if (!clubCoordinator.hasClubs()) {
			System.out.println("\t > No more clubs in the stroage.");
			return "0"; 
		}

		System.out.print("\n\t > Input club name to find(0.Previous menu): ");
        String clubName = scanner.nextLine();
        
        return clubName.trim(); 
	}

	private void findAll() {
		//
		if (!clubCoordinator.hasClubs()) {
			System.out.println("\t > No clubs in the stroage.");
			return; 
		}
		
		Collection<TravelClub> travelClubs = clubCoordinator.findAll();
		System.out.println("\t >> Found " + travelClubs.size() + " clubs."); 
		
		for (TravelClub travelClub : travelClubs) {
	        System.out.print("\t >> Found club : " + travelClub.toString()); 
		}
	}
}