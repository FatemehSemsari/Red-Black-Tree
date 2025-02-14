import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTree {
    Node root = null;
    Node temp = null;
    boolean tempIsUsed = false;

    public void insertion(Node newNode){
        if(root == null){
            newNode.color = Color.BLACK;
            root = newNode;
            return;
        }
        newNode.color = Color.RED;
        insert(newNode, root);
        fixUpInsert(newNode);
    }

    public void insert(Node newNode, Node current){
        if(current.value > newNode.value){
            if(current.left == null){
                current.left = newNode;
                newNode.parent = current;
                return;
            }
            insert(newNode, current.left);
        } else if(current.value < newNode.value){
            if(current.right == null){
                current.right = newNode;
                newNode.parent = current;
                return;
            }
            insert(newNode, current.right);
        }
    }


    public void fixUpInsert(Node newNode){
        Node parent = newNode.parent;
        if(parent == null){
            newNode.color = Color.BLACK;
            return;
        }
        if(parent.color.equals(Color.BLACK)){
            return;
        }
        Node grandParent = parent.parent;
        Node uncle = null;
        if(parent == parent.parent.left){
            uncle = parent.parent.right;
            if(uncle != null && uncle.color.equals(Color.RED)){
                grandParent.color = Color.RED;
                uncle.color = Color.BLACK;
                parent.color = Color.BLACK;
                fixUpInsert(grandParent);
            } else {
                if(parent.right == newNode){
                    newNode = newNode.parent;
                    rotateLeft(newNode);
                }
                newNode.parent.color = Color.BLACK;
                newNode.parent.parent.color = Color.RED;
                rotateRight(newNode.parent.parent);
            }
        } else {
            uncle = parent.parent.left;
            if(uncle != null && uncle.color.equals(Color.RED)){
                newNode.parent.color = Color.BLACK;
                uncle.color = Color.BLACK;
                grandParent.color = Color.RED;
                fixUpInsert(grandParent);
            } else {
                if(parent.left == newNode){
                    newNode = newNode.parent;
                    rotateRight(newNode);
                }
                newNode.parent.color = Color.BLACK;
                newNode.parent.parent.color = Color.RED;
                rotateLeft(newNode.parent.parent);
            }
        }
    }

    public void rotateRight(Node center) {
        if (center.left == null){
            return;
        }
        Node left = center.left;
        center.left = left.right;
        if (left.right != null) {
            left.right.parent = center;
        }
        left.parent = center.parent;
        if (center.parent == null) {
            root = left;
        } else if (center == center.parent.left) {
            center.parent.left = left;
        } else {
            center.parent.right = left;
        }
        left.right = center;
        center.parent = left;
    }


    public void rotateLeft(Node center) {
        if (center.right == null){
            return;
        }
        Node right = center.right;
        center.right = right.left;
        if (right.left != null) {
            right.left.parent = center;
        }
        right.parent = center.parent;
        if (center.parent == null) {
            root = right;
        } else if (center == center.parent.left) {
            center.parent.left = right;
        } else {
            center.parent.right = right;
        }
        right.left = center;
        center.parent = right;
    }


    public void search(int value, Node root){
        if(root == null){
            System.out.println("Not Found");
            return;
        }
        if(value == root.value){
            System.out.println("Found");
        }else if(value > root.value){
            search(value, root.right);
        } else if (value < root.value){
            search(value, root.left);
        }
    }

    public void delete(int value, Node root) {
        if (root == null) {
            return;
        }
        if (value < root.value) {
            delete(value, root.left);
        } else if (value > root.value) {
            delete(value, root.right);
        } else {
             if (root.right == null || root.left == null) {
                Node replacement = (root.left != null) ? root.left : root.right;
                transplant(root, replacement);
                if (root.color == Color.BLACK) {
                    fixUpDelete(replacement, root.parent);
                }
            } else {
                Node successor = findMax(root.left);
                Color originalColor = successor.color;
                Node temp = successor.left;
                Node parent = successor.parent;
                if(successor.parent == root){
                    if(temp != null) {
                        temp.parent = successor;
                    }
                    parent = successor;
                } else {
                    transplant(successor, successor.left);
                    successor.left = root.left;
                    successor.left.parent = successor;
                }
                transplant(root, successor);
                successor.right = root.right;
                successor.right.parent = successor;
                successor.color = root.color;
                if(originalColor == Color.BLACK){
                    fixUpDelete(temp, parent);
                }
            }
        }
    }


    public Node findMax(Node node){
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    public Node fixUpDelete(Node node, Node parent){
        if (node == root || (node != null && node.color == Color.RED)) {
            if (node != null) {
                node.color = Color.BLACK;
            }
            return parent;
        }
        if(node == parent.left){
            Node brother = parent.right;
            if(brother.color == Color.RED){
                brother.color = Color.BLACK;
                parent.color = Color.RED;
                rotateLeft(parent);
                parent = fixUpDelete(node, parent);
            } else {
                if((brother.left == null || brother.left.color == Color.BLACK) && (brother.right == null || brother.right.color == Color.BLACK)){
                    brother.color = Color.RED;
                    parent = fixUpDelete(parent, parent.parent);
                }else if((brother.right == null || brother.right.color == Color.BLACK) && brother.left.color == Color.RED){
                    brother.left.color = Color.BLACK;
                    brother.color = Color.RED;
                    rotateRight(brother);
                    parent = fixUpDelete(node, parent);
                } else if((brother.left == null || brother.left.color == Color.BLACK) && brother.right.color == Color.RED){
                    if(parent.color == Color.RED){
                        parent.color = Color.BLACK;
                        brother.color = Color.RED;
                        brother.right.color = Color.BLACK;
                        rotateLeft(parent);
                    } else {
//                        brother.right.color = Color.BLACK;
//                        rotateLeft(parent);
                    }
                    parent = parent.parent;
                    return parent;
                } else if(brother.right.color == Color.RED && brother.left.color == Color.RED){
                    if(parent.color == Color.RED){
                        parent.color = Color.BLACK;
                        brother.color = Color.RED;
                        brother.right.color = Color.BLACK;
                        rotateLeft(parent);
                    } else {
                        brother.right.color = Color.BLACK;
                        rotateLeft(parent);
                    }
                    parent = parent.parent;
                    return parent;
                }
            }
        }else {
            Node brother = parent.left;
            if(brother.color == Color.RED){
                brother.color = Color.BLACK;
                parent.color = Color.RED;
                rotateRight(parent);
                parent = fixUpDelete(node, parent);
            } else {
                if((brother.right == null || brother.right.color == Color.BLACK) && (brother.left == null || brother.left.color == Color.BLACK)){
                    brother.color = Color.RED;
                    parent = fixUpDelete(parent, parent.parent);
                } else if((brother.left == null || brother.left.color == Color.BLACK) && brother.right.color == Color.RED){
                    brother.right.color = Color.BLACK;
                    brother.color = Color.RED;
                    rotateLeft(brother);
                    brother = brother.parent;
                    parent = fixUpDelete(node, parent);
                } else if((brother.right == null || brother.right.color == Color.BLACK) && brother.left.color == Color.RED){
                    if(parent.color == Color.RED){
                        parent.color = Color.BLACK;
                        brother.color = Color.RED;
                        brother.left.color = Color.BLACK;
                        rotateRight(parent);
                    } else {
//                        brother.left.color = Color.BLACK;
//                        rotateRight(parent);
                    }
                    parent = parent.parent;
                    return parent;
                } else if(brother.left.color == Color.RED && brother.right.color == Color.RED){
                    if(parent.color == Color.RED){
                        parent.color = Color.BLACK;
                        brother.color = Color.RED;
                        brother.left.color = Color.BLACK;
                        rotateRight(parent);
                    } else {
                        brother.left.color = Color.BLACK;
                        rotateRight(parent);
                    }
                    parent = parent.parent;
                    return parent;
                }
            }
        }
        return parent;
    }

    public void transplant(Node target, Node replacement) {
        if (target.parent == null) {
            root = replacement;
        } else if (target == target.parent.left) {
            target.parent.left = replacement;
        } else {
            target.parent.right = replacement;
        }
        if (replacement != null) {
            replacement.parent = target.parent;
        }
    }


    public static void printTree(Node root) {
        int maxLevel = maxDepth(root);
        System.out.println("🔴⚫🌳 : ");
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || allElementsNull(nodes)){
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                if(node.color == Color.RED){
                    System.out.print("\u001B[31m" + node.value +"\u001B[0m");
                } else {
                    System.out.print("\u001B[90m" + node.value +"\u001B[0m");
                }

                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printWhitespaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null){
                    System.out.print("/");
                }
                else{
                    printWhitespaces(1);
                }

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null) System.out.print("\\");
                else printWhitespaces(1);

                printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++){
            System.out.print(" ");
        }
    }

    private static int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right)) + 1;
    }

    private static boolean allElementsNull(List<Node> list) {
        for (Node node : list) {
            if (node != null){
                return false;
            }
        }
        return true;
    }


}
