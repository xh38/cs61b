### CS61B

#### Java

**Hello.java**  javac **Hello.class** java **some stuff**

- Every method (a.k.a. function) is associated with some class.
- To run a class, we must define a main method.

###### arrays of objects

```java
Dog[] dogs = new Dog[2];
T[] item = (T[]) new Object[100];
```

###### static and instance members

- static method are invoked using the class name 

- instance method are invoked using an instance name 

- static method can't access "my" instance variables, because there is no me

###### main

```java
public static void main(String[] args)
```

args: command line arguments

###### Using libraries

```java
import org.junit.Test;
org.junit.Assert.assertEquals(expected, actual);
```

```java
assertNotnull();assertFalse();fail();
```

Java allows method overloading 

###### Primitive types

- 8 primitive types in Java: byte, short, **int**, long, float, **double**, boolean, char

###### Reference types

- everything else is reference types
- address in java is 64 bits

###### Parameter Passing

**the golden rule of  Equals**

- b = a copies all the bits from a to b.
- parameter passing obeys the same rule

###### instantiation of Arrays

###### public and private 

use a private keyword to prevent code in other classes from using members of a class

```java
public class SLList {
    private IntNode first;
    .......
}
```

###### nested class

```java
public class SLList {
    public class IntNode {
        public int item
            ...
    }
    ...
}
```

nested class are useful when class does not stand on its own

**static nested class** 

if nested class never use any instance or method of outer class, declare it static (minor savings of memory)

###### Generic 

```java
public class SLList<T> {
    private Node sentinel;
    private int size;
    
    public class Node {
        public T item;
        public Node next;
    }
}

SLList<Integer> s1 = new SLList<>(5);
```

##### interface and class 

interface is a specification of method

```java
public interface List61B<Item> {
    public void addFirst(Item x);
    public void addLast(Item x);
    ....
}

public class Alist<Item> implements List61B<Item> {
    ...
    public void addLast(Item x) {
        ...
}
```

use @override when subclass override the method

###### default method 

```java
/** not efficient for SLL*/
default public void print(){
    for (int i = 0; i < size(); i++) {
    	System.out.print(get(i) + " ");
    }
    System.out.println();
}

/** to be efficient */
public interface SLList<Item> implements {
    @override
    public void print() {
        for (Node p = sentinel.next; p != null; p = p.next) {
            System.out.print(p,item + " ");
        }
        System.out.println();
    }
}
```

###### static and dynamic type

"compile-time type" = "static type"

"run-time type" = "dynamic type" specified at instantiation

if dynamic type y override the static type, use y instead

**method selection: static type goes first, if overrides the recorded signature use the overridden method**

```java
public class RotatingSLList<Blorp> extends SLList<Blorp> {
    public void rotateRight() {
        Blorp oldBack = removeLast();
        insertFront(oldBack);
    }
}
```

by extends, RotatingSLList inherits all instance and static variables all methods all nested classes but not constructors.

all constructors must start with a call to one of the super class's constructors. If you don't explicitly call the constructor, java will automatically do it for you.

```java
public class VengefulSLList<Item> extends SLList<Item> {
    private SLList<Item> deletedItems;
    /**the followint two are equivalent*/
    public VengefulSLList() {
        super();
        deletedItems = new SLList<Item>();
    }
    public VengefulSLList() {
        deletedItems = new SLList<Item>();
    }
    /**but this one is different*/
    public VengefulSLList(Item x) {
        super(x);
        deletedItems = new SLList<Item>();
    }
}
```

###### Encapsulation

###### casting

```java
Poodle largePoodle = (Poodle) maxDog(frank,frankSr); 
```

not change anything

static type to decide which is allowed

###### Subtype Polymorphism

providing a single interface to entities of different types

```python
def print_larfer(x, y):
    if x.largerThan(y):
        return x.str()
    return y.str()
```

###### access control

private

package private

protected

public

#### Data structure

data structure should be easy to use, users has no needs to know the detail

##### LinkedList

###### sentinel node 

to make all the SLLists the same 

sentinel is never null

**invariants** 

- Can assume they are true to simplify code (e.g. addLast doesn’t need to worry about nulls).
- Must ensure that methods preserve invariants.

###### Doubly Linked List

##### ArrayList

resize()

