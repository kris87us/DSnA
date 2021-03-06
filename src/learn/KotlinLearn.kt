package learn

import java.util.*
import kotlin.math.min
import kotlin.math.roundToInt


class ParkingSystem(var big: Int, var medium: Int, var small: Int) {
    fun addCar(carType: Int): Boolean {
        return if (carType == 1 && big > 0) {
            big--
            true
        } else if (carType == 2 && this.medium > 0) {
            medium--
            true
        } else if (carType == 3 && this.small > 0) {
            small--
            true
        } else {
            false
        }
    }
}

fun main() {
    maximum69Number(9669)
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun deleteNode(node: ListNode?) {
    node?.`val` = node?.next?.`val`!!
    node.next = node.next?.next
}

fun middleNode(head: ListNode?): ListNode? {
    var arr = Array<ListNode?>(100) { null }
    var lHead = head
    var i = 0
    while (lHead?.next != null) {
        arr[i] = lHead
        lHead = lHead.next
        i++
    }
    return arr[i / 2]
}

fun maximum69Number(num: Int): Int {
    val strNum = num.toString().toCharArray()
    for ((i, n) in strNum.withIndex()) {
        if (n == '6') {
            strNum[i] = '9'
            break
        }
    }
    return strNum.joinToString("").toInt()
}

fun halvesAreAlike(s: String): Boolean {
    val vowels = listOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    val splitArr = s.chunked(s.length / 2)
    val left = splitArr[0].count { it in vowels }
    val right = splitArr[1].count { it in vowels }
    return left == right
}

fun uniqueMorseRepresentations(words: Array<String>): Int {
    val morse = listOf(
        ".-",
        "-...",
        "-.-.",
        "-..",
        ".",
        "..-.",
        "--.",
        "....",
        "..",
        ".---",
        "-.-",
        ".-..",
        "--",
        "-.",
        "---",
        ".--.",
        "--.-",
        ".-.",
        "...",
        "-",
        "..-",
        "...-",
        ".--",
        "-..-",
        "-.--",
        "--.."
    )
    val list = mutableListOf<String>()
    for (word in words) {
        val str = StringBuilder()
        for (s in word) {
            str.append(morse[s.toInt() - 96 - 1])
        }
        list.add(str.toString())
    }
    return list.distinct().count()
}

fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
    val flat = pieces.flatMap { it.asList() }.toIntArray().sort()
    if (pieces.size > 1 && pieces.size <= arr.size) {
        return flat == arr.sort()
    }
    return false
}

fun createTargetArray(nums: IntArray, index: IntArray): IntArray {
    var target = mutableListOf<Int>()
    var i = 0
    while (i < nums.size) {
        target.add(index[i], nums[i])
        i++
    }
    return target.stream().mapToInt { it }.toArray()
}

fun nextGreaterElement(findNums: IntArray, nums: IntArray): IntArray? {
    val map: MutableMap<Int, Int> = HashMap() // map from x to next greater element of x
    val stack = Stack<Int>()
    for (num in nums) {
        while (!stack.isEmpty() && stack.peek() < num) map[stack.pop()] = num
        stack.push(num)
    }
    for (i in findNums.indices) findNums[i] = map.getOrDefault(findNums[i], -1)
    return findNums
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return a
    return gcd(b, a % b)
}

fun lcm(a: Int, b: Int): Int {
    return a / gcd(a, b) * b
}

fun kaprekarNumbers(p: Int, q: Int) {
    var kaprekarFound = false
    for (n in p..q) {
        val sq: Long = n.toLong() * n.toLong()
        val strSquare = sq.toString()
        var left = strSquare.substring(0, strSquare.length / 2)
        if (left.isEmpty) left = "0"
        val right = strSquare.substring(strSquare.length / 2)
        println("$n | $strSquare | $left | $right | ${left.toInt() + right.toInt()} | ==n ${left.toInt() + right.toInt() == n}")
        if (left.toInt() + right.toInt() == n) {
            kaprekarFound = true
            print("$n ")
        }
    }
    if (!kaprekarFound) println("INVALID RANGE")
}

fun subtractProductAndSum(n: Int): Int {
    var product = 1
    var sum = 0
    var num = n
    while (num > 0) {
        var remainder = num % 10
        sum += remainder
        product *= remainder
        num = num / 10
    }
    return product - sum
}

