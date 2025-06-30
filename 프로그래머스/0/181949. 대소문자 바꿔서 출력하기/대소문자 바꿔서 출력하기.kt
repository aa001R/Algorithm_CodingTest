import java.io.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s1 = readLine()!!.map{
        if (it.isUpperCase()) it.lowercaseChar() else it.uppercaseChar()
    }.joinToString("")
    println(s1)
}