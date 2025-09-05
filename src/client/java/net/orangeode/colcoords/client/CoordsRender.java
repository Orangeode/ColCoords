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

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.orangeode.colcoords.client.config.ModConfig;
import net.orangeode.colcoords.client.config.ModConfigHandler;

public class CoordsRender implements HudElement {
    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();

    private static final int DEFAULT_BOTTOM_OFFSET = 65;

    private final ModConfig config = ModConfigHandler.HANDLER.instance();

    @Override
    public void render(DrawContext context, RenderTickCounter renderTickCounter) {
        if (!shouldRender()) {
            return;
        }

        String coordsText = buildCoordsText();
        Position pos = calculatePosition(coordsText);

        context.drawText(
                CLIENT.textRenderer,
                coordsText,
                pos.x,
                pos.y,
                config.colorOfText.getRGB(),
                config.isCoordsShadow
        );
    }

    private boolean shouldRender() {
        return CLIENT.player != null
                && CLIENT.world != null
                && ColCoordsClient.isShowHud
                && config.isShowCoords;
    }

    private String buildCoordsText() {
        double x = CLIENT.player.getX();
        double y = CLIENT.player.getY();
        double z = CLIENT.player.getZ();
        String format = String.format(
                "XYZ: [ %%.%df | %%.%df | %%.%df ] [%%s]",
                config.numOfDecimalPoints,
                config.numOfDecimalPoints,
                config.numOfDecimalPoints
        );
        String yawTarget = new YawTarget().getTarget();
        return String.format(format, x, y, z, yawTarget);
    }

    private Position calculatePosition(String text) {
        int screenW = CLIENT.getWindow().getScaledWidth();
        int screenH = CLIENT.getWindow().getScaledHeight();
        int textW   = CLIENT.textRenderer.getWidth(text);

        int baseX = (screenW - textW) / 2;
        int offsetX = (int) ((screenW * config.relativeX) / 100);

        int baseY = screenH - DEFAULT_BOTTOM_OFFSET;
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