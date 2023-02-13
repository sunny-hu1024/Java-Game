public class Card {
    private int cardID;
    private int[] card2coin=new int[4];
    private String cardName="";
    public Card(int cardid){
        if(cardid>0&&cardid<=6){
            cardID=cardid;
            card2coin[0]=2;
            card2coin[1]=3;
            cardName="matatabi";//木天蓼
        }
        else if(cardid>6&&cardid<=14){
            cardID=cardid;
            card2coin[0]=2;
            card2coin[1]=3;
            card2coin[2]=4;
            card2coin[3]=5;
            cardName="can";//罐罐
        }
        else if(cardid>14&&cardid<=24){
            cardID=cardid;
            card2coin[0]=2;
            card2coin[1]=4;
            card2coin[2]=5;
            card2coin[3]=6;
            cardName="laptop";//筆電
        }
        else if(cardid>24&&cardid<=36){
            cardID=cardid;
            card2coin[0]=2;
            card2coin[1]=4;
            card2coin[2]=6;
            card2coin[3]=7;
            cardName="carton";//紙箱
        }
        else if(cardid>36&&cardid<=50){
            cardID=cardid;
            card2coin[0]=3;
            card2coin[1]=5;
            card2coin[2]=6;
            card2coin[3]=7;
            cardName="dove";//鴿子
        }
        else if(cardid>50&&cardid<=66){
            cardID=cardid;
            card2coin[0]=3;
            card2coin[1]=5;
            card2coin[2]=7;
            card2coin[3]=8;
            cardName="mouse";//勞贖
        }
        else if(cardid>66&&cardid<=84){
            cardID=cardid;
            card2coin[0]=3;
            card2coin[1]=6;
            card2coin[2]=8;
            card2coin[3]=9;
            cardName="cat_teaser";//逗貓棒
        }
        else if(cardid>84&&cardid<=104){
            cardID=cardid;
            card2coin[0]=4;
            card2coin[1]=6;
            card2coin[2]=8;
            card2coin[3]=10;
            cardName="fish";//魚抱枕
        }
    }
    String getCardName(){
        return cardName;
    }
    int getCardID(){
        return cardID;
    }
    public static String getCardName(int cardid){
        if(cardid>0&&cardid<=6){
            return "matatabi";//木天蓼
        }
        else if(cardid>6&&cardid<=14){
            return "can";//罐罐
        }
        else if(cardid>14&&cardid<=24){
            return "laptop";//筆電
        }
        else if(cardid>24&&cardid<=36){
            return "carton";//紙箱
        }
        else if(cardid>36&&cardid<=50){
            return "dove";//鴿子
        }
        else if(cardid>50&&cardid<=66){
            return "mouse";//勞贖
        }
        else if(cardid>66&&cardid<=84){
            return "cat_teaser";//逗貓棒
        }
        else if(cardid>84&&cardid<=104){
            return "fish";//魚抱枕
        }
        else{
            return "";
        }
    }
    public int getCoin(int number){
        int i=0;
        int num_of_coin=0;
        if(number<card2coin[0]){
            return 0;
        }
        while(number>=card2coin[i]){
            num_of_coin=i;
            i++;
            if(i>3){
                break;
            }
        }
        return num_of_coin+1;
    }
}
