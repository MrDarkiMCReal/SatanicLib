package org.mrdarkimc.SatanicLib.loops;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mrdarkimc.SatanicLib.SatanicLib;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DelayTimer {


    private static final Map<Player, Long> delayMap = new ConcurrentHashMap<>(); //player EndTimeInMilis


    public static Long getCurrentTimeLeft(Player player) {
        long endtime = delayMap.getOrDefault(player, -1L);
        long timeLeft;
        if (endtime == -1L)
            return -1L;
        else {
            long currenttime = System.currentTimeMillis();
            timeLeft = endtime - currenttime;
        }
        return timeLeft/1000; //возвращаем в секундах
    }
    public static void removePlayer(Player player){
        delayMap.remove(player);
    }

    public static String getCurrentTimeFormatted(Player player) {
        return formatTime(getCurrentTimeLeft(player));
    }

    public static String getCurrentTimeFormatted(long seconds) {
        return formatTime(seconds);
    }

    private static String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(" ч.");
        }
        if (minutes > 0) {
            if (result.length() > 0) result.append(" ");
            result.append(minutes).append(" мин.");
        }
        if (secs > 0 || result.length() == 0) {
            if (result.length() > 0) result.append(" ");
            result.append(secs).append(" сек.");
        }

        return result.toString();

//        String hoursFormat = "ч.";
//        String minutesFormat = "мин.";
//        String secondsFormat = "сек.";
//
//        if (seconds != 0) {
//            long hours = seconds/3600;
//            long mins = seconds / 60;
//            long lastSeconds = seconds % 60;
//            if (mins > 0) {
//                return String.format("%h %hf %m %mf %s %sf", hours, hoursFormat, mins, minutesFormat, lastSeconds, secondsFormat);
//            } else {
//                return String.format("%d %s", lastSeconds, secondsFormat);
//            }
//        }
//        return "0 сек.";
    }
    public static void setTimer(Player player, long secounds, boolean bukkitTask){
        long endTime = System.currentTimeMillis() + (secounds * 1000);
        delayMap.put(player, endTime);
        new BukkitRunnable() {
            @Override
            public void run() {
                delayMap.remove(player);
            }
        }.runTaskLater(SatanicLib.getPlugin(), secounds * 20);
    }
    public static void setTimer(Player player, long secounds){
        CompletableFuture.supplyAsync(() -> {
            delayMap.put(player,System.currentTimeMillis() + (secounds*1000));
            try {
                TimeUnit.SECONDS.sleep(secounds);
                return delayMap.remove(player);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
