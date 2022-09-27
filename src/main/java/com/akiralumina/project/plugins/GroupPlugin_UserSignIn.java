package com.akiralumina.project.plugins;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static com.akiralumina.project.terminus.LuminaCommon.signedSetUserID;

@Slf4j
@Component
public class GroupPlugin_UserSignIn extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {


        if(event.getRawMessage().equals("签到")) {
            if(signedSetUserID.contains(event.getUserId())) {
                //  存在
                bot.sendGroupMsg(event.getGroupId(), "自本次服务器启动时你已签过到！", false);
            } else {
                //  不存在
                bot.sendGroupMsg(event.getGroupId(), "签到已确认", false);
                signedSetUserID.add(event.getUserId());
            }
        } else if(event.getRawMessage().equals("反签到")) {
            if(signedSetUserID.contains(event.getUserId())) {
                //  存在
                signedSetUserID.remove(event.getUserId());
                bot.sendGroupMsg(event.getGroupId(), "反签到已执行，已可以重新签到。", false);
            } else {
                //  不存在
                bot.sendGroupMsg(event.getGroupId(), "你还没有签到，请签到！", false);
//                signedSetUserID.add(event.getUserId());
            }
        }

        if(event.getRawMessage().equals("清空登记")) {
            signedSetUserID = new HashSet<>();
            bot.sendGroupMsg(event.getGroupId(), "签到表已经清空", false);
        }

        return super.onGroupMessage(bot, event);
    }
}
