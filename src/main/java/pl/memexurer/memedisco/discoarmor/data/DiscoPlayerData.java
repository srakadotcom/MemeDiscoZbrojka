package pl.memexurer.memedisco.discoarmor.data;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DiscoPlayerData {
    private final ConcurrentHashMap<UUID, DiscoPlayer> playerMap = new ConcurrentHashMap<>();

    public DiscoPlayer getPlayer(UUID playerUniqueId) {
        return playerMap.computeIfAbsent(playerUniqueId, DiscoPlayer::new);
    }

    public Collection<DiscoPlayer> getPlayers() {
        return playerMap.values().stream().filter(DiscoPlayer::isArmorEnabled).collect(Collectors.toList());
    }
}
