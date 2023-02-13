import java.util.ArrayList;
import java.util.HashMap;
public class Player {
    private String ID;
    private int cat;//coin
    private ArrayList<Integer> handCard=new ArrayList<>();
    private Farm[] farms=new Farm[2];
    public Player(String id){
        this.ID=id;
        this.cat=0;
        farms[0]=new Farm();
        farms[1]=new Farm();
    }
    String getID(){
        return ID;
    }
    ArrayList<Integer> getHandCard(){
        return handCard;
    }
    Farm getFarm(int number){
        return farms[number];
    }
    int getCats(){
        return cat;
    }
    void addCats(int number){
        cat=cat+number;
    }
    void plant(int fnum,HashMap<Integer,String> cardStatus){//plant first handcard
        Card c=new Card(handCard.get(0));
        if(!farms[fnum].isPlantable(c)){
            System.out.println("is not plantable, harvest!");
            int coin=farms[fnum].harvest(cardStatus);
            cat=cat+coin;
            System.out.println("get "+coin+" cats, cats= "+cat);
        }
        farms[fnum].getCards().add(handCard.get(0));
        if(farms[fnum].getItemName().equals("")){
            farms[fnum].setItemName(c.getCardName());
        }
        cardStatus.replace(handCard.get(0),"inFarm");
        handCard.remove(handCard.get(0));
        System.out.print("farm"+(fnum+1)+":");
        for(int i=0;i<farms[fnum].getCards().size();i++){
            System.out.print(farms[fnum].getCards().get(i)+" ");
        }
        System.out.println();
    }
    void plant(int fnum,Card card,HashMap<Integer,String> cardStatus,Player owner){
        if(!farms[fnum].isPlantable(card)){
            System.out.println("is not plantable, harvest!");
            int coin=farms[fnum].harvest(cardStatus);
            cat=cat+coin;
            System.out.println("get "+coin+" cats, cats="+cat);
        }
        farms[fnum].getCards().add(card.getCardID());
        if(farms[fnum].getItemName().equals("")){
            farms[fnum].setItemName(card.getCardName());
        }
        cardStatus.replace(card.getCardID(),"inFarm");
        System.out.print("farm"+(fnum+1)+":");
        for(int i=0;i<farms[fnum].getCards().size();i++){
            System.out.print(farms[fnum].getCards().get(i)+" ");
        }
        System.out.println();
    }
    void addHandCard(int[] drawed){
        for(int element:drawed){
            handCard.add(element);
        }
    }
}
