package io.github.nosequel.hcf.teams.types;

import io.github.nosequel.hcf.TeamsConstants;
import io.github.nosequel.hcf.teams.Team;
import io.github.nosequel.hcf.teams.models.TeamMember;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PlayerTeam extends Team {

    // members of the team
    private TeamMember leaderId;

    private final Set<TeamMember> members = new HashSet<>();
    private final Set<TeamMember> captains = new HashSet<>();

    public PlayerTeam(TeamMember leaderId, UUID uniqueId, String name) {
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
    public Collection<TeamMember> getAllMembers() {
        final Set<TeamMember> members = new HashSet<>(this.members);

        members.add(this.leaderId);
        members.addAll(this.captains);

        return Collections.unmodifiableSet(members);
    }

    @Override
    public void saveEntry() {
        TeamsConstants.PLAYER_TEAM_STORAGE.setEntry(this.uniqueId.toString(), this);
    }
}