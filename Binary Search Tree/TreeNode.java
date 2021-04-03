class TreeNode{
    // integer element attribute
    // two children references, indicates by default null while being created
    private int nodeValue;
    private TreeNode leftChildNode;
    private TreeNode rightChildNode;

    // all getter and setters
    public int getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(int nodeValue) {
        this.nodeValue = nodeValue;
    }

    public TreeNode getLeftChildNode() {
        return leftChildNode;
    }

    public void setLeftChildNode(TreeNode leftChildNode) {
        this.leftChildNode = leftChildNode;
    }

    public TreeNode getRightChildNode() {
        return rightChildNode;
    }

    public void setRightChildNode(TreeNode rightChildNode) {
        this.rightChildNode = rightChildNode;
    }


    public TreeNode(int v){
        // generating a node with integer type element value
        // assigning its children as null references
        this.nodeValue = v;
        this.leftChildNode = null;
        this.rightChildNode = null;
    }

}
