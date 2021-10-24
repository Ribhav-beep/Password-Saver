import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Info {
private String website;
private String password;
private int pin;
private boolean access;

public Info(String w, String password, int pin){
this.website = w;
this.password = password;
this.pin = pin;
this.access = false;
}

public String getWeb() {
	return website;
}

public String getPassword() {
	return password;
}

public void disableAccess() {
	access = false;
}

public boolean getAccess() {
	return access;
}

public void checkPin(int pinInput){
	if( pin == pinInput ){
		access = true;
	} else {
		access = false;
	}
}

public void save(String w, String p) {
	if (access == true) {
		website = w;
		password = p;
	}
}

public String get(String w) {
	if (access == true && website.equals(w)) {
		return password;
	}
	return "";
}

public void saveToFile(String filename) {
	try {
	PrintWriter out = new PrintWriter(filename);
	out.println(website);
	out.println(password);
	out.close();
	} catch (Exception e) {
		System.out.println(e);
	}
}

public void readFromFile(String filename) {
	try {
        BufferedReader bufferreader = new BufferedReader(new FileReader(filename));
        website =  bufferreader.readLine();
        password = bufferreader.readLine();
        bufferreader.close();
    } catch (Exception e) {
    	System.out.println(e);
    }
}

}