##### ArrayMap

##### Disjoint sets

(using an array)

###### Quick Find

int[] where ith entry gives set number of item i

make connect really slow

constructor $$\theta(N)$$

connect $$\theta(N)$$

isConnected $$\theta(1)$$

######  Quick Union

eg: connect(5,2) make root(5) into child of root(2)

problem： for extremely tall tree is not suitable

constructor $$\theta(N)$$

connect O(N)

isConnected O(N)

###### Weighted Quick Union

always makes the smaller tree to be the child

import size[] array 

connect needs two changes: 

- link root of the smaller tree to larger one
- update size[] array

max depth: log(N)

constructor $$\theta(N)$$

connect O(logN)

isConnected O(logN)

###### Path Compression

isConnected tie all nodes seen to root

make M operations on N nodes O(N + M lg*N)

more O(N+M$$\alpha(N)$$)

hint: lg* is  5 for $$2^{65536}$$ 

###### *improve connect 

assign each node a parent 

##### BST

every key in the left is less than X's while the right one is larger 

######  searchKey

```java
static BST find (BST T, Key sk) {
    if (T == null)
        return null;
    if (sk.keyequals(T.label()))
        return T;
    else if (sk < T.label()) 
        return find(T.left, sk);
    else 
        return find(T.right, sk);
}
```

$$\theta(logN)$$

###### Insert

```java
static BST insert(BST T, Key ik) {
    if (T == null)
        return new BST(ik);
    if (ik < T.label())
        T.left = insert(T.left, ik);
    else if (ik > T.label())
        T.right = insert(T.right, ik);
    return T;
}
```

$$\theta(logN)$$

###### Delete

- no children

just sever the parent's link

- one child

safe to move the parent's link to child

- two children

Hibbard deletion

find max in left or min in right

delete it and make a copy of it

sqrt(N)

###### Tree Rotation

RotateLeft(P):promote its right child

RotateRight(P): promote its left child

can shorten or lengthen a tree but preserves Search Tree property

Rotation allows balancing of **any** BST

to achieve balance: rotate after each insertion and deletion to maintain balance

##### Search trees

- binary
- treaps
- splay trees
- 2-3 / 2-3-4 / B-trees

(2-3 trees provide a rotation strategy for BSTs called "red-black")

###### B-trees

no rotation needed

 set a cap for a node 

split if it's more than the cap

$$\theta(logN)$$

###### Red-Black

 Left-Leaning Red Black Binary Search Tree 

insertion

##### Tree Traversals

###### Depth First Traversals

- pre-order, in-order, post-order
- rough idea: traverse "deep nodes" before shallow ones

pre-order

```java
preOrder(BSTNode x) {
    if (x == null) return;
    print(x.key);
    preOrder(x.left);
    preOrder(x.right);
}
```

DBACFEG

in-order

```java
inOrder(BSTNode x) {
    if (x == null) return;
    inOrder(x.left);
    print(x.key);
    inOrder(x.right);
}
```

ABCDEEFG

post-order

```java
postOrder(BSTNode x) {
    if (x == null) return;
    postOrder(x.left);
    postOrder(x.right);
    print(x.key);
}
```

ACBEGFD

We walk the graph, from top going counter-clockwise.

- Pre-order traversal:  Shout every time we pass the LEFT of a node.
- In-order traversal: Shout when you cross the bottom.
- Post-order traversal: Shout when you cross the right.

Pre-order: print directory listing

Post-order: for gathering file sizes.

time: $$\theta(N)$$ Every node visited exactly once. Constant work pre visit.

###### Level Order Traversal

DBFACEG

```java
/*
Iterative Deeping
*/
pubic void levelOrder(Tree T, Action toDo) {
    for (int i = 0; i < T.height(); i++) {
        visitLevel(T, i, toDO);
    }
}

public void visitLevel(Tree T, int level, Action toDo) {
    if (T == null) {
        return;
    }
    if (level == 0) {
        toDo.visit(T.key);
    } else {
        visitLevel(T.left(), level - 1, toDo);
        visitLevel(T.right(), level - 1, toDo);
    }
}
```

time:

- bushy trees: $$\theta(N)$$
- spindly trees: $$\theta(N^2)$$ 

###### Range Finding

Geometric Search

