package com.akiralumina.project.terminus;

import java.util.ArrayList;
import java.util.List;

public class LuminaCommon {

    public static int LUMINA_DEFAULT_BAN_SECONDS = 60;
    public static List<Long> adminListUserID = new ArrayList<>();

    public static List<Long> miuGrantedUserID = new ArrayList<>();

    public static List<Long> allowListGroupID = new ArrayList<>();

    public static boolean isGrantedUser(long presentUserID) {

        adminListUserID.add(2792556795L);

        return allowListGroupID.contains(presentUserID);

    }

    public static boolean inMiuGrantedList(long presentUserID) {

        miuGrantedUserID.add(179223207L);

        return miuGrantedUserID.contains(presentUserID);
    }

    public static boolean inGroupAllowList(long presentGroupID) {

        //  临时代码块
        allowListGroupID.add(618982405L);               //  露米娜斯工作室
        allowListGroupID.add(491572923L);               //  世界-露米娜斯

        return allowListGroupID.contains(presentGroupID);

    }

}
