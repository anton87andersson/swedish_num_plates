package swe_numplates_project;

import java.io.FileWriter;   
import java.io.File;  
import java.io.IOException;  
import org.json.simple.JSONObject;

class car_plates {
    public int first_letter_from;
    public int second_letter_from;
    public int third_letter_from;
    
    public int first_number_from;
    public int second_number_from;
    public int third_number_from;
    
    public String file_name;
    
    
    public car_plates(int a, int b, int c, int d, int e , int f, String file_name){
        
        // Create plate-object whith custon letters and number range
        this.first_letter_from = a;
        this.second_letter_from = b;
        this.third_letter_from = c;
    
        this.first_number_from = d;
        this.second_number_from = e; 
        this.third_number_from = f;
        
        this.file_name = file_name;
        
        
    }
    
    public car_plates() {
        
        // Create an object for Standard for A-Z and 0-9 plate.
        
        this.first_letter_from = 65;
        this.second_letter_from = 65;
        this.third_letter_from = 65;
    
        this.first_number_from = 0;
        this.second_number_from = 0; 
        this.third_number_from = 0;
    }
    
    // The section below is an TO-DO atm.
    // The puropse of this is plates that are not allowd in sweden
    public String[] plates_letters_not_allowed() {
        String plates [] = {
            "AAA", "APA","ARG",  "ASS", "BAJ" , "BSS",  "CUC", "CUK", "DUM", "ETA",
            "ETT", "FAG", "FAN", "FEG", "FEL" , "FEM" , "FES", "FET", "FNL",
            "FUC", "FUK", "FUL", "GAM", "GAY",  "GEJ",  "GEY", "GHB", "GUD" ,
            "GYN", "HAT", "HBT", "HKH",	"HOR",	"HOT",	"KGB", "KKK", "KUC",
            "KUF", "KUG", "KUK", "KYK",	"LAM",	"LAT",	"LEM", "LOJ", "LSD",
            "LUS", "MAD", "MAO", "MEN",	"MES",	"MLB",	"MUS", "NAZ", "NRP",
            "NSF", "NYP", "OND", "OOO",	"ORM",	"PAJ",	"PKK", "PLO", "PMS",
            "PUB", "RAP", "RAS", "ROM",	"RPS",	"RUS",	"SEG", "SEX", "SJU",
            "SOS", "SPY", "SUG", "SUP",	"SUR",	"TBC",	"TOA", "TOK", "TRE",
            "TYP", "UFO", "USA", "WAM",	"WAR",	"WWW",	"XTC", "XTZ", "XXL",
            "XXX", "ZEX", "ZOG", "ZPY",	"ZUG",	"ZUP",	"ZOO" };
        return plates;
    }
    
    
    public void save_plates() {
        
        // Using this for both the id in json and for counting number of rows created.
        int id_inc = 0;
        
        // Create the json object (simple-json)
        JSONObject obj = new JSONObject();
        
        // Setting all the variables first so we not do it like 17.500.000 times ;)
        int i , j, k , a , b , c = 0;
        
        // A** ***
        for (i = first_letter_from; i<= 90; i++){
            // *A* ***
            for (j = second_letter_from; j<= 90; j++){
                // **A ***
                for (k = third_letter_from; k<= 90; k++){
                    // *** 1**
                    for (a = first_number_from; a <= 9; a++){
                        // *** *1*
                        for (b = second_number_from; b <= 9; b++) {
                            // *** **1
                            for (c = third_number_from; c <= 9; c++){
                                // ID-increment for json
                                id_inc = id_inc + 1;
                                // Get the plate in this format and string ex. AAA000
                                String car_plate = (String) ((char) i + "" + (char) j + "" + (char) k +  "" + a + "" + b + "" + c);
                                // Use this to view created plate and the id.
                                System.out.println(id_inc + ": " + car_plate);
                                // Add the plate to JSON, the id_inc will be key.
                                obj.put(id_inc, car_plate);
                            }
                        }
                    }
                }
            }
        }
        // Print total number of plates
        System.out.println("Total plates: " + id_inc);
        
        System.out.println("Saving to Json.... (this will take some time)");
        // Save the json to desired filename.
        file_operation.write(obj.toJSONString(), file_name, true);
        
    }
    
}

// Classs for everything with I/O operations.
class file_operation {
    
    // Create the file if it dosent exist.
    public static void create_file(String file_name, boolean debug) {
        try {
            // Filename will go here.
            File create_file = new File(file_name);
            if (create_file.createNewFile()) {
                // If file is created
                if (debug) { System.out.println("File created: " + create_file.getName()); }
            } 
            else {
                // If file exist
                if(debug) {  System.out.println("File already exists."); }  
            } 
        } catch (IOException e) {
            // If there is any error.
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    // Write to the created file
    public static void write(String to_write, String file_name, boolean debug) {
    try {

        // First we want to create the file from the crate_file method.
        create_file(file_name, true);
        
        // Crate the object and then write data to the file 
        FileWriter Writer = new FileWriter(file_name);
        Writer.write(to_write);
        
        // Close the file
        Writer.close();
        if (debug == true){
            // If everything is written to the file!
            System.out.println("Successfully wrote to the file");
        }
    } catch (IOException e) {
        if (debug == true){
            // If there is any error
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    }
}

public class Swe_numplates_project {

    
    public static void main(String[] args) {
        car_plates c_plates = new car_plates(65,65,65,0,0,0,"plates.json");
        c_plates.save_plates();
    }
    
}
