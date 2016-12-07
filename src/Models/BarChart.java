package Models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * Class that builds a bar chart representation of sales data
 * @author kpigna
 */
public class BarChart implements Icon {
	/**
	 * BarChart constructor that creates one bar
	 * @param width The width of the bar to be created
	 * @param color The color the bar should have
	 */
	public BarChart(int width, Color color){
		barHeight = 33;
		barWidth = width;
		barColor = color;
	}
	 
	/**
	 * Method to set the bar's width to a new value
	 * @param width New width for the bar to have
	 * @param label The JLabel holding the bar
	 */
	public void setBarWidth(String width, JLabel label){
			barWidth = (Integer.parseInt(width));
			label.repaint();
	}
	
	/**
	 * Accessor for the bar's height
	 */
	@Override
	public int getIconHeight() {
		return barHeight;
	}

	/**
	 * Accessor for the bar's width
	 */
	@Override
	public int getIconWidth() {
		return barWidth;
	}

	/**
	 * Method to create the rectangular bar shape
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double circle = new Rectangle2D.Double(x,y,barWidth,barHeight);
		g2.setColor(barColor);
		g2.fill(circle);
	}
	
	private int barHeight;
	private int barWidth;
	private Color barColor;
}