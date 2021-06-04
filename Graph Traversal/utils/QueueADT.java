package utils;// a basic queue ADT interface

public interface QueueADT<ElementType> {
    int queueSize();
    boolean isEmpty();
    void enqueue(ElementType e);
    ElementType first();
    ElementType dequeue();
}
