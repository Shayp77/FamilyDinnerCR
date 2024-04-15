package javaLanjutan;

public class Special {
	private String kode;
	private String nama;
	private int harga;
	private int diskon;
	public Special(String kode,String nama,int harga,int diskon) {
		this.kode=kode;
		this.nama=nama;
		this.harga=harga;
		this.diskon=diskon;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	public int getDiskon() {
		return diskon;
	}
	public void setDiskon(int diskon) {
		this.diskon = diskon;
	}
}
