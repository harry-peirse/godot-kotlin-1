package godot.tests

import godot.core.AABB
import godot.core.Plane
import godot.core.Vector3
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AABBTest {

    @Test
    fun `Construct new AABB with default constructor should init it with two Vector3 zeros`() {
        val aabb = AABB()
        assertEquals(Vector3(), aabb.position, "aabb's position vector3 was not Vector3(0, 0)")
        assertEquals(Vector3(), aabb.size, "aabb's size vector3 was not Vector3(0, 0)")
    }

    @Test
    fun `Construct new AABB with values for position and size should init it with the two corresponding Vector3's`() {
        val aabb = AABB(Vector3(1, 2, 3), Vector3(4, 5, 6))
        assertEquals(1.0, aabb.position[0], "aabb's position was not the value it should have been initialized with")
        assertEquals(2.0, aabb.position[1], "aabb's position was not the value it should have been initialized with")
        assertEquals(3.0, aabb.position[2], "aabb's position was not the value it should have been initialized with")
        assertEquals(4.0, aabb.size[0], "aabb's size was not the value it should have been initialized with")
        assertEquals(5.0, aabb.size[1], "aabb's size was not the value it should have been initialized with")
        assertEquals(6.0, aabb.size[2], "aabb's size was not the value it should have been initialized with")
    }

    @Test
    fun `AABB with size 0 on x axis should have no area`() {
        val aabb = AABB()
        assertTrue(aabb.hasNoArea(), "aabb with size 0 on x axis had an area when it shouldn't")
    }

    @Test
    fun `AABB with size 0 on all axis should have no surface`() {
        val aabb = AABB()
        assertTrue(aabb.hasNoSurface(), "aabb with size 0 on all axis had a surface when it shouldn't")
    }

    @Test
    fun `AABB including other AABB should not detect intersection`() {
        val aabb = AABB(Vector3(4, 4, 4), Vector3(1, 1, 1))
        val includingAABB = AABB(Vector3(5, 5, 5), Vector3(1, 1, 1))
        assertTrue(!aabb.intersects(includingAABB), "aabb including another aabb detects an intersection when it shouldn't")
    }

    @Test
    fun `AABB including other AABB should detect intersection with intersectsIncluding`() {
        val aabb = AABB(Vector3(4, 4, 4), Vector3(1, 1, 1))
        val includingAABB = AABB(Vector3(5, 5, 5), Vector3(1, 1, 1))
        assertTrue(aabb.intersectsInclusive(includingAABB), "aabb including another aabb detects no intersection when it should")
    }

    @Test
    fun `AABB enclosing other AABB should detect enclosing`() {
        val aabb = AABB(Vector3(-1, -1, -1), Vector3(2, 2, 2))
        val enclosingAABB = AABB(Vector3(-0.5, -0.5, -0.5), Vector3(1, 1, 1))
        assertTrue(aabb.encloses(enclosingAABB), "aabb enclosing another aabb detects no enclosing when it should")
    }

    @Test
    fun `AABB with size 1, 1, 1 should get support with size 1, 1, 1`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        assertEquals(Vector3(1, 1, 1), aabb.getSupport(Vector3(-1, -1, -1)), "aabb support didn't match expected support")
    }

    @Test
    fun `AABB with size 1, 1, 1 with endpoint 0 should return endpoint 0, 0, 0`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        assertEquals(Vector3(0, 0, 0), aabb.getEndpoint(0), "aabb endpoint didn't match expected endpoint")
    }

    @Test
    fun `AABB with size 1, 1, 1 should intersect plane`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        val plane = Plane(-1, -1, -1, -2)
        assertTrue(aabb.intersectsConvexShape(arrayOf(plane), 1), "aabb didn't intersect plane")
    }

    @Test
    fun `AABB with size 1, 1, 1 should include point with position 0.5, 0.5, 0.5`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        assertTrue(aabb.hasPoint(Vector3(0.5, 0.5, 0.5)), "aabb didn't have point when it should have it")
    }

    @Test
    fun `AABB with size 1, 1, 1 should be able to expand to 2, 2, 2`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        aabb.expand(Vector3(2, 2, 2))
        assertEquals(Vector3(2, 2, 2), aabb.size, "aabb's size didn't match expected size after expansion")
    }

    @Test
    fun `AABB with size 1, 1, 1 should project range in plane`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(2, 2, 2))
        val plane = Plane(-1, -1, -1, -2)
        assertEquals(-4.0, aabb.projectRangeInPlane(plane).first, "aabb didn't project into plane")
        assertEquals(2.0, aabb.projectRangeInPlane(plane).second, "aabb didn't project into plane")
    }

    @Test
    fun `AABB with size 1, 2, 3 should have 3 as it's longest axis size`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 2, 3))
        assertEquals(3.0, aabb.getLongestAxisSize(), "aabb's longest axis size didn't match expected size")
    }

    @Test
    fun `AABB with size 1, 2, 3 should have 1 as it's shortest axis size`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 2, 3))
        assertEquals(1.0, aabb.getShortestAxisSize(), "aabb's shortest axis size didn't match expected size")
    }

    @Test
    fun `AABB with position 1, 1, 1 and size 1, 1, 1 should intersect rat`() {
        val aabb = AABB(Vector3(1, 1, 1), Vector3(1, 1, 1))
        assertTrue(aabb.smitsIntersectRat(Vector3(0, 0, 0), Vector3(1, 1, 1), 1.0, 2.0), "aabb didn't intersect rat")
    }

    @Test
    fun `AABB with position 1, 1, 1 and size 1, 1, 1 should not intersect rat`() {
        val aabb = AABB(Vector3(1, 1, 1), Vector3(1, 1, 1))
        assertTrue(!aabb.smitsIntersectRat(Vector3(-1, 0, 0), Vector3(1, 1, 1), 1.0, 2.0), "aabb did intersect rat when it shouldn't")
    }

    @Test
    fun `AABB be able to grow by 1`() {
        val aabb = AABB(Vector3(1, 1, 1), Vector3(1, 1, 1))
        aabb.grow(1.0)
        assertEquals(Vector3(0, 0, 0), aabb.position, "aabb's position didn't match expected position")
        assertEquals(Vector3(3, 3, 3), aabb.size, "aabb's size didn't match expected size")
    }

    @Test
    fun `AABB area should be 27 when size is 3, 3, 3`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(3, 3, 3))
        assertEquals(27.0, aabb.getArea(), "aabb's area didn't match expected area size")
    }

    @Test
    fun `Merging two aabb's should result in one correspondingly bigger aabb`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        val secondAabb = AABB(Vector3(1, 1, 1), Vector3(1, 1, 1))
        aabb.mergeWith(secondAabb)
        assertEquals(Vector3(0, 0, 0), aabb.position, "aabb's position didn't match expected position")
        assertEquals(Vector3(2, 2, 2), aabb.size, "aabb's size didn't match expected size")
    }

    @Test
    fun `Intersecting two touching aabb's should result in an aabb with no size`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(1, 1, 1))
        val secondAabb = AABB(Vector3(1, 1, 1), Vector3(1, 1, 1))
        assertEquals(AABB(Vector3(1, 1, 1), Vector3(0, 0, 0)), aabb.intersection(secondAabb), "resulting aabb didn't match expected aabb")
    }

    @Test
    fun `Intersecting two overlapping aabb's should result in an aabb with the intersection size`() {
        val aabb = AABB(Vector3(0, 0, 0), Vector3(2, 2, 2))
        val secondAabb = AABB(Vector3(1, 1, 1), Vector3(2, 2, 2))
        assertEquals(AABB(Vector3(1, 1, 1), Vector3(1, 1, 1)), aabb.intersection(secondAabb), "resulting aabb didn't match expected aabb")
    }
}