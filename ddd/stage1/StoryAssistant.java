/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package javastory.club.stage1.step42.app;

public class StoryAssistant {
	//
	public static void main(String[] args) {
		//
		startStory(); 
	}

	private static void startStory() {
		//
		TravelClubConsole travelClubConsole = new TravelClubConsole();
		travelClubConsole.showMenu();
	}
}