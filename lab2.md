# Lab 2: Merge Sort on Linked Lists

## Background and Overview

In this lab you are asked to implement two versions of merge sort on linked lists:
one that produces a _new_, sorted list and another that works _in-place_ by changing
the input list.

Section 7.6 of the textbook describes merge sort on an array ([lecture notes](./lectures/more-algo-analysis.html)).
You’ll need to adapt the algorithm for linked lists. The steps of the algorithms are

1. Split the input sequence in half
2. Recursively sort the two halves
3. Merge the two results into one:
   - scan through the two input sequences and choose the smaller of the two
     current elements for the output sequence

The main difference between the two versions that you are supposed to write
is that the sorted list is new in the first, [functional](https://en.wikipedia.org/wiki/Functional_programming)
version, while it overwrites the input list in the second, in-place version.
We will explain the difference using examples:

**[Example 1]** The `sort` function applied to the following linked list

```
[5] -> [2] -> [7] -> [1]
```

produces the following new linked list, while leaving _the original one unchanged_:

```
[1] -> [2] -> [5] -> [7]
```

**[Example 2]** The `sort_in_place()` function applied to the following list

```
[6] -> [3] -> [8] -> [2]
```

should rearrange the nodes into the following order:

```
[2] -> [3] -> [6] -> [8]
```

## Support Code and Submission

+ Student support code is at [link](https://github.com/IUDataStructuresCourse/merge-sort-list-student-support-code).
  You may find the helper functions in `Utils.java` helpful.
+ Submit your code file `MergeSort.java` ([Problem 1](#problem-1-implementing-merge-sort))
  and lab write-up `README.md` ([Problem 3](#problem-3-lab-report)) to
  [link](https://autograder.luddy.indiana.edu/web/project/936).
+ Submit your test file `MergeSortTest.java` ([Problem 2](#problem-2-testing-merge-sort)) to
    [link](https://autograder.luddy.indiana.edu/web/project/942).

## Problem Set

Prerequisite: download student support code and import the project into IntelliJ.

### Problem 1: Implementing Merge Sort

Look at `MergeSort.java` in the student support code. Apart from `sort()` and `sort_in_place()`,
it also contains type signatures for `merge()` and `merge_in_place()`. The sort functions call
their respective merge functions to combine two sorted halves into one. Similar to their sorting
counterparts, `merge()` creates a _new_ list from two input lists while `merge_in_place()` works
in-place by rearranging the nodes.

**[Example 3]** The `merge()` function applied to the following two sorted lists

```
[1] -> [2] -> [5] -> [7]
[2] -> [3] -> [6] -> [8]
```

produces the following newly allocated list

```
[1] -> [2] -> [2] -> [3] -> [5] -> [6] -> [7] -> [8]
```

Implement both `sort()` (functional) and `sort_in_place()` (in-place) in class `MergeSort`.
Both sort functions have a `Node`-typed parameter and `Node` return type, which point to
the first node in the input list and the first node in the output list.
The `Node` class is defined in `Node.java`.
The two sort functions should call their respective merge functions, `merge()` and
`merge_in_place()`, so you are supposed to implement those merge functions as well.


### Problem 2: Testing Merge Sort

Before running your code on Autograder, create your own test cases and run them locally.
Think about their correctness criteria of the two versions of merge sort.
_Write regular, corner, and random test cases_ for `merge()`, `sort()`, `merge_in_place()`,
and `sort_in_place()`.

<details open="true">
  <summary>Hints: a few things to test...</summary>
  <ul>
    <li>Whether <code>merge()</code> and <code>sort()</code> create <em>new</em> output lists</li>
    <li>Whether the output lists of <code>sort()</code> and <code>sort_in_place()</code> are sorted</li>
    <li>Whether the output lists of <code>sort()</code> and <code>sort_in_place()</code> are permutations
        of their input lists</li>
    <li>Corner cases</li>
    <li>...</li>
  </ul>
</details>

Run your test cases on Autograder against four buggy implementations and see whether they can
catch all the bugs!


### Problem 3: Lab Report

Answer the following questions in your lab write-up (`README.md`):

+ **[Question 1]** What is the time and space complexity of your `merge()` function?
+ **[Question 2]** What is the time and space complexity of your `sort()` function?
+ **[Question 3]** What is the time and space complexity of your `merge_in_place()` function?
+ **[Question 4]** What is the time and space complexity of your `sort_in_place()` function?

### Last Step: Checking Your Submission

Make sure `MergeSort.java`, `MergeSortTest.java` and `README.md` were submitted.

-----------------

* You have reached the end of Lab 2. Yay!
