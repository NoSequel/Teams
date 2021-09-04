package io.github.nosequel.hcf.teams;

import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;

import java.util.HashSet;
import java.util.Set;

public class TeamHandler {

    private final Set<Team> teams = new HashSet<>();
    private final MongoStorageProvider<? extends Team>[] providers;

    /**
     * Constructor to make a new {@link TeamHandler} object.
     *
     * @param providers all of the providers to load the teams from
     *                  this is an array because there are several
     *                  different team types (eg. PlayerTeam, SystemTeam)
     */
    public TeamHandler(MongoStorageProvider<? extends Team>[] providers) {
        this.providers = providers;
    }

    public void loadTeams() {
        for (MongoStorageProvider<? extends Team> provider : this.providers) {
            provider.fetchAllEntries().whenCompleteAsync((teams, throwable) -> this.teams.addAll(teams.values()));
        }
    }

    public void saveTeams() {
        for (Team team : this.teams) {
            team.saveEntry();
        }
    }
}