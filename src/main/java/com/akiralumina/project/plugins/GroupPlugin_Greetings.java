package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.LuminaCommon;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class GroupPlugin_Greetings extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {

        if(LuminaCommon.inGroupAllowList(event.getGroupId())) {
            //  获取群号
//        long userId = event.getUserId();
//        long groupId = event.getGroupId();
//        String rawMessage = event.getRawMessage();
//        System.out.println("1[AI-LM][UserID/用户QQ号] " + userId);
//        System.out.println("1[AI-LM][GroupID/QQ群号] " + groupId);
//        System.out.println("1[AI-LM][RawMessage/原始消息] " + rawMessage);
            log.info("LuminaBot 群号：{} QQ：{} 内容：{}", event.getGroupId(), event.getUserId(), event.getRawMessage());

            if(event.getRawMessage().contains("greetings")) {
                bot.sendGroupMsg(event.getGroupId(), "I'm LuminaBot. Greetings !", false);
                bot.sendGroupMsg(event.getGroupId(), "这则消息发自GroupPlugin_Greetings，该类用于测试露米娜Bot对问候消息的反应，并发出问候", false);
            }

            if(event.getRawMessage().contains("date")) {
                bot.sendGroupMsg(event.getGroupId(), new Date().toString(), false);
            }

            if(event.getRawMessage().contains("Bot")) {
                bot.sendGroupMsg(event.getGroupId(), "LuminaBot 基础测试" + "\n" + "第二代露米娜 系统运作中...", false);
            }
        } else {
            log.info("GroupPlugin_Greetings 当前群["+event.getGroupId()+"]未获取允许权限");
        }

        return super.onGroupMessage(bot, event);
    }

    @Override
    public int onGroupRecallNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupRecallNoticeEvent event) {

        if(LuminaCommon.inGroupAllowList(event.getGroupId())) {
//            bot.sendGroupMsg(event.getGroupId(), "LuminaBot has detected a recall message.", false);
            bot.sendGroupMsg(event.getGroupId(), "LuminaBot 侦测到一则已撤回的消息", false);
        } else {
            log.info("GroupPlugin_Greetings 当前群["+event.getGroupId()+"]未获取允许权限");
        }

        return super.onGroupRecallNotice(bot, event);
    }
}
