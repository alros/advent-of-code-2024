package advent2024.day17

import kotlin.math.pow
import kotlin.reflect.KFunction2

enum class Opcode(private val op: Int, private val func: KFunction2<CPU, Int, Int?>) {
    _adv(0, CPU::adv),
    _bxl(1, CPU::bxl),
    _bst(2, CPU::bst),
    _jnz(3, CPU::jnz),
    _bxc(4, CPU::bxc),
    _out(5, CPU::out),
    _bdv(6, CPU::bdv),
    _cdv(7, CPU::cdv);

    fun process(cpu: CPU, parameter: Int): Int? = func.call(cpu, parameter)

    companion object {
        fun fromOp(op: Int): Opcode = entries.stream().filter { it.op == op }.findFirst().get()
    }
}


class CPU(regs: IntArray, private val program: IntArray) {
    private var regs = regs.copyOf(4)
    private var output: String? = null

    fun regA() = regs[0]
    fun regB() = regs[1]
    fun regC() = regs[2]
    fun output() = output

    fun process() {
        var out = mutableListOf<Int>()
        while (regs[3] < program.size) {
            val opcode = Opcode.fromOp(program[regs[3]])
            val result = opcode.process(this, program[regs[3] + 1])
            if (result != null) {
                out.add(result)
            }
        }
        output = out.joinToString(separator = ",")
    }

    private fun comboOp(op: Int): Int {
        return when (op) {
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            4 -> regs[0]
            5 -> regs[1]
            6 -> regs[2]
            else -> throw RuntimeException("invalid")
        }
    }

    //perform reg A / pow(2, comboOp) and store in A (truncated)
    fun adv(parameter: Int): Int? {
        regs[0] = regs[0] / 2.toDouble().pow(comboOp(parameter)).toInt()
        regs[3] += 2
        return null
    }

    //reg B xor literal op and store in B
    fun bxl(parameter: Int): Int? {
        regs[1] = regs[1].xor(parameter)
        regs[3] += 2
        return null
    }

    //comboOp mod 8 and store in B
    fun bst(parameter: Int): Int? {
        regs[1] = comboOp(parameter).mod(8)
        regs[3] += 2
        return null
    }

    //if reg A != 0, jump to literalOp
    fun jnz(parameter: Int): Int? {
        if (regs[0] != 0) {
            regs[3] = parameter
        } else {
            regs[3] += 2
        }
        return null
    }

    //reg B xor reg C and store in B
    fun bxc(parameter: Int): Int? {
        regs[1] = regs[1].xor(regs[2])
        regs[3] += 2
        return null
    }

    //comboOp mod 8 and outputs
    fun out(parameter: Int): Int? {
        regs[3] += 2
        return comboOp(parameter).mod(8)
    }

    //like _adv but stores in B
    fun bdv(parameter: Int): Int? {
        regs[1] = regs[0] / 2.toDouble().pow(comboOp(parameter)).toInt()
        regs[3] += 2
        return null
    }

    //like _adv but stores in C;
    fun cdv(parameter: Int): Int? {
        regs[2] = regs[0] / 2.toDouble().pow(comboOp(parameter)).toInt()
        regs[3] += 2
        return null
    }
}

fun solveStep1(input: String): CPU {
    return solve(input)
}

fun solveStep2(input: String): CPU {
    return solve(input)
}

private fun solve(input: String): CPU {
    val cpu = convertInput(input)
    cpu.process()
    return cpu
}

private fun convertInput(input: String): CPU {
    var lines = input.split("\n")
    val registersRegex = "Register \\w: (\\d+)".toRegex()
    val programRegex = "Program: (.*)".toRegex()

    val regs = IntArray(3)
    regs[0] = registersRegex.find(lines[0])!!.groupValues[1].toInt()
    regs[1] = registersRegex.find(lines[1])!!.groupValues[1].toInt()
    regs[2] = registersRegex.find(lines[2])!!.groupValues[1].toInt()
    val program =
        programRegex.find(lines[4])!!.groupValues[1].split(",").stream().map { it.toInt() }.toList().toIntArray()

    return CPU(regs, program)
}
