package io.github.nosequel.hcf.teams.types;

import io.github.nosequel.hcf.TeamsConstants;
import io.github.nosequel.hcf.teams.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PlayerTeam extends Team {

    // members of the team
    private UUID leaderId;

    private final Set<UUID> members = new HashSet<>();
    private final Set<UUID> captains = new HashSet<>();

    public PlayerTeam(UUID leaderId, UUID uniqueId, String name) {
        super(uniqueId, name);

        this.leaderId = leaderId;
    }

    /**
     * Collect all members within the {@link PlayerTeam} object and
     * return them as a {@link Collection}.
     * <p>
     * The returned members is a collection of all member types.
     *
     * @return the unmodifiable set of members
     */
    public Collection<UUID> getAllMembers() {
        final Set<UUID> members = new HashSet<>(this.members);

        members.add(this.leaderId);
        members.addAll(this.captains);

        return Collections.unmodifiableSet(members);
    }

    @Override
    public void saveEntry() {
        TeamsConstants.PLAYER_TEAM_STORAGE.setEntry(this.uniqueId.toString(), this);
    }
}