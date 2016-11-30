package Models;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.JLabel;

public class BarChart implements Icon {
	public BarChart(int width, Color color){
		barHeight = 33;
		barWidth = width/10;
		barColor = color;
	}
	
	public void setBarWidth(String width, JLabel label){
			barWidth = (Integer.parseInt(width))%10;
			label.repaint();
	}
	
	@Override
	public int getIconHeight() {
		return barHeight;
	}

	@Override
	public int getIconWidth() {
		return barWidth;
	}

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