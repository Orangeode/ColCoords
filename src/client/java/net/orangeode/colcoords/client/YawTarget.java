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

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class YawTarget {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public String getTarget(){
        assert mc.player != null;

        float rawYaw = mc.player.getYaw();
        float yaw = (rawYaw % 360 + 360) % 360;

        if (45 < yaw && yaw < 135) {
            return Text.translatable("net.orangeode.west").getString();
        } else if (135 < yaw && yaw < 225) {
            return  Text.translatable("net.orangeode.north").getString();
        } else if (225 < yaw && yaw < 315) {
            return  Text.translatable("net.orangeode.east").getString();
        } else {
            return  Text.translatable("net.orangeode.south").getString();
        }
    }
}
