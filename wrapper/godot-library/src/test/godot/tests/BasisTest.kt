package godot.tests

import godot.core.Basis
import kotlin.test.Test
import kotlin.test.assertEquals


class BasisTest {

    @Test
    fun `test determinant`() {
        val basis = Basis(3, 2, 3, 4, 5, 6, 7, 8, 9)
        assertEquals(-6.0, basis.determinant(), "determinant didn't match expected")
    }
}