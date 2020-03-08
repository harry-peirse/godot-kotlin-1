package godot.tests

import godot.core.GDArray
import godot.core.PoolByteArray
import godot.core.Variant
import godot.core.print


class PoolByteArrayTest {
    fun test() {
        print("Testing PoolByteArray")
        print("--------------------------------------")
        val arr = GDArray()
        arr.pushBack(Variant(1.toByte()))
        arr.pushBack(Variant(2.toByte()))
        val poolFromArray = PoolByteArray(arr)
        print("Testing constructor with GDArray arg: " +
                if (poolFromArray[0] == 1.toUByte() && poolFromArray[1] == 2.toUByte() && poolFromArray.size() == 2)
                    "OK"
                else
                    "ERROR")
        val copy = PoolByteArray(poolFromArray)
        print("Testing copy constructor: " +
                if (copy[0] == 1.toUByte() && copy[1] == 2.toUByte() && copy.size() == 2)
                    "OK"
                else
                    "ERROR")
        val pool = PoolByteArray()
        print("Testing size: " +
                if (pool.size() == 0)
                    "OK"
                else
                    "ERROR")
        pool.pushBack(128.toUByte())
        print("Testing pushBack: " +
                if (pool.size() == 1)
                    "OK"
                else
                    "ERROR")
        print("Testing get: " +
                if (pool[0] == 128.toUByte())
                    "OK"
                else
                    "ERROR")
        pool.pushBack(127.toUByte())
        pool.invert()
        print("Testing invert: " +
                if (pool[0] == 127.toUByte() && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        pool.append(126.toUByte())
        print("Testing append: " +
                if (pool[2] == 126.toUByte() && pool.size() == 3)
                    "OK"
                else
                    "ERROR")
        pool.insert(2, 100.toUByte())
        print("Testing insert: " +
                if (pool[2] == 100.toUByte() && pool[3] == 126.toUByte() && pool.size() == 4)
                    "OK"
                else
                    "ERROR")
        val otherPool = PoolByteArray()
        otherPool.pushBack(99.toUByte())
        otherPool.pushBack(80.toUByte())
        pool.appendArray(otherPool)
        print("Testing appendArray: " +
                if (pool[4] == 99.toUByte() && pool[5] == 80.toUByte() && pool.size() == 6)
                    "OK"
                else
                    "ERROR")
        pool.remove(0)
        print("Testing remove: " +
                if (pool[0] == 128.toUByte() && pool.size() == 5)
                    "OK"
                else
                    "ERROR")

        pool.resize(2)
        print("Testing resize: " +
                if (pool[0] == 128.toUByte() && pool.size() == 2)
                    "OK"
                else
                    "ERROR")
        print("--------------------------------------")
        print("")
    }
}