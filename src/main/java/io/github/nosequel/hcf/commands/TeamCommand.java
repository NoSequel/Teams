package io.github.nosequel.hcf.commands;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.hcf.teams.Team;
import io.github.nosequel.hcf.teams.TeamHandler;
import io.github.nosequel.hcf.teams.enums.PlayerRole;
import io.github.nosequel.hcf.teams.models.TeamMember;
import io.github.nosequel.hcf.teams.types.PlayerTeam;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TeamCommand {

    private final TeamHandler teamHandler;

    @Command(label = "team", userOnly = false)
    public void execute(CommandExecutor executor) {
        executor.sendMessage("&c/team create <name>");
        executor.sendMessage("&c/team delete <name>");
        executor.sendMessage("&c/team debug");
    }

    @Subcommand(label = "create", parentLabel = "team", userOnly = true)
    public void create(BukkitCommandExecutor executor, String teamName) {
        if (this.teamHandler.findTeamByPlayer(executor.getPlayer().getUniqueId()).isPresent()) {
            executor.sendMessage("&cYou're already in a team.");
            return;
        }

        final TeamMember member = new TeamMember(executor.getPlayer().getName(), executor.getPlayer().getUniqueId(), PlayerRole.LEADER);
        final Team team = new PlayerTeam(member, UUID.randomUUID(), teamName);

        this.teamHandler.registerTeam(team);

        executor.sendMessage("&aYou have created a new team.");
    }

    @Subcommand(label = "delete", parentLabel = "team", userOnly = false)
    public void delete(CommandExecutor executor, Team team) {
        team.deleteEntry();
        executor.sendMessage("&cYou have deleted a team.");
    }

    @Subcommand(label = "debug", parentLabel = "team", userOnly = true)
    public void debug(BukkitCommandExecutor executor) {
        final Optional<PlayerTeam> teamOptional = this.teamHandler.findTeamByPlayer(executor.getPlayer().getUniqueId());

        teamOptional.ifPresent(playerTeam -> executor.sendMessage("Team: " + playerTeam.getName()));

        executor.sendMessage("Team Amount: " + this.teamHandler.getTeams().size());
        executor.sendMessage("Team Type Amount: " + this.teamHandler.getProviders().length);
    }
}