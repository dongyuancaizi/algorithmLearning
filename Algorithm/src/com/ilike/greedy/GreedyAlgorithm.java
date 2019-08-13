package com.ilike.greedy;

import java.util.*;

/**
 * 贪心算法
 */
public class GreedyAlgorithm {

    public static void main(String[] args) {
        //创建广播电台，放入到map中
        Map<String, Set<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        Set<String> hashset1 = new HashSet<>();
        hashset1.add("北京");
        hashset1.add("上海");
        hashset1.add("天津");
        Set<String> hashset2 = new HashSet<>();
        hashset2.add("广州");
        hashset2.add("北京");
        hashset2.add("深圳");
        Set<String> hashset3 = new HashSet<>();
        hashset3.add("成都");
        hashset3.add("上海");
        hashset3.add("杭州");
        Set<String> hashset4 = new HashSet<>();
        hashset4.add("上海");
        hashset4.add("天津");
        Set<String> hashset5 = new HashSet<>();
        hashset5.add("杭州");
        hashset5.add("大连");
        broadcasts.put("k1", hashset1);
        broadcasts.put("k2", hashset2);
        broadcasts.put("k3", hashset3);
        broadcasts.put("k4", hashset4);
        broadcasts.put("k5", hashset5);
        //allAreas，存放所有的地区
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        allAreas.add("广州");
        //创建List，存放选择的电台集合
        List<String> selects = new ArrayList<>();
        //定义一个临时的集合，保存在遍历的过程中电台覆盖的地区和当前换没有覆盖地区的交集
        Set<String> tempSet = new HashSet<>();
        //定义一个maxKey,保存在一次遍历过程中，能够覆盖的最多未覆盖地区对应的电台的key
        String maxKey;
        while (allAreas.size() != 0) {
            maxKey = null;
            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                tempSet.addAll(broadcasts.get(key));
                //求出tempSet和allAreas的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量比maxKey指向的未覆盖的地区换多
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey=key;
                }
            }
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区从allAreas清除掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