```java
class rangeFind implements Action<String> {
    private Label min, max;
    public Set<Label> inRange;
    public rangeFind(Label min, Label max) {
        this.min = min;
        this.max = max;
        inRange = new HashSet<Label>();
    }
//just traversal of the whole tree    
    void action(Tree<Label> T) {
        if (T.label <= max && T.label >= min) {
            inRange.add(T.label);
        }
    }
}
```

**pruning**:  restricting our search to only nodes that might contain the answers we seek

time: $$\theta(logN+R)$$ R is the number of matches

*spatial trees: Quadtree

###### Tree Iterators

use list

use stack: space saving

##### Hashing

###### binary representations DataIndexedSet

word: base(27) or base(32) base(31) works well

###### Handling Collision

instead of storing true in position h, store a list of these N items.

if N items distributed across M buckets, average time grows with N/M == L

###### hash table

- every item is mapped to a bucket number using hash function

- compute a hash code and compute the index = hashcode modulo M

- if L = N / M gets too large, increase M

- to resolve ambiguity 

  - 1.Exteranl chaining 

  - 2.open addressing

hashcode for list

```java
@override
public int hashcode() {
    int hashcode = 1;
    for (Object o : this) {
        hashcode = hashcode * 31;
        hashcode = hashcode + o.hashcode();
    }
    return hashcode;
} 
```

hashcode for tree

```java
@override
public int hashcode() {
    if (this.value == null) {
        return 0;
    }
    return this.value.hashcode() + 
        31 * this.left.hashcode() + 
        31 * 31 * this.right.value.hashcode();
}
```

default hashCode in java: return this

all objects must implement hashcode();

- never store mutable objects in a HashSet or HashMap
- never override equals without overriding hashCode

##### Priority Queues and heaps

###### interface

```java
/**(MIN) priority queue: Allowing tracking and removal of the 
  *smallest item in a priority queue. */
public interface MinPQ(Item) {
    /** adds the item to the priority queue. */
    public void add(Item x);
    /** returns the smallest item in the priority queue. */
    public Item getSmallest();
    /** removes the smallest item from the priority queue. */
    public Item removeSmallest();
    /** returns the size of the priority queue. */
    public int size();
}
```

###### heaps

Binary min-heap: Binary tree that is **complete** and obeys **min-heap property**.

- Min-heap: Every node is less than or equal to both of its children.
- Complete: Missing items only at the bottom level (if any), all nodes are as far left as possible.

heaps lend themselves very naturally to implementation of a priority queue.

- getSmallest() - return the item in the root node.
- add(x) - place the new employee in the last position, and promote as high as possible.(swim())
- removeSmallest() - assassinate the president (of the company), promote the rightmost person in the company to president. Then demote repeatedly, always taking the ‘better’ successor.(sink())

###### Tree representation

- Approach 1:  create mapping from node to children 
- Approach 2:  store keys in an array, store parent ID in another array
- Approach 3:  store keys in an array. (only works for complete trees) (can leave a empty spot makes leftchild 2*k right child 2*k+1 and parent k/2)

###### time of heap

add $$\theta(logN)$$ getSmallest $$\theta(1)$$ removeSmallest $$\theta(logN)$$

##### Graph

###### definition

- Graph:
  - Set of **vertices**, a.k.a. **nodes**.
  - Set of **edges**: Pairs of vertices. Vertices with an edge between are **adjacent**.
  - Optional: Vertices or edges may have **labels** (or **weights**).
- A **path** is a sequence of vertices connected by edges.
- A **cycle** is a path whose first and last vertices are the same.
  - A graph with a cycle is ‘cyclic’.
- Two vertices are **connected** if there is a path between them. If all vertices are connected, we say the graph is connected.

###### Representations

*degree is different from edges

- Adjacency Matrix
  - printing runtime: $$\theta(V^2)$$
- Edge Set: collection of all edges
  - Example: ```HashSet<Edge>```
- Adjacency lists
  - maintain array of lists indexed by vertex number
  - runtime: 
    - best: $$\theta(V)$$
    - worst: $$\theta(V^2)$$
    - all: $$theta(V+E)$$

###### Depth-Frist traversal

- Mark vertex v
- Recursive visit all  unmarked vertices adjacent to v

