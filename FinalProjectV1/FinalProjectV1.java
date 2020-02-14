/*
 * Name: Alan Lai
 * Date: 2/13/2020
 * CIS18A
 */

import java.io.File; /* File for documentation. This allows for website profiles to be saved independently of the program, so
* profiles already entered won't be inexplicably lost when the program terminates
*/
import java.io.FileNotFoundException; //For catching FileNotFound Exceptions
import java.io.FileWriter; // For writing to the documentation file.
import java.util.Scanner; //For reading input and reading documentation
import java.io.IOException; // Imported to handle errors regarding file creation and  input errors

//Class used to turn a URL into a valid filename by removing all non-alphabetical characters
class RemNonAlphabet{
    public String str; //stores URL input
    private String[] strAry; //holds array of converted strings
    public String name; //converted URL into valid filename
    public RemNonAlphabet(){
        Scanner url = new Scanner(System.in);
        str = url.nextLine(); //read in URL
        strAry = str.split("\\W+"); //split the String into an array of strings, using \\W+ to remove all non-alphabetical characters in the process
        name = new String(); //new filename string
        for(int i=0;i<strAry.length;i++){
            name = name + strAry[i]; //append each member of the array to name, translating the string array back into a single string
        }
    }
}
//Class for creating and writing to documentation files
class DocuCreator {
    public DocuCreator(String name){
        try{
            //attempts to create a text document corresponding to the URL of the website
            File doc = new File(name + ".txt");
            if (doc.createNewFile()){
                //alerts the user if a new file has been created
                System.out.println("New File Created: " + doc.getName());
            }
            else {
                //alerts the user if an existing website was found
                System.out.println("Existing File " + doc.getName() + " Found");
            }
        }
        catch (IOException error) {
            //Alerts the user of an error while attempting to create a documentation file
            System.out.println("An Error occured while creating documentation file!");
            //Prints error info
            error.printStackTrace();
        }
    }
    public void DocuWriter(String str, String name){
        try{
            /*These next several lines are used to create a form that the user
            * will then fill out to document the website. Each response is written
            * to a file created from the website name, allowing for numerous websites
            * to be stored and multiple documentations to exist from the same website
            */
            FileWriter doc = new FileWriter(name + ".txt");
            System.out.print("Please write your name: ");
            Scanner user = new Scanner(System.in);
            String username = user.nextLine();
            doc.write("\n\n\nReviewer Name: " + username + " \n ");
            doc.write("Website URL: " + str + "\n ");
            System.out.println("Please write what you came to this website looking for: ");
            Scanner search = new Scanner(System.in);
            String srchIn = search.nextLine();
            doc.write(username + " was searching for: \n" + srchIn + " \n ");
            System.out.println("Did you eventually find what you came for?");
            Scanner found = new Scanner(System.in);
            String foundIn = found.nextLine();
            doc.write("Did " + username + " find what they were looking for? \n " + foundIn + " \n ");
            System.out.println("Next, please list any difficulties you may have experienced");
            System.out.println("when browsing this website, and if/how you worked around them:");
            Scanner obstacles = new Scanner(System.in);
            String obIn = obstacles.nextLine();
            doc.write("Difficulties to expect when browsing this website: \n " + obIn + " \n ");
            System.out.println("Did you notice anything suspicious or potentially harmful");
            System.out.println("While browsing this website?");
            Scanner suspect = new Scanner(System.in);
            String susIn = suspect.nextLine();
            doc.write("Suspicious activity worth noting: \n " + susIn + " \n ");
            System.out.println("Is content from this website copyrighted? Does it require a");
            System.out.println("Liscence to use? Does content from this website require payment?");
            Scanner cpyPay = new Scanner(System.in);
            String cpyIn = cpyPay.nextLine();
            doc.write("Payment and Copyright info: \n " + cpyIn + " \n ");
            System.out.println("Lastly, what would you rate your overall experience on a scale of 1-10?");
            Scanner rating = new Scanner(System.in);
            String rate = rating.nextLine();
            doc.write(username + " rates this website " + rate + " \n ");
            doc.close();
            System.out.println("Your documentation was saved successfully!");
            System.out.println("Returning to main menu... \n");
        }
        catch (IOException error){
            System.out.println("An Error occured while writing to documentation file!");
            error.printStackTrace();
        }
    }
}
//class for reading documentation files
class DocuReader{
    public DocuReader(String name){
        try{
            File doc = new File(name + ".txt"); //attempt to open the documentation file
            System.out.println("Documentation Found! \n");
            Scanner docRead = new Scanner(doc);
            while (docRead.hasNextLine()){//iterate through every line of the document and print it
                String read = docRead.nextLine();
                System.out.println(read);
            }
        }
        catch(FileNotFoundException error){//alert the user if no prior documentation could be found
            System.out.println("Prior documentation could not be found!");
        }
    }
}

//begin program execution
//this version does not implement GUI

public class FinalProjectV1 {
    public static void main(String[] args) throws java.io.IOException {
	//Creating documentation file if it doesn't already exist
        boolean cont=true;
        System.out.println("Web Address Documenting v1.0.0");
        Program:
	do{
            boolean loop;
            Scanner in = new Scanner(System.in);
            int inint;
            do{
                loop = false;
                System.out.println("Welcome! Would you like to:");
                /* 1. Provide Documentation. This allows the user to write an overview of their browsing experience
                * As well as any important details such as what they were searching for, is this source free to
                * use, if they found what they were looking for or if they know or felt the content on the website
                * was unsafe in any way.
                */
                System.out.println("1. Provide Documentation on an online source?");
                // 2. Search for Documentation. Look for documentation via a URL or search tags.
                System.out.println("2. Search for documentation on applicable sources?");
                // 3. Exit. This terminates the program.
                System.out.println("3. Exit the program?");
                // Prompt for input
                System.out.print("(Enter 1, 2, or 3): ");
                inint = in.nextInt(); //read menu input
                if (inint < 1 || inint > 3){
                    System.out.println("Invalid input.");
                    loop = true;
                }
            }
            while (loop);
            switch(inint){
                case 1: //create new docucreator object
                    System.out.print("Please enter the URL of the website you are creating documentation for: ");
                    RemNonAlphabet parse = new RemNonAlphabet();
                    DocuCreator newDoc = new DocuCreator(parse.name);
                    newDoc.DocuWriter(parse.str,parse.name);
                    break;
                case 2: //create new docureader object
                    System.out.print("Please enter the URL of the website you are seeking documentation for: ");
                    RemNonAlphabet parse1 = new RemNonAlphabet();
                    DocuReader readDoc = new DocuReader(parse1.name);
                    break;
                case 3: System.out.println("Exiting Program..."); break Program; //end program
                default: System.out.println("Error: Default case should not be accessible.");
            }
        }
        while (cont);
    }
    
}
