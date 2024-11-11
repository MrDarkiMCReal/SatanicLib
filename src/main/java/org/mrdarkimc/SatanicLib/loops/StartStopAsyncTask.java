package org.mrdarkimc.SatanicLib.loops;


import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.mrdarkimc.SatanicLib.taskManager.interfaces.TimedTask;

public class StartStopAsyncTask {
    private final TimedTask task;
    private final JavaPlugin plugin;
    private final long delayPluginBoot;
    private final long taskActiveTimeInTicks;
    private final long timeBetweenActivation;
    public StartStopAsyncTask(TimedTask task, JavaPlugin plugin) {
        this.task = task;
        this.plugin = plugin;
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
        }.runTaskLaterAsynchronously(plugin, ticks);
    }
    public void deactivateAfter(long ticks){
        new BukkitRunnable() {
            @Override
            public void run() {
                deactivate();
            }
        }.runTaskLaterAsynchronously(plugin, ticks);
    }
    public void activateAfter(long ticks){
        new BukkitRunnable() {
            @Override
            public void run() {
                activate();
            }
        }.runTaskLaterAsynchronously(plugin, ticks);
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
