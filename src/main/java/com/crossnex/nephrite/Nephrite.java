package com.crossnex.nephrite;

import com.google.inject.Inject;
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.slf4j.Logger;

@Plugin(
        id = "nephrite",
        name = "Nephrite",
        version = BuildConstants.VERSION,
        description = "Resource pack sending plugin",
        authors = {"Zomka"}
)
public class Nephrite {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        logger.info("Startup successful.");
    }

    @Subscribe
    public void onPlayerJoin(PlayerResourcePackStatusEvent e) {
        if (e.getStatus().toString().equals("SUCCESSFUL")) {
            logger.info(e.getPlayer().getUsername() + " has successfully applied the resource pack.");
            final Title title = Title.title(Component.text("∄"), Component.text("Welcome to ").color(NamedTextColor.GOLD).append(Component.text("CrossNex.com").color(NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, true).decoration(TextDecoration.UNDERLINED, true)).append(Component.text("!").color(NamedTextColor.GOLD)));
            e.getPlayer().showTitle(title);
            e.getPlayer().playSound(Sound.sound(Key.key("ui.toast.challenge.complete"), Sound.Source.PLAYER, 1f, 1f));
        }
    }
}
