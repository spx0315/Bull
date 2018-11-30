package game.bullbattle;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] point = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] type = {"spades", "clubs", "hearts", "diamonds"};
        String[] name={"师姐","里脊学长","a","b","c","d","e","f"};
        int[] num={1,2,3,4,5,6,7,8,9,10,11,12,13};
        Poker[] pokers = new Poker[52];
        int[] combo = new int[5];
        int[] combop = new int[52];
        int[] random = new int[52];
        int[] bull=new int[8];
        int[] bull1=new int[8];
        System.out.println("请输入玩家个数");
        Scanner information = new Scanner(System.in);
        int amount = information.nextInt();
        if (amount > 8) {
            System.out.println("不玩了，哪有那么多牌");
        }
        else if(amount < 2){
            System.out.println("一个人玩什么斗牛？");
        }
        else {
            Player[] players = new Player[amount];
            for (int i = 0; i < amount; i++) {
                players[i] = new Player();
                players[i].setId(i+1);
                players[i].setName(name[i]);
                players[i].printName();
            }
            int k = 0;
            for (int t = 0; t < 4; t++) {
                for (int j = 0; j < 13; j++) {
                    pokers[k] = new Poker(type[t], point[j],num[j]);
                    k++;
                }
            }
            Random rd = new Random();
            for (int i = 0; i < 52; i++) {
                random[i] = rd.nextInt(52);
                for (int j = 0; j < i; j++) {
                    while (random[i] == random[j]) {
                        random[i] = rd.nextInt(52);
                    }
                }
            }
            for (int i = 0; i < 52; i++) {
                if (pokers[i] != pokers[random[i]]) {
                    Poker temp = pokers[i];
                    pokers[i] = pokers[random[i]];
                    pokers[random[i]] = temp;
                }
            }
            for(int n=0;n<amount;n++) {
                System.out.println(players[n].getName() + "的牌型为：");
                for (int i = 0 + n * 5; i < 5 + n * 5; i++) {
                    pokers[i].getCard();
                }
                int ten1 = 0;
                bull[n] = 0;
                bull1[n]= 0;
                for (int i = 0 + n * 5; i < 5 + n * 5; i++) {
                    combop[i] = pokers[i].getNum();
                    if (combop[i] >= 10) {
                        ten1++;
                    }
                }
                int[] combop1=new int[5];
                for(int i = 0;i<5;i++){
                    combop1[i]=combop[i+ n * 5];
                }
                System.out.println();
                Arrays.sort(combop1);
                for (int i = 0; i < 5 ; i++) {
                    if (combop1[i] >= 10) {
                        combo[i] = 10;
                    } else {
                        combo[i]= combop1[i];
                    }
                }
                for (int i = 0; i < 5; i++) {
                    System.out.print(combo[i] + " ");
                }
                switch (ten1) {
                    case 5:
                        bull[n] = 10;
                        break;
                    case 4:
                        bull[n] = combo[0];
                        break;
                    case 3:
                        if ((combo[0] + combo[1]) % 10 == 0) {
                            bull[n] = (combo[0] + combo[1]);
                        } else {
                            bull[n] = ((combo[0] + combo[1]) % 10);
                        }
                        break;
                    case 2:
                        int judgeH2 = combo[0] + combo[1] + combo[2];
                        if (judgeH2 % 10 == 0) {
                            bull[n] = 10;
                        } else {
                            for (int t = 0; t < 3; t++) {
                                if ((judgeH2 - combo[t]) % 10 == 0) {
                                    bull[n] = combo[t];
                                }
                            }
                            if (bull[n] == 0) {
                                bull1[n] = combop1[4];
                            }
                        }
                        break;
                    case 1:
                        int judgeH1 = combo[0] + combo[1] + combo[2] + combo[3];
                        for (int t = 0; t < 4; t++) {
                            if ((judgeH1 - combo[t]) % 10 == 0) {
                                bull[n] = combo[t];
                            } else {
                                for (int s = t + 1; s < 4; s++) {
                                    if ((judgeH1 - combo[t] - combo[s]) % 10 == 0) {
                                        bull[n] = (combo[t] + combo[s]) % 10;
                                    }
                                }
                                if (bull[n] == 0) {
                                    bull[n] = 0;
                                    bull1[n] = combop1[4];
                                }
                            }
                        }
                        break;
                    default:
                        int judgeH0 = combo[0] + combo[1] + combo[2] + combo[3] + combo[4];
                        for (int t = 0; t < 5; t++) {
                            for (int s = t + 1; s < 5; s++) {
                                if ((judgeH0 - combo[t] - combo[s]) % 10 == 0) {
                                    bull[n] = (combo[t] + combo[s]) % 10;
                                }
                            }
                        }
                        if (bull[n]== 0) {
                            bull1[n] = combop1[4];
                        }
                        break;
                }
                if (bull[n] != 0) {
                    if (bull[n]!= 10) {
                        System.out.println(players[n].getName() + "的牌型是：牛" + bull[n]);
                    } else {
                        System.out.println(players[n].getName() + "的牌型是：牛牛");
                    }
                } else {
                    if (bull1[n] <= 10) {
                        System.out.println(players[n].getName() + "没牛：" + bull1[n]);
                    }
                    if (bull1[n] == 11) {
                        System.out.println(players[n].getName() + "没牛：J");
                    }
                    if (bull1[n] == 12) {
                        System.out.println(players[n].getName() + "没牛：Q");
                    }
                    if (bull1[n] == 13) {
                        System.out.println(players[n].getName() + "没牛：K");
                    }
                }
            }
        }
        int same=0;
        int maximum=0;
        for(int n = 0;n < amount;n++) {
            for (int t = n+1; t < amount; t++) {
                while (bull[n] == bull[t]) {
                    same++;
                }
                if (same == amount - 1) {
                    int max = n;
                    for (int j = n; j < amount; j++) {
                        if (bull1[j] > bull1[max]) {
                            max = j;
                        }
                    }
                    if (max != n) {
                        maximum = n + 1;
                        int temp = bull1[n];
                        bull1[n] = bull1[max];
                        bull1[max] = temp;
                    }
                }
                else {
                    int max = n;
                    for (int j = n; j < amount; j++) {
                        if (bull[j] > bull[max]) {
                            max = j;
                        }
                    }
                    if (max != n) {
                        maximum = n + 1;
                        int temp = bull[n];
                        bull[n] = bull[max];
                        bull[max] = temp;
                    }
                }
            }
        }
        System.out.print("胜者是玩家" + maximum);
    }
}