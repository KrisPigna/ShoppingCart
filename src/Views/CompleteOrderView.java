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

import Inventory.Inventory;
import Models.ShoppingCart;

public class CompleteOrderView extends JPanel { // for commit
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
	
	//method to add a change listener to an object
		public void addChangeListener(ChangeListener listener) {
		    listenerList.add(ChangeListener.class, listener);
		}
		
		//method to iterate through all existing change listeners and notify them of a state change
		protected void fireStateChanged(ChangeEvent evt) {
		    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
		    if (listeners != null && listeners.length > 0) {
		        for (ChangeListener listener : listeners) {
		            listener.stateChanged(evt);
		        }
		    }
		}
}
