
Lecture Overview

* Recipes for time complexity analysis
* Testing checklist


# Time Complexity Analysis Recipes

## Simple Statements


## Composing Statements Sequentially

Recipe:

    time_of_two_stmt = time_of_first + time_of_second

Example:

## If-Then-Else Statements

Recipe:

    time_of_if_then_else = time_of_condition + time_of_then + time_of_else

## If-Then Statements

Recipe:

    time_of_if_then = time_of_condition + time_of_then

## Loops

Recipe:

    time_of_loop = number_iterations * time_of_body

Example:




## Recursive Functions

Recipe:

    time_of_function = recursion_depth * time_per_level

To determine `recursion_depth`, analyze how much the input size changes
in the recursive calls.
* If size gets smaller by 1 (or any constant amount), recursion depth is O(n).
* If size get divided in half (or more), recursion depth is O(log n).

To determine the `time_per_level`:

    time_per_level = number_calls_per_level * time_per_call

To determine `number_calls` occuring within each level, one needs to
analyze the code to see how many recursive calls can be spawned by one
call to the recursive function.

To determine `time_per_call`, analyze the time complexity of one call
to the recursive function, **ignoring the recursive calls**.

One complication is that the number of calls in each level often
changes (e.g. doubling at each level: 1, 2, 4, 8, ..., 2^d) and
the time_per_call also changes because the input size gets smaller
(e.g. cut in half at each level: n, n/2, n/4, ..., 1),
but in many examples, these two affects cancel out and the
time per level stays the same.

Example:

    static Node append(Node N1, Node N2) {
        if (N1 == null)
            return N2;
        else {
            return new Node(N1.data, append(N1.next, N2));
        }
    }

analysis:

* `recursion_depth` = O(n), input size reduced by one: `append(N1.next, N2)`

* `number_calls_per_level` = 1 (only one call to append)
	
* `time_per_call` = O(1)  (allocate one node)

* `time_per_level` = `number_calls_per_level` * `time_per_call` = O(1)

* `time_of_function` = `recursion_depth` * `time_per_level` = O(n) * O(1) = O(n)

Example:

Assume the tree is AVL, so it is balanced.

    protected Node<K> find(K key, Node<K> curr, Node<K> parent) {
        if (curr == null)
            return parent;
        else if (lessThan.test(key, curr.data))
            return find(key, curr.left, curr);
        else if (lessThan.test(curr.data, key))
            return find(key, curr.right, curr);
        else
            return curr;
    }

analysis:

* `recursion_depth` = O(log(n)), input size cut in half:
   `find(key, curr.left, curr)`

* `number_calls_per_level` = 1 (only one call to `find`)
	
* `time_per_call` = O(1)  (assuming call to `test` is O(1))

* `time_per_level` = `number_calls_per_level` * `time_per_call` = O(1)

* `time_of_function` = `recursion_depth` * `time_per_level` 
   = O(log(n)) * O(1) = O(log(n))

Example:

    static Node merge_sort(Node N) {
        if (N == null || N.next == null) {
            return N;
        } else {
            int n = Utils.length(N);
            Node left = merge_sort(Utils.take(N, n / 2));
            Node right = merge_sort(Utils.drop(N, n / 2));
            return merge(left, right);
        }
    }

analysis:

* `recursion_depth` = O(log(n)), input size cut in half:
   `merge_sort(Utils.take(N, n / 2)`

* `number_calls_per_level` = 1, 2, 4, 8, ... 
   doubling because there are 2 recursive calls to `merge_sort`
	
* `time_per_call` = n, n/2, n/4, n/8, ...
    (Utils.length + Utils.take + Utils.drop + merge)

* `time_per_level` = `number_calls_per_level` * `time_per_call` = O(n)

* `time_of_function` = `recursion_depth` * `time_per_level` 
   = O(log(n)) * O(n) = O(n log(n))


# Testing Checklist

UNDER CONSTRUCTION
