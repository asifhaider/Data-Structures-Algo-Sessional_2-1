// a basic queue ADT interface

interface QueueADT<ElementType> {
    int queueSize();
    boolean isEmpty();
    void enqueue(ElementType e);
    ElementType first();
    ElementType dequeue();
}