fun toLowerCase(str: String): String {
    val result = CharArray(str.length)
    for ((index, s) in str.withIndex()) {
        if (s >= 65.toChar() && s < 90.toChar()) {
            result[index] = s + 32
        } else {
            result[index] = s
        }
    }
    return String(result)
}

fun numTeams(rating: IntArray): Int {
    var count = 0
    var i = 0
    while (i >= 0 && i < rating.size - 2) {
        var j = i + 1
        while (j > i && j < rating.size - 1) {
            var k = j + 1
            while (k > j && k < rating.size) {
                if ((rating[i] < rating[j] && rating[i] < rating[k] && rating[j] < rating[k]) ||
                    (rating[i] > rating[j] && rating[i] > rating[k] && rating[j] > rating[k])
                ) {
                    count++
                }
                k++
            }
            j++
        }
        i++
    }
    return count
}

fun xorOperation(n: Int, start: Int): Int {
    var i = 0
    var xorNum = 0
    while (i < n) {
        xorNum = xorNum.xor(start + 2 * i)
    }
    return xorNum
}

fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
    if (word1.joinToString("") == word2.joinToString("")) return true
    return false
}

fun restoreString(s: String, indices: IntArray): String {
    var result = CharArray(indices.size)
    for ((i, n) in indices.withIndex()) {
        result[n] = s[i]
    }
    return result.joinToString("")
}

fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
    var i = 0
    val result = IntArray(nums.size)

    while (i < nums.size) {
        var count = 0
        var j = 0
        while (j < nums.size) {
            if (i != j && nums[j] < nums[i]) {
                count++
            }
            j++
        }
        result[i] = count
        i++
    }
    return result
}

fun checkIfExist(arr: IntArray): Boolean {
    val map = mutableMapOf<Int, Int>()
    for ((i, a) in arr.withIndex()) {
        if (map.containsKey(a * 2)) {
            return true
        } else if (map.containsKey(a / 2) && a % 2 == 0) {
            return true
        }
        map[a] = i
    }
    return false
}

fun firstNotRepeatingCharacter(s: String): Char {
    var map = mutableMapOf<Char, Int>()
    for (item in s) {
        map[item] = map.getOrDefault(item, 0) + 1
    }

    for (item in map) {
        if (item.value == 1) {
            return s[minOf(s.indexOf(item.key), s.indexOf(item.key))]
        }
    }
    return '_'
}

fun firstDuplicate(a: MutableList<Int>): Int {
    var min = -1
    var i = 0
    var countList = IntArray(a.size + 1) { it -> it }
    while (i < a.size) {
        if (countList[a[i]] == 0) {
            countList[a[i]]++
        } else {
            min = a[i]
            break
        }
        i++
    }
    return min
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var i = 0
    while (i < nums2.size) {
        var j = i
        while (j < nums1.size) {

        }
    }
}

fun duplicateZeros(arr: IntArray): Unit {
    var i = 0
    while (i < arr.size) {
        if (arr[i] == 0) {
            var j = arr.size - 1
            while (j > i + 1) {
                arr[j] = arr[j - 1]
                j--
            }
            if (i != arr.size - 1) {
                arr[i + 1] = 0;
                i++
            }
        }
        i++
    }
    arr.forEach { println(it) }
}

fun makingAnagrams(s1: String, s2: String): Int {
    val temp = IntArray(26)
    var i = 0
    var j = 0
    while (i < s1.length || j < s2.length) {
        if (i < s1.length) temp[s1[i] - 'a']++
        if (j < s2.length) temp[s2[j] - 'a']--
        i++
        j++
    }
    return temp.sumBy { it -> Math.abs(it) }
}

fun gemstones(arr: Array<String>): Int {
    var firstElement = arr[0].toSet()
    var i = 1
    while (i < arr.size) {
        firstElement = firstElement.intersect(arr[i].toSet())
        i++
    }
    return firstElement.size
}

fun beautifulBinaryString(b: String): Int {
    val oneCount = b.replace("010", "011").count { it == '1' }
    return oneCount - b.count { it == '1' }
}

fun sortedSquares(nums: IntArray): IntArray {
    var result = IntArray(nums.size)
    for ((i, n) in nums.withIndex()) {
        result[i] = n * n
    }
    result.sort()
    return result
}

// Complete the gameOfThrones function below.
fun gameOfThrones(s: String): String {
    var sum = 0
    for (alphabet in s.toSet()) {
        val count = s.count { it == alphabet }
        sum += count % 2
    }
    if (sum < 2) return "YES"
    return "NO"
}

