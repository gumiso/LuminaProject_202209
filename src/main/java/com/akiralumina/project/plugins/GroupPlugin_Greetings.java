package com.akiralumina.project.plugins;

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
        }

        if(event.getRawMessage().contains("date")) {
            bot.sendGroupMsg(event.getGroupId(), new Date().toString(), false);
        }

        if(event.getRawMessage().contains("Bot")) {
            bot.sendGroupMsg(event.getGroupId(), "LuminaBot Basic Test Functioning...", false);
        }

        return super.onGroupMessage(bot, event);
    }

    @Override
    public int onGroupRecallNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupRecallNoticeEvent event) {

        bot.sendGroupMsg(event.getGroupId(), "LuminaBot has detected a recall message.", false);

        return super.onGroupRecallNotice(bot, event);
    }
}
