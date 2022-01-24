import java.util.ArrayList;

public class AVL {
    private Node root;

    public AVL() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int height(Node node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private int checkBalance(Node x) {
        return height(x.getLeft()) - height(x.getRight());
    }

    private Node balance(Node x) {
        if (checkBalance(x) < -1) {
            if (checkBalance(x.getRight()) > 0)
                x.setRight(rotateRight(x.getRight()));
            x = rotateLeft(x);
        } else if (checkBalance(x) > 1) {
            if (checkBalance(x.getLeft()) < 0)
                x.setLeft(rotateLeft(x.getLeft()));
            x = rotateRight(x);
        }

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);

        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);

        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        return y;
    }

    private Node insert(Node node, Receipt receipt) {
        // code here and change the return value
        if (node == null)
            return new Node(receipt);

        if (receipt.getReceiptId() < node.getReceipt().getReceiptId())
            node.setLeft(insert(node.getLeft(), receipt));
        if (receipt.getReceiptId() > node.getReceipt().getReceiptId())
            node.setRight(insert(node.getRight(), receipt));
        else
            node.setReceipt(receipt);

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));
        return balance(node);
    }

    public void insertReceipt(Receipt receipt) {
        this.root = insert(this.root, receipt);
    }

    private void NLR(Node node, ArrayList<String> result) {
        if (node != null) {
            result.add(node.getReceipt().toString());
            NLR(node.getLeft(), result);
            NLR(node.getRight(), result);
        }
    }

    public void NLR(ArrayList<String> result) {
        NLR(this.root, result);
    }

    private Node search(Node x, int receiptId) {
        // code here and change the return value
        if (x == null)
            return x;

        if (receiptId < x.getReceipt().getReceiptId())
            return search(x.getLeft(), receiptId);
        else if (receiptId > x.getReceipt().getReceiptId())
            return search(x.getRight(), receiptId);
        else
            return x;
    }

    public String search(int receiptId) {
        return search(root, receiptId).getReceipt().toString();
    }
}
