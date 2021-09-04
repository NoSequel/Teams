package io.github.nosequel.hcf.teams;

import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.UUID;

@Getter
@Setter
public abstract class Team<T extends Team<?>> {

    private String name;
    private MongoStorageProvider<T> storageProvider;

    private ChatColor color = ChatColor.WHITE;

    private final UUID uniqueId;

    public Team(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    /**
     * Save the team to the {@link Team#storageProvider} database.
     *
     * @throws IllegalStateException thrown if the storageProvider field has not been set.
     *                               this should not be the case, unless someone doesn't
     *                               set the storageProvider field within the default
     *                               team constructor.
     */
    @SuppressWarnings("unchecked")
    public void saveEntry() throws IllegalStateException {
        if (this.storageProvider == null) {
            throw new IllegalStateException("Team#storageProvider field is null, unable to save.");
        }

        this.storageProvider.setEntry(this.uniqueId.toString(), (T) this);
    }
}