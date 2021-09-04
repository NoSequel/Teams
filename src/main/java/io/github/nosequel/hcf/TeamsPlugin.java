package io.github.nosequel.hcf;

import io.github.nosequel.hcf.teams.TeamHandler;
import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamsPlugin extends JavaPlugin {

    private TeamHandler teamHandler;

    @Override
    public void onEnable() {
        this.teamHandler = new TeamHandler(new MongoStorageProvider[]{
                TeamsConstants.PLAYER_TEAM_STORAGE,
                TeamsConstants.SYSTEM_TEAM_STORAGE
        });

        this.teamHandler.loadTeams();
    }

    @Override
    public void onDisable() {
        this.teamHandler.saveTeams();
    }
}