package de.erdbeerbaerlp.dcintegration.common.discordCommands;

import de.erdbeerbaerlp.dcintegration.common.storage.Configuration;
import de.erdbeerbaerlp.dcintegration.common.storage.Localization;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.jetbrains.annotations.NotNull;



public class CommandLink extends DiscordCommand {

    public CommandLink() {
        super("link", Configuration.instance().linking.whitelistMode ? Localization.instance().commands.descriptions.whitelist : Localization.instance().commands.descriptions.link);
        addOption(OptionType.INTEGER, "code", "Link Code", true);
    }

/* TODO
    @Override
    public void execute(SlashCommandInteractionEvent ev, ReplyCallbackAction replyCallbackAction) {
        final CompletableFuture<InteractionHook> reply = replyCallbackAction.setEphemeral(true).submit();
        Member m = ev.getMember();
        if (m == null) m = DiscordIntegration.INSTANCE.getMemberById(ev.getUser().getIdLong());
        if (m != null)
            if (Configuration.instance().linking.requiredRoles.length != 0) {
                AtomicBoolean ok = new AtomicBoolean(false);
                m.getRoles().forEach((role) -> {
                    for (String s : Configuration.instance().linking.requiredRoles) {
                        if (s.equals(role.getId())) ok.set(true);
                    }
                });
                if (!ok.get()) {
                    reply.thenAccept((c) -> c.editOriginal(MessageEditData.fromContent(Localization.instance().linking.link_requiredRole)).queue());
                    return;
                }
            }
        final OptionMapping code = ev.getOption("code");
        if (code != null) {
            try {
                int num = Integer.parseInt(code.getAsString());
                if (PlayerLinkController.isDiscordLinked(ev.getUser().getId()) && (discord_instance.pendingBedrockLinks.isEmpty() && PlayerLinkController.isDiscordLinkedBedrock(ev.getUser().getId()))) {
                    reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.alreadyLinked.replace("%player%", MessageUtils.getNameFromUUID(PlayerLinkController.getPlayerFromDiscord(ev.getUser().getId())))).queue());
                    return;
                }
                if (discord_instance.pendingLinks.containsKey(num)) {
                    final boolean linked = PlayerLinkController.linkPlayer(ev.getUser().getId(), discord_instance.pendingLinks.get(num).getValue());
                    if (linked) {
                        reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.linkSuccessful.replace("%prefix%", "/").replace("%player%", MessageUtils.getNameFromUUID(PlayerLinkController.getPlayerFromDiscord(ev.getUser().getId())))).queue());
                        discord_instance.srv.sendMCMessage(Localization.instance().linking.linkSuccessfulIngame.replace("%name%", ev.getUser().getName()).replace("%name#tag%", ev.getUser().getAsTag()), discord_instance.pendingLinks.get(num).getValue());
                    } else
                        reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.linkFailed).queue());
                } else if (discord_instance.pendingBedrockLinks.containsKey(num)) {
                    final boolean linked = PlayerLinkController.linkBedrockPlayer(ev.getUser().getId(), discord_instance.pendingBedrockLinks.get(num).getValue());
                    if (linked) {
                        reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.linkSuccessful.replace("%prefix%", "/").replace("%player%", MessageUtils.getNameFromUUID(PlayerLinkController.getBedrockPlayerFromDiscord(ev.getUser().getId())))).queue());
                        discord_instance.srv.sendMCMessage(Localization.instance().linking.linkSuccessfulIngame.replace("%name%", ev.getUser().getName()).replace("%name#tag%", ev.getUser().getAsTag()), discord_instance.pendingBedrockLinks.get(num).getValue());
                    } else
                        reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.linkFailed).queue());
                } else {
                    reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.invalidLinkNumber).queue());
                }
            } catch (NumberFormatException nfe) {
                reply.thenAccept((c) -> c.editOriginal(Localization.instance().linking.linkNumberNAN).queue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }*/

    @Override
    public void execute(SlashCommandInteractionEvent ev, ReplyCallbackAction reply) {

    }

    @Override
    public boolean canUserExecuteCommand(@NotNull User user) {
        return true;
    }
}
