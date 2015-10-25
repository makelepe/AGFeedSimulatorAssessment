package za.co.allangray.assessment;

import java.util.List;
import java.util.Scanner;

import za.co.allangray.assessment.model.Feed;
import za.co.allangray.assessment.model.User;
import za.co.allangray.assessment.service.FeedService;

/**
 * @author Samuel Radingwane
 * @since 22 October 2015	
 *
 * This program simulate twitter feed for available users and feeds provided in the files as argument. 
 *
 */
public class AGFeedSimulator {
	
	private static FeedService feedService;
	
	/**
	 * This method experts two data files in the ORDER [user files and feed files]   
	 * @param args = array of strings
	 */
	public static void main(String[] args) {
		feedService = new FeedService();
		
		/** check that files [User and Feed] are passed as arguments */
		if (args.length != 2) {
			System.err.println("User file and Feed file must be passed in the Program arguments on startup");
		}
		
		/** load data into virtual storage */ 
		feedService.load(args[0], args[1]);
		
		/** start simulator */
		start();
		
	}
	
	/**
	 * This method starts the simulator program
	 */
	private static void start () {
		System.out.println("\n#######################################################################################");
		System.out.println("\n                    WELCOME TO Allan Gray Feed Simulator");
		System.out.println("#######################################################################################\n\n");
	
		boolean running = true;
		do {
			int value;
			System.out.println("\n\nEnter one of the following options to continue:");
			System.out.println("1 - Display Users and their feeds");
			System.out.println("2 - To add new users");
			System.out.println("3 - To add feed to a User");
			System.out.println("4 - Quit\n>>");
			
			try {
                            Scanner in = new Scanner(System.in);
                            value = in.nextInt();
			} catch (Exception e) {
				value = 0; /** reset selection */
				System.err.println("Please enter valid number [1 | 2 | 3 | 4]");
			}
			
			switch (value) {
			case 1:
				display();
				break;
			case 2:
				System.out.println("Add new User functionality not yet supported. We are sorry for inconvenience caused.");
				break;
			case 3:
				System.out.println("Adding Feeds functionality not yet supported. We are sorry for inconvenience caused.");
				break;
			case 4:
				running = false;
				break;
			default:
				break;
			}
			
			
		} while (running);

	}
	
	/** 
	 * display users and their feeds
     */
	private static void display() {
		
		/** validation */
		if (feedService == null) {
			System.err.println("Service is currently unavailable. Please try again later or bounce app");
			return;
		}
		
		try {
			/** read users from the FeedService */
			List<User> users = feedService.getUsers();
			
			/** display users */
			for (User user : users) {
				System.out.println(user.getName());
				/** display user feed messages */
				for (Feed feed : user.getFeeds()) {
					System.out.println("      " + feed.getFormattedMessage());
				}
			}
			
		} catch (Exception e) {
			System.err.println("Error reading captured value : " + e.getMessage());
		}
	}

}
