package godot.samples.games.catchBall

import godot.Area
import org.godotengine.kotlin.annotation.RegisterClass
import org.godotengine.kotlin.annotation.RegisterFunction

@RegisterClass("Games/CatchBall/Scripts")
class End: Area() {

    @RegisterFunction
    override fun _process(delta: Double) {
        if(getOverlappingBodies().size() == 1)
            print("END")
            setProcess(false)
    }
}