package godot.core

import godot.gdnative.godot_print
import godot.gdnative.godot_print_error
import godot.gdnative.godot_print_warning

/**
 * Same as [print] but prefixes the message with DEBUG:
 */
fun debug(message: String) {
    godot_print("DEBUG: $message".toGDString())
}

/**
 * Same as [print]
 */
fun info(message: String) {
    godot_print(message.toGDString())
}

/**
 * Same as [printWarning]
 */
fun warning(message: String, function: String, file: String, line: Int) {
    godot_print_warning(message, function, file, line)
}

/**
 * Same as [printError]
 */
fun error(message: String, function: String, file: String, line: Int, exception: Exception? = null) {
    godot_print_error(message, function, file, line)
    if (exception != null) {
        throw exception
    }
}

fun print(message: String) {
    godot_print(message.toGDString())
}

fun printWarning(description: String, function: String, file: String, line: Int) {
    godot_print_warning(description, function, file, line)
}

fun printError(description: String, function: String, file: String, line: Int) {
    godot_print_error(description, function, file, line)
}

fun printError(description: String, function: String, file: String, line: Int, exception: Exception) {
    godot_print_error(description, function, file, line)
    throw exception
}

