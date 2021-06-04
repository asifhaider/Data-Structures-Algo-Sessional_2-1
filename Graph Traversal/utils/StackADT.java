package utils;// basic stack ADT interface

public interface StackADT <ElementType>{
    int stackSize();
    boolean isEmpty();
    void stackPush(ElementType e);
    ElementType stackTop();
    ElementType stackPop();
}