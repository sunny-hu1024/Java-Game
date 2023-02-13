import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Planting implements ActionListener{
    JButton farm1,farm2,end;
    Player player;
    Prepare prepare;
    Panel panel;
    boolean conti=true,isFinish=false;
    public Planting(Panel p,Player player,Prepare prepare){
        this.player=player;
        this.prepare=prepare;
        this.panel=p;
        setButton();
    }
    public void setButton(){
        farm1=new JButton("Plant");
        farm1.setActionCommand("1");
        farm1.addActionListener(this);
        farm1.setBounds(525,330,150,60);
        panel.add(farm1);
        farm2=new JButton("Plant");
        farm2.setActionCommand("2");
        farm2.addActionListener(this);
        farm2.setBounds(1065,330,150,60);
        panel.add(farm2);
        end=new JButton("End Planting");
        end.setVisible(false);
        farm1.setVisible(true);
        farm2.setVisible(true);
    }
    public void reset(){
        farm1.setVisible(true);
        farm2.setVisible(true);
    }
    public void Continue(){
        conti=false;
        end.setActionCommand("0");
        end.addActionListener(this);
        end.setBounds(45,675,120,45);
        panel.add(end);
        end.setVisible(true);
        reset();
        panel.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        farm1.setVisible(false);
        farm2.setVisible(false);
        end.setVisible(false);
        int farmToPlant=Integer.valueOf(e.getActionCommand());
        if(farmToPlant==1||farmToPlant==2){
            player.plant((farmToPlant-1),prepare.getCardStatus());
        }
        //System.out.println(farmToPlant);
        if(conti==true){
            Continue();
        }
        else{
            isFinish=true;
        }
        panel.repaint();
    }
    public boolean isFinish(){
        return isFinish;
    }
}
