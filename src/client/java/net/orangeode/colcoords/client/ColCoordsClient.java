//Copyright 2025 orangeode
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.

package net.orangeode.colcoords.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.orangeode.colcoords.client.config.ColCoordsConfigScreen;
import net.orangeode.colcoords.client.config.ModConfigHandler;
import org.lwjgl.glfw.GLFW;

public class ColCoordsClient implements ClientModInitializer {
    private static final String MOD_ID = "colcoords";
    private static final Text CATEGORY_KEY = Text.translatable("net.orangeode.colcoords");
    private static final Identifier HUD_COORDS = Identifier.of(MOD_ID, "coordinates_hud");
    private static final Identifier HUD_TIME = Identifier.of(MOD_ID, "time_hud");

    public static boolean isShowHud;

    private static KeyBinding toggleHudKey;
    private static KeyBinding openConfigKey;

    @Override
    public void onInitializeClient() {
        loadConfig();
        registerKeyBindings();
        registerClientTickHandler();
        registerHudElements();
    }

    private void loadConfig() {
        ModConfigHandler.HANDLER.load();
        isShowHud = ModConfigHandler.HANDLER.instance().isShowCoords;
    }

    private void registerKeyBindings() {
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "net.orangeode.showhud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_APOSTROPHE,
                CATEGORY_KEY.getString()
        ));
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "net.orangeode.showsettings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                CATEGORY_KEY.getString()
        ));
    }

    private void registerClientTickHandler() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Открыть меню настроек
            while (openConfigKey.wasPressed()) {
                if (client.player != null) {
                    client.execute(() ->
                            client.setScreen(ColCoordsConfigScreen.create(client.currentScreen))
                    );
                }
            }

            // Переключить отображение координат
            while (toggleHudKey.wasPressed()) {
                isShowHud = !isShowHud;
                if (client.player != null) {
                    Text status = isShowHud ? Text.translatable("net.orangeode.on") : Text.translatable("net.orangeode.off");
                    client.player.sendMessage(
                            Text.translatable("net.orangeode.coords", status),
                            true
                    );
                }
            }
        });
    }

    private void registerHudElements() {
        HudElementRegistry.addLast(HUD_COORDS, new CoordsRender());
        HudElementRegistry.addLast(HUD_TIME,   new TimeRender());
    }
}
