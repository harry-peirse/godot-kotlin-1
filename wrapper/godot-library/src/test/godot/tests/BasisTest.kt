package godot.tests

import godot.core.Basis


class BasisTest {
    fun test() {
        val basis = Basis(3, 2, 3, 4, 5, 6, 7, 8, 9)
        print("Testing determinant(): " +
                if (basis.determinant() == -6.0)
                    "OK"
                else
                    "ERROR")
    }
}