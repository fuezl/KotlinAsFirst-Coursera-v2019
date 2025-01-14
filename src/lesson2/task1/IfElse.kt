@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
import kotlin.math.abs

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val a = if (age % 10 == 1 && age % 100 != 11)
        "год"
    else if (age % 10 in 2..4 && age % 100 !in 12..14)
        "года"
    else
        "лет"
    return "$age $a"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    var s = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    var t = 0.0
    when {
        t1 * v1 == s -> {
            t += t1
            s -= t1 * v1
        }
        t1 * v1 < s -> {
            t += t1
            s -= t1 * v1
        }
        else -> {
            t += s / v1
            s = 0.0
        }
    }
    when {
        t2 * v2 == s -> {
            t += t2
            s -= t2 * v2
        }
        t2 * v2 < s -> {
            t += t2
            s -= t2 * v2
        }
        else -> {
            t += s / v2
            s = 0.0
        }
    }
    when {
        t3 * v3 == s -> {
            t += t3
            s -= t3 * v3
        }
        t3 * v3 < s -> {
            t += t3
            s -= t3 * v3
        }
        else -> {
            t += s / v3
        }
    }
    return t
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return if ((kingX == rookX1 || kingY == rookY1) && (kingX == rookX2 || kingY == rookY2))
        3
    else if (kingX == rookX2 || kingY == rookY2)
        2
    else if (kingX == rookX1 || kingY == rookY1)
        1
    else
        0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    return if ((kingX == rookX || kingY == rookY) && (abs(kingX - bishopX) == abs(kingY - bishopY)))
        3
    else if (abs(kingX - bishopX) == abs(kingY - bishopY))
        2
    else if (kingX == rookX || kingY == rookY)
        1
    else
        0
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var x3 = max(a, b)
    x3 = max(x3, c)
    var x1 = min(a, b)
    x1 = min(x1, c)
    val x2 = a + b + c - x1 - x3
    return if (x1 + x2 <= x3)
        -1
    else {
        when {
            x1 * x1 + x2 * x2 > x3 * x3 -> 0
            x1 * x1 + x2 * x2 == x3 * x3 -> 1
            else -> 2
        }
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a1: Int, b1: Int, a2: Int, b2: Int): Int {
    return if ((a2 > a1 && b2 < b1) || (a2 > a1 && b1 == b2) || (a1 == a2 && b1 > b2))
        b2 - a2
    else if (a2 > a1 && b2 > b1 && b1 > a2)
        b1 - a2
    else if (a1 > a2 && b1 > b2 && b2 > a1)
        b2 - a1
    else if ((a1 > a2 && b1 < b2) || (a1 > a2 && b1 == b2) || (a1 == a2 && b1 < b2) || (a1 == a2 && b1 == b2))
        b1 - a1
    else if (a2 > a1 && b2 > b1 && a2 == b1)
        0
    else if (a1 > a2 && b1 > b2 && b2 == a1)
        0
    else
        -1
}
