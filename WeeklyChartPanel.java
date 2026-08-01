import java.awt.*;
import javax.swing.*;

public class WeeklyChartPanel extends JPanel {

    private int[] data = {5, 3, 7, 4, 6, 2, 8};

    private final String[] days =
            {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

    public WeeklyChartPanel() {

        setPreferredSize(new Dimension(600, 300));

        setBackground(new Color(35,35,35));

    }

    public void setData(int[] values){

        if(values != null && values.length == 7){

            data = values;

            repaint();

        }

    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();

        int height = getHeight();

        int left = 60;

        int bottom = height - 50;

        int chartHeight = height - 90;

        g2.setColor(Color.WHITE);

        g2.drawLine(left,20,left,bottom);

        g2.drawLine(left,bottom,width-20,bottom);

        int barWidth = 45;

        int gap = 25;

        for(int i=0;i<7;i++){

            int barHeight = data[i] * 20;

            int x = left + 20 + i*(barWidth+gap);

            int y = bottom - barHeight;

            g2.setColor(new Color(59,130,246));

            g2.fillRoundRect(x,y,barWidth,barHeight,10,10);

            g2.setColor(Color.WHITE);

            g2.drawString(days[i],x+8,bottom+20);

            g2.drawString(String.valueOf(data[i]),x+15,y-8);

        }

        g2.setFont(new Font("Segoe UI",Font.BOLD,18));

        g2.drawString("Weekly Productivity",180,20);

    }

}
