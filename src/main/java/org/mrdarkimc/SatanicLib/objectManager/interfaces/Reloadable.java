package org.mrdarkimc.SatanicLib.objectManager.interfaces;


import java.util.ArrayList;
import java.util.List;

public interface Reloadable {
    /**
     * This interface allows you to reload your config with ease
     * <p>
     * Just implement this interface, override reload(); method and register your class
     * <p>
     *     Reloadable.register(new SomeClass);
     *     <p>
     *         Or you can do it inide of your object
     *         <p>
     *         for example:
     *         <p>
     *        public User(){
     *        Reloadable.register(this);
     *        }
     *        <p>
     *            Then just call Reloadable.reloadAll(); when you need to reload your class
     *
     */

    static void register(Reloadable r){
        if (ReloadableClasses.contains(r))
            return;
        ReloadableClasses.add(r);
    }
    static void reloadAll(){
        //ReloadableClasses.forEach(Reloadable::reload);
        ReloadableClasses.forEach(Reloadable::reload);
    }


    void reload();
    static List<Reloadable> ReloadableClasses = new ArrayList<>();
}
