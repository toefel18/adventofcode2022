package nl.toefel.aoc2022

import java.io.File

fun resource(resourceName: String): File = File(ClassLoader.getSystemResource(resourceName).file)