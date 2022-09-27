package com.akiralumina.project.plugins;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GroupPlugin_UserPoke extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        if(event.getRawMessage().contains("戳一戳")) {

            bot.sendGroupPoke(event.getGroupId(), event.getUserId());
            bot.sendGroupMsg(event.getGroupId(), "戳一戳已经执行", false);
        }

        return super.onGroupMessage(bot, event);
    }
}
