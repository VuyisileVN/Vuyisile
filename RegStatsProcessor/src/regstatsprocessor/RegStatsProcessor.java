
package regstatsprocessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RegStatsProcessor {

    
    public static void main(String[] args) {
        int totalStudent = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\E410 ASUS\\Desktop\\New folder\\regstats.txt"));) {
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                
                if(line.length() == 2){
                    try{
                        int students = Integer.parseInt(parts[1].trim());
                        totalStudent += students;
                    }catch(NumberFormatException e){
                        System.err.println("Invalid number format in line: " + line);
                        return;
                    }
                }   
            }
        } catch(IOException ex){
            System.out.println("the file you are looking for is currently not available" + ex.getMessage());
        }
         try(PrintWriter print = new PrintWriter(new FileWriter("C:\\Users\\E410 ASUS\\Desktop\\New folder\\vuyisile.txt"))){
                    //PrintWriter print = new PrintWriter(new FileWriter("C:\\Users\\E410 ASUS\\Desktop\\New folder\\summary.txt"));
                    print.println("Total students registered in Computer Science is: " + totalStudent);
         }catch(IOException ex){
             System.out.println("Error writing the file " + ex.getMessage());
         }
    }
    
}
