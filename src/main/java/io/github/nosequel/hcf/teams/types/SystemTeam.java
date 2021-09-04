package io.github.nosequel.hcf.teams.types;

import io.github.nosequel.hcf.TeamsConstants;
import io.github.nosequel.hcf.teams.Team;

import java.util.UUID;

public class SystemTeam extends Team<SystemTeam> {

    public SystemTeam(UUID uniqueId, String name) {
        super(uniqueId, name);
    }

    @Override
    public void saveEntry() {
        TeamsConstants.SYSTEM_TEAM_STORAGE.setEntry(this.uniqueId.toString(), this);
    }
}