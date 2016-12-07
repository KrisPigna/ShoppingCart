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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Models.BarChart;
import Models.ShoppingCart;

/**
 * GUI class for sellers to view important sales data
 * @author kpigna
 *
 */
public class SalesDataView extends JPanel {

	/**
	 * Constructor for SalesDataVIew GUI
	 * @param inv The main inventory to pull sales data from
	 * @param cart Used to pull data of all items sold
	 */
	public SalesDataView(Inventory inv, ShoppingCart cart){
		JLabel subtitle = new JLabel("Sales Data");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		JPanel chart = populateBarChart(0,0,0);
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
	public void updateView(Inventory inv, ShoppingCart cart) {
		this.removeAll();
		JLabel subtitle = new JLabel("Sales Data");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		double cost = inv.getCosts();
		double revenue = cart.getAllRevenue();
		double profit = revenue - cost;
		JPanel chart = populateBarChart((int) cost, (int) revenue, (int) profit);
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
	 * Method to create a JPanel that holds a bar chart representing sales data
	 * @param c Total costs
	 * @param r Total revenue
	 * @param p Total profit
	 * @return JPanel holding the bar chart
	 */
	public JPanel populateBarChart(int c, int r, int p) {
		BarChart bar1 = new BarChart(c, Color.RED);
		BarChart bar2 = new BarChart(r, Color.GREEN);
		BarChart bar3 = new BarChart(p, Color.BLUE);
		JLabel label1 = new JLabel(bar1, JLabel.LEFT);
		JLabel label2 = new JLabel(bar2, JLabel.LEFT);
		JLabel label3 = new JLabel(bar3, JLabel.LEFT);
		JLabel cost = new JLabel("Costs: $" + Integer.toString(c));
		JLabel revenue = new JLabel("Revenues: $" + Integer.toString(r));
		JLabel profit = new JLabel("Profit: $" + Integer.toString(p));
		JPanel chart = new JPanel();
		chart.setLayout(new BoxLayout(chart, BoxLayout.Y_AXIS));
		chart.setPreferredSize(new Dimension(400,100));
		chart.add(cost);
		chart.add(label1);
		chart.add(revenue);
		chart.add(label2);
		chart.add(profit);
		chart.add(label3);
		return chart;
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