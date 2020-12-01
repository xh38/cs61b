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

