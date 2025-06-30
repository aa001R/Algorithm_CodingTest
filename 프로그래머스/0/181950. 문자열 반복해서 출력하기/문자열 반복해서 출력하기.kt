import java.io.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
	val bw = BufferedWriter(OutputStreamWriter(System.out))
    val input = readLine()!!.split(' ')
    val s1 = input[0]
    val a = input[1]!!.toInt()
    repeat(a){
        bw.append(s1)
    }
    bw.flush()
}