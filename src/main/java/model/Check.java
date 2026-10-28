package model;

public class Check {
private int id;//ID全体格納場所
private String side;//教師・生徒識別詞（英文字）
private String number; //識別番号
private int password;


//IDは英文字1語と複数の数字であるものとする

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSide() {
	return side;
}
public void setSide(String alphabets) {
	this.side = alphabets;
}
public String getNumber() {
	return number;
}
public void setNumber(String alphabets) {
	this.number = alphabets;
}
public int getPassword() {
	return password;
}
public void setPassword(int password) {
	this.password = password;
}



}

