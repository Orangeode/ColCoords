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

import dev.isxander.yacl3.config.v2.api.SerialEntry;

import java.awt.*;

public class ModConfig {
    @SerialEntry
    public boolean isShowCoords = true;
    @SerialEntry
    public Color colorOfText = new Color(255, 255, 255);
    @SerialEntry
    public Double relativeX = 0d;
    @SerialEntry
    public Double relativeY = 0d;
    @SerialEntry
    public boolean isCoordsShadow = true;
    @SerialEntry
    public Integer numOfDecimalPoints = 1;
    @SerialEntry
    public boolean isShowTime = true;
    @SerialEntry
    public boolean isTimeShadow = true;
    @SerialEntry
    public Color colorOfTime = new Color(255, 255, 255);
}