fun theLoveLetterMystery(s: String): Int {
    var i = 0
    var j = s.length - 1
    var count = 0
    while (j - i >= 1) {
        count += Math.abs(s[j].toInt() - s[i].toInt())
        j--
        i++
    }
    return count
}

fun pangrams(s: String): String {
    for (letter in 'a'..'z') {
        if (s.contains(letter, true)) {
            continue
        } else {
            return "not pangram"
        }
    }
    return "pangram"
}

fun funnyString(s: String): String {
    val asciiList = mutableListOf<Int>()
    for (letter in s) {
        asciiList.add(letter.toInt())
    }
    val revAsciiList = asciiList.reversed()
    var i = 1
    while (i < asciiList.size) {
        val minusOrig = Math.abs(asciiList[i] - asciiList[i - 1])
        val minusRev = Math.abs(revAsciiList[i] - revAsciiList[i - 1])
        if (minusOrig != minusRev) return "Not Funny"
        i++
    }
    return "Funny"
}

fun hackerrankInString(s: String): String {
    val str = "hackerrank"
    var i = 0
    var j = 0
    while (i < s.length) {
        if (j < str.length && s[i] == str[j]) {
            j++
        }
        i++
    }

    if (j == str.length) return "YES"
    return "NO"
}

fun permutationEquation(p: Array<Int>): Array<Int> {
    var result = mutableListOf<Int>()

    for (i in 1..p.size) {
        var p1 = p.indexOf(i) + 1
        result.add(p.indexOf(p1) + 1)
    }
    result.forEach { println(it) }
    return result.toTypedArray()
}

fun superReducedString(s: String): String {
    var i = 1
    var s = s
    while (i < s.length) {
        if (s[i] == s[i - 1]) {
            s = s.substring(0, i - 1) + s.substring(i + 1)
            i = 0
        }
        i++
    }

    if (s.isEmpty()) {
        return "Empty String"
    }
    return s
}

fun howManyGames(p: Int, d: Int, m: Int, s: Int): Int {
    // Return the number of games you can buy
    var priceOfFirst = p
    var startingBudget = s
    var noOfGames = 0

    while (startingBudget >= priceOfFirst) {
        noOfGames++
        startingBudget -= priceOfFirst
        priceOfFirst = Math.max(priceOfFirst - d, m)
    }
    return noOfGames
}

fun jumpingOnClouds2(c: Array<Int>): Int {
    var i = 0
    var count = 0
    while (i <= c.size - 1) {
        if (i + 2 > c.size - 1 && i + 1 > c.size - 1) {
            break
        }
        if (i + 2 <= c.size - 1 && c[i + 2] == 0) {
            count++
            i += 2
        } else {
            i += 1
            count++
        }
    }
    return count
}

fun rightCircularArrayRotation(a: Array<Int>, k: Int, queries: Array<Int>): Array<Int> {
    val newArray = a.sliceArray(a.size - k until a.size) // 76, 62

    var i = a.size - 1 //5
    while (i >= k) { // 5 > 2 | 4 > 2 | 3>2 | 2=2
        a[i] = a[i - k] // a[5] = a[3] | a[4] = a[2] | a[3] = a[1] | a[2] = a[0]
        i-- // 4 | 3 | 2 | 1
    }
    val partialRotated = a.copyOfRange(k, a.size)

    val rotated = newArray.plus(partialRotated)

    val result = Array<Int>(queries.size) { it -> it }
    var j = 0
    for (query in queries) {
        result[j++] = rotated[query]
    }
    return result
}

// Complete the circularArrayRotation function below.
fun leftCircularArrayRotation(a: Array<Int>, k: Int, queries: Array<Int>): Array<Int> {
    val newArray = a.sliceArray(0 until k)
    var i = 0
    while (i + k < a.size) {
        a[i] = a[i + k]
        i++
    }
    val rotated = a.plus(newArray)
    val result = Array<Int>(queries.size) { it -> it }
    var j = 0
    for (query in queries) {
        result[j++] = rotated[query]
    }
    return result
}

// Complete the equalizeArray function below.
// Count occurence of each element in array..
// Maximum occuring element is the one we want to retain..
//
fun equalizeArray(arr: Array<Int>): Int {
    val map = mutableMapOf<Int, Int>()
    for (a in arr) {
        map[a] = map.getOrDefault(a, 0) + 1
    }
    val maxValue = map.maxOf { it.value }
    return arr.size - maxValue
}

