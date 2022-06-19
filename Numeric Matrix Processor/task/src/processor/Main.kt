package processor

import kotlin.math.pow

typealias Matrix = MutableList<MutableList<Double>>

/**
 * Control loop and entrypouint.
 *
 */
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

/**
 * Propmpts for two matrices and adds them to another.
 *
 */
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

/**
 * Prompts for a matrix and a scalar and performs a scalar multiplication.
 */
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

/**
 * Prompts for two matrices and multiplies them.
 *
 */
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

/**
 * Prompts for a matrix and transposes it.
 *
 */
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

/**
 * Prompts for a matrix and calculates the determinant.
 *
 */
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

/**
 * Prompts a matrix and calculates its inverse matrix.
 *
 */
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

/**
 * Sum a matrix to the current matrix instance.
 *
 * @param other matrix
 * @return sum of matrices
 */
fun Matrix.sum(other: Matrix): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][j] = this[i][j] + other[i][j]
        }
    }

    return result
}

/**
 * Multiply a matrix to the current matrix instance.
 *
 * @param other matrix
 * @return product of the matrices
 */
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

/**
 * Multiply a scalar to the current matrix instance.
 *
 * @param scalar scalar
 * @return product of the matrix and the scalar
 */
fun Matrix.multiplyByConstant(scalar: Double): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][j] = this[i][j] * scalar
        }
    }
    return result
}

/**
 * Transpose a matrix instance on the main diagonal.
 *
 * @return transposed matrix
 */
fun Matrix.transposeMainDiagonal(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[j][i] = this[i][j]
        }
    }

    return result
}

/**
 * Transpose a matrix instance on the side diagonal.
 *
 * @return transposed matrix
 */
fun Matrix.transposeSideDiagonal(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[this.size - j - 1][this.size - i - 1] = this[i][j]
        }
    }

    return result
}

/**
 * Transpose a matrix instance on the vertical line.
 *
 * @return transposed matrix
 */
fun Matrix.transposeVerticalLine(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[i][this.size - j - 1] = this[i][j]
        }
    }

    return result
}

/**
 * Transpose a matrix instance on the horizontal line.
 *
 * @return transposed matrix
 */
fun Matrix.transposeHorizontalLine(): Matrix {
    val result = MutableList(this.size) { MutableList(this.first().size) { 0.0 } }

    for (i in this.indices) {
        for (j in this[i].indices) {
            result[this.size - i - 1][j] = this[i][j]
        }
    }

    return result
}

/**
 * Calculate the minor of the matrix.
 *
 * @param i row to drop
 * @param j column to drop
 * @return minor of the matrix
 */
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

/**
 * Determinant of the current matrix instance.
 *
 * @return determinant
 */
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

/**
 * Cofactors of the matrix instance.
 *
 * @return cofactors
 */
fun Matrix.cofactors(): Matrix {

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

/**
 * Inverse of the matrix instance
 *
 * @return inverse
 */
fun Matrix.inverse(): Matrix {

    assert(this.size == this.first().size)
    return this.cofactors().transposeMainDiagonal().multiplyByConstant(1.0 / this.determinant())
}

/**
 * Prints a matrix in a readable format.
 *
 */
fun Matrix.print() {
    this.forEach { row -> println(row.joinToString(" ")) }
}


