package com.bytedance.recycleview.recycler;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {

    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("吴朝晖献唱", "524.6w"));
        result.add(new TestData("黄河大合唱", "433.6w"));
        result.add(new TestData("龙的传人", "357.8w"));
        result.add(new TestData("浙大跨年狂欢", "333.6w"));
        result.add(new TestData("舞龙舞狮", "285.6w"));
        result.add(new TestData("OOP出分", "183.2w"));
        result.add(new TestData("在浙大一定要选的课", "139.4w"));
        result.add(new TestData("ADS期末考哭考生", "75.6w"));
        result.add(new TestData("6月食堂菜价上涨", "55w"));
        result.add(new TestData("浙大后勤团队有多强", "43w"));
        result.add(new TestData("热搜全是浙大买的", "-0.01w"));
        return result;
    }

}
