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

//import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.orangeode.colcoords.client.config.ModConfig;
import net.orangeode.colcoords.client.config.ModConfigHandler;

public class TimeRender {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    private static final int DEFAULT_BOTTOM_OFFSET = 65;

    private static final ModConfig config = ModConfigHandler.HANDLER.instance();

    public static void render(DrawContext context, RenderTickCounter renderTickCounter){
        if (!shouldRender()) {
            return;
        }

        String timeText = buildTimeText();
        Position pos = calculatePosition(timeText);

        context.drawText(
                CLIENT.textRenderer,
                timeText,
                pos.x,
                pos.y,
                config.colorOfTime.getRGB(),
                config.isTimeShadow
        );
    }

    private static boolean shouldRender() {
        return CLIENT.player != null
                && CLIENT.world != null
                && ColCoordsClient.isShowHud
                && config.isShowTime;
    }

    private static String buildTimeText() {
        assert CLIENT.world != null;
        long ticks = CLIENT.world.getTimeOfDay() % 24000;
        int hours   = (int) ((ticks / 1000 + 6) % 24);
        int minutes = (int) (60 * (ticks % 1000) / 1000.0);

        return String.format(
                Text.translatable("net.orangeode.time").getString(),
                hours,
                minutes
        );
    }

    private static Position calculatePosition(String text) {
        int screenW = CLIENT.getWindow().getScaledWidth();
        int screenH = CLIENT.getWindow().getScaledHeight();
        int textW   = CLIENT.textRenderer.getWidth(text);

        int baseX   = (screenW - textW) / 2;
        int offsetX = (int) ((screenW * config.relativeX) / 100);

        int baseY   = screenH - DEFAULT_BOTTOM_OFFSET + 9;  // +10, чтобы не накладываться на координаты
        int offsetY = (int) ((screenH * config.relativeY) / 100);

        return new Position(baseX + offsetX, baseY - offsetY);
    }

    private static class Position {
        final int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
