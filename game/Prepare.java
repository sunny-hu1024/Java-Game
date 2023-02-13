import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
public class Prepare {
    private HashMap<Integer,String> cardStatus=new HashMap<Integer,String>();//cardid->status,status=(玩家1,2,3,4,fold(廢牌堆),gacha(卡池),forTrade(交易用),inFarm(田裡))
    private ArrayList<Integer> randomCard=new ArrayList<Integer>();
    private int times_of_shuffle=0;
    public Prepare(Player[] players){
        for(int i=0;i<104;i++){
            cardStatus.put(i+1,"fold");
        }
        shuffle();
        //發牌
        for(int i=0;i<4;i++){
            int[] card=drawCard(5,"players"+i);
            for(int element:card){
                players[i].getHandCard().add(element);
            }
        } 
    }
    int getTimes(){
        return times_of_shuffle;
    }
    String getStatus(int cardid){
        return cardStatus.get(cardid);
    }
    HashMap<Integer,String> getCardStatus(){
        return cardStatus;
    }
    ArrayList<Integer> getRandomCard(){
        return randomCard;
    }
    void shuffle(){
        times_of_shuffle++;
        //取得廢牌堆
        ArrayList<Integer> fold=new ArrayList<Integer>();
        for(int i=0;i<cardStatus.size();i++){
            if(cardStatus.get(i+1).equals("fold")){
                fold.add(i+1);
            }
        }
        //將廢牌洗入卡池
        System.out.println();
        while(fold.size()>0){
            int random=(int)(Math.random()*fold.size());
            randomCard.add(fold.get(random));
            cardStatus.replace(fold.get(random),"gacha");
            fold.remove(random);
        }
    }
    int[] drawCard(int number,String player){
        int[] drawed=new int[number];
        System.out.print("drawed card:");
        for(int i=0;i<number;i++){
            int draw=(int)(Math.random()*randomCard.size());
            drawed[i]=randomCard.get(draw);
            System.out.print(drawed[i]+" ");
            cardStatus.replace(drawed[i],player);
            randomCard.remove(draw);
        }
        System.out.println();
        return drawed;
    }
}
