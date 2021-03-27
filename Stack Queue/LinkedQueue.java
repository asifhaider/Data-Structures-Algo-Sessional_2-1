class LinkedQueue<ElementType> implements QueueADT<ElementType> {
    // basic queue operations within a singly linked list
    private SinglyLinkedList<ElementType> list;

    public LinkedQueue(){
        list = new SinglyLinkedList<>();
    }

    @Override
    public int queueSize() {
        return list.getListSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(ElementType e) {
        list.addLast(e);
    }

    @Override
    public ElementType first() {
        return list.first();
    }

    @Override
    public ElementType dequeue() {
        return list.removeFirst();
    }
}
