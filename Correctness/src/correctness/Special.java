package correctness;

public class Special extends MenuItem {
    private int diskon;

    public Special(String kode, String nama, int harga, int diskon) {
        super(kode, nama, harga);
        this.diskon = diskon;
    }

	public int getDiskon() {
		return diskon;
	}

	public void setDiskon(int diskon) {
		this.diskon = diskon;
	}

}
