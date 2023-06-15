public class TestMart {
    public static void main(String[] args) {
        Mart mart = new Mart(5);

        Seller seller = new Seller(mart, "Seller");
        seller.getStore(mart.store);
        seller.start();

        for(int i = 0 ; i< 100 ; i++){
            Buyer buyer = new Buyer("Buyer"+i, mart);
            buyer.start();
        }
    }
}
