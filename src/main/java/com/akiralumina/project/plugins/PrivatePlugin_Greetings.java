package com.akiralumina.project.plugins;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrivatePlugin_Greetings extends BotPlugin {

    @Override
    public int onFriendAddNotice(@NotNull Bot bot, @NotNull OnebotEvent.FriendAddNoticeEvent event) {
        return super.onFriendAddNotice(bot, event);
    }

    @Override
    public int onFriendRecallNotice(@NotNull Bot bot, @NotNull OnebotEvent.FriendRecallNoticeEvent event) {
        return super.onFriendRecallNotice(bot, event);
    }

    @Override
    public int onFriendRequest(@NotNull Bot bot, @NotNull OnebotEvent.FriendRequestEvent event) {
        return super.onFriendRequest(bot, event);
    }

    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        System.out.println("rawMessage = " + event.getRawMessage());
        System.out.println("containsExtra(lumina) = " + event.containsExtra("lumina"));
        System.out.println("messageId = " + event.getMessageId());
        System.out.println("eventId = " + event.getUserId());
        System.out.println("sendLike Status: " + bot.sendLike(2792556795L, 1));
        return super.onPrivateMessage(bot, event);
    }
}
