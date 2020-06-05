import kotlin.math.pow

class Combinatorics {
    /**
     * Combinatorics
     *  - Factorials
     *    - Factorial
     *    - Double factorial
     *    - Rising factorial
     *    - Falling factorial
     *    - Subfactorial
     *  - Permutations and Combinations
     *    - Permutations nPn
     *    - Permutations nPk
     *    - Combinations without repetition nCk
     *    - Combinations with repetition nC′k
     *    - Central binomial coefficient
     *  - Other Combinatorics
     *    - Catalan number
     *    - Lah number
     *    - Multinomial coefficient
     */
    companion object{
        /**************************************************************************
         * Factorials
         *************************************************************************/

        /**
         * Factorial (iterative)
         * Represents the number of ways to arrange n things (permutations)
         * n! = n(n - 1)(n - 2) ・・・ (n - (n - 1))
         */
        fun factorial(n: Int): Double {
            if (n<0)
                throw Exception("Cannot compute factorial of a negative number.")
            var factorial = 1.0
            for (i in n downTo 1)
                factorial *= i
            return factorial
        }

        /**
         * Double factorial (iterative)
         * Also known as semifactorial
         *
         * The product of all the integers from 1 up to some non-negative integer n
         * that have the same parity as n. Denoted by n!!
         *
         * n‼︎ = n(n - 2)(n - 4) ・・・
         *
         * For even n:
         *       n/2
         * n‼︎ =  ∏ (2k) = n(n - 2) ・・・ 2
         *       k=1
         *
         * For odd n:
         *     (n+1)/2
         * n‼︎ =  ∏ (2k - 1) = n(n - 2) ・・・ 1
         *       k=1
         *
         * 0‼︎ = 1
         */
        fun doubleFactorial(n: Int): Double{
            if (n<0)
                throw Exception("Cannot compute factorial of a negative number.")

            // Zero base case
            if (n == 0) {
                return 1.0
            }
            // Even and odd initialization base cases: odd = 1, even = 2
            var doublefactorial = if (n % 2 == 0) {
                2.0
            } else {
                1.0
            }
            for (i in n downTo 3 step 2)
                doublefactorial *= i
            return doublefactorial
        }

        /**
         * Rising Factorial
         * Also known as Pochhammer function, Pochhammer polynomial, ascending factorial,
         * rising sequential product, upper factorial.
         * https://en.wikipedia.org/wiki/Falling_and_rising_factorials
         *
         * x⁽ⁿ⁾ = x * (x + 1) * (x + 2) ... (x + n - 1)
         */
        fun risingFactorial(x: Double, n: Int): Double        {
            if (n < 0)
            throw Exception("Cannot compute rising factorial of a negative number.")

            var fact = 1.0
            for (i in n downTo 1)
                fact *= x + n - 1

            return fact
        }

        /**
        * Falling Factorial
        * Also known as descending factorial, falling sequential product, lower factorial.
        * https://en.wikipedia.org/wiki/Falling_and_rising_factorials
        *
        * x₍ᵢ₎ = x * (x - 1) * (x - 2) ... (x - i + 1)
        */
        fun fallingFactorial(x: Double, n: Int): Double{
            if (n < 0)
            throw Exception("Cannot compute rising factorial of a negative number.")

            if (n > x) {
            return 0.0
        }
            var fact = 1.0
            for (i in n downTo 1)
                fact *= x - n + 1
            return fact
        }

        /**
         * Subfactorial - Derangement number (iterative)
         * The number of permutations of n objects in which no object appears in its natural place.
         *
         *         n  (-1)ⁱ 
         * !n = n! ∑  -----
         *        ᵢ₌₀  i!
         *
         * https://en.wikipedia.org/wiki/Derangement
         * http://mathworld.wolfram.com/Subfactorial.html
         */
        fun subfactorial(n: Int): Double {
            if (n < 0)
            throw Exception("Cannot compute subfactorial of a negative number.")

            var sigma = 0.0
            for (i in 0..n){
                sigma += ((-1.0).pow(i))/ factorial(i)
            }
            return factorial(n) * sigma
        }

        /**************************************************************************
         * Permutations and combinations
         *************************************************************************/

        /**
         * Permutations (ordered arrangements)
         *
         * nPn - number of permutations of n things, taken n at a time.
         * P(n) = nPn = (N)n = n(n - 1)(n - 2) ・・・ (n - (n - 1)) = n!
         *
         *
         * nPk: number of permutations of n things, taking only k of them.
         *                    n!
         * P(n,k) = nPk =  --------
         *                 (n - k)!
         */
        fun permutations(n: Int, k: Int?): Double {
            if (n < 0)
            throw Exception("Cannot compute negative permutations.")

            if (k != null && k > n)
            throw Exception("k cannot be larger than n.")

            return if (k == null)
            // nPn: permutations of n things, taken n at a time
                factorial(n)
            // nPk: Permutations of n things taking only k of them
            else factorial(n)/ factorial(n-k)
        }

        /**
         * Combinations - Binomial Coefficient
         * Number of ways of picking k unordered outcomes from n possibilities
         * n choose k: number of possible combinations of n objects taken k at a time.
         *
         * Without repetition:
         *        (n)       n!
         *  nCk = ( ) = ----------
         *        (k)   (n - k)!k!
         *
         * With repetition:
         *         (n)   (n + k - 1)!
         *  nC'k = ( ) = ------------
         *         (k)    (n - 1)!k!
         */
        fun combinations(n: Int, k: Int, repetition: Boolean): Double {
            if (n < 0)
            throw Exception("Cannot compute negative combinations.")

            if (k > n)
            throw Exception("k cannot be larger than n.")

            // nC'k with repetition
            if (repetition)
            return factorial(n + k - 1) / (factorial(n - 1) * factorial(k))

            // nCk without repetition
            return factorial(n) / (factorial(n - k) * factorial(k))
        }

        /**
         * Central Binomial Coefficient
         *
         * (2n)   (2n)!
         * (  ) = ----- for n ≥ 0
         * (n )   (n!)²
         */
        fun centralBinomialCoefficient(n: Int): Double {
            if (n < 0) {
            throw Exception("Cannot compute negative central binomial coefficient.")
        }

            return factorial(2*n) / factorial(n).pow(2)
        }

        /**************************************************************************
         * Other Combinatorics
         *************************************************************************/

        /**
         * Catalan number
         *
         *        1   (2n)
         * Cn = ----- (  ) for n ≥ 0
         *      n + 1 (n )
         *
         * https://en.wikipedia.org/wiki/Catalan_number
         */
        fun catalanNumber(n: Int): Double
        {
            if (n < 0) {
            throw Exception("Cannot compute negative catalan number.")
        }

            return (1 / (n + 1)) * centralBinomialCoefficient(n)
        }

        /**
         * Lah number
         * Coefficients expressing rising factorials in terms of falling factorials.
         * https://en.wikipedia.org/wiki/Lah_number
         *
         *           / n - 1 \  n!
         * L(n,k) = |         | --
         *           \ k - 1 /  k!
         *
         */
        fun lahNumber(n: Int, k: Int): Double {
            if (n < 1 || k < 1) {
            throw Exception("n and k must be > 1 for Lah Numbers")
        }
            if (n < k) {
            throw Exception("n must be >= k for Lah Numbers")
        }

            return combinations(n - 1, k - 1, false) * (factorial(n) / factorial(k))
        }

        /**
         * Multinomial coefficient (Multinomial Theorem)
         * Finds the number of divisions of n items into r distinct nonoverlapping subgroups of sizes k₁, k₂, etc.
         *
         *       n!       (n₁ + n₂ + ⋯ + nk)!
         *   ---------- = -------------------
         *   k₁!k₂!⋯km!       k₁!k₂!⋯km!
         *
         * http://mathworld.wolfram.com/MultinomialCoefficient.html
         * https://en.wikipedia.org/wiki/Multinomial_theorem
         */
        fun multinomial(groups: Array<Int>): Double
        {
            var n = 0
            groups.map { n += it }
            var groupsFactorial = 0.0
            groups.map { groupsFactorial *= factorial(it) }
            return factorial(n) / groupsFactorial
        }

    }
}