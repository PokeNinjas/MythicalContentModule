package com.mythicalnetwork.mythicalmod.util

import joptsimple.internal.Strings
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent

object TooltipHelper {
    fun makeProgressBar(progress: Float, lightColor: Int, darkColor: Int): Component {
        var fontRenderer: Font = Minecraft.getInstance().font
        var char: Int = fontRenderer.width("|")
        var tip: Int = fontRenderer.width("Tooltip Bar Progress")
        var max: Int = tip / char
        var total: Int = (progress * max).toInt()
        var bars: String = ""
        bars += Strings.repeat('|', total)
        if (progress < 1)
            bars += Strings.repeat('|', max - total)
        val components = mutableListOf<Component>()
        components.add(Component.literal(bars.substring(0, total)).withStyle{it.withColor(lightColor)})
        components.add(Component.literal(bars.substring(total, max)).withStyle{it.withColor(darkColor)})
        var component: MutableComponent = Component.empty()
        for (c in components) {
            component = component.append(c)
        }
        return component
    }

    fun formatTime(ticks: Long): String {
        var totalSeconds = ticks / 20
        // output format: 1d 2h 3m 4s. There are 20 ticks in a second.
        // calculate days, hours, minutes, and remaining seconds
        val days = totalSeconds / (60 * 60 * 24)
        totalSeconds -= days * (60 * 60 * 24)
        val hours = totalSeconds / (60 * 60)
        totalSeconds -= hours * (60 * 60)
        val minutes = totalSeconds / 60
        totalSeconds -= minutes * 60
        val seconds = totalSeconds
        var builder = StringBuilder()
        if (days > 0) {
            builder.append(days).append("d ")
        }
        if (hours > 0) {
            builder.append(hours).append("h ")
        }
        if (minutes > 0) {
            builder.append(minutes).append("m ")
        }
        if (seconds > 0) {
            builder.append(seconds).append("s")
        }
        // remove the space at the end
        builder = StringBuilder(builder.toString().trim())
        return builder.toString()
    }
}