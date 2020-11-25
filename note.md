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

