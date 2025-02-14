public class Node {
    Node parent = null;
    Node left = null;
    Node right = null;
    Color color = Color.BLACK;
    int value = 0;

    public Node(String key) {
        this.value = convertKey(key);
    }

    public Node(int value) {
        this.value = value;
    }
    public int convertKey(String key){
        int value = 0;
        for(char c : key.toCharArray()){
            value = value + c;
        }
        return value;
    }

}
