package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.LuminaCommon;
import com.akiralumina.project.terminus.manager.PluginHelper_GroupManager;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GroupPlugin_BanUser extends BotPlugin {
    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        System.out.println("[BanUser] rawMessage: " + event.getRawMessage());
        boolean isAtLuminaPresent = event.getRawMessage().contains("@LuminaBot") || event.getRawMessage().contains("<at qq=\"" + bot.getSelfId() + "\"/>");
        boolean containKeyword = event.getRawMessage().contains("禁言自己");
        if(LuminaCommon.inGroupAllowList(event.getGroupId()) && isAtLuminaPresent && containKeyword) {
            //  [SetBanMyself] 禁言自己模块
            PluginHelper_GroupManager groupManager = new PluginHelper_GroupManager();
            groupManager.groupManager_SetBanMyself(bot, event.getRawMessage(), event.getGroupId(), event.getUserId());
        } else {
            log.info("GroupPlugin_GroupManage 当前群["+event.getGroupId()+"]未获取允许权限或未对机器人进行[@]指定");
        }
        return super.onGroupMessage(bot, event);
    }

}
