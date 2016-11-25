package kr.ac.koreatech.book.dto;

// 이 클래스는 객체를 만들기 위한 class 에요
// 책 1권의 정보를 저장하기 위해 필요한 클래스
// DTO : Data Transfer Object(정형화된 용어)
// entity, VO(Value Object)라고도 표현함, 또는 DO(Domain Object)
public class BookDTO {
	private String bimgurl;
	private String btitle;
	private String bauthor;
	private String bprice;
	private String bisbn;
	public String getBimgurl() {
		return bimgurl;
	}
	public void setBimgurl(String bimgurl) {
		this.bimgurl = bimgurl;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getBprice() {
		return bprice;
	}
	public void setBprice(String bprice) {
		this.bprice = bprice;
	}
	public String getBisbn() {
		return bisbn;
	}
	public void setBisbn(String bisbn) {
		this.bisbn = bisbn;
	}
	
	
}
