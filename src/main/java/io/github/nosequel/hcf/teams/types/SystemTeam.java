package io.github.nosequel.hcf.teams.types;

import io.github.nosequel.hcf.TeamsConstants;
import io.github.nosequel.hcf.teams.Team;

import java.util.UUID;

public class SystemTeam extends Team<SystemTeam> {

    {
        this.setStorageProvider(TeamsConstants.SYSTEM_TEAM_STORAGE);
    }

    public SystemTeam(UUID uniqueId, String name) {
        super(uniqueId, name);
    }
}