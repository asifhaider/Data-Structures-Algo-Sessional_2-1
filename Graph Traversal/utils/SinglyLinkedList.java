package utils;

class ListNode<ElementType> {
    private ElementType element; // reference to the stored element
    private ListNode<ElementType> nextReference; // reference to the next node

    public ListNode (ElementType e, ListNode<ElementType> node){
        this.element = e;
        this.nextReference = node;
    }

    public ElementType getElement() {
        return element;
    }

    public void setElement(ElementType element) {
        this.element = element;
    }

    public ListNode<ElementType> getNextReference() {
        return nextReference;
    }

    public void setNextReference(ListNode<ElementType> nextReference) {
        this.nextReference = nextReference;
    }
}

class SinglyLinkedList<ElementType> {
    private ListNode<ElementType> headNode;
    private ListNode<ElementType> tailNode;
    private int listSize;

    public SinglyLinkedList(){
        this.headNode = null;
        this.tailNode = null;
        this.listSize = 0;
    }

    public boolean isEmpty(){
        return this.listSize==0;
    }

    public int getListSize(){
        return this.listSize;
    }

    public ElementType first(){
        if (isEmpty())
            return null;
        return this.headNode.getElement();
    }

    public ElementType second(){
        // for unary minus checking
        if (isEmpty())
            return null;
        return this.headNode.getNextReference().getElement();
    }

    public ElementType last(){
        if (isEmpty())
            return null;
        return this.tailNode.getElement();
    }

    public void addFirst(ElementType e){
        headNode = new ListNode<>(e,headNode);
        if (listSize == 0){
            tailNode = headNode;
        }
        listSize++;
    }

    public void addLast(ElementType e){
        ListNode<ElementType> newNode = new ListNode<>(e, null);
        if (isEmpty())
            headNode = newNode;
        else
            tailNode.setNextReference(newNode);
        tailNode = newNode;
        listSize++;
    }

    public ElementType removeFirst(){
        if (isEmpty())
            return null;
        ElementType element = headNode.getElement();
        headNode = headNode.getNextReference();
        listSize--;
        if (listSize==0)
            tailNode = null;
        return element;
    }
}