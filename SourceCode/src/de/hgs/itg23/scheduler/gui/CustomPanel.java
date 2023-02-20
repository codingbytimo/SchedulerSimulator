package de.hgs.itg23.scheduler.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import de.hgs.itg23.scheduler.model.Process;
import javax.swing.JPanel;

public class CustomPanel extends JPanel {
	
	private ArrayList<ArrayList<String>> timeline;
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        if (timeline != null)
        {
            
            for (int i = 0; i < timeline.size(); i++)
            {
            	for (int j = 0; j < timeline.size(); j++) {
            		int x = 30 * (i + 1);
                    int y = 20;
                    
                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    g.drawString(timeline.get(i).get(0), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                    g.drawString(timeline.get(i).get(j), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(timeline.get(i).get(j), x + 27, y + 45);
                    }
            	}
                
            }
            
        }
    }
    
    public void setTimeline(ArrayList<ArrayList<String>> timeline)
    {
        this.timeline = timeline;
        repaint();
    }

}
