package godot.tests

import godot.core.GDArray
import godot.core.PoolRealArray
import godot.core.Variant
import godot.core.print


class PoolRealArrayTest {
    fun test() {
        print("Testing PoolRealArray")
        print("--------------------------------------")
        val arr = GDArray()
        arr.pushBack(Variant(32443.34f))
        arr.pushBack(Variant(65555.65f))
        val poolFromArray = PoolRealArray(arr)
        print("Testing constructor with GDArray arg: " +
                if (poolFromArray[0] == 32443.34 && poolFromArray[1] == 65555.65 && poolFromArray.size() == 2)
                    "OK"
                else
                    "ERROR")
        val copy = PoolRealArray(poolFromArray)
        print("Testing copy constructor: " +
                if (copy[0] == 32443.34 && copy[1] == 65555.65 && copy.size() == 2)
                    "OK"
                else
                    "ERROR")
        val pool = PoolRealArray()
        print("Testing size: " +
                if (pool.size() == 0)
                    "OK"
                else
                    "ERROR")
        pool.pushBack(128000.0)
        print("Testing pushBack: " +
                if (pool.size() == 1)
                    "OK"
                else
                    "ERROR")
        print("Testing get: " +
                if (pool[0] == 128000.0)
                    "OK"
                else
                    "ERROR")
        pool.pushBack(127456.3435)
        pool.invert()
        print("Testing invert: " +
                if (pool[0] == 127456.3435 && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        pool.append(234245676.3)
        print("Testing append: " +
                if (pool[2] == 234245676.3 && pool.size() == 3)
                    "OK"
                else
                    "ERROR")
        pool.insert(2, 84457.3)
        print("Testing insert: " +
                if (pool[2] == 84457.3 && pool[3] == 234245676.3 && pool.size() == 4)
                    "OK"
                else
                    "ERROR")
        val otherPool = PoolRealArray()
        otherPool.pushBack(99.0)
        otherPool.pushBack(5675754.67)
        pool.appendArray(otherPool)
        print("Testing appendArray: " +
                if (pool[4] == 99.0 && pool[5] == 5675754.67 && pool.size() == 6)
                    "OK"
                else
                    "ERROR")
        pool.remove(0)
        print("Testing remove: " +
                if (pool[0] == 128000.0 && pool.size() == 5)
                    "OK"
                else
                    "ERROR")

        pool.resize(2)
        print("Testing resize: " +
                if (pool[0] == 128000.0 && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        print("--------------------------------------")
        print("")
    }
}