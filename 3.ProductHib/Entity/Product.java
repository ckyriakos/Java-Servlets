  package Entity;

import javax.persistence.*;

@Entity
@Table(name="producthib")

public class Product{
	@Id
	@Column(name="pbarcode")
	private String pbarcode;
	
	@Column(name="pcolor")
	private String pcolor;

	@Column(name="pname")
	private String pname;

	@Column(name="pdescription")
	private String pdescription;

	public Product() {
		super();
		this.pbarcode="default";
		this.pcolor="default";
		this.pname="default";
		this.pdescription="default";
	}
	public Product(String pbarcode, String pcolor,String pname,String pdescription) {
		super();
		this.pbarcode=pbarcode;
		this.pcolor = pcolor;
		this.pname = pname;
		this.pdescription = pdescription;
	}

	public String getPbarcode() {return pbarcode;}
	public String getPcolor() {return pcolor;}
	public String getPname() {return pname;}
	public String getPdescription() {return pdescription;}
	@Override
	public String toString() {return "Product [Barcode=" + pbarcode+ ", Name=" + pname + ", Color=" + pcolor + ", Description=" + pdescription + "]";}
}