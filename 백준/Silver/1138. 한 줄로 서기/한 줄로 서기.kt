import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }; val result = LinkedList<Int>()
    for (i in n - 1 downTo 0) { result.add(input[i], i + 1) }
    println(result.joinToString(" "))
}