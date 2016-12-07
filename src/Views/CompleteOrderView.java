package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * View class handles UI for a window confirming user transaction is complete.
 * <p>For the scope of the Shopping Cart Application Project, this view serves
 * as a placeholder for what would be a payment screen. Instead of collection and 
 * processing payment, the user is simply informed their order is complete, and given
 * the opportunity to return to the main product browse screen to do more shopping.</p>
 * @author Paul
 */
public class CompleteOrderView extends JPanel { 
	/**
	 * Constructor for the view.
	 * <p>A JButton "returnToBrowse" includes functionality to initiate a state change
	 * and switch views back to CustomerInventoryView.</p>
	 */
	public CompleteOrderView() {
		JLabel title = new JLabel("Your Order has been Placed!");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		JButton returnToBrowse = new JButton("Shop Some More!");
		returnToBrowse.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(returnToBrowse);
						fireStateChanged(evt);
					}
		});
		
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(returnToBrowse, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	
	/**
	 * Method to add a change listener to an object
	 * @param listener The listener to be added
	 */
	public void addChangeListener(ChangeListener listener) {
	    listenerList.add(ChangeListener.class, listener);
	}
	
	/**
	 * Method to iterate through all existing change listeners and notify them of a state change
	 * @param evt The ChangeEvent that listeners are being notified of
	 */
	protected void fireStateChanged(ChangeEvent evt) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}
}
