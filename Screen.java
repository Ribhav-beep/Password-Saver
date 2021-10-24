import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel implements ActionListener {

private JTextField pinInput;
private JTextField webInput;
private JTextField passInput;
private JButton logInButton;
private JButton signOutButton;
private JButton saveButton;
private JButton getButton;
private Info info;

public Screen() {

setLayout(null);

this.pinInput = new JTextField();
pinInput.setBounds(50, 300, 100, 30);
add(pinInput);

this.webInput = new JTextField();
webInput.setBounds(300, 300, 150, 30);
add(webInput);

this.passInput = new JTextField();
passInput.setBounds(300, 325, 150, 30);
add(passInput);


logInButton = new JButton("Login");
logInButton.setBounds(50, 350, 100, 30);
add(logInButton);
logInButton.addActionListener(this);
setFocusable(true);

signOutButton = new JButton("Signout");
signOutButton.setBounds(50, 375, 100, 30);
add(signOutButton);
signOutButton.addActionListener(this);
setFocusable(true);

saveButton = new JButton("Save");
saveButton.setBounds(300, 350, 125, 30);
add(saveButton);
saveButton.addActionListener(this);
setFocusable(true);

getButton = new JButton("Get");
getButton.setBounds(300, 375, 125, 30);
add(getButton);
getButton.addActionListener(this);
setFocusable(true);

info = new Info("", "", 1234);
info.readFromFile("passwords.txt");

}

@Override
public Dimension getPreferredSize(){
return new Dimension(800,600);
}

@Override
public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.drawString("PIN: ",20 , 320);
	g.drawString("Website : ", 200, 320);
	g.drawString("Password: ", 200, 345);

	if (info.getAccess()) {
		g.drawString("Website: " + info.getWeb(), 400, 100);
		g.drawString("Password: " + info.getPassword(), 400, 120);
	}
	else {
		g.drawString("No Access ", 400, 100);
	}
}

public void actionPerformed(ActionEvent e) {
	if (e.getSource() == logInButton) {
		int pin = Integer.parseInt(pinInput.getText());
		info.checkPin(pin);
		webInput.setText("");
		passInput.setText("");
		repaint();
	}
	else if (e.getSource() == signOutButton) {
		info.disableAccess();
		pinInput.setText("");
		repaint();
	}
	else if (e.getSource() == saveButton) {
		String website = webInput.getText();
		String password = passInput.getText();
		info.save(website, password);
		info.saveToFile("passwords.txt");
		repaint();
	}
	else if (e.getSource() == getButton) {
		String website = webInput.getText();
		String password = info.get(website);
		passInput.setText(password);
		repaint();
	}
}
}