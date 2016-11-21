package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Models.BarChart;

public class SalesDataView extends JPanel {

	public SalesDataView(Inventory inv){
		JLabel subtitle = new JLabel("Sales Data");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		BarChart bar1 = new BarChart((int) inv.getWholesaleTotal(), Color.RED);
		BarChart bar2 = new BarChart(200, Color.GREEN);
		BarChart bar3 = new BarChart(200, Color.BLUE);
		JLabel label1 = new JLabel("Costs: ", bar1, JLabel.LEFT);
		JLabel label2 = new JLabel("Revenue: ", bar2, JLabel.LEFT);
		JLabel label3 = new JLabel("Profit: ", bar3, JLabel.LEFT);
		JPanel chart = new JPanel();
		chart.setLayout(new BoxLayout(chart, BoxLayout.Y_AXIS));
		chart.setPreferredSize(new Dimension(400,100));
		chart.add(label1);
		chart.add(label2);
		chart.add(label3);
		JButton invMang = new JButton("Inventory Management");
		invMang.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(invMang);
						fireStateChanged(evt);
					}
		});
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(chart, BorderLayout.CENTER);
		this.add(invMang, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	
	/**
	 * Re-creates the view after a state-change to update its contents
	 * @param inv Inventory object to populate view and add products to
	 */
	public void updateView(Inventory inv) {
		this.removeAll();
		JLabel subtitle = new JLabel("Sales Data");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		BarChart bar1 = new BarChart((int) inv.getWholesaleTotal(), Color.RED);
		BarChart bar2 = new BarChart(200, Color.GREEN);
		BarChart bar3 = new BarChart(200, Color.BLUE);
		JLabel label1 = new JLabel("Costs: ", bar1, JLabel.LEFT);
		JLabel label2 = new JLabel("Revenue: ", bar2, JLabel.LEFT);
		JLabel label3 = new JLabel("Profit: ", bar3, JLabel.LEFT);
		JPanel chart = new JPanel();
		chart.setLayout(new BoxLayout(chart, BoxLayout.Y_AXIS));
		chart.setPreferredSize(new Dimension(400,100));
		chart.add(label1);
		chart.add(label2);
		chart.add(label3);
		JButton invMang = new JButton("Inventory Management");
		invMang.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(invMang);
						fireStateChanged(evt);
					}
		});
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(chart, BorderLayout.CENTER);
		this.add(invMang, BorderLayout.SOUTH);
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