// Complete the appendAndDelete function below.
fun appendAndDelete(s: String, t: String, k: Int): String {
    var commonLength = 0

    for (i in 0 until min(s.length, t.length)) {
        if (s[i] == t[i]) commonLength++ else break
    }
    return when {
        // diff in length is bigger than k
        ((s.length + t.length - 2) * commonLength > k) -> {
            "No"
        }
        // since we need exact k moves.. only possible if they are odd/even
        ((s.length + t.length - 2) * commonLength) % 2 == k % 2 -> {
            "Yes"
        }
        // delete string..
        s.length + t.length - k < 0 -> {
            "Yes"
        }
        else -> {
            "No"
        }
    }
}

// Complete the repeatedString function below.
fun repeatedString(s: String, n: Long): Long {
    var count = 0L
    for (c in s) {
        if (c == 'a') count++
    }
    val fullParts = n / s.length
    val remainingParts = n % s.length
    // fullpart count with all parts
    count *= fullParts
    var i = 0
    while (i < remainingParts) {
        if (s[i] == 'a') count++
        i++
    }
    return count.toLong()
}

fun cutTheSticks(arr: Array<Int>): Array<Int> {
    val mutableArr = arr.toMutableList()

    var min = arr.min()
    var sticksCut: Int
    var result = mutableListOf<Int>()
    while (true) {
        sticksCut = 0
        for ((i, item) in mutableArr.withIndex()) {
            if (item != -1) {
                val cut = mutableArr[i] - min!!
                if (cut == 0) mutableArr[i] = -1 else mutableArr[i] = cut
                sticksCut++
            }
        }
        result.add(sticksCut)
        min = mutableArr.filter { it > 0 }.minOrNull()
        if (min == null) break
    }
    return result.toTypedArray()
}

fun designerPdfViewer(h: Array<Int>, word: String): Int {
    var max = 0
    for (char in word) {
        var height = h[(char - 96).toInt() - 1]
        if (height > max) max = height
    }
    return max * word.length
}

fun saveThePrisoner(n: Int, m: Int, s: Int): Int {
    val mod = m % n
    if (m < n) return m + s - 1
    return mod + s - 1
}

fun solve(meal_cost: Double, tip_percent: Int, tax_percent: Int): Unit {
    val tip = (meal_cost * tip_percent / 100).toDouble()
    val tax = (meal_cost * tax_percent / 100).toDouble()
    val total = meal_cost + tip + tax
    println(total.roundToInt())
}

fun jumpingOnClouds(c: Array<Int>, k: Int): Int {
    var i = k
    var energy = 100
    while (true) {
        if (i >= c.size) i -= c.size
        if (c[i] == 1) energy -= 2
        if (i == 0) break // 3
        i += k // 6,9
        energy-- // 98
    }
    return energy - 1
}

// Complete the beautifulDays function below.
fun beautifulDays(i: Int, j: Int, k: Int): Int {
    var count = 0
    for (digit in i..j) {
        if ((digit - reverseLocal(digit)) % k == 0) count++
    }
    return count
}

fun reverseLocal(i: Int): Int {
    var rev = 0
    var n = i
    while (n != 0) {
        rev = rev * 10 + n % 10
        n /= 10
    }
    return rev
}


fun migratoryBirds(arr: Array<Int>): Int {
    var map = mutableMapOf<Int, Int>()
    val arr0 = mutableListOf<Int>()
    for (a in arr) {
        map[a] = map.getOrDefault(a, 0) + 1
    }
    //val max = map.maxOf { it.value }
    var maxEntry: Map.Entry<Int, Int>? = null
    for (entry in map.entries) {
        if (maxEntry == null || entry.value.compareTo(maxEntry.value) > 0) {
            maxEntry = entry
        }
    }
    val max = maxEntry!!.value
    println(max)
    for (it in map) {
        if (it.value == max) arr0.add(it.key)
    }
    arr0.sort()
    return arr0[0]
}

fun birthday(s: Array<Int>, sumI: Int, pieces: Int): Int {
    if (s.size == 1 && s[0] == sumI && pieces == 1) return 1
    var count = 0
    var i = 0

    while (i < s.size) {
        var j = i
        var piece = 0
        val limit = i + pieces
        if (limit > s.size) break
        while (j < limit) {
            piece += s[j]
            j++
        }
        if (piece == sumI) {
            count++
        }
        i++
    }
    return count
}

