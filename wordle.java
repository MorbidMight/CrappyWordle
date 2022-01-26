import java.io.*;
import java.util.*;

public class wordle
{
   public static int getFileSize(String fileName)throws IOException
   {
      Scanner input = new Scanner(new FileReader(fileName));
      int size=0;
      while (input.hasNextLine())				//while there is another line in the file
      {
         size++;										//add to the size
         input.nextLine();							//go to the next line in the file
      }
      input.close();									//always close the files when you are done
      return size;
   }
   
   	//pre:  "fileName" is the name of a real file containing lines of text - the first line intended to be unused
      //post:returns a String array of all the elements in <filename>.txt, with index 0 unused (heap) O(n)
   public static String[] readFile(String fileName)throws IOException
   {
      int size = getFileSize(fileName);		//holds the # of elements in the file
      String[] list = new String[size];		//a heap will not use index 0;
      Scanner input = new Scanner(new FileReader(fileName));
      int i=0;											//index for placement in the array
      String line;	
      while (input.hasNextLine())				//while there is another line in the file
      {
         line=input.nextLine();					//read in the next Line in the file and store it in line
         list[i]= line;								//add the line into the array
         i++;											//advance the index of the array         
      }
      input.close();	
      return list;					
   }
   
     //pre: 
     //post:displays all of the elements of the array words O(n)
   public static void showArray(String[] words)
   {
      for (int i=0; i<words.length; i++)
         System.out.println(words[i] + " ");
      System.out.println();
      System.out.println("Size of array:" + words.length);
   }
   
   public static void writeToFile(String[] array, String filename) throws IOException
   {
      System.setOut(new PrintStream(new FileOutputStream(filename)));
      for(int i = 0; i < array.length; i++) 
         System.out.println(array[i]);
   }
   
   public static void main(String[] args)throws IOException
   {
   /*
      String[] words = readFile("words.txt");
      ArrayList<String> betterWords = new ArrayList<String>();
      for(int x = 0; x < words.length; x++)
      {
         if(words[x].length() == 5)
         {
            betterWords.add(words[x]);
         }
      }
      words = betterWords.toArray(new String[betterWords.size()]);
      writeToFile(words, "betterWords.txt");*/
      Scanner s = new Scanner(System.in);
      boolean playAgain = true;
      int triesLeft;
      String guess, word;
      ArrayList<String> correctPos, contained;
      String[] words = readFile("betterWords.txt"); 
      while(playAgain)
      {
         word = getRandom(words);
         triesLeft = 6;
         while(triesLeft > 0)
         {
           correctPos = new ArrayList<String>();
           contained = new ArrayList<String>();
            System.out.println("You have " + triesLeft + " tries left!");
            System.out.print("Enter Guess: ");
            guess = s.nextLine();
               if(guess.equals(word))
               {
                  System.out.println("You won!");
                  break;
               }
            for(int x = 0; x < guess.length(); x++)
            {
               if(guess.charAt(x) == word.charAt(x))
               {
                  correctPos.add(Character.toString(guess.charAt(x)));
               }
               if(word.indexOf(Character.toString(guess.charAt(x))) != -1)
               {
                  contained.add(Character.toString(guess.charAt(x)));
               }
            }
            System.out.println("Correct positions: " + correctPos.toString());
            System.out.println("Correct letters: " + contained.toString());
            triesLeft--;
         }
         if(triesLeft < 1)
         {
            System.out.println("The word was " + word);
            System.out.println("You lost!");
         }
         System.out.print("Play again(y/n)");
         String playAgainString = s.nextLine().toLowerCase();
         if(playAgainString.equals("n"))
         {
            playAgain = false;
         }  
      }
   }
   public static String getRandom(String[] array) {
    int rnd = new Random().nextInt(array.length);
    return array[rnd];
   }
} 