class LinkedStack <ElementType> implements StackADT <ElementType>{
    // basic stack operations within a singly linked list
    private SinglyLinkedList<ElementType> list;

    public LinkedStack(){
        list = new SinglyLinkedList<>();
    }

    @Override
    public int stackSize() {
        return list.getListSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void stackPush(ElementType e) {
        list.addFirst(e);
    }

    @Override
    public ElementType stackTop() {
        return list.first();
    }

    @Override
    public ElementType stackPop() {
        return list.removeFirst();
    }

    public ElementType stackBelowTop(){
        return list.second();
    }
}