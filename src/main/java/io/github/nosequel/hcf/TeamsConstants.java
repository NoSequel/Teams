package io.github.nosequel.hcf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import io.github.nosequel.hcf.teams.types.PlayerTeam;
import io.github.nosequel.hcf.teams.types.SystemTeam;
import io.github.nosequel.storage.mongo.MongoStorageHandler;
import io.github.nosequel.storage.mongo.provider.MongoStorageProvider;
import io.github.nosequel.storage.mongo.settings.impl.NoAuthMongoSettings;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TeamsConstants {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .create();

    // storage handling
    public static final MongoStorageHandler STORAGE_HANDLER = new MongoStorageHandler(new NoAuthMongoSettings("127.0.0.1", 6379, "teams"));

    // the storage providers for teams
    public static final MongoStorageProvider<PlayerTeam> PLAYER_TEAM_STORAGE = new MongoStorageProvider<>(
            "player_teams",
            STORAGE_HANDLER,
            PlayerTeam.class,
            GSON
    );

    public static final MongoStorageProvider<SystemTeam> SYSTEM_TEAM_STORAGE = new MongoStorageProvider<>(
            "system_teams",
            STORAGE_HANDLER,
            SystemTeam.class,
            GSON
    );

}