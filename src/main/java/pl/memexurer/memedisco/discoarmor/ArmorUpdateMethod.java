package pl.memexurer.memedisco.discoarmor;

import pl.memexurer.memedisco.discoarmor.data.DiscoPlayer;

public interface ArmorUpdateMethod {
    void updateArmor(DiscoPlayer discoPlayer);

    void resetArmor(DiscoPlayer player);
}
