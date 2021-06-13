package modeloInfo;

import java.util.ArrayList;

public class InfoClientesEspera implements Informable {

	private ArrayList<Integer> dnis;
	
	@Override
	public int getIdOperacion() {
		return 0;
	}
	
	public void setDnis(ArrayList<Integer> dnis){
		this.dnis = dnis;
	}
	
	public ArrayList<Integer> getDnis(){
		return this.dnis;
	}

}
