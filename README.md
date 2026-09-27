# Assignment 2: Algorithmic Analysis, Correctness and Performance Trade-offs

## 1. Overview
This project implements and analyzes three fundamental data structures in Java:
1. **Dynamic Array**: A resizable array supporting direct indexing and automatic capacity management.
2. **Linked List**: A singly linked list composed of nodes, optimized for insertions/removals at the ends.
3. **Min-Heap**: A complete binary tree stored in an array where each parent node is smaller than or equal to its children.

The goal is to evaluate algorithmic correctness using loop invariants, perform asymptotic analysis ($O, \Omega, \Theta$), and conduct empirical benchmarking across controlled workloads.

---

## 2. Asymptotic Complexity Analysis

| Data Structure | Operation | Best Case | Average Case | Worst Case | Auxiliary Space |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Dynamic Array** | `get(index)` | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ | $\Theta(1)$ |
| **Dynamic Array** | `contains(x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $\Theta(1)$ |
| **Dynamic Array** | `add(index, x)`| $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $\Theta(n)$ |
| **Linked List** | `get(index)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $\Theta(1)$ |
| **Linked List** | `contains(x)` | $\Theta(1)$ | $\Theta(n)$ | $\Theta(n)$ | $\Theta(1)$ |
| **Min-Heap** | `insert(x)` | $\Theta(1)$ | $\Theta(\log n)$ | $\Theta(\log n)$ | $\Theta(n)$ |
| **Min-Heap** | `extractMin()` | $\Theta(1)$ | $\Theta(\log n)$ | $\Theta(\log n)$ | $\Theta(n)$ |

---

## 3. Algorithmic Correctness (Loop Invariants)

### A. Search Operation (`contains` in Dynamic Array)
* **Loop Invariant:** Prior to every iteration at index `i`, no element in the range `[0, i-1]` is equal to the target value `x`.
* **Initialization:** Before the loop starts (`i = 0`), the checked range is empty, so the invariant holds trivially.
* **Maintenance:** If `data[i] == x`, the algorithm correctly terminates and returns `true`. If not, `data[i] != x`, and the checked range expands by one valid element for the next iteration.
* **Termination:** If the loop terminates without a match, all elements from `0` to `size-1` have been checked, proving that `x` is absent, and `false` is correctly returned.

### B. Min-Heap Sift-Up Operation (`siftUp`)
* **Loop Invariant:** At the start of each iteration, the min-heap property holds for all nodes except possibly between the current node at `index` and its parent.
* **Initialization:** When a new element is inserted at the end (`size`), the tree is structurally valid, with a potential violation restricted to the new element and its parent.
* **Maintenance:** If the child is smaller than its parent, they are swapped, moving the violation up to the parent's level while preserving the heap property below.
* **Termination:** The loop terminates when the element is greater than or equal to its parent or reaches the root, fully restoring the min-heap property.

---

## 4. Experimental Setup
* **Input Sizes ($n$):** $100, 1,000, 10,000, 100,000$[cite: 1]
* **Operation Count ($m$):** 10,000 accesses, 1,000 searches/insertions/removals.
* **Repetitions:** Each experiment is run **5 times** and the average execution time is reported[cite: 1].
* **Timing Method:** `System.nanoTime()` for high-precision nanosecond measurements[cite: 1].
* **Random Seed:** Fixed `Random(42)` to ensure reproducibility[cite: 1].

---

## 5. Design Recommendations & Conclusion
* **Dynamic Array** is optimal for random access workloads (`get(index)`) due to contiguous memory allocation and cache locality.
* **Linked List** is inefficient for random access ($O(n)$) but advantageous for frequent insertions/removals at arbitrary known positions once the node is reached.
* **Min-Heap** is the ideal choice for priority-based processing, providing efficient logarithmic time complexity for insertions and extractions while keeping the minimum element constantly accessible at $O(1)$.