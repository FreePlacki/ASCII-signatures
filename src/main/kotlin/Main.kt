package signature

import java.io.File


fun readFont(fontFile: File): MutableMap<Char, MutableList<String>> {
    val font = mutableMapOf<Char, MutableList<String>>()

    var current = 'a'
    var j = 0
    var mod = 0
    for (line in fontFile.readLines()) {
        if (j == 0) {
            j++
            mod = line.split(" ")[0].toInt() + 1
            continue
        }
        if (j % mod == 1) {
            j++
            current = line[0]
            continue
        }
        if (j % mod == 2) {
            j++
            font[current] = mutableListOf(line)
            continue
        }
        font[current]?.add(line)
        j++
    }
    return font
}


fun main() {
    print("Name and surname: ")
    val name = readLine()!!
    print("Status: ")
    val status = readLine()!!
    val fileMedium = File("C:\\Users\\biele\\Downloads\\medium.txt")
    val fileRoman = File("C:\\Users\\biele\\Downloads\\roman.txt")

    val font = readFont(fileRoman)
    val fontMedium = readFont(fileMedium)

    val nameStr = mutableListOf<String>()
    for (i in 0..9) {
        var first = true
        for (c in name) {
            if (first) {
                first = false
                nameStr.add(font[c]!![i])
                continue
            }
            if (c == ' ') {
                nameStr[i] += " ".repeat(10)
                continue
            }
            nameStr[i] += font[c]!![i]
        }
    }
    val nameStrStatus = mutableListOf<String>()
    for (i in 0..2) {
        var first = true
        for (c in status) {
            if (first) {
                first = false
                nameStrStatus.add(fontMedium[c]!![i])
                continue
            }
            if (c == ' ') {
                nameStrStatus[i] += " ".repeat(5)
                continue
            }
            nameStrStatus[i] += fontMedium[c]!![i]
        }
    }


    if (nameStr[0].length >= nameStrStatus[0].length) {
        println("8".repeat(nameStr[0].length + 8))
        val space = (nameStr[0].length - nameStrStatus[2].length) / 2
        var space2 = space
        if ((nameStr[0].length - nameStrStatus[2].length) % 2 == 1) space2 = space + 1
        for (i in 0..nameStrStatus.lastIndex) {
            nameStrStatus[i] =
                "88  " + " ".repeat(space) + nameStrStatus[i] + " ".repeat(space2) + "  88\n"
        }
        for (i in 0..nameStr.lastIndex)
            nameStr[i] = "88  " + nameStr[i] + "  88\n"
        print(nameStr.joinToString(""))
        print(nameStrStatus.joinToString(""))
        println("8".repeat(nameStr[0].length-1))
    } else {
        println("8".repeat(nameStrStatus[0].length + 8))
        val space = (nameStrStatus[0].length - nameStr[9].length) / 2
        var space2 = space
        if ((nameStrStatus[0].length - nameStr[9].length) % 2 == 1) space2 = space + 1
        for (i in 0..nameStr.lastIndex) {
            nameStr[i] = "88  " + " ".repeat(space) + nameStr[i] + " ".repeat(space2) + "  88\n"
        }
        for (i in 0..nameStrStatus.lastIndex)
            nameStrStatus[i] = "88  " + nameStrStatus[i] + "  88\n"
        print(nameStr.joinToString(""))
        print(nameStrStatus.joinToString(""))
        println("8".repeat(nameStrStatus[0].length-1))
    }

}