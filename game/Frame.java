import javax.swing.JFrame;
public class Frame extends JFrame{
    Panel[] panels=new Panel[4];
    public Frame(Player[] players){
        super("Game");
        for(int i=0;i<4;i++){
            panels[i]=new Panel(players,i);
            panels[i].setLayout(null);
            panels[i].setBounds(0,0,1920,1080);
            panels[i].setVisible(false);
            add(panels[i]);
        }
    }
    public void changePanel(int nowPlayer){
        for(Panel p:panels){
            p.setVisible(false);
        }
        panels[nowPlayer].setVisible(true);
        panels[nowPlayer].repaint();
    }
}