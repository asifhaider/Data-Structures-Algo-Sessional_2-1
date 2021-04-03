class BinarySearchTree {
    private TreeNode rootNode;

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public BinarySearchTree() {
        // assigning a newly created bst root node as null reference
        this.rootNode = null;
    }

    public TreeNode insertItem(TreeNode rootNode, int n) {
        // Case 1: In case the root is already blank, insert it to the root
        if (rootNode == null) {
            // recursive base case
            rootNode = new TreeNode(n); // putting value into the tree node
            this.rootNode = rootNode; // setting newly created root node to the active class instance
            return rootNode;
        }
        /*
         Case 2: Root not blank, comparing and choosing proper subtree
         recursively assigning left or right children according to BST property
        */
        if (n < rootNode.getNodeValue()) {
            // in the left subtree;
            rootNode.setLeftChildNode(insertItem(rootNode.getLeftChildNode(), n));
        } else if (n > rootNode.getNodeValue()) {
            // in the right subtree
            rootNode.setRightChildNode(insertItem(rootNode.getRightChildNode(), n));
        } else
            return rootNode; // if equals, do not add anything
        this.rootNode = rootNode;
        return rootNode;
    }

    public TreeNode searchItem(TreeNode rootNode, int n) {
        /*
         null means leaf node, the item not found
         otherwise the correct output returned
         recursive search performed
        */
        if (rootNode == null || rootNode.getNodeValue() == n)
            return rootNode;
        if (n < rootNode.getNodeValue()) {
            return searchItem(rootNode.getLeftChildNode(), n);
        }
        return searchItem(rootNode.getRightChildNode(), n);
    }

    public int getMaxItem(TreeNode rootNode) {
        // initial root node null checking must be done inside main
        int max = rootNode.getNodeValue();
        while (rootNode.getRightChildNode() != null) {
            max = rootNode.getRightChildNode().getNodeValue();
            rootNode = rootNode.getRightChildNode();
        }
        return max;
    }

    public int getMinItem(TreeNode rootNode) {
        // initial root node null checking must be done inside main
        int min = rootNode.getNodeValue();
        while (rootNode.getLeftChildNode() != null) {
            min = rootNode.getLeftChildNode().getNodeValue();
            rootNode = rootNode.getLeftChildNode();
        }
        return min;
    }

    public int getInOrderSuccessor(TreeNode rootNode, TreeNode currentNode){
        // Must give a check for inappropriate input for example value that doesn't exist in the bst
        // Case 1: input node has a right subtree
        if (currentNode.getRightChildNode() != null) {
            return getMinItem(currentNode.getRightChildNode());
        }
        // Case 2: input node doesn't have a right subtree
        else {
            TreeNode successorNode = null;
            TreeNode ancestorNode = rootNode;
            while (ancestorNode != currentNode) {
                if (currentNode.getNodeValue() < ancestorNode.getNodeValue()) {
                    successorNode = ancestorNode;
                    ancestorNode = ancestorNode.getLeftChildNode();
                } else {
                    ancestorNode = ancestorNode.getRightChildNode();
                }
            }
            // find out the no successor invalid -1 case
            try{
                if (successorNode.getNodeValue() > currentNode.getNodeValue())
                    return successorNode.getNodeValue();
            } catch (NullPointerException npe){
                System.out.println("In Order Successor Doesn't Exist!");
            }
            return -1;
        }
    }

    public int getInOrderPredecessor(TreeNode rootNode, TreeNode currentNode){
        // Must give a check for inappropriate input for example value that doesn't exist in the bst
        // Case 1: input node has a left subtree
        if (currentNode.getLeftChildNode() != null) {
            return getMaxItem(currentNode.getLeftChildNode());
        } else {
            // Case 2: input node has no left subtree
            TreeNode predecessorNode = null;
            TreeNode ancestorNode = rootNode;
            while (ancestorNode != currentNode) {
                if (currentNode.getNodeValue() > ancestorNode.getNodeValue()) {
                    predecessorNode = ancestorNode;
                    ancestorNode = ancestorNode.getRightChildNode();
                } else {
                    ancestorNode = ancestorNode.getLeftChildNode();
                }
            }
            try{
                if (predecessorNode.getNodeValue() < currentNode.getNodeValue())
                    return predecessorNode.getNodeValue();
            } catch (NullPointerException npe){
                System.out.println("In Order Predecessor Doesn't Exist!");
            }
            return -1;
        }
    }


    public TreeNode deleteItem(TreeNode rootNode, int n) {
        // recursive base case
        if (rootNode == null)
            return rootNode;
        if (n < rootNode.getNodeValue()) {
            rootNode.setLeftChildNode(deleteItem(rootNode.getLeftChildNode(), n));
        } else if (n > rootNode.getNodeValue()) {
            rootNode.setRightChildNode(deleteItem(rootNode.getRightChildNode(), n));
        } else {
            // Case 1: One or Zero Child
            if (rootNode.getLeftChildNode() == null) {
                return rootNode.getRightChildNode();
            } else if (rootNode.getRightChildNode() == null) {
                return rootNode.getLeftChildNode();
            }

            // Case 2: Two Child: Always will have a right subtree and Case 1 Successor
            // replace the successor value with current node
            rootNode.setNodeValue(getMinItem(rootNode.getRightChildNode()));
            // successor is to be deleted now
            rootNode.setRightChildNode(deleteItem(rootNode.getRightChildNode(), rootNode.getNodeValue()));
        }
        return rootNode;
    }

    public int getHeight (TreeNode rootNode){
        // base case of recursion
        if (rootNode == null)
            return -1;
        return Math.max(getHeight(rootNode.getLeftChildNode()),
                getHeight(rootNode.getRightChildNode())) + 1;
    }

    public int getItemDepth (TreeNode rootNode, int n){
        //?? must check for what happens when input is not found in tree
        // base case of recursion
        if (rootNode == null)
            return -1;

        // initializing the depth count
        int depthCount = -1;

        if ((rootNode.getNodeValue() == n) ||
                (depthCount = getItemDepth(rootNode.getLeftChildNode(), n)) >= 0 ||
                (depthCount = getItemDepth(rootNode.getRightChildNode(), n)) >= 0) {
            return depthCount + 1;
        }
        return depthCount;
    }

    public void printInOrder(TreeNode rootNode){
        //System.out.println("First");
        if (rootNode != null){
            //System.out.println("Inside");
            printInOrder(rootNode.getLeftChildNode());
            System.out.println(rootNode.getNodeValue());
            printInOrder(rootNode.getRightChildNode());
        }
        //System.out.println("Last");
    }

    public void printPreOrder(TreeNode rootNode){
        if (rootNode != null){
            System.out.println(rootNode.getNodeValue());
            printPreOrder(rootNode.getLeftChildNode());
            printPreOrder(rootNode.getRightChildNode());
        }
    }

    public void printPostOrder(TreeNode rootNode){
        if (rootNode != null){
            printPostOrder(rootNode.getLeftChildNode());
            printPostOrder(rootNode.getRightChildNode());
            System.out.println(rootNode.getNodeValue());
        }
    }

    public int getSize (TreeNode rootNode){
        if (rootNode == null)
            return 0;
        return 1 + getSize(rootNode.getRightChildNode()) + getSize(rootNode.getLeftChildNode());
    }
}
