# frozen_string_literal: true
=begin
From Wikipedia: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

Overview:
To find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:
  1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
  2. Initially, let p equal 2, the smallest prime number.
  3. Enumerate the multiples of p by counting in increments of p from 2p to n, and mark them in the list (these will be 2p, 3p, 4p, ...; the p itself should not be marked).
  4. Find the smallest number in the list greater than p that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
  5. When the algorithm terminates, the numbers remaining not marked in the list are all the primes below n.

Pseudocode:
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
=end

LIMIT = 120

def sieve_of_eratosthenes_like_overview_description(n)
  list = (2..n).to_a
  p = 2
  while p < n
    list.each{ |number|
      if number % p == 0 && number != p
        list.delete(number)
      end
    }
    p = list.find {|number| number > p}
    if p.nil?
      break
    end
  end

  list
end

def sieve_of_eratosthenes_like_pseudo_code(n)
  list = Array.new(n + 1, true)
  list[0] = false
  list[1] = false
  n_sqrt = Math.sqrt(n).to_i

  (2..n_sqrt).each { |i|
    if list[i]
      (0..n).each { |j|
        new_index = i ** 2 + i * j
        if new_index <= n
          list[new_index] = false
        else
          break
        end
      }
    end
  }

  list.each_with_index.map { |is_prime, index| index if is_prime }.compact
end

print(sieve_of_eratosthenes_like_overview_description(LIMIT))
print("\n")
print(sieve_of_eratosthenes_like_pseudo_code(LIMIT))