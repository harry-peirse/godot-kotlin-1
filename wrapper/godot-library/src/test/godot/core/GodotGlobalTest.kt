package godot.core

import kotlin.test.Test
import kotlin.test.assertEquals

class GodotGlobalTest {


    @Test
    fun `check cartesian2Polar conversion`() {
        assertEquals(Vector2(1.4142135381698608, 0.7853981852531433), cartesian2Polar(1f, 1f))
    }

    @Test
    fun `65 should be converted to A`() {
        assertEquals("A", char(65))
    }

    @Test
    fun `clamping 50 between 0 and 25 should result in 25`() {
        assertEquals(25, 50.clamp(0, 25))
        assertEquals(25f, 50f.clamp(0f, 25f))
        assertEquals(25.0, 50.0.clamp(0.0, 25.0))
    }

    @Test
    fun `clamping 50 between 100 and 200 should result in 100`() {
        assertEquals(100, 50.clamp(100, 200))
        assertEquals(100f, 50f.clamp(100f, 200f))
        assertEquals(100.0, 50.0.clamp(100.0, 200.0))
    }

    @Test
    fun `clamping 25 between 0 and 50 should result in 25`() {
        assertEquals(25, 25.clamp(0, 50))
        assertEquals(25f, 25f.clamp(0f, 50f))
        assertEquals(25.0, 25.0.clamp(0.0, 50.0))
    }

    //TODO: implement as self assembling, executing and destructing game as godot calls cannot be tested in unit tests without a running engine
//    @Test
//    fun `converting a variant to another type should result in variant being that type`() {
//        convert<Int>(Variant(1), Variant.Type.INT)
//    }

    @Test
    fun `25 Decibel to linear should result in the correct linear representation`() {
        assertEquals(17.782795f, db2Linear(25f))
        assertEquals(17.78279410038923, db2Linear(25.0))
    }

    @Test
    fun `dectime should decrease the value by step * amount`() {
        assertEquals(59f, dectime(60f, 10f, 0.1f))
        assertEquals(59.0, dectime(60.0, 10.0, 0.1))
    }

    @Test
    fun `deg2Rad should convert degrees to radians`() {
        assertEquals(3.1415927f, deg2Rad(180f))
        assertEquals(3.141592653589793, deg2Rad(180.0))
    }

    @Test
    fun `ease checks`() {
        assertEquals(1.0f, ease(180f, 1f))
        assertEquals(3.1415927f, ease(180f, -1f))
        assertEquals(3.1415927, ease(180.0, 1.0))
        assertEquals(3.1415927, ease(180.0, -1.0))
    }
}