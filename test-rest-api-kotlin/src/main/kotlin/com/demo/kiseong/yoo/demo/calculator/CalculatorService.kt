package com.demo.kiseong.yoo.demo.calculator

import org.springframework.stereotype.Service

@Service
class CalculatorService {

    private val ALL_AVAILABLE_OPERATORS = arrayOf("(", ")", "+", "-", "*", "/", "%")
    private val OPERATORS_EXCEPT_PARENTHESIS = arrayOf("+", "-", "*", "/", "%")
    private val MIDDLE_PRECEDENCE_OPERATORS = arrayOf("*", "/", "%")
    private val LOWEST_PRECEDENCE_OPERATORS = arrayOf("+", "-")

    fun execute(expression: String): Double {
        require(expression.isNotEmpty()) { "expression must not be empty" }
        require(ALL_AVAILABLE_OPERATORS.any { x -> expression.contains(x) }) { "expression must contain at least one operator" }
        val tokens = expression
            .replace("(", " ( ")
            .replace(")", " ) ")
            .replace("+", " + ")
            .replace("-", " - ")
            .replace("*", " * ")
            .replace("/", " / ")
            .replace("%", " % ")
            .split(" ")
            .filter { x -> !listOf(" ", "").contains(x) }
            .toList()
        require(OPERATORS_EXCEPT_PARENTHESIS.none { x -> tokens.last() == x }) { "expression must not end with operator" }
        require(
            tokens
                .filter { x -> !ALL_AVAILABLE_OPERATORS.contains(x) }
                .none { x -> !isNumeric(x) }
        ) { "expression must not contain non-numeric token as operand" }
        require(
            (tokens.filter { x -> x == "(" }).size == (tokens.filter { x -> x == ")" }).size
        ) { "expression must properly close parenthesis" }
        return calculate(tokens)
    }

    fun calculate(tokens: List<String>): Double {
        val currentTokens = tokens.slice(tokens.indices).toMutableList()
        val operators = ArrayList<String>().toMutableList()
        val operands = ArrayList<Double>().toMutableList()
        while (currentTokens.isNotEmpty()) {
            val currentToken = currentTokens.removeFirst()
            when (currentToken) {
                "(", "+", "-" -> {
                    operators.add(currentToken)
                }
                ")" -> {
                    while (operators.last() != "(") {
                        val operator = operators.removeLast()
                        require(LOWEST_PRECEDENCE_OPERATORS.contains(operator)) { "operator with lowest precedence expected but accepted '${operator}'" }
                        val nextOperand = operands.removeLast()
                        val prevOperand = operands.removeLast()
                        when (operator) {
                            "+" -> {
                                operands.add(prevOperand + nextOperand)
                            }
                            "-" -> {
                                operands.add(prevOperand - nextOperand)
                            }
                        }
                    }
                    operators.removeLast()
                    while (operators.isNotEmpty() && MIDDLE_PRECEDENCE_OPERATORS.contains(operators.last())) {
                        val operator = operators.removeLast()
                        val nextOperand = operands.removeLast()
                        val prevOperand = operands.removeLast()
                        when (operator) {
                            "*" -> {
                                operands.add(prevOperand * nextOperand)
                            }
                            "/" -> {
                                operands.add(prevOperand / nextOperand)
                            }
                            "%" -> {
                                operands.add(prevOperand % nextOperand)
                            }
                        }
                    }
                }
                "*" -> {
                    if (currentTokens.first() == "(") {
                        operators.add(currentToken)
                        continue
                    }
                    val prevOperand = operands.removeLast()
                    require(isNumeric(currentTokens.first())) { "operand expected but accepted '${currentTokens.first()}'" }
                    val nextOperand = currentTokens.removeFirst().toDouble()
                    operands.add(prevOperand * nextOperand)
                }
                "/" -> {
                    if (currentTokens.first() == "(") {
                        operators.add(currentToken)
                        continue
                    }
                    val prevOperand = operands.removeLast()
                    require(isNumeric(currentTokens.first())) { "operand expected but accepted '${currentTokens.first()}'" }
                    val nextOperand = currentTokens.removeFirst().toDouble()
                    operands.add(prevOperand / nextOperand)
                }
                "%" -> {
                    if (currentTokens.first() == "(") {
                        operators.add(currentToken)
                        continue
                    }
                    val prevOperand = operands.removeLast()
                    require(isNumeric(currentTokens.first())) { "operand expected but accepted '${currentTokens.first()}'" }
                    val nextOperand = currentTokens.removeFirst().toDouble()
                    operands.add(prevOperand % nextOperand)
                }
                else -> {
                    require(isNumeric(currentToken)) { "operand expected but accepted '$currentToken'" }
                    operands.add(currentToken.toDouble())
                }
            }
        }
        while (operators.isNotEmpty()) {
            val operator = operators.removeLast()
            require(LOWEST_PRECEDENCE_OPERATORS.contains(operator)) { "operator with lowest precedence expected but accepted '${operator}'" }
            val nextOperand = operands.removeLast()
            val prevOperand = operands.removeLast()
            when (operator) {
                "+" -> {
                    operands.add(prevOperand + nextOperand)
                }
                "-" -> {
                    operands.add(prevOperand - nextOperand)
                }
            }
        }
        return operands.removeLast()
    }

    private fun isNumeric(toCheck: String): Boolean {
        return toCheck.all { x -> x.isDigit() }
    }

}
