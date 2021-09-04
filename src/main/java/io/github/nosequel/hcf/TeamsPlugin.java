package io.github.nosequel.hcf;

import io.github.nosequel.hcf.teams.Team;
import io.github.nosequel.hcf.teams.TeamHandler;
import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public class TeamsPlugin extends JavaPlugin {

    private TeamHandler teamHandler;

    // make this a global field in case someone wants to
    // add a team from an external plugin, they could just
    // simply register the storage provider here and it will
    // automatically load their custom team object.
    private final Set<MongoStorageProvider<? extends Team>> storageProviders = new HashSet<>(Arrays.asList(
            TeamsConstants.PLAYER_TEAM_STORAGE,
            TeamsConstants.SYSTEM_TEAM_STORAGE
    ));

    @Override
    public void onEnable() {
        this.teamHandler = new TeamHandler(this.storageProviders.toArray(new MongoStorageProvider[0]));
        this.teamHandler.loadTeams();
    }

    @Override
    public void onDisable() {
        this.teamHandler.saveTeams();
    }
}