# ? Binary Search Tree Implementation

> A custom binary search tree implementation in Java featuring recursive insertion, complex node removal, and depth-first traversal algorithms.

## ? About The Project
This project was developed as part of an Algorithms and Data Structures university course. It demonstrates a deep understanding of non-linear data structures, specifically focusing on the average-case logarithmic time complexity for insertions and lookups in a binary search tree (BST).

### ? The Assignment
The objective of this assignment was to implement a binary search tree data structure from scratch to store and search for integer values efficiently. To prove a fundamental understanding of the mechanics, the use of external Java API methods was strictly prohibited (with the exception of basic Lists and Strings).

**Core Requirements:**
* **Custom Node Structure:** Implement an inner `TreeNode` class that maintains strict references to its left child, right child, and parent node.
* **Recursive Operations:** Develop recursive algorithms for the `insert()` and `exists()` methods to navigate the tree structure.
* **Complex Removal Logic:** Implement a robust `remove(int value)` method that correctly handles node deletion across all three mathematical edge cases (leaf nodes, nodes with one child, and nodes with two children) while keeping parent references consistent.
* **Custom Exception Handling:** Create an `ElementExistsException` (inheriting from `java.lang.Exception`) to prevent and handle duplicate entries within the tree.
* **Tree Traversal:** Implement algorithms to traverse and output the tree's contents in three distinct orders: inorder, preorder, and postorder (`inOrderList`, `preOrderList`, `postOrderList`).

## ?? Built With
* **Language:** Java
* **IDE:** IntelliJ IDEA
* **Core Concepts:** Data Structures, Binary Search Trees, Recursion, Traversal Algorithms, Exception Handling

## ? Getting Started
1. Clone the repository: `git clone https://github.com/p4ulHash/Binary-Search-Tree.git`
2. Open the project in IntelliJ IDEA.
3. Run the application via the main class to execute the traversal tests and observe the tree's behavior.

## ? Author
**Paul Lang**
* GitHub: [p4ulHash](https://github.com/p4ulHash)