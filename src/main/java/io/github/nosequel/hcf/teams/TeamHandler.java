package io.github.nosequel.hcf.teams;

import io.github.nosequel.hcf.teams.types.PlayerTeam;
import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Getter
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

    /**
     * Register a team to the teams set.
     *
     * @param team the team to register.
     */
    public void registerTeam(Team team) {
        this.teams.add(team);
    }

    /**
     * Find a {@link Team} object by the team's name.
     * <p>
     * This method loops through all registered teams,
     * and finds the first team matching the provided name.
     *
     * @param name the name to find the team by
     * @return the found team
     */
    public Optional<Team> findTeamByName(String name) {
        for (Team team : this.teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                return Optional.of(team);
            }
        }

        return Optional.empty();
    }

    /**
     * Find a {@link PlayerTeam} object by a player's {@link UUID} object.
     * <p>
     * This method loops through all registered teams, filters
     * out all non-player team objects, and then finds the
     * first team which contains a member with the provided UUID.
     *
     * @param uniqueId the unique identifier to find the team by
     * @return the found team, or null
     */
    public Optional<PlayerTeam> findTeamByPlayer(UUID uniqueId) {
        for (Team team : this.teams) {
            if (team instanceof PlayerTeam && ((PlayerTeam) team).getTeamMember(uniqueId) != null) {
                return Optional.of((PlayerTeam) team);
            }
        }

        return Optional.empty();
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