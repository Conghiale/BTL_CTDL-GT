public class Rating {
    private Customer customer;
    private Product product;
    private int soSao;

    public Rating(Customer u, Product v, int w) {
        this.customer = u;
        this.product = v;
        this.soSao = w;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getSoSao() {
        return soSao;
    }

    @Override
    public String toString() {
        return customer + " " + product + " " + soSao;
    }
}