```java
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    
    public DepthFirstPaths(Graph G, int s) {
        ...
        dfs(G, s);
    }
    
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
}
```

- Guaranteed to reach every node
- Runs in $$\theta(V+E)$$
- space is $$\theta(V)$$

###### Topological Sorting

- DFS post order in a list
- Topological ordering is given by the reverse of that list

```java
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePostorder;
    public DepthFirstOrder(Digraph G) {
        reversePostorder = new Stack<Integer>();
        marked = new boolean[G.V()];
        for (int v; v < G.V(); v++) {
            if (!marked(v)) {
                dfs(G, v);
            }
        }
    }
    
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
            reversePostorder.push(v);
        }
    }
    
    public Iterable<Integer> reversePostorder() {
        return reversePostorder();
    }
}
```

###### Breadth First Search

- Initialize a queue with a starting vertex s and mark that vertex.
  - Let’s call this the **fringe**.
- Repeat until queue is empty:
  - Remove vertex v from queue.
  - Add to the queue any unmarked vertices adjacent to v and mark them.

```java
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    ...
    
    private void bfs(Graph G, int s) {
        Queue<Integer> fringe = new Queue<Integer>();
        fringe.enqueue(s);
        marked[s] = true;
        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    fringe.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }
}
```

- Runs in $$\theta(V+E)$$ time
- use $$\theta(V)$$ 

###### Shortest paths

###### Dijkstra

SPT(shortest path tree) edge: V - 1

- visit vertices in order of best-known distance from source, relaxing each edge from the visited vertex (relax: Add to SPT if better distance)

```java
public DijkstraSP(EdgeWeightedDiagraph G, int s) {
    distTo = new double[G.V()];
    DirectedEdge[] edgeTo = new DirectedEdge[G.V()];
    distTo[s] = 0;
    setDistancesToInfinityExceptsS(s);
    
    fringe = new SpecialPQ<Integer>();
    insertAllVertices(fringe);
    
    /*relax verticex in order of distance from s*/
    while(!fringe.isEmpty()) {
        int v = fringe.delMin();
        for (DirectedEdge e : G.adj(v)) {
            relax(e);
        }
    }
    
    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contain(w)) {
                pq.decreasePriority(w,distTo[w]);
            }
        }
    }
}
```

time: $$O(ElogV)$$

###### A*

- Visit vertices in order of d(source, v) + h(v), where h(V) is an estimate of the distance from v to target
- In other words, look at some location v if:
  - we know already the fastest way to reach v
  - And we suspect that v is always the fastest way to target taking into account the time to get to v

heuristic has to be admissible but doesn't have to be perfect 

(不是最短路径的必要条件)

###### minimum spanning trees

Given a undirected graph, a spanning tree T is a subgraph of G, where T:

- is connected
- is acyclic
- includes all of the vertices

A **minimum spanning tree** is a spanning tree of minimum total weight.

cut property

- a cut is an assignment of a graph's nodes to two non-empty sets
- a crossing edge is an edge which connects a node from the other set
- **Cut property** Given any cut, minimum weight crossing edge is in the MST 

**Prim's Algorithm**

start from some arbitrary start node

- repeatedly add shortest edge that has one node inside the MST under construction
- repeat until V-1edges
- relaxation in prim's considers an edge better based on distance to tree

```java
public class PrimMST {
    public PrimMST(EdgeweightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        fringe = new SpecialPQ<Double>(G.V());
        
        distTo[s] = 0.0;
        SetDistancesToInfinityExceptS(s);
        insertAllVertices(fringe);
        
        while(!fringe.isEmpty()) {
            int v = fringe.delMin();
            scan(G, v);
        }
    }
    
    private void scan(EdgeweightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if(marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                pq.decreasePriority(w, distTo[w]);
            }
        }
    }
}
```

time: $$O(ElogV)$$

**Kruskal's Algorithm**

- consider edges in increasing order of weight
- add edge to MST unless doing so creates a cycle
- repeat until V-1 edges

```java
public class KruskalMST {
    private List<Edge> mst = new ArrayList<Edge>();
    
    public KruskalMST(EdgeweightedGraph G) {
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        WeightedQuickUnionPC uf = new WeightedQuickUnionPC(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.from();
            int w = e.to();
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.add(e);
            }
        }
    }
}
```

