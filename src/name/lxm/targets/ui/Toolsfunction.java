package name.lxm.targets.ui;

public class Toolsfunction {
	private Definedata definedata;

	public Definedata getDefinedata() {
		return definedata;
	}

	public void setDefinedata(Definedata definedata) {
		this.definedata = definedata;
	}

	public static String num2str(int[] a) {
		String string = "";
		for (int i = 0; i < a.length; i++) {
			string = string + String.valueOf(a[i]) + ",";
		}
		if (a.length > 0) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}

	public static int[] str2num(String string) {
		String[] strings = string.split(",");
		int w = strings.length;
		int[] a;
		if (!strings[0].equals("")) {
			a = new int[w];
			for (int i = 0; i < w; i++) {
				a[i] = Integer.parseInt(strings[i]);
			}
		} else {
			a = new int[0];
		}
		return a;

	}

	public static Definedata splitGroupedornot(Bazhi[] bazhis) {
		int k = Guanlifenzu.getGroupedbazhinum();
		boolean isexist = false;
		Bazhi[] bazhis2 = new Bazhi[100];
		Bazhi[] bazhis5=new Bazhi[100];
		int num = 0;
		int[] existedbazhi = new int[100];
		int[] notexistedbazhi=new int[100];
		//int[] existedbazhi1=new int[100];
		int existednum = 0;
		for (int i = 0; i < bazhis.length; i++) {
			for (int j = 0; j < k; j++) {
				if (bazhis[i].getBianhao() == Guanlifenzu.getGroupedbazhis()[j]) {
					isexist = true;
					break;
				} else {

				}
			}
			if (isexist) {
				existedbazhi[existednum] = bazhis[i].getBianhao();
				bazhis5[existednum]=bazhis[i];
				existednum++;
				isexist=false;
			} else {
				Guanlifenzu.getGroupedbazhis()[Guanlifenzu.getGroupedbazhinum()]= bazhis[i].getBianhao();
				Guanlifenzu.setGroupedbazhinum(Guanlifenzu.getGroupedbazhinum()+1);
				notexistedbazhi[num]=bazhis[i].getBianhao();
				bazhis2[num] = bazhis[i];
				num++;
			}
		}
		System.out.println(existednum);
		Bazhi[] bazhis3 = new Bazhi[num];
		int [] notexistedbazhi1=new int[num];
		for (int i = 0; i < num; i++) {
			bazhis3[i] = bazhis2[i];
			notexistedbazhi1[i]=notexistedbazhi[i];
		}
		Bazhi [] bazhis4=new Bazhi[existednum];
		int [] existedbazhi1=new int[existednum];
		for(int i=0;i<existednum;i++){
			bazhis4[i]=bazhis5[i];
			existedbazhi1[i]=existedbazhi[i];
		}
		Definedata definedata=new Definedata();
		definedata.setExistednum(existednum);
		definedata.setExistedbazhi(existedbazhi1);
		definedata.setNotexistedbazhi(notexistedbazhi1);
		definedata.setBazhis3(bazhis3);
		definedata.setBazhis4(bazhis4);
		return definedata;
	}
}

class Definedata {
	private int existednum;
	private Bazhi[] bazhis3;
	private Bazhi[] bazhis4;
	private int[] existedbazhi;
	private int[] notexistedbazhi;
	public int[] getExistedbazhi() {
		return existedbazhi;
	}
	public void setExistedbazhi(int[] existedbazhi) {
		this.existedbazhi = existedbazhi;
	}
	public int[] getNotexistedbazhi() {
		return notexistedbazhi;
	}
	public void setNotexistedbazhi(int[] notexistedbazhi) {
		this.notexistedbazhi = notexistedbazhi;
	}
	public Bazhi[] getBazhis4() {
		return bazhis4;
	}
	public void setBazhis4(Bazhi[] bazhis4) {
		this.bazhis4 = bazhis4;
	}
	public int getExistednum() {
		return existednum;
	}
	public void setExistednum(int existednum) {
		this.existednum = existednum;
	}
	public Bazhi[] getBazhis3() {
		return bazhis3;
	}
	public void setBazhis3(Bazhi[] bazhis3) {
		this.bazhis3 = bazhis3;
	}

}
