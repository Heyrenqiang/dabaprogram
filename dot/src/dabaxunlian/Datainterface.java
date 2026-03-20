package dabaxunlian;

public interface Datainterface {
	public void dataInitial(Bazhi[] bazhis,Dabarenyuan[] dabarenyuans);//数据初始化，参数为靶子数组和打靶人员数组
	public void dataInitial(Tubiao[] tubiaos);//数据初始化，和上面的一个意思，参数为图标数组，图标是靶子和打靶人员的父类
	
	
	public void dataUpdate(Bazhi[] bazhis,Dabarenyuan[] dabarenyuans);//数据更新，参数为靶子数组和打靶人员数组
	public void dataUpdate(Tubiao[] tubiaos);//数据更新和上面的意思一样，参数为图标数组，图标是靶子和打靶人员的父类
}
