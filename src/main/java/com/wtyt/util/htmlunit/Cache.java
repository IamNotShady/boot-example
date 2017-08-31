package com.wtyt.util.htmlunit;

import java.util.Random;

public class Cache {
    //记录id
    static int id = 0;
    //记录cache大小
    static int size = 0;
    //记录头结点
    static Iterm headIterm;
    static class Iterm {
        int id;
        int age = 0;
        Iterm next;
        public Iterm(int id, Iterm next) {
            this.id = id;
            this.next = next;
        }
    }
    //初始化50个iterm
    public static void init() {
        Iterm previous = null;
        for (int i = 1; i < 51; i++) {
            id = i;
            size++;
            if (i == 1) {
                previous = new Iterm(i, null);
            } else if (i == 50) {
                headIterm = new Iterm(i, previous);
            } else {
                previous = new Iterm(i, previous);
            }
        }
    }
    public static void run() {
        //所有iterm的age加1
        ageAdd(headIterm);
        //随机生成一个节点
        int s = random(size,1);
        if (size<100){
            if (s==1){
                headIterm = new Iterm(++id,headIterm);
                size++;
            }else{
                Iterm a = headIterm;
                Iterm b = null;
                for (int i= 1;i<s;i++){
                    b=a;
                    a = a.next;
                    if (i == s-1){
                        b.next = new Iterm(++id,a);
                        size++;
                    }
                }
            }
        }
        //删除一个节点
        boolean isDelHead = true;
        Iterm c = headIterm;
        Iterm d = null;
        while (true){
            d=c;
            c = c.next;
            if (c==null)break;
            if (c.age == 10){
                d.next=c.next;
                c.next=null;
                isDelHead = false;
                size--;
                break;
            }
        }
        if (isDelHead & size == 100){
            headIterm = headIterm.next;
            size--;
        }
    }
    //所有iterm的age加1
    public static void ageAdd(Iterm iterm) {
        iterm.age++;
        if (iterm.next != null) {
            ageAdd(iterm.next);
        }
    }
    //生成指定范围随机数使用java类库random类
    public static int random(int max, int min) {
        return new Random().nextInt(max - min) + min;
    }
    public static void main(String[] args) throws InterruptedException {
        init();
        for (int i =0;i<200;i++){
            run();
            StringBuffer str = new StringBuffer();
            Iterm a = headIterm;
            while (true){
                str.append("{"+a.id+","+a.age+"},");
                a = a.next;
                if (a == null)break;
            }
            System.out.println(str.append("\n"));
            System.out.println(size);
            //Thread.sleep(1000);
        }
    }

}
