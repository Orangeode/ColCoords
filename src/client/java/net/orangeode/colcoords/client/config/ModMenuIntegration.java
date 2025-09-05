package net.orangeode.colcoords.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {
    ColCoordsConfigScreen configScreen = new ColCoordsConfigScreen();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> configScreen.create(parent);
    }
}
