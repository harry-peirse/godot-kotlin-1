@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package godot.core

import platform.posix.pow
import kotlin.math.*

/*
    All basic math functions are already implemented in kotlin.math and don't need to be reimplemented
    Here are only the godot specific math functions
*/

fun bytes2Var(poolByteArray: PoolByteArray): Variant {
    throw NotImplementedError("this is not yet implemented") //TODO: implement
}

fun cartesian2Polar(x: Float, y: Float) = Vector2(sqrt(x * x + y * y), atan2(y, x))

fun char(code: Int) = code.toChar().toString()

fun <T : Comparable<T>> T.clamp(min: T, max: T) = this.coerceIn(min, max)

inline fun <reified T : Any> convert(what: Variant, type: Variant.Type): T? {
    @Suppress("UNCHECKED_CAST")
    return when (type) {
        Variant.Type.NIL -> null
        Variant.Type.BOOL -> what.toBoolean() as? T
        Variant.Type.INT -> what.toInt() as? T
        Variant.Type.REAL -> what.toFloat() as? T
        Variant.Type.STRING -> what.toString() as? T
        Variant.Type.VECTOR2 -> what.toVector2() as? T
        Variant.Type.RECT2 -> what.toRect2() as? T
        Variant.Type.VECTOR3 -> what.toVector3() as? T
        Variant.Type.TRANSFORM2D -> what.toTransform2D() as? T
        Variant.Type.PLANE -> what.toPlane() as? T
        Variant.Type.QUAT -> what.toQuat() as? T
        Variant.Type.RECT3 -> throw NotImplementedError("this is not yet implemented") //TODO: implement
        Variant.Type.BASIS -> what.toBasis() as? T
        Variant.Type.TRANSFORM -> what.toTransform() as? T
        Variant.Type.COLOR -> throw NotImplementedError("this is not yet implemented") //TODO: implement
        Variant.Type.NODE_PATH -> what.toNodePath() as? T
        Variant.Type._RID -> what.toRID() as? T
        Variant.Type.OBJECT -> what.toObject() as? T
        Variant.Type.DICTIONARY -> what.toDictionary() as? T
        Variant.Type.ARRAY -> what.toGDArray() as? T
        Variant.Type.POOL_BYTE_ARRAY -> what.toPoolByteArray() as? T
        Variant.Type.POOL_INT_ARRAY -> what.toPoolIntArray() as? T
        Variant.Type.POOL_REAL_ARRAY -> what.toPoolRealArray() as? T
        Variant.Type.POOL_STRING_ARRAY -> what.toPoolStringArray() as? T
        Variant.Type.POOL_VECTOR2_ARRAY -> what.toPoolVector2Array() as? T
        Variant.Type.POOL_VECTOR3_ARRAY -> what.toPoolVector3Array() as? T
        Variant.Type.POOL_COLOR_ARRAY -> what.toPoolColorArray() as? T
        Variant.Type.VARIANT_MAX -> throw NotImplementedError("this is not yet implemented") //TODO: implement
    }
}

fun db2Linear(db: Float): Float = exp(db * 0.11512925464970228420089957273422).toFloat() //from https://github.com/godotengine/godot/blob/a7f49ac9a107820a62677ee3fb49d38982a25165/core/math/math_funcs.h
fun db2Linear(db: Double): Double = exp(db * 0.11512925464970228420089957273422) //from https://github.com/godotengine/godot/blob/a7f49ac9a107820a62677ee3fb49d38982a25165/core/math/math_funcs.h

fun dectime(value: Float, amount: Float, step: Float): Float { //from https://github.com/godotengine/godot/blob/c9768f15f7bb194622b9020ab2614d47ac7e63dd/modules/mono/glue/GodotSharp/GodotSharp/Core/GD.cs
    val sign = sign(value)
    var newValue = abs(value)
    newValue -= amount * step
    return if (newValue < 0) {
        0.toFloat()
    } else {
        newValue * sign
    }
}

fun dectime(value: Double, amount: Double, step: Double): Double { //from https://github.com/godotengine/godot/blob/c9768f15f7bb194622b9020ab2614d47ac7e63dd/modules/mono/glue/GodotSharp/GodotSharp/Core/GD.cs
    val sign = sign(value)
    var newValue = abs(value)
    newValue -= amount * step
    return if (newValue < 0) {
        0.0
    } else {
        newValue * sign
    }
}

fun deg2Rad(degrees: Float): Float = degrees * PI.toFloat() / 180
fun deg2Rad(degrees: Double): Double = degrees * PI / 180

fun ease(value: Float, curve: Float) = ease(value.toDouble(), curve.toDouble()).toFloat()
fun ease(value: Double, curve: Double): Double {
    var mutableValue = value

    if (mutableValue < 0) {
        mutableValue = 0.0
    } else if (mutableValue > 1.0) {
        mutableValue = 1.0
    }

    when {
        curve > 0 -> {
            return if (curve < 1.0) {
                1.0 - pow(1.0 - mutableValue, 1.0 / curve);
            } else {
                pow(mutableValue, curve);
            }
        }
        curve < 0 -> {
            //inout ease

            return if (mutableValue < 0.5) {
                pow(mutableValue * 2.0, -curve) * 0.5;
            } else {
                (1.0 - pow(1.0 - (mutableValue - 0.5) * 2.0, -curve)) * 0.5 + 0.5;
            }
        }
        else -> {
            return 0.0 // no ease (raw)
        }
    }
}

fun fmod(a: Float, b: Float) = a.rem(b)
fun fmod(a: Double, b: Double) = a.rem(b)

fun fposmod(a: Float, b: Float): Float {
    var value = a.rem(b)
    if ((value < 0 && b > 0) || (value > 0 && b < 0)) {
        value += b
    }
    return value
}

fun fposmod(a: Double, b: Double): Double {
    var value = a.rem(b)
    if ((value < 0 && b > 0) || (value > 0 && b < 0)) {
        value += b
    }
    return value
}

fun fposmod(a: Int, b: Int): Int {
    var value = a % b
    if ((value < 0 && b > 0) || (value > 0 && b < 0)) {
        value += b
    }
    return value
}


//TODO: remove!
fun testFunction() {
    abs(2)
}