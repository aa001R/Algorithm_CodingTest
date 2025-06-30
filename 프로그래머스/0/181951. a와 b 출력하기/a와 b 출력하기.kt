import java.io.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (a, b) = readLine()!!.split(" ").map(String::toInt)
    println("a = $a")
    println("b = $b")
}