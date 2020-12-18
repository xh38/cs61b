### CS61B

Java

##### LinkedList

sentinel

ArrayList

resize()

```java
(T[]) new Object[100];
```

```java
import org.junit.Test;
org.junit.Assert.assertEquals(expected, actual);
```

```java
assertNotnull();assertFalse();fail();
```

Java allows method overloading 

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



