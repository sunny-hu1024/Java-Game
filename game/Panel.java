import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.HashMap;
import java.util.ArrayList;
public class Panel extends JPanel{
    String[] imgName={"zero","one","two","three","four","five","six","seven","eight","nine","grass","map","handcard","question","list","paw","cat","matatabi","laptop","carton","mouse","cat_teaser","dove","fish","can"};
    HashMap<String,Image> img=new HashMap<String,Image>();
    ArrayList<Integer> playerCard=new ArrayList<Integer>();
    Farm farm1,farm2;
    Player p=new Player("");
    Player[] players;
    int nowPlayer=-1;
    public Panel(Player[] players,int nowPlayer){
        setSize(1920,1080);
        this.p=players[nowPlayer];
        this.players=players;
        this.nowPlayer=nowPlayer;
        for(int i=0;i<imgName.length;i++){
            img.put(""+imgName[i],new ImageIcon("icon/"+imgName[i]+".png").getImage());
        }
        playerCard=p.getHandCard();
        farm1=p.getFarm(0);
        farm2=p.getFarm(1);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //background
        g.drawImage(img.get("map"),0,0,1920,1080,null);
        g.drawImage(img.get("grass"),360,120,480,480,null);
        g.drawImage(img.get("grass"),900,120,480,480,null);
        g.drawImage(img.get("handcard"),20,700,550,120,null);
        //farm1
        g.drawImage(img.get(imgName[farm1.getItemNum()]),750,125,90,90,null);
        g.drawImage(img.get(farm1.getItemName()),360,480,120,120,null);
        //farm2
        g.drawImage(img.get(imgName[farm2.getItemNum()]),1290,125,90,90,null);
        g.drawImage(img.get(farm2.getItemName()),900,480,120,120,null);
        //handcard
        for(int i=0;i<playerCard.size();i++){
            String s=Card.getCardName(playerCard.get(i));
            g.drawImage(img.get(s),45+i*100,720,90,90,null);
        }
        //players
        int i=0;
        while(i<4){
            if(i==nowPlayer){
                i++;
                if(i>=4){
                    break;
                }
            }
            g.drawImage(img.get("handcard"),67,40+i*60,270,50,null);
            g.drawImage(img.get("paw"),45,45+i*60,45,45,null);
            for(int j=0;j<players[i].getHandCard().size();j++){
                String s=Card.getCardName(players[i].getHandCard().get(j));
                g.drawImage(img.get(s),100+45*j,47+i*60,40,40,null);
            }
            i++;
        }
        //rule
        g.drawImage(img.get("question"),1465,30,45,45,null);
        //list
        g.drawImage(img.get("list"),1465,85,45,45,null);
        //harvest
        JButton farm1=new JButton("Harvest Farm1");
        farm1.setBounds(540,615,120,45);
        add(farm1);
        JButton farm2=new JButton("Harvest Farm2");
        farm2.setBounds(1080,615,120,45);
        add(farm2);
        //cat
        g.drawImage(img.get("cat"),500,300,90,90,null);
    }
}
