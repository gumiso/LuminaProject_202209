package com.akiralumina.project.plugins;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class GroupPlugin_MuseWar extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {

        String message = event.getRawMessage();
        long groupID = event.getGroupId();
        long userID = event.getUserId();

        String pattern = "(<at qq=[1-9][0-9]{4,10}>)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(message);
//        Set<String> keyList = new HashSet();
//        while(matcher.find()) {
//            keyList.add(matcher.group());
//        }
        if (matcher.find( )) {
            System.out.println("Found value: " + matcher.group(0) );
            System.out.println("Found value: " + matcher.group(1) );
            System.out.println("Found value: " + matcher.group(2) );
            System.out.println("Found value: " + matcher.group(3) );
        } else {
            System.out.println("NO MATCH");
        }
//        System.out.println(keyList.toString());

        return super.onGroupMessage(bot, event);
    }
}
