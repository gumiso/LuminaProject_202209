package com.akiralumina.project.plugins;

import com.akiralumina.project.terminus.LuminaCommon;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GroupPlugin_GroupManage extends BotPlugin {

    @Override
    public int onGroupAdminNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupAdminNoticeEvent event) {
        return super.onGroupAdminNotice(bot, event);
    }

    @Override
    public int onGroupBanNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupBanNoticeEvent event) {
        return super.onGroupBanNotice(bot, event);
    }

    @Override
    public int onGroupDecreaseNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupDecreaseNoticeEvent event) {
        return super.onGroupDecreaseNotice(bot, event);
    }

    @Override
    public int onGroupIncreaseNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupIncreaseNoticeEvent event) {
        return super.onGroupIncreaseNotice(bot, event);
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {

        if(LuminaCommon.inGroupAllowList(event.getGroupId())) {
            boolean isKeywordPresent0 = event.getRawMessage().contains("禁言启动");
            boolean isKeywordPresent1 = event.getRawMessage().contains("测试");
            boolean isAtLuminaPresent = event.getRawMessage().contains("@LuminaBot") || event.getRawMessage().contains("<at qq=\"" + bot.getSelfId() + "\"/>");

            if(isKeywordPresent0 && isAtLuminaPresent) {
                bot.setGroupBan(event.getGroupId(), event.getUserId(), 60);
            }


            if(isKeywordPresent1 && isAtLuminaPresent) {
//            bot.sendGroupPoke(event.getGroupId(), event.getUserId());

                if(event.getMessageId() != null) {
                    System.out.println("event.getMessageId() 不为空");
                    Msg msg0 = Msg.builder().text("测试1");
                    bot.sendGroupMsg(event.getGroupId(), msg0, false);
                    Msg msg1 = Msg.builder().reply(event.getMessageId()).text("测试2");
                    bot.sendGroupMsg(event.getGroupId(), msg1, false);
                } else {
                    System.out.println("event.getMessageId() 为空");
                }
            }
        } else {
            log.info("GroupPlugin_GroupManage 当前群["+event.getGroupId()+"]未获取允许权限");
        }

        return super.onGroupMessage(bot, event);
    }

    @Override
    public int onGroupRecallNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupRecallNoticeEvent event) {
        return super.onGroupRecallNotice(bot, event);
    }

    @Override
    public int onGroupRequest(@NotNull Bot bot, @NotNull OnebotEvent.GroupRequestEvent event) {
        return super.onGroupRequest(bot, event);
    }

    @Override
    public int onGroupUploadNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupUploadNoticeEvent event) {
        return super.onGroupUploadNotice(bot, event);
    }
}
