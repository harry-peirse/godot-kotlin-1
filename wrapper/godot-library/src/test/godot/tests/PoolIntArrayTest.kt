package godot.tests

import godot.core.GDArray
import godot.core.PoolIntArray
import godot.core.Variant
import godot.core.print


class PoolIntArrayTest {
    fun test() {
        print("Testing PoolIntArray")
        print("--------------------------------------")
        val arr = GDArray()
        arr.pushBack(Variant(32443))
        arr.pushBack(Variant(65555))
        val poolFromArray = PoolIntArray(arr)
        print("Testing constructor with GDArray arg: " +
                if (poolFromArray[0] == 32443 && poolFromArray[1] == 65555 && poolFromArray.size() == 2)
                    "OK"
                else
                    "ERROR")
        val copy = PoolIntArray(poolFromArray)
        print("Testing copy constructor: " +
                if (copy[0] == 32443 && copy[1] == 65555 && copy.size() == 2)
                    "OK"
                else
                    "ERROR")
        val pool = PoolIntArray()
        print("Testing size: " +
                if (pool.size() == 0)
                    "OK"
                else
                    "ERROR")
        pool.pushBack(128000)
        print("Testing pushBack: " +
                if (pool.size() == 1)
                    "OK"
                else
                    "ERROR")
        print("Testing get: " +
                if (pool[0] == 128000)
                    "OK"
                else
                    "ERROR")
        pool.pushBack(127456)
        pool.invert()
        print("Testing invert: " +
                if (pool[0] == 127456 && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        pool.append(234245676)
        print("Testing append: " +
                if (pool[2] == 234245676 && pool.size() == 3)
                    "OK"
                else
                    "ERROR")
        pool.insert(2, 84457)
        print("Testing insert: " +
                if (pool[2] == 84457 && pool[3] == 234245676 && pool.size() == 4)
                    "OK"
                else
                    "ERROR")
        val otherPool = PoolIntArray()
        otherPool.pushBack(99)
        otherPool.pushBack(5675754)
        pool.appendArray(otherPool)
        print("Testing appendArray: " +
                if (pool[4] == 99 && pool[5] == 5675754 && pool.size() == 6)
                    "OK"
                else
                    "ERROR")
        pool.remove(0)
        print("Testing remove: " +
                if (pool[0] == 128000 && pool.size() == 5)
                    "OK"
                else
                    "ERROR")

        pool.resize(2)
        print("Testing resize: " +
                if (pool[0] == 128000 && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        print("--------------------------------------")
        print("")
    }
}