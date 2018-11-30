package game.bullbattle;

public class Player {
    private String name;
    private int id;
    public Player(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public void printName(){
        System.out.println("player" + getId() + "   " + getName());
    }
}
        /*if (amount > 8) {
                System.out.println("不玩了，哪有那么多牌");
                }
                else if(amount < 2){
        System.out.println("一个人玩什么斗牛？");
        }
        else {*/