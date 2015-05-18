// a gene is the most basic unit in an organism, containing a single information
// (in this case, an integer), e.g: a+b+c = 30 -> b is a gene

public class Gene {
	// attributes
	private int _info;
	
	// default Gene constructor
	public Gene(int info){
		_info = info;
	}
	
	public void setInfo(int newInfo){
		_info = newInfo;
	}
	
	// info accessor
	public int getInfo(){
		return _info;
	}
}
