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

package net.orangeode.colcoords.client.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;

public class ColCoordsConfigScreen {
    public static Screen create(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("Used for narration. Could be used to render a title in the future."))
                .save(ModConfigHandler.HANDLER::save)
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Col Coords"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("net.orangeode.pos"))
                                .description(OptionDescription.of(Text.translatable("net.orangeode.relative")))
                                .option(Option.<Double>createBuilder()
                                        .name(Text.translatable("net.orangeode.Xoffset"))
                                        .binding(0d,
                                                () -> ModConfigHandler.HANDLER.instance().relativeX,
                                                newVal -> ModConfigHandler.HANDLER.instance().relativeX = newVal)
                                        .controller(opt -> DoubleSliderControllerBuilder.create(opt)
                                                .range(-50d, 50d)
                                                .step(1d)
                                                .formatValue(val -> Text.literal(String.valueOf(val))))
                                        .build())
                                .option(Option.<Double>createBuilder()
                                        .name(Text.translatable("net.orangeode.Yoffset"))
                                        .binding(0d,
                                                () -> ModConfigHandler.HANDLER.instance().relativeY,
                                                newVal -> ModConfigHandler.HANDLER.instance().relativeY = newVal)
                                        .controller(opt -> DoubleSliderControllerBuilder.create(opt)
                                                .range(0d, 100d)
                                                .step(1d)
                                                .formatValue(val -> Text.literal(String.valueOf(val))))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("net.orangeode.coordssettings"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("net.orangeode.showcoords"))
                                        .description(OptionDescription.of(Text.translatable("net.orangeode.showcoordsdecription")))
                                        .binding(true,
                                                () -> ModConfigHandler.HANDLER.instance().isShowCoords,
                                                newVal -> ModConfigHandler.HANDLER.instance().isShowCoords = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("net.orangeode.coordscolor"))
                                        .binding(new Color(255, 255, 255),
                                                () -> ModConfigHandler.HANDLER.instance().colorOfText,
                                                newVal -> ModConfigHandler.HANDLER.instance().colorOfText = newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("net.orangeode.coordsshadow"))
                                        .binding(true,
                                                () -> ModConfigHandler.HANDLER.instance().isCoordsShadow,
                                                newVal -> ModConfigHandler.HANDLER.instance().isCoordsShadow = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.translatable("net.orangeode.coordsnumofdecimal"))
                                        .binding(1,
                                                () -> ModConfigHandler.HANDLER.instance().numOfDecimalPoints,
                                                newVal -> ModConfigHandler.HANDLER.instance().numOfDecimalPoints = newVal)
                                        .controller(integerOption -> IntegerSliderControllerBuilder.create(integerOption)
                                                .range(0, 5)
                                                .step(1)
                                                .formatValue(value -> Text.literal(String.valueOf(value))))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("net.orangeode.timesettings"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("net.orangeode.showtime"))
                                        .description(OptionDescription.of(Text.translatable("net.orangeode.showtimedescription")))
                                        .binding(true,
                                                () -> ModConfigHandler.HANDLER.instance().isShowTime,
                                                newVal -> ModConfigHandler.HANDLER.instance().isShowTime = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.translatable("net.orangeode.timecolor"))
                                        .binding(new Color(255, 255, 255),
                                                () -> ModConfigHandler.HANDLER.instance().colorOfTime,
                                                newVal -> ModConfigHandler.HANDLER.instance().colorOfTime =newVal)
                                        .controller(opt -> ColorControllerBuilder.create(opt).allowAlpha(true))
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("net.orangeode.timeshadow"))
                                        .binding(true,
                                                () -> ModConfigHandler.HANDLER.instance().isTimeShadow,
                                                newVal -> ModConfigHandler.HANDLER.instance().isTimeShadow = newVal)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build()
                .generateScreen(parent);
    }
}