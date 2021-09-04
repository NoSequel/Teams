package io.github.nosequel.hcf.commands.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.hcf.teams.Team;
import io.github.nosequel.hcf.teams.TeamHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TeamTypeAdapter implements TypeAdapter<Team> {

    private final TeamHandler teamHandler;

    @Override
    public Team convert(CommandExecutor commandExecutor, String s) throws Exception {
        return this.teamHandler.findTeamByName(s).orElse(null);
    }
}
