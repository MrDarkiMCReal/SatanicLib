package org.mrdarkimc.SatanicLib.chat.extras;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.mrdarkimc.SatanicLib.chat.interfaces.IChatExtra;

public class ClickEvents {
    public static class TypedEvent implements IChatExtra {
        ClickEvent event;
        public TypedEvent(ClickEvent.Action action, String value) {
            this.event = new ClickEvent(action, value);
        }
        @Override
        public void applyToTextComponent(TextComponent component) {
            component.setClickEvent(event);
        }
    }
    public static class FromClickEvent implements IChatExtra {
        ClickEvent event;
        public FromClickEvent(ClickEvent event) {
            this.event = event;
        }
        @Override
        public void applyToTextComponent(TextComponent component) {
            component.setClickEvent(event);
        }
    }
    public static class RunCommand implements IChatExtra {
        String command;
        public RunCommand(String command) {
            this.command = command;
        }
        @Override
        public void applyToTextComponent(TextComponent component) {
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        }
    }
    public static class SuggestCommand implements IChatExtra {
        String command;
        public SuggestCommand(String command) {
            this.command = command;
        }
        @Override
        public void applyToTextComponent(TextComponent component) {
            component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        }
    }
    public static class OpenUrl implements IChatExtra {
        String URL;
        public OpenUrl(String url) {
            this.URL = url;
        }
        @Override
        public void applyToTextComponent(TextComponent component) {
            component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, URL));
        }
    }
}
