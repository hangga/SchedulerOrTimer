import java.util.*

/**
 * Sederhana -> https://chat.openai.com/share/84c1030d-1619-49b2-8b64-e3e6203b5874
 * Agak kompleks -> https://chat.openai.com/share/d8545179-cc2e-4561-9fff-3cfa9cfccd43
 */
class Graph(private val vertices: Int) {
    private val adjMatrix: Array<IntArray> = Array(vertices) { IntArray(vertices) }

    fun addEdge(src: Int, dest: Int, weight: Int) {
        adjMatrix[src][dest] = weight
        adjMatrix[dest][src] = weight
    }

    fun primMST() {
        val parent = IntArray(vertices)
        val key = IntArray(vertices)
        val mstSet = BooleanArray(vertices)

        // Initialization
        for (i in 0 until vertices) {
            key[i] = Int.MAX_VALUE
            mstSet[i] = false
        }

        key[0] = 0
        parent[0] = -1

        // Prim's algorithm
        for (count in 0 until vertices - 1) {
            val u = minKey(key, mstSet)
            mstSet[u] = true

            for (v in 0 until vertices) {
                if (adjMatrix[u][v] != 0 && !mstSet[v] && adjMatrix[u][v] < key[v]) {
                    parent[v] = u
                    key[v] = adjMatrix[u][v]
                }
            }
        }

        printMST(parent)
    }

    private fun printMST(parent: IntArray) {
        println("Edge   Weight")
        for (i in 1 until vertices) {
            println("${parent[i]} - $i    ${adjMatrix[i][parent[i]]}")
        }
    }

    private fun minKey(key: IntArray, mstSet: BooleanArray): Int {
        var min = Int.MAX_VALUE
        var minIndex = -1

        for (v in 0 until vertices) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v]
                minIndex = v
            }
        }

        return minIndex
    }
}

fun main() {
    val graph = Graph(6)

    graph.addEdge(0, 1, 5)
    graph.addEdge(0, 2, 1)
    graph.addEdge(0, 3, 4)
    graph.addEdge(1, 2, 6)
    graph.addEdge(1, 4, 3)
    graph.addEdge(2, 3, 2)
    graph.addEdge(2, 4, 7)
    graph.addEdge(3, 5, 8)
    graph.addEdge(4, 5, 9)

    graph.primMST()
}
