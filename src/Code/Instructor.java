package Code;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Instructor extends User{
    
    private List<String> classesTaught = new ArrayList<>();
    
    public Instructor(String username, String firstName, String lastName, String dateJoined, String email, String birthDate, String loginPassword, String role) {
        super(username, firstName, lastName, dateJoined, email, birthDate, loginPassword, role);
    }

    @Override
    public void dashboard() {
        String[] args = null;
        InstructorDashboard.main(args);
    }
    
    public List taughtClasses(){
        if (classesTaught == null) {
            classesTaught = new ArrayList<>();
        } else {
            classesTaught.clear();
        }
        try (Reader classReader = new InputStreamReader(new FileInputStream("Martial_Classes.txt"), StandardCharsets.UTF_8)) {
                
            JsonArray classRecords = JsonParser.parseReader(classReader).getAsJsonArray();
            
                for (JsonElement classElement : classRecords) {
                    JsonObject classRecord = classElement.getAsJsonObject();
                    
                    if (classRecord.get("instructor").getAsString().equals(super.getUsername())){
                        classesTaught.add(classRecord.get("classId").getAsString());
                    }

                }
                
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }        
        return classesTaught;
    }

}
