package com.akiralumina.project.terminus;

import onebot.OnebotEvent;

import java.util.ArrayList;
import java.util.List;

public class LuminaCommon {

    static List<Long> allowListGroupID = new ArrayList<>();

    public static boolean inGroupAllowList(long presentGroupID) {

        //  临时代码块
        allowListGroupID.add(618982405L);               //  露米娜斯工作室
//        allowListGroupID.add(491572923L);               //  世界-露米娜斯

        return allowListGroupID.contains(presentGroupID);

    }

}
