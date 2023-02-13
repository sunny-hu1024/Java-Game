import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
public class Thread {
    private ArrayList<Card> cards=new ArrayList<Card>();
    private Prepare prepare;
    private Player[] players=new Player[4];
    Scanner sc=new Scanner(System.in);
    Frame fr;
    int nowPlayer=0;
    int[] forTrade;
    Thread(){
         //遊戲前準備
        for(int i=1;i<=104;i++){
            Card card=new Card(i);//No.1~104
            cards.add(card);
        }
        players[0]=new Player("player1");
        players[1]=new Player("player2");
        players[2]=new Player("player3");
        players[3]=new Player("player4");
        prepare=new Prepare(players);
        fr=new Frame(players);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1920,1080);
        fr.setVisible(true);
    }
    void run(){
        //遊戲流程
        while(prepare.getTimes()<=2){//最多洗牌兩次，開局算一次
            System.out.println("<now playing is player "+(nowPlayer+1)+">");
            fr.changePanel(nowPlayer);
            //手牌<2時抽兩張
            if(players[nowPlayer].getHandCard().size()<2){
                for(int c:prepare.drawCard(2,"player"+nowPlayer)){
                    players[nowPlayer].getHandCard().add(c);
                }
            }
            //放1~2個道具
            Planting planting=new Planting(fr.panels[nowPlayer],players[nowPlayer],prepare);
            // planting.setButton();
            boolean isFinish=planting.isFinish();
            while(isFinish==false){
                isFinish=planting.isFinish();
                //System.out.print(".");
            }
            //翻牌
            forTrade=new int[2];
            forTrade=prepare.drawCard(2,"forTrade");
            //交易
            // System.out.print("trade?(y/n)");
            JDialog ifTrade=new JDialog(fr);
            ifTrade.setBounds(680,465,180,80);
            ifTrade.setVisible(true);
            ifTrade.setLayout(new FlowLayout());
            ifTrade.add(new JLabel("Trade?"));
            ifTrade.add(new JButton("Yes"));
            ifTrade.add(new JButton("No"));

            // con="y";
            // while(con.equals("y")){
                System.out.print("choose a player to trade: 2");
                Player p=players[/*sc.nextInt()-1*/2];
                Trade trade=new Trade(forTrade,players[nowPlayer],p,fr);
                //放交易過的道具
                System.out.println("<"+players[nowPlayer].getID()+" plant>");
                for(int n=0;n<trade.p2b.size();n++){
                    System.out.print("choose a farm(1/2):");
                    // f=sc.nextInt();
                    // players[nowPlayer].plant((f-1),cards.get(trade.p2b.get(n)-1),prepare.getCardStatus(),p);
                }
                System.out.println("<"+p.getID()+" plant>");
                for(int n=0;n<trade.b2p.size();n++){
                    System.out.print("choose a farm(1/2):");
                    // f=sc.nextInt();
                    // p.plant((f-1),cards.get(trade.b2p.get(n)-1),prepare.getCardStatus(),players[nowPlayer]);
                }
                System.out.print("continue to trade?(y/n)");
                // con="n";
            // }
            //交易結束，種沒交易出去的道具
            System.out.println("<plant untraded card>");
            if(forTrade[0]!=-1){
                System.out.print("choose a farm(1/2):");
                // f=sc.nextInt();
                // players[nowPlayer].plant(f-1,new Card(forTrade[0]),prepare.getCardStatus(),players[nowPlayer]);
            }
            if(forTrade[1]!=-1){
                System.out.print("choose a farm(1/2):");
                // f=sc.nextInt();
                // players[nowPlayer].plant(f-1,new Card(forTrade[1]),prepare.getCardStatus(),players[nowPlayer]);
            }
            //抽牌
            while(players[nowPlayer].getHandCard().size()>=5){
                if(prepare.getRandomCard().size()<1){
                    prepare.shuffle();
                    System.out.println("<shuffle>");
                }
                players[nowPlayer].addHandCard(prepare.drawCard(5-players[nowPlayer].getHandCard().size(), "player"+nowPlayer));
            }
            if(prepare.getRandomCard().size()<4){
                prepare.shuffle();
                System.out.println("<shuffle>");
            }
            nowPlayer++;
            if(nowPlayer>3){
                nowPlayer=0;
            }
        }
        //結算
        System.out.println("<counting cats>");
        for(int i=0;i<4;i++){
            if(players[i].getFarm(0).getCards().size()!=0){
                int cat=players[i].getFarm(0).harvest(prepare.getCardStatus());
                players[i].addCats(cat);
            }
            if(players[i].getFarm(1).getCards().size()!=0){
                int cat=players[i].getFarm(1).harvest(prepare.getCardStatus());
                players[i].addCats(cat);
            }
        }
        int winnerCats=0;
        int winner=-1;
        for(int i=0;i<4;i++){
            System.out.println("player"+(i+1)+":"+players[i].getCats());
            if(players[i].getCats()>=winnerCats){
                winner=i;
                winnerCats=players[i].getCats();
            }
        }
        System.out.println();
        //玩家winner獲勝
        System.out.println("player "+(winner+1)+" 獲勝");
    }
}
