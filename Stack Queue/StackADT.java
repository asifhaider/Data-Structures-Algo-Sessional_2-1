// basic stack ADT interface

interface StackADT <ElementType>{
    int stackSize();
    boolean isEmpty();
    void stackPush(ElementType e);
    ElementType stackTop();
    ElementType stackPop();
}