package io.github.nosequel.hcf.teams;

import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;

import java.util.UUID;

@Getter
@Setter
public abstract class Team<T extends Team<?>> {

    protected String name;
    protected ChatColor color = ChatColor.WHITE;

    protected final UUID uniqueId;

    public Team(UUID uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
    }

    public abstract void saveEntry();
}