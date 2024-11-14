package org.mrdarkimc.SatanicLib.loops;


import org.bukkit.scheduler.BukkitRunnable;
import org.mrdarkimc.SatanicLib.SatanicLib;
import org.mrdarkimc.SatanicLib.taskManager.interfaces.TimedTask;

public class StartStopAsyncTask {
    private final TimedTask task;
    private final long delayPluginBoot;
    private final long taskActiveTimeInTicks;
    private final long timeBetweenActivation;
    public StartStopAsyncTask(TimedTask task) {
        this.task = task;
        this.delayPluginBoot = task.getDelayPluginBoot();
        this.timeBetweenActivation = task.getTimeBetweenActivation();
        this.taskActiveTimeInTicks = task.getTaskActiveTimeInTicks();
        pluginStartUp();
    }
    public void pluginStartUp(){ //autostart when plugin boots
        startUp(delayPluginBoot);
    }
    public void startUp(long ticks){ //just start
        new BukkitRunnable() {
            @Override
            public void run() {
                activate();
            }
        }.runTaskLaterAsynchronously(SatanicLib.getPlugin(), ticks);
    }
    public void deactivateAfter(long ticks){
        new BukkitRunnable() {
            @Override
            public void run() {
                deactivate();
            }
        }.runTaskLaterAsynchronously(SatanicLib.getPlugin(), ticks);
    }
    public void activateAfter(long ticks){
        new BukkitRunnable() {
            @Override
            public void run() {
                activate();
            }
        }.runTaskLaterAsynchronously(SatanicLib.getPlugin(), ticks);
    }
    public void activate(){
        task.activate();
        //Bukkit.getServer().broadcastMessage("Shop is active for: " + taskActiveTimeInTicks /20 + " sec");
        deactivateAfter(taskActiveTimeInTicks);
    }
    public void deactivate(){
        task.deactivate();
        //Bukkit.getServer().broadcastMessage("Shop is closed. Next update in: " + timeBetweenActivation/20 + " sec");
        activateAfter(timeBetweenActivation);
    }
}
