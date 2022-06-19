package processor

import kotlin.math.pow

typealias Matrix = MutableList<MutableList<Double>>

fun main() {

    while (true) {

        println("1. Add matrices")
        println("2. Multiply matrix by a constant")
        println("3. Multiply matrices")
        println("4. Transpose matrix")
        println("5. Calculate a determinant")
        println("6. Inverse matrix")
        println("0. Exit")

        val choice = readln()
        println("Your choice: > $choice")

        try {

            when (choice) {
                "1" -> addMatrices()
                "2" -> multiplyMatricesByConstant()
                "3" -> multiplyMatrices()
                "4" -> transposeMatrix()
                "5" -> calculateDeterminant()
                "6" -> inverseMatrix()
                else -> break
            }

        } catch (e: Exception) {
            println("ERROR")
        }

    }


}

fun addMatrices() {
    println("Enter matrix size:")
    val (rowsA, colsA) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    val (rowsB, colsB) = readln().split(" ").map { it.toInt() }

    val matrixB = MutableList(rowsB) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    assert(rowsA == rowsB && colsA == colsB)

    val result = matrixA.sum(matrixB)

    println("The result is:")
    result.print()


}

fun multiplyMatricesByConstant() {

    println("Enter matrix size:")
    val (rowsA, _) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    val scalar = readln().toDouble()
    val result = matrixA.multiplyByConstant(scalar)

    println("The result is:")
    result.print()
}

fun multiplyMatrices() {
    println("Enter matrix size:")
    val (rowsA, colsA) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    println("Enter matrix size:")
    val (rowsB, colsB) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixB = MutableList(rowsB) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    assert(colsA == rowsB)

    val result = matrixA.multiply(matrixB)

    println("The result is:")
    result.print()
}

fun transposeMatrix() {

    println("1. Main diagonal")
    println("2. Side diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")

    val choice = readln()
    println("Your choice: > $choice")

    println("Enter matrix size:")
    val (rowsA, _) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    val result = when (choice) {
        "2" -> matrixA.transposeSideDiagonal()
        "3" -> matrixA.transposeVerticalLine()
        "4" -> matrixA.transposeHorizontalLine()
        else -> matrixA.transposeMainDiagonal()
    }

    println("The result is:")
    result.print()
}

fun calculateDeterminant() {
    println("Enter matrix size:")
    val (rowsA, colsA) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    println("The result is:")
    println(matrixA.determinant())
}

fun inverseMatrix() {
    println("Enter matrix size:")
    val (rowsA, colsA) = readln().split(" ").map { it.toInt() }

    println("Enter matrix:")
    val matrixA = MutableList(rowsA) {
        readln().split(" ").map { it.toDouble() }.toMutableList()
    }

    try {
        val result = matrixA.inverse()

        println("The result is:")
        result.print()
    } catch (e: Exception) {
        println("This matrix doesn't have an inverse.")
    }


}


fun Matrix.sum(other: Matrix): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][j] = this[i][j] + other[i][j]
        }
    }

    return result
}

fun Matrix.multiply(other: Matrix): Matrix {
    val result = MutableList(this.size) { MutableList(other.first().size) { 0.0 } }
    for (i in this.indices) {
        for (j in other.first().indices) {
            for (k in this[i].indices) {
                result[i][j] += this[i][k] * other[k][j]
            }
        }
    }
    return result
}

fun Matrix.multiplyByConstant(scalar: Double): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][j] = this[i][j] * scalar
        }
    }
    return result
}

fun Matrix.transposeMainDiagonal(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[j][i] = this[i][j]
        }
    }

    return result
}

fun Matrix.transposeSideDiagonal(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[this.size - j - 1][this.size - i - 1] = this[i][j]
        }
    }

    return result
}

fun Matrix.transposeVerticalLine(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][this.size - j - 1] = this[i][j]
        }
    }

    return result
}

fun Matrix.transposeHorizontalLine(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[this.size - i - 1][j] = this[i][j]
        }
    }

    return result
}

fun Matrix.minor(i: Int, j: Int): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][j] = this[i][j]
        }
    }

    result.forEach { row -> row.removeAt(j) }
    result.removeAt(i)


    assert(result.size == this.size - 1)
    assert(result.first().size == this.first().size - 1)

    return result
}

fun Matrix.determinant(): Double {

    assert(this.size == this.first().size)

    if (this.size <= 1) {
        return this[0][0]
    } else if (this.size == 2) {
        return this[0][0] * this[1][1] - this[0][1] * this[1][0]
    }

    var result = 0.0

    for (j in this.indices) {
        result += this[0][j] * this.minor(0, j).determinant() * (-1.0).pow(0 + j)
    }

    return result
}

fun Matrix.adjacent(): Matrix {

    assert(this.size == this.first().size)

    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this.indices) {
            result[i][j] = this.minor(i, j).determinant() * (-1.0).pow(i + j)
        }
    }
    assert(this.size == result.size)
    assert(this.first().size == result.first().size)

    return result
}

fun Matrix.inverse(): Matrix {

    assert(this.size == this.first().size)
    return this.adjacent().transposeMainDiagonal().multiplyByConstant(1.0 / this.determinant())
}


fun Matrix.print() {
    this.forEach { row -> println(row.joinToString(" ")) }
}


