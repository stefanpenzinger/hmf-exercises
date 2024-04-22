# Hypermedia Frameworks Exercise 6
Implement the [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) Algorithm so that all primes are beeing output up to a constant limit (120).

## Algorithm Description
Both algorithm approaches were taken from Wikipedia. There were two descriptions in wikipedia on how to implement the algorithm. The first one was the overview description and the second one was the pseudo code. Both descriptions were implemented in the code.

Two methods were implemented:
1. def sieve_of_eratosthenes_like_overview_description(n)
2. def sieve_of_eratosthenes_like_pseudo_code(n)
### Overview Description

To find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:
1. Create a list of consecutive integers from 2 through _n: (2, 3, 4, ..., n)_.
2. Initially, let _p_ equal 2, the smallest prime number.
3. Enumerate the multiples of _p_ by counting in increments of _p_ from _2p_ to _n_, and mark them in the list (these will be _2p_, _3p_, _4p_, ...; the _p_ itself should not be marked).
4. Find the smallest number in the list greater than _p_ that is not marked. If there was no such number, stop. Otherwise, let _p_ now equal this new number (which is the next prime), and repeat from step 3.
5. When the algorithm terminates, the numbers remaining not marked in the list are all the primes below _n_.

### Pseudo Code

```
algorithm Sieve of Eratosthenes is
    input: an integer n > 1.
    output: all prime numbers from 2 through n.

    let A be an array of Boolean values, indexed by integers 2 to n,
    initially all set to true.
    
    for i = 2, 3, 4, ..., not exceeding √n do
        if A[i] is true
            for j = i2, i2+i, i2+2i, i2+3i, ..., not exceeding n do
                set A[j] := false

    return all i such that A[i] is true.
```
