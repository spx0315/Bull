package game.bullbattle;

public class Poker{
    private String type;
    private String point;
    private int num;
    public Poker(String type,String point,int num){
        this.point=point;
        this.type=type;
        this.num=num;
    }

    public String getType() {
        return type;
    }

    public String getPoint() {
        return point;
    }

    public int getNum() {
        return num;
    }

    public void getCard(){
        System.out.print(this.type + this.point + " ");
    }

}
