public class Main {
    public static void main(String[] args) {
    DataReader dataReader = new DataReader("data");

        System.out.println(dataReader.getList().toString());
       // System.out.println(dataReader.getHashMap().get("Polish"));
    }
}
