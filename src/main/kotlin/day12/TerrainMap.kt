package day12

import Point

open class TerrainMap(input: List<String>) {

    val points = mutableMapOf<Point,Char>()
    lateinit var start : Point
    lateinit var finish : Point

    private var rowCount = 0
    private var colCount = 0

    val distances: MutableMap<Point,Int> = mutableMapOf()
    private val pi: MutableMap<Point,Point> = mutableMapOf()

    init {

        // Djikstra's algorithm

        // initialise the value for each point of the grid (height)
        // distances are unknown initially
        input.forEachIndexed{ row, line ->
            line.forEachIndexed { col, c ->
                when (c) {
                    'S' -> {
                        start = Point(row,col)
                        points[start] = 'a'
                    }
                    'E' -> {
                        finish = Point(row,col)
                        points[finish] = 'z'
                    }
                    else -> {
                        points[Point(row,col)] = c
                    }
                }
            }
        }

        rowCount = input.size
        colCount = input.first().length
    }

    private fun getRoutesForPoint(point : Point) : List<Point> {
        val connections = mutableListOf<Point>()

        if (point.x > 0) connections.add(Point(point.x-1, point.y)) // above
        if (point.x < rowCount - 1) connections.add(Point(point.x+1, point.y)) // below
        if (point.y > 0) connections.add(Point(point.x, point.y-1)) // left
        if (point.y < colCount - 1) connections.add(Point(point.x, point.y+1)) // right

        return connections.filter { routeFilter(point, it) }
    }

    open fun routeFilter(from:Point,to:Point) : Boolean {
        // filter down to only the connections less than one above
        return points[to]!! - points[from]!! <= 1
    }

    private fun extractClosest(Q: List<Point>): Point {
        return Q.sortedBy { distances[it] }.first()
    }

    fun findShortestRoutes(startingPoint: Point) {

        // initialise the current best known distances
        points.keys.forEach { point->
            distances[point] = Int.MAX_VALUE
        }
        distances[startingPoint] = 0
        pi.clear()


        val s = mutableListOf<Point>() // visited points
        val q = points.keys.toMutableList() // unvisited points


        while (q.isNotEmpty()) {
            val closestPoint = extractClosest(q)
            q.remove(closestPoint)
            s.add(closestPoint)

            if (distances[closestPoint] == Int.MAX_VALUE) {
                // this point is unreachable
            } else {
                getRoutesForPoint(closestPoint).forEach { route ->
                    // relaxation
                    if (distances[route]!! > distances[closestPoint]!! + 1) {
                        distances[route] = distances[closestPoint]!! + 1
                        pi[route] = closestPoint
                    }
                }
            }
        }
    }

    fun getShortestRouteTo(point: Point) :Int {
        return if (distances[point] != null) distances[point]!! else -1
    }
}

class ReverseTerrainMap(input: List<String>) : TerrainMap(input) {
    override fun routeFilter(from: Point, to: Point): Boolean {
        // filter down to only the connections at most one below
        return points[from]!! - points[to]!! <= 1
    }

    fun getMostScenicPath() : Int {
        // all points with height 'a'
        val aPoints = points.filter { it.value == 'a' }.keys
        var lowestPoint = aPoints.first()
        aPoints.forEach {
            if (distances[it]!! < distances [lowestPoint]!!) lowestPoint = it
        }

        return distances[lowestPoint]!!
    }
}
