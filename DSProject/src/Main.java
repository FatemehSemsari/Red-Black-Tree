public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();


        tree.insertion(new Node(100));
        tree.printTree(tree.root);
        tree.insertion(new Node("Hello"));
        tree.printTree(tree.root);
        tree.insertion(new Node(150));
        tree.insertion(new Node(40));
        tree.printTree(tree.root);
        tree.insertion(new Node(65));
        tree.printTree(tree.root);
        tree.insertion(new Node(55));
        tree.printTree(tree.root);
        tree.insertion(new Node(78));
        tree.printTree(tree.root);
        tree.insertion(new Node(37));
        tree.printTree(tree.root);
        tree.insertion(new Node(46));
        tree.insertion(new Node(68));
        tree.insertion(new Node(140));
        tree.insertion(new Node(180));
        tree.insertion(new Node(135));
        tree.insertion(new Node(145));
        tree.insertion(new Node(136));
        tree.insertion(new Node(142));
        tree.insertion(new Node(148));
        tree.insertion(new Node(170));
        tree.insertion(new Node(200));
        tree.insertion(new Node(220));
        tree.printTree(tree.root);
        tree.delete(65, tree.root);
        tree.printTree(tree.root);
        tree.delete(55, tree.root);
        tree.printTree(tree.root);
        tree.delete(140, tree.root);
        tree.printTree(tree.root);
        tree.delete(100, tree.root);
        tree.printTree(tree.root);
        tree.delete(46, tree.root);
        tree.printTree(tree.root);
        tree.delete(148, tree.root);
        tree.printTree(tree.root);
        tree.delete(135,tree.root);
        tree.printTree(tree.root);
        tree.delete(150,tree.root);
        tree.printTree(tree.root);
        tree.delete(145,tree.root);
        tree.printTree(tree.root);
        tree.delete(180,tree.root);
        tree.printTree(tree.root);
        tree.delete(142,tree.root);
        tree.printTree(tree.root);
        tree.search(200, tree.root);
        tree.search(140, tree.root);
        tree.search(180, tree.root);
        tree.search(57, tree.root);
        tree.search(1, tree.root);
        tree.search(170, tree.root);
    }
}




