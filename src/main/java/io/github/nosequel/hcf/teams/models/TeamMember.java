package io.github.nosequel.hcf.teams.models;

import io.github.nosequel.hcf.teams.enums.PlayerRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class TeamMember {

    private String name;
    private PlayerRole role;

    private final UUID uniqueId;

    /**
     * Get an {@link OfflinePlayer} object from the
     * team member's uniqueId field.
     *
     * @return the offline player found using {@link Bukkit#getOfflinePlayer(UUID)}
     */
    public OfflinePlayer getBukkitPlayer() {
        return Bukkit.getPlayer(this.uniqueId);
    }
}