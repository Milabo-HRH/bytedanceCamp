package com.example.chapter3.homework;

import java.util.List;

public class myDataSet {
    public static String[] getData(int i){
        switch (i){
            case 0:
                return new String[]{"肖申克的救赎", "这个杀手不太冷", "霸王别姬", "泰坦尼克号", "瓦力",
                        "三傻大闹宝莱坞", "放牛班的春天", "千与千寻", "鬼子来了", "星际穿越"};
            case 1:
                return new String[]{"b站崩了", "陈睿偷拿b站服务器下…", "蒙古上单回应陈睿", "叔叔我啊", "你所热爱的就是你的生活","一定是米哈游干的！"};
            case 2:
                return new String[]{
                    "吴朝晖", "任少波", "陈锦辉", "方富民", "黄河清", "汪国军", "翁恺", "陈越", "张振跃"
                };
            default:
                return null;
        }
    }
}
