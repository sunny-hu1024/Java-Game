import java.util.ArrayList;
import java.util.HashMap;
public class Farm {
    private ArrayList<Integer> cardID=new ArrayList<Integer>();
    private String itemName="";
    public Farm(){}
    int getItemNum(){
        return cardID.size();
    }
    String getItemName(){
        return itemName;
    }
    void setItemName(String name){
        itemName=name;
    }
    ArrayList<Integer> getCards(){
        return cardID;
    }
    int harvest(HashMap<Integer,String> cardStatus){
        Card countCoin=new Card(cardID.get(0));
        int cat=countCoin.getCoin(cardID.size());
        while(cardID.size()!=0){
            cardStatus.replace(cardID.get(0),"fold");
            cardID.remove(0);
        }
        setItemName("");
        return cat;
    }
    boolean isPlantable(Card card){
        if(cardID.size()==0){
            return true;
        }
        else if(card.getCardName().equals(itemName)){
            return true;
        }
        else{
            return false;
        }
    }
}
