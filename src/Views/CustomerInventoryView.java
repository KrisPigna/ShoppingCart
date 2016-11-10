package Views;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CustomerInventoryView extends JPanel {
	public CustomerInventoryView(){
		final JTextArea text = new JTextArea("Inventory goes here!");
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.CENTER);
		this.setVisible(false);
	}
}
