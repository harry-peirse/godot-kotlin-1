package godot.tests

import godot.core.Dictionary
import godot.core.Variant
import godot.core.print


class DictionaryTest {
    fun test() {
        print("Testing Dictionary")
        print("--------------------------------------")
        val dict = Dictionary()
        print("Testing isEmpty: " +
                if (dict.isEmpty())
                    "OK"
                else
                    "ERROR")
        print("Testing size: " +
                if (dict.size() == 0)
                    "OK"
                else
                    "ERROR")
        print("Testing values: " +
                if (dict.values.size() == 0)
                    "OK"
                else
                    "ERROR")
        print("Testing has:" +
                if (!dict.has(Variant()))
                    "OK"
                else
                    "ERROR")
        print("Testing toJson: " + dict.toJson())
        print("--------------------------------------")
        print("")
    }
}