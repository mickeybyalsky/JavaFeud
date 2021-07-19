/* FamilyFeud.java
 * Grade 11 Computer Science Culminating Project
 * Program Description: Using the content learned throughout the semester including Arrays, Loops and Strings, the user 
 * 						particpates in a Game contest with a series of questions and tries to guess
						the most common answers. The user is awarded points based on the popularity of their answer.
						The amount of points is presented at the end of the program. 
* June 19, 2020
* Mickey Byalsky
* SOURCES ARE INCLUDED IN AN JOPTIONPANE AT END OF PROGRAM
*/
import java.util.Scanner;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class FamilyFeud {//start class
	
	final static int TOP_PRIZE=50;//If user enters the most popular answer, they get 50 points
	final static int SECOND_PRIZE=40;//If user enters the second most popular answer they get 40 points
	final static int THIRD_PRIZE=30;//If user enters the third most popular answer they get 30 points
	final static int FOURTH_PRIZE=20;//If user enters the fourth most popular answer they get 20 points
	final static int FIFTH_PRIZE=10;//If user enters the fifth most popular answer they get 10 points
	final static int SIXTH_PRIZE=1;//If user enters the least popular answer they get 1 point.

	final static ImageIcon ICON = new ImageIcon("FF.png");//imports picture using file name and file is given name title to use as icon in dialog box
	
		public static void main(String[] args) {
				String name; //variable to hold user's name for program
				String message = "";//String is created to host the HTML text 
				
				final File TITLE_TEXT = new File ("Title.txt");//file is imported
				Scanner input = null;
				try {
					input =new Scanner(TITLE_TEXT);//program sets up to scan the text file
					}catch (FileNotFoundException e) {//If program is unable to locate file
						JOptionPane.showMessageDialog(null, "File not found.","File Not Found", 0);//JOptionPane presents issue 
					}
					
					while(input.hasNextLine()) {//while lines in text file exists, add them to the "message" string
						message+= input.nextLine();//lines from scanner are added onto to the message string
					}
					//method is called to displayTitle
					displayTitle(message);//Message Dialog that introduces user to program. User presses button to advance to next screen. Dialog contains custom image. 
						
					name = (String) JOptionPane.showInputDialog(null,"Enter your Name to begin the Game\n","Java Feud",0, ICON,null,"");//Input Dialog is used to obtain user's name for parts of the Program. User's name is stored from here
				
					//Confirm Dialog asks user if they have played "Java Feud" before to determine whether or not to display instructions. 
					int firstTime=JOptionPane.showConfirmDialog(null, "Hi " + name+", welcome to Java Feud!\nIf this is your First Time Playing Press Yes, "
							+ "otherwise Press No","Welcome", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,ICON);
					        
							//user says if they have played before and outputs differently depending on input.
					
						if (firstTime == JOptionPane.NO_OPTION) { // if they say NO this is not their first time and they have played the game before. 
							
							final Object[] OPTIONS = {"Start!"};//used to create custom button text
							
							//Program uses User's name to display a Welcome Back Message in a JOptionPane
							JOptionPane.showOptionDialog(null,"Welcome Back " +name+ ", It's Nice to Have You Again!\n",
									"Message",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, OPTIONS,OPTIONS[0]);
						}
						
						else {//if they say yes, they have played the game
							displayInstructions();//method that displays instructions from an HTML file is called.
						}
						 questionPrompt(name);//next method is called			 
		}

		/**
		 * Prints the questions for the game
		 * pre: none (user can not enter something for name and it will be null)
		 * post:Prints question for user along with corresponding answers			
		 */
		public static void questionPrompt(String name) {
				//MAIN PROGRAM LOOP
			
				//Array to hold all the questions in the program. Loops in the program increment counter (questionNum) to display different questions
				final String [] QUESTIONS = {
						                    	"Your first question is: \nName a reason a person might wake up at 2 in the morning.", 
						                    	"Your second question is: \nWhere do kids nowadays spend most of their time?",
												"Your third question is: \nName something you might eat with a hamburger.",
												"Your fourth question is: \nTell me something you can buy that costs a dollar or less.",
												"Your fifth question is: \nName something that breaks down.",
												"Your sixth question is: \nName something you always have to keep plugged in.",
												"Your seventh question is: \nName a reason you might be late for work.",
												"Your eighth question is: \nWhat is the first thing most people do in the morning?",
												"Your ninth question is: \nName a liquid in your kitchen that you hope no one ever accidentally drinks",
												"Almost Done!\nYour last question is: \nName a type of vehicle you really wouldn't want to hit while driving,"
											};
				//Array to hold all the answers to questions in the program. They are displayed with the corresponding question using a counter
				final String [] ANSWERS = {  
											 "1. Bathroom\n2. Child\n3. Bad dream\n4. Heard a noise\n5. Hot/Cold \n6. Hungry / Thirsty",
											 "1. Mall\n2. Internet\n3. School\n4. Room/ Bed\n5. Park\n6. Friend's house",
											 "1. Onion rings\n2. Soup\n3. Salad\n4. Pickles\n5. Tater tots\n6. French fries",
											 "1. Fruit\n2. Fast food\n3. Soft drink\n4. Newspaper\n5. Stamp\n6. Gum",
											 "1. Foreign relations\n2. Body\n3. Computer\n4. Communication\n5. Car\n6. TV",
											 "1. Lamp\n2. Computer\n3. Phone\n4. TV\n5. Computer mouse\n6. Headphones",
											 "1. Traffic\n2. Woke up / left late\n3. Car trouble / accident\n4. Bus / Train delay\n5. Dropping off kids\n6. Sick / Medical emergancy",
							 				 "1. Open Eyes\n2. Shower\n3. Breakfast\n4. Brush teeth\n5. Turn off alarm\n6. Bathroom",
											 "1. Cooking oil\n2.Vinegar\n3. Soy sauce\n4. Lemon juice\n5. Bacon grease\n6. Soap",
											 "1. Firetruck\n2. Ambulance\n3. Train\n4. Police car\n5. Hummer.\n6. Hearse"
										  };
			
				int points =0;//variable to store the amount of points that user Accumulated
				int userAnswer=0;//variable to store user's final answer as an int
				int pointsAdded=0;//Temporary variable to store the number of points from a question to add to overall points
				int questionNum;//track the question program is on
				
				//while questionNum, starting at 0 is less than the index of the answers length, the loop repeats and increments counter "questionNum".
				for (questionNum=0;questionNum < ANSWERS.length;questionNum++) {
					
					//Loop stays on same question until the user enters a value between and including 1 to 6. Prevents wrong input errors.
					do {
						//program calls method questionScreen to present the question and answers. User's answer stored in variable userAnswer is brought back to this method.
						userAnswer = questionScreen(QUESTIONS, ANSWERS, questionNum);
					} while (userAnswer < 1 || userAnswer >6);//while the user doesnt enter a value between and including 1 and 6.
					
					
					//user's answer is checked against the predetermined point weightings using the user's answer value and question numbers. earned points is returned as "pointsAdded"
					pointsAdded = checkAnswer(questionNum, userAnswer);
					points=points+pointsAdded;//pointsAdded value is added to previous total points value to form new points value
					
					
					/*if user guesses the most common answers, display a custom message in a JOptionPane letting them know they guessed the most popular answer. 
					Also displaying the points earned, total points count and questions left.*/
					if(pointsAdded==50) {
						JOptionPane.showMessageDialog(null, "NUMBER ONE ANSWER!!!!\nCongratulations! You really hit the nail on this question.\nYou now have "+points+" points.\n"
								+ "There are "+(10-questionNum-1)+" questions left to go.","Round Results",JOptionPane.INFORMATION_MESSAGE,ICON);
					}
					else {//output ordinary message in a  JOptionPane displaying the points earned, total points count and questions left.
						   JOptionPane.showMessageDialog(null, "Congratulations! You won " +pointsAdded+ " point(s) this round. Your score is now " +points+" points\n"
						   		+ "There are "+(10-questionNum-1)+" questions left to go.","Round Results",JOptionPane.INFORMATION_MESSAGE,ICON);
						}
					
				}
				if(questionNum==10) {//after questions are done, output a final message presenting number of points earned in a JOptionPane
					JOptionPane.showMessageDialog(null, "Out of 500 possible points, You got " +points+ " points. Nice round!\nThanks for Playing!!");
					displayProgramSources();
				}
				
			}
			
			/**
			 * Given the Question number and the user's answer, determine and return the points awarded
			 * pre: questionNum>0, answer < 1 && answer >6
			 * post: returns the value of points earned that is predetermined 
			 */
			public static int checkAnswer(int questionNum, int answer) {
				//if user is on this set of questions, follow this set of switch statements to determine points earned and return int to method questionPrompt()
				if (questionNum == 0 ||questionNum == 3|| questionNum == 4|| questionNum ==6) {		
					switch (answer) {
						case 1://if user enters 1
							return TOP_PRIZE;//returns value predetermined in beginning of class 
						case 2://if user enters 2 
							return SECOND_PRIZE;//returns value predetermined in beginning of class 
						case 3://if user enters 3 
							return THIRD_PRIZE;//returns value predetermined in beginning of class 
						case 4://if user enters 4
							return FOURTH_PRIZE;//returns value predetermined in beginning of class 
						case 5://if user enters 5
							return FIFTH_PRIZE;//returns value predetermined in beginning of class 
						default://if nothing is entered
							return SIXTH_PRIZE;//returns value predetermined in beginning of class 
				}
					}
				//if user is on this set of questions, follow this set of switch statements to determine points earned and return int to method questionPrompt()
				else if (questionNum==1||questionNum==5||questionNum==9) {		
					switch (answer) {
						case 1:
							return FOURTH_PRIZE;
						case 2:
							return THIRD_PRIZE;
						case 3:
							return SECOND_PRIZE;
						case 4:
							return TOP_PRIZE;
						case 5:
							return SIXTH_PRIZE;
						default:
							return FIFTH_PRIZE;
				}
					}
				//if user is on this set of questions, follow this set of switch statements to determine points earned and return int to method questionPrompt()
				 else if (questionNum==2||questionNum ==7||questionNum ==8)  {
					switch (answer) {
						case 1:
							return FOURTH_PRIZE;
						case 2:
							return SECOND_PRIZE;
						case 3:
							return THIRD_PRIZE;
						case 4:
							return SIXTH_PRIZE;
						case 5:
							return FIFTH_PRIZE;
						default:
							return TOP_PRIZE;
				}
				 	}
						return 0; // just in case they entered an invalid number.
			}
			
			/**
			 * Given a Question number, display the Current Question and answers to the question in a JOptionPane
			 * pre: questions[]>0, answers[]>0,questionNum>
			 * post: Question and Answers to question are displayed in a JOptionPane
			 */
			public static int questionScreen(String []questions,String []answers, int questionNum) {
				/*Input Dialog that presents current Question and the Corresponding answers. Allows user to input a answer and the answer value is returned 
				to method and checked against checkAnswer () method*/
				return Integer.parseInt(JOptionPane.showInputDialog(null,questions[questionNum]+"\n"+ answers[questionNum]+"\n"
						+ "Please enter the number beside your Final Answer, then press OK.\n","Question "+(questionNum+1),JOptionPane.QUESTION_MESSAGE));
			}
		
			/**
			 * Title Screen is displayed in a JOptionPane by reading from a HTML text file
			 * pre: none
			 * post: Title is displayed using HTML in a JOptionPane
			 */
			public static void displayTitle(String message) {
					//Title is displayed in a JOptionPane using message text from main method 
					JOptionPane.showMessageDialog(null, message,"Java Feud",0,ICON);
			}
			
			/**
			 * Instructions for the game are displayed in a JOptionPane by reading from a HTML text file
			 * pre: none
			 * post: Instructions are displayed in a JOptionPane using HTML language
			 */
			public static void displayInstructions() {
					File Instructions = new File ("Instructions.txt");
					Scanner input = null;
					String instructionsMessage = "";
					try {//  Block of code to try
							input =new Scanner(Instructions);
						}catch (FileNotFoundException e) {//  Block of code to handle errors
							JOptionPane.showMessageDialog(null, "File not found.","File Not Found", 0);
						}
							while(input.hasNextLine()) {
								instructionsMessage+= input.nextLine();
			}
				//Program instructions are presented in a JOptionPane
				JOptionPane.showMessageDialog(null, instructionsMessage,"Rules and Instructions",JOptionPane.PLAIN_MESSAGE,ICON);
			}
			
			/**
			 * Displays Works Cited of Program
			 * pre:none
			 * post: Presents Works Cited in a JOptionPane.
			 */
			public static void displayProgramSources() {
					File sourcesNotMLA = new File ("sourcesNotMLA.txt");
					Scanner input = null;
					String sourcesNotMLAText = "";
					try {//  Block of code to try
							input =new Scanner(sourcesNotMLA);
						}catch (FileNotFoundException e) {//  Block of code to handle errors
							JOptionPane.showMessageDialog(null, "File not found.","File Not Found", 0);
						}
							while(input.hasNextLine()) {
								sourcesNotMLAText+= input.nextLine();
						}
				
				final Object[] BUTTONS = {"MLA Format","Exit"};//custom button text
				int choice; //user chooses if they would like to exit the program or view the sources in a MLA format
				
				//Sources are showed in a JOptionPane using the sourcesNotMLAText. 2 buttons at the bottom allow for different actions (exitting program and seeing MLA Format Citations)
				//Yes or No options output an int
				choice = JOptionPane.showOptionDialog(null, sourcesNotMLAText,"Java Feud",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null,BUTTONS,BUTTONS[0]);//
				
				if (choice ==JOptionPane.YES_OPTION) {// if user presses yes (renamed to "MLA Format")
					displayProgramSourcesMLAFormat();//method that displays Works Cited in MLA Format is called
				}
				
				if (choice== JOptionPane.NO_OPTION) {// if user presses no (renamed to "Exit")
					System.exit(0);//Program quits
				}
			}
			
			/**Displays Works Cited of Program in MLA Format
			 * pre: choice in previous method == JOptionPane.YES_OPTION
			 * post: Presents Works Cited in MLA Format in a JOptionPane
			 */
			public static void displayProgramSourcesMLAFormat (){
					File sourcesMLA = new File ("sourcesMLA.txt");
					Scanner input = null;
					String sourcesMLAText = "";
						try {//  Block of code to try
							input =new Scanner(sourcesMLA);
						}catch (FileNotFoundException e) {//  Block of code to handle errors
							JOptionPane.showMessageDialog(null, "File not found.","File Not Found", 0);
						}
							while(input.hasNextLine()) {
								sourcesMLAText+= input.nextLine();
						}
				//MLA formatted Works Cited is displayed in a JOptionPane
				JOptionPane.showMessageDialog(null, sourcesMLAText,"Java Feud",JOptionPane.PLAIN_MESSAGE,ICON);
				
				}
		}//ends class
	