time: $$O(Elog*V)$$

###### Dynamic Programming

**Shortest Path in DAG**

Directed Acyclic Graphs

- Any such graph can be topological sorted (sometimes called linearization)

SPTs on DAG: just visit vertices in topological order, relaxing all edges from a vertex when it is visited. time: $$\theta(E+V)$$

**DP**

This approach of solving increasingly large subproblems is sometimes called **dynamic programming**.

- Identify a collection of subproblems.
- Solve subproblems one by one, working from smallest to largest.
- Use the answers to the smaller problems to help solve the larger ones.

**Longest Increasing Subsequence**

using a negative weight graph to find longest paths

##### Tries

#### sort

##### basic

###### selection sort

- find the smallest 
- move it to the end of the sorted portion (swap)
- sort the rest

time: $$\theta(N^2)$$

###### Heap sort

**naive**

- insert all items into a max heap ($$(Nlog(N))$$)
- remove the top  ($$logN$$) put it at the end

time:$$O(NlogN)$$

memory:$$\theta(N)$$

**in-place heap sort**

bottom-up heapify input array 

- sink nodes in reverse order
- guaranteed the tree rooted at position k is a heap
- delete largest item from the max heap, swapping root with last item in the heap

time: $$O(NlogN)$$ 

memory: $$\theta(1)$$

###### merge sort

Top-down

- split items into 2 roughly even pieces
- merge sort each half 
- merge two sorted halves to form the final result 

time: $$\theta(NlogN)$$

memory: $$\theta(N)$$

###### insertion sort

not in place

- start with an empty output sequence
- add each item from input, inserting into output at right point

in-place

- repeat for i = 1 to N - 1
  - designate item i as the travelling item
  - swap item backwards until traveler is in the  right place all previously  examined items

time: $$\Omega(N)$$ to $$O(N^2)$$

memory: $$\theta(1)$$

**for small arrays insertion sort is faster**

##### quick sort

###### partitioning 

to partition an array a[] on x=a[i] is to rearrange a[] so that:

- x moves to position j
- all entries to the left of x are <= x
- all entries to the right of x are >= x

###### Quicksort

naive

- partition on the left most item
- quicksort left half
- quicksort right half

Time:

- best case: $$\theta(NlogN)$$
- worst case: $$\theta(N^2)$$
- randomly chosen case: $$\theta(NlogN)$$ expected

Memory: $$\theta(logN)$$

###### to avoid the worst case

- Randomness: pick a random pivot
- Smarter pivot selection: calculate or approximate the median
- Introspection: Switch to a safer sort if recursion goes too deep
- Preprocess the array

###### Tony Hoare's In-place Partitioning Scheme 

take two pointers

- left pointer loves small items
- right pointer loves large items
- walk towards each other, swap items they don't like
- swap the pivot

###### Median Identification

BFPRT

but quick sort with exact median is terrible

**Quick Select**

use partition for finding the median

worst case: $$\theta(N^2)$$

Expected Performance: $$\theta(N)$$

but quick sort using quick select is still quite slow

###### stability

A sort is said to be stable if order of equivalent items is preserved

insertion sort  and merge sort is stable 

heap sort is not stable 

quick sort can be stable but use different partitioning schemes

**optimizing**

when subproblem reaches size 15 or lower, use insertion sort.

...

###### shuffling

##### Theoretical bounds on sorting

$$N! \in \Omega((\frac{N}{2})^2)$$

$$NlogN \in \Omega(log(N!))$$

**puppy cat dog**

- How many questions would you need to ask to definitely solve the generalized “puppy, cat, dog” problem for N items?
- Answer: Ω(log(N!))
  - Decision tree needs N! leaves
  - So we need lg(N!) rounded up level

**Any comparison based sort requires at least order N log N comparisons.**

#### Radix sorting

###### sleeping sort

###### counting sort

alphabet size is R

time: $$\theta(N + R)$$

Memory: $$\theta(N+R)$$

if a really big collection of items or a really small alphabet counting sort will be fast

###### LSD Radix Sorting

sort from the Least Significant Digit

time: $$\theta(WN+WR)$$ W is for max key length

###### MSD Radix Sorting

sort each subproblem separately

best case: $$\theta(N+R)$$

worst case: $$theta(WN+WR)$$

