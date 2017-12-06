import java.io.File

fun main(args : Array<String>) {
    val input = mutableListOf<String>()
    File("day4_input.txt").useLines { lines -> lines.forEach { input.add(it) } }

    println("Step 1: " + solve(input, false))
    println("Step 2: " + solve(input, true))
}

fun solve(input : List<String>, step2 : Boolean): Int {
    var cnt = 0

    input.forEach{
        val words = mutableSetOf<String>()
        val split = it.split(Regex("\\s+"))

        var valid = true
        split.forEach each@{
            var x : String
            if (step2) {
                var chars = it.toCharArray()
                chars.sort()
                x = chars.joinToString("")
            } else {
                x = it
            }

            if (!words.add(x)) {
                valid = false;
                return@each;
            }
        }

        if (valid) {
            cnt++
        }
    }

    return cnt
}