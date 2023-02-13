import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
public class Trade {
    private ArrayList<Integer> bankCard;//莊家的牌
    private ArrayList<Integer> playerCard;//其他玩家的牌
    public ArrayList<Integer> b2p=new ArrayList<Integer>();
    public ArrayList<Integer> p2b=new ArrayList<Integer>();
    private boolean isBank=false;
    private boolean bank=true;
    private boolean player=false;
    private Player p0;
    private Player p1;
    Scanner sc=new Scanner(System.in);
    JLabel fortrade1,fortrade2;
    public Trade(int[] forTrade,Player p0,Player p1,Frame fr){
        this.p0=p0;
        this.p1=p1;
        bankCard=p0.getHandCard();
        playerCard=p1.getHandCard();
        // 
        Icon t1=new ImageIcon("icon/"+Card.getCardName(forTrade[0])+".png");
        System.out.println(Card.getCardName(forTrade[0]));
        fortrade1=new JLabel(Card.getCardName(forTrade[0]));
        fortrade1.setIcon(t1);
        fortrade1.setBounds(545,720,90,90);
        // 
        // 
        System.out.print("your card:");
        for(Integer i:bankCard){
            System.out.print(Integer.valueOf(i)+" ");
        }
        System.out.print(", for trade:");
        for(int i:forTrade){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.print(p1.getID()+"'s card:");
        for(Integer i:playerCard){
            System.out.print(Integer.valueOf(i)+" ");
        }
        System.out.println();
        System.out.println("choose your cards(num) to exchange.(enter -1 to end)");
        int c=sc.nextInt();
        while(c!=-1){
            b2p.add(c);
            c=sc.nextInt();
        }
        System.out.println("choose the cards(num) you want.(enter -1 to end)");
        c=sc.nextInt();
        while(c!=-1){
            p2b.add(c);
            c=sc.nextInt();
        }
        while(player==false||bank==false){
            check();
            isBank=!isBank;
        }
        //交易成立
        for(int i=0;i<b2p.size();i++){
            if(Integer.valueOf(b2p.get(i))==forTrade[0]){
                forTrade[0]=-1;
            }
            else if(Integer.valueOf(b2p.get(i))==forTrade[1]){
                forTrade[1]=-1;
            }
            else{
                for(int j=0;j<bankCard.size();j++){
                    if(Integer.valueOf(b2p.get(i))==bankCard.get(j)){
                        bankCard.remove(j);
                        j=j-1;
                    }
                }
            }
        }
        for(int i=0;i<p2b.size();i++){
            for(int j=0;j<playerCard.size();j++){
                if(Integer.valueOf(p2b.get(i))==playerCard.get(j)){
                    playerCard.remove(j);
                    j=j-1;
                }
            }
        }
    }
    void check(){
        if(isBank==true){
            System.out.println("<"+p0.getID()+" check>");
        }
        else{
            System.out.println("<"+p1.getID()+" check>");
        }
        System.out.print(p0.getID()+"'s cards to exchange:");
        for(int i=0;i<b2p.size();i++){
            System.out.print(Integer.valueOf(b2p.get(i))+" ");
        }
        System.out.println();
        System.out.print(p1.getID()+"'s cards to exchange:");
        for(int i=0;i<p2b.size();i++){
            System.out.print(Integer.valueOf(p2b.get(i))+" ");
        }
        System.out.println(", agree?(y/n)");
        String agree=sc.next();
        if(!agree.equals("y")){
            //玩家更改交易內容
            b2p.clear();
            p2b.clear();
            System.out.println("choose "+p0.getID()+"'s cards(num) to exchange(enter -1 to end)");
            int c=sc.nextInt();
            while(c!=-1){
                b2p.add(c);
                c=sc.nextInt();
            }
            System.out.println("choose "+p1.getID()+"'s cards(num) to exchange(enter -1 to end)");
            c=sc.nextInt();
            while(c!=-1){
                p2b.add(c);
                c=sc.nextInt();
            }
                player=!player;
                bank=!bank;
        }
        if(isBank==true){
            bank=true;
        }
        else{
            player=true;
        }
    }

}