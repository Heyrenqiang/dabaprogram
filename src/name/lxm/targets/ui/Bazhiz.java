package name.lxm.targets.ui;

public class Bazhiz {
	private int pingbanhao;
	private int[] bazhisnum;
	private Bazhi[] bazhis;
	private int bazhishu;
	public int getPingbanhao() {
		return pingbanhao;
	}
	public void setPingbanhao(int pingbanhao) {
		this.pingbanhao = pingbanhao;
	}
	public Bazhiz(){
		
	}
	public Bazhiz(int pingbanhao, Bazhi[] bazhis) {
		this.pingbanhao = pingbanhao;
		this.bazhis = bazhis;
		this.bazhishu=bazhis.length;
		bazhisnum=new int[bazhis.length];
		for(int i=0;i<bazhis.length;i++){
			bazhisnum[i]=bazhis[i].getBianhao();
		}
	}
	public int[] getBazhisnum() {
		return bazhisnum;
	}
	public void setBazhisnum(int[] bazhisnum) {
		this.bazhisnum = bazhisnum;
	}
	public Bazhi[] getBazhis() {
		return bazhis;
	}
	public void setBazhis(Bazhi[] bazhis) {
		this.bazhis = bazhis;
	}
	public void addBazhis(Bazhi[] bazhis){
		int w=this.bazhishu+bazhis.length;
		Bazhi[] www=new Bazhi[w];
		for(int i=0;i<this.bazhishu;i++){
			www[i]=this.bazhis[i];
		}
		for(int i=0;i<bazhis.length;i++){
			www[this.bazhishu+i]=bazhis[i];
		}
		this.bazhis=www;
		this.bazhishu=w;
		this.bazhisnum=new int[this.bazhis.length];
		for(int i=0;i<this.bazhis.length;i++){
			bazhisnum[i]=this.bazhis[i].getBianhao();
		}
	}
	public void deleteBazhis(Bazhi bazhi){
		//bazhi.setGroup(0);
		int w=this.bazhis.length-1;
		Bazhi[] www=new Bazhi[w];
		int k=0;
		for(int i=0;i<this.bazhis.length;i++){
			if(this.bazhis[i].equals(bazhi)){
				
			}else{
				www[k]=this.bazhis[i];
				k++;
			}
		}
		this.bazhis=www;
		this.bazhishu=w;
		this.bazhisnum=new int[w];
		for(int i=0;i<w;i++){
			bazhisnum[i]=this.bazhis[i].getBianhao();
		}
	}
	public void deleteBazhis(Bazhi[] bazhis){
		
	}
	public void updateBazhis(Bazhi[] bazhis){
		
	}
    public void addBazhis(Bazhi bazhi){
		
	}
	public void updateBazhis(Bazhi bazhi){
		
	}

